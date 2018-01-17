package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Options {

    static void showMenu() {
        System.out.println("1. Departamentos");
        System.out.println("2. Empleados");
        System.out.println("3. Consultas");
        System.out.println("4. Salir");
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

    static void option1(int option, DataBaseManager dbManager) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                System.out.println("dept_name: ");
                String dept_name = scanner.nextLine();
                if (dbManager.createDepartment(dept_no, dept_name))
                    System.out.println("Exito.");
                else
                    System.out.println("Error.");
                break;
            }
            case 2: {
                System.out.println("Departamento a modificar: ");
                String dep_no = scanner.nextLine();
                System.out.println("Nuevo nombre: ");
                String dept_name = scanner.nextLine();
                if (dbManager.modifyDepartment(dep_no, dept_name))
                    System.out.println("Exito.");
                else
                    System.out.println("Error.");
                break;
            }
            case 3: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.deleteDepartment(dept_no))
                    System.out.println("Exito.");
                else
                    System.out.println("Error");
                break;
            }
            case 4: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if(dbManager.existsDepartment(dept_no)){

                    ArrayList<String> department = dbManager.searchDepartment(dept_no);
                    department.forEach(i -> System.out.println("> " + i));
                }
            }
        }
    }
}
