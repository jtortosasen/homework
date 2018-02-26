package PracticaEmployee.main;

import PracticaEmployee.model.dao.DataBaseManager;
import PracticaEmployee.view.MainView;

import javax.swing.*;

public class Main {
    private static final String USER_DATABASE = "root";
    private static final String PWD_DATABASE = "admin";
    private static final String URL_CONN = "jdbc:mysql://localhost/";

    private static final String WINDOW_TITLE = "Employees Manager";


    public static void main(String[] args) {

        DataBaseManager dbManager = new DataBaseManager(USER_DATABASE, PWD_DATABASE, URL_CONN);

        if (dbManager.connect()) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }
                    createAndShowGUI(dbManager);
                }
            });
        }
    }

    static void createAndShowGUI(DataBaseManager dbManager){
        MainView mainView = new MainView(WINDOW_TITLE, dbManager);
        mainView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(dbManager.close())
                    System.exit(0);

            }
        });
        mainView.setSize(1000, 500);
        mainView.setLocationRelativeTo(null);
        mainView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainView.setVisible(true);
    }


}
