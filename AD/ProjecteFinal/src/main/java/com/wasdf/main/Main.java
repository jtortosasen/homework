package com.wasdf.main;

import com.wasdf.logic.DatabaseManager;
import com.wasdf.model.Departments;
import com.wasdf.model.DeptEmp;
import com.wasdf.model.Employees;
import com.wasdf.view.MainView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final String WINDOW_TITLE = "Employees Manager";

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    @SuppressWarnings("Duplicates")
    public static void main(final String[] args) throws Exception {

        final DatabaseManager databaseManager = new DatabaseManager(getSession());

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }
                    createAndShowGUI(databaseManager);
                }
            });
    }

    static void createAndShowGUI(DatabaseManager databaseManager) {
        MainView mainView = new MainView(WINDOW_TITLE, databaseManager);
        mainView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (databaseManager.close())
                    System.exit(0);

            }
        });
        mainView.setSize(1000, 500);
        mainView.setLocationRelativeTo(null);
        mainView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainView.setVisible(true);
    }
}




