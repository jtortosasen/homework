package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String USER_DATABASE = "root";
    private static final String PWD_DATABASE = "admin";
    private static final String URL_CONN = "jdbc:mysql://localhost/";

    private static boolean exit = false;


    public static void main(String[] args) {

        DataBaseManager dbManager = new DataBaseManager(USER_DATABASE, PWD_DATABASE, URL_CONN);

        if (dbManager.connect()) {
            do {
                Options.showMenu();

                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                switch (option) {
                    case 1: {
                        Options.showMenu1();
                        option = scanner.nextInt();
                        Options.option1(option, dbManager);
                        break;
                    }

                    case 2: {
                        Options.showMenu2();
                        option = scanner.nextInt();
                        Options.options2(option, dbManager);
                        break;
                    }
                    case 3:{
                        Options.showMenu3();
                        option = scanner.nextInt();
                        Options.options3(option, dbManager);
                        break;
                    }
                    case 4:{
                        exitApplication();
                    }
                }
            } while (!exit);
        }
        System.out.println("Cerrando acceso a DB");
        if(dbManager.close()){
            System.out.println("Cerrado.");
        }
    }

    private static void exitApplication(){
        exit = true;
    }
}
