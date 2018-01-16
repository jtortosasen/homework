package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String USER_DATABASE = "root";
    private static final String PWD_DATABASE = "admin";
    private static final String URL_CONN = "jdbc:mysql://localhost/";


    public static void main(String[] args) {
        // write your code here
        DataBaseManager dbManager = new DataBaseManager(USER_DATABASE, PWD_DATABASE, URL_CONN);
        if(dbManager.connect()){
            System.out.println("ha conectao");
        }
    }

    private static void showMenu(){
        System.out.println("1. Departamentos");
        System.out.println("2. Empleados");
        System.out.println("3. Consultas");
    }

    private static void showMenu1(){
        System.out.println("1. Crear departamento");
        System.out.println("2. Modificar departamento");
        System.out.println("3. Eliminar departmaento");
        System.out.println("4. Buscar departamento");
    }

    private static void showMenu2(){
        System.out.println("1. Crear empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Buscar empleado");
    }

    private static void showMenu3(){
        System.out.println("1. Dado el código de un empleado ver su información");
        System.out.println("2. Dado ");
        System.out.println("3. Eliminar departmaento");
        System.out.println("4. Buscar departamento");
    }[]
}
