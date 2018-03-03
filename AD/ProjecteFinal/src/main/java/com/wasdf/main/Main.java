package com.wasdf.main;

import com.wasdf.logic.DatabaseManager;
import com.wasdf.view.MainView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;


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


    public static void main(String[] args) {

        final DatabaseManager databaseManager = new DatabaseManager(getSession());

        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            createAndShowGUI(databaseManager);
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




