package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

    Connection conn = null;

    private String user;
    private String password;
    private String urlConnection;

    private String databaseName;

    DataBaseManager(String user, String password, String urlConnection) {
        this.user = user;
        this.password = password;
        this.urlConnection = urlConnection;
    }

    boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlConnection, user, password);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
            return false;
        }
    }

    boolean close() {
        if (conn != null) {
            try {
                conn.close();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return false;
            }
        }
        return false;
    }

//    try {
//        // The newInstance() call is a work around for some
//        // broken Java implementations
//        Class.forName("com.mysql.jdbc.Driver").newInstance();
//    } catch (Exception ex) {
//        // handle the error
//    }
//        try {
//        conn = DriverManager.getConnection("jdbc:mysql://localhost/employees" +
//                "user=root&password=admin");
//
//        // Do something with the Connection
//
//    } catch (SQLException ex) {
//        // handle any errors
//
//    }
}
