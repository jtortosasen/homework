/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dam2.tema2.dbcreate;


/**
 * @author professor
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conn {

    private boolean Success = false;
    private Connection Con = null;
    private String strmySqlConn = "jdbc:mysql://localhost/";
    private String strUser = "root";
    private String strPass = "admin";

    public Conn() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            initConn();
            Success = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
            Success = false;
        }
    }

    private void initConn() {
        try {
            Con = DriverManager.getConnection(strmySqlConn, strUser, strPass);
            Success = true;
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
            Success = false;
        }
    }

    public void closeConn() {
        if (getSuccess()) {
            try {
                Con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean createDataBase(String dbName) {
        boolean bolst;
        String strSql;

        strSql = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
        bolst = execUpdate(strSql);
        if (bolst) bolst = execUpdate("USE " + dbName + ";");
        return bolst;
    }

    public boolean createTable1(String name) {
        boolean bolst;
        String strSql;

        strSql = "create table " + name + " (dept_no tinyint(2) not null primary key," +
                "dnombre varchar(15), loc varchar(15));";
        bolst = execQuery(strSql);
        return bolst;
    }

    public boolean createTable2(String name) {
        boolean bolst;
        String strSql;

        strSql = "create table " + name + " (emp_no smallint(4) unsigned not null primary key," +
                "apellido varchar(10), oficio varchar(10), dir smallint, fecha_alt date, salario float(6,2)," +
                "comision float(6,2), dept_no tinyint(2) not null references departamentos(dept_no)";
        bolst = execQuery(strSql);
        return bolst;
    }

    public boolean insertDep(String no, String dnombre, String loc) {
        boolean bolst;
        String strSql;

        strSql = "insert into departamentos (dept_no, dnombre, loc) values ( " +
                no + ", " + dnombre + ", " + loc + ");";
        bolst = execQuery(strSql);

        return bolst;
    }


    private boolean execQuery(String sql) {
        boolean b;
        try {
            Statement st = Con.createStatement();
            System.out.print("Ejecutando: " + sql + " ... ");
            st.executeQuery(sql);
            b = true;
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        System.out.println(b);
        return b;
    }

    private ResultSet rawQuery(String sql) {
        ResultSet resultSet;
        try {
            Statement st = Con.createStatement();
            System.out.print("Ejecutando: " + sql + " ... ");
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean execUpdate(String sql) {
        boolean b;
        try {
            Statement st = Con.createStatement();
            System.out.print("Ejecutando: " + sql + " ... ");
            st.executeUpdate(sql);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        System.out.println(b);
        return b;
    }

    public boolean getSuccess() {
        return Success;
    }

    public Connection Connect() {
        return Con;
    }


    public void visualizeTable(String departamentos) {
        String strSql = "Select * from " + departamentos + ";";
        ResultSet resultSet = rawQuery(strSql);
        try{
            if(resultSet != null){
                while (resultSet.next()){
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2)
                    + " " + resultSet.getString(3));
                }
            }
        }catch (SQLException e){e.printStackTrace();}
    }
}
