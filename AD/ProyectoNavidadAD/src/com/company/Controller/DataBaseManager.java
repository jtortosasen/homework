package com.company.Controller;

import com.company.Model.Department;
import com.company.Model.Employee;

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
        } catch (ClassNotFoundException ex) {
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

    public boolean createDepartment(Department department) {
        String sql = "insert into departments(dept_no, dept_name) values (?,?);";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, department.getNo());
            preparedStatement.setString(2, department.getName());
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

    public boolean existDepartment(String dept_no) {
        String sql = "select count(1) from departments where dept_no = ?;";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dept_no);
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

    public Department getDepartment(String dept_no) {
        String sql = "select * from departments where dept_no = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dept_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new Department(resultSet.getString(1), resultSet.getString(2));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public boolean modifyDepartment(Department department) {
        String sql = "update departments set dept_name = ? where dept_no = ?;";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getName());
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

    public boolean deleteDepartment(String dept_no) {
        String sql = "delete from departments where dept_no = ?;";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dept_no);
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

    public boolean createEmployee(Employee employee) {
        String sql = "insert into employees(emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                "values(?, ?, ?, ?, ?, ?);";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, employee.getEmpNo());
            preparedStatement.setString(2, employee.getBirthDate());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setString(5, employee.getGender());
            preparedStatement.setString(6, employee.getHireDate());
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

    public boolean existEmployee(String emp_no){
        String sql = "select count(1) from employees where emp_no = ?;";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,emp_no);
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

    public Employee getEmployee(String emp_no) {
        String sql = "select * from employees where emp_no = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, emp_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new Employee(
                    resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public boolean modifyEmployee(Employee employee) {
        String sql = "update employees set birth_date = ?, first_name= ?, last_name = ?, " +
                "gender = ?, hire_date = ? where emp_no = ?";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, employee.getBirthDate());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setString(5, employee.getHireDate());
            preparedStatement.setString(6, employee.getEmpNo());
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

    public boolean deleteEmployee(String emp_no) {
        String sql = "delete from employees where emp_no = ?;";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, emp_no);
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

    public Department getDepartmentFromEmployee(String emp_no){

    }









}
