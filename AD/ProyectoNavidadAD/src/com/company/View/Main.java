package com.company.View;

import com.company.Controller.DataBaseManager;

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
                showMenu();

                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                switch (option) {
                    case 1: {
                        showMenu1();
                        option = scanner.nextInt();
                        ViewLogic.option1(option, dbManager);
                        break;
                    }

                    case 2: {
                        showMenu2();
                        option = scanner.nextInt();
                        ViewLogic.options2(option, dbManager);
                        break;
                    }
                    case 3:{
                        showMenu3();
                        option = scanner.nextInt();
                        ViewLogic.options3(option, dbManager);
                        break;
                    }
                    case 4:{
                        showMenu4();
                        option = scanner.nextInt();
                        ViewLogic.options4(option, dbManager);
                        break;
                    }
                    case 5:{
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

    static void showMenu() {
        System.out.println("1. Departamentos");
        System.out.println("2. Empleados");
        System.out.println("3. Consultas");
        System.out.println("4. Funciones y procedimientos");
        System.out.println("5. Salir");
    }

    static void showMenu1() {
        System.out.println("1. Crear departamento");
        System.out.println("2. Modificar departamento");
        System.out.println("3. Eliminar departamento");
        System.out.println("4. Buscar departamento");
    }

    static void showMenu2() {
        System.out.println("1. Crear empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Buscar empleado");
    }

    static void showMenu3() {
        System.out.println("1. Dado el código de un empleado ver su información");
        System.out.println("2. Dado el código de un departamento ver su información");
        System.out.println("3. Dado el código de un departamento ver los empleados que tiene");
        System.out.println("4. Dado el código de un empleado ver a qué departamento pertenece");
        System.out.println("5. Dado del código de un empleado y un departamento ver si el empleado " +
                "a pertenecido a ese departamento y si es afirmativo  en qué fechas");
        System.out.println("6. Dado el código de un empleado y un departamento mover al empleado del departamento");
        System.out.println("7. Dado el código de un departamento ver los empleados (actuales) y " +
                "el histórico (empleados que trabajaron allí pero que ahora no lo hacen).");
        System.out.println("8. Dado el código de un empleado ver el departamento al que pertenece (actual)" +
                " y el histórico a los que ha pertenecido");
    }

    static void showMenu4(){
        System.out.println("1. Función que devuelve el número de departamentos");
        System.out.println("2. Función que devuelve el número de empleados");
        System.out.println("3. Función que devuelve el número de empleados de un departamento");
        System.out.println("4. Procedimiento que devuelve los datos de un departamento");
        System.out.println("5. Procedimiento que devuelve los datos de un empleado");
        System.out.println("6. Procedimiento que nos devuelve la media de los salarios de un departamento");
    }

    private static void exitApplication(){
        exit = true;
    }
}
