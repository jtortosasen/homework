package com.company.Controller;

import com.company.Model.Department;

import java.sql.*;
import java.util.logging.Logger;

public class DataBaseManager {

    Connection conn = null;
    PreparedStatement preparedStatement;

    private String user;
    private String password;
    private String urlConnection;

    private String databaseName;

    public DataBaseManager(String user, String password, String urlConnection) {
        this.user = user;
        this.password = password;
        this.urlConnection = urlConnection;
    }

    public boolean connect() {
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

    public boolean close() {
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

//    private boolean execQuery(String sql) {
//        boolean b;
//        try {
//            Statement st = conn.createStatement();
//            st.executeQuery(sql);
//            b = true;
//            st.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//            b = false;
//        }
//        System.out.println(b);
//        return b;
//    }

    public boolean createDepartment(Department department){
        String sql = "insert into departments(dept_no, dept_name) values (?,?);";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,department.getNo());
            preparedStatement.setString(2,department.getName());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            success = false;
        }
        return success;
    }

    public boolean existDepartment(String dept_no){
        String sql = "select count(1) from departments where dept_no = ?;";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,dept_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist = resultSet.getInt(1) == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            exist = false;
        }
        return exist;
    }

    public Department getDepartment(String dept_no){
        String sql = "select * from departments where dept_no = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,dept_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new Department(resultSet.getString(1),resultSet.getString(2));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public boolean modifyDepartment(Department department){
        String sql = "select count(1) from departments where dept_no = ?;";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,dept_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            exist = resultSet.getInt(1) == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            exist = false;
        }
        return exist;
    }


}
