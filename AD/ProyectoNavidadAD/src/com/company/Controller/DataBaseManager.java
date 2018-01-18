package com.company.Controller;

import com.company.Model.Department;
import com.company.Model.Employee;

import java.sql.*;
import java.util.ArrayList;
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
            preparedStatement.setInt(1, Integer.parseInt(employee.getEmpNo()));
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

    public boolean existEmployee(String emp_no) {
        String sql = "select count(1) from employees where emp_no = ?;";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
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
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
            ResultSet resultSet = preparedStatement.executeQuery();
            return new Employee(
                    String.valueOf(resultSet.getInt(1)), resultSet.getString(2),
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
            preparedStatement.setInt(6, Integer.parseInt(employee.getEmpNo()));
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
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
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

    public Department getDepartmentFromEmployee(String emp_no) {
        String sql = "select departments.dept_no, departments.dept_name from departments \n" +
                "join dept_emp on departments.dept_no = dept_emp.dept_no\n" +
                "join employees on dept_emp.emp_no = employees.emp_no\n" +
                "where employees.emp_no = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
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

    public ArrayList<Employee> getEmployeesFromDepartment(String dept_no) {
        String sql = "select employees.emp_no, employees.birth_date, employees.first_name, employees.last_name, employees.gender, employees.hire_date from employees\n" +
                "left join dept_emp on employees.emp_no = dept_emp.emp_no\n" +
                "left join departments on dept_emp.dept_no = departments.dept_no\n" +
                "where departments.dept_no = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dept_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(new Employee(
                        String.valueOf(resultSet.getInt(1)), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)));
            }
            return employees;

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public boolean isEmployeeOnDateHistoryDepartment(String emp_no, String dept_no){
        String sql = "SELECT count(1) FROM dept_emp where emp_no = ? and dept_no = ?;";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
            preparedStatement.setString(2, dept_no);
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

    public String getDatesEmployeeOnDepartment(String emp_no, String  dept_no){
        String sql = "SELECT dept_emp.from_date, dept_emp.to_date FROM dept_emp where emp_no = ? and dept_no = ?;";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
            preparedStatement.setString(2, dept_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            String date = resultSet.getString(1) + " " + resultSet.getString(2);
            return date;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public boolean employeeOnDepartment (String emp_no, String dept_no){
        String sql = "SELECT count(1) from dept_emp where emp_no = ? and dept_no = ? and to_date = '9999-01-01';";
        boolean exist;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
            preparedStatement.setString(2, dept_no);
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

    public boolean moveEmployeeToDepartment(String emp_no, String dept_no){
        String sql = "update dept_emp set to_date='2016-01-01' where emp_no = ? and to_date = '9999-01-01';";
        String sql2 =  "insert into dept_emp (emp_no, dept_no, from_date, to_date) values (?, ?, '2016-01-01', '9999-01-01');";
        boolean success;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setInt(1, Integer.parseInt(emp_no));
            preparedStatement.setString(2, dept_no);
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



}
