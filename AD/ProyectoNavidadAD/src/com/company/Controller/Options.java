package com.company.Controller;

import com.company.Model.Department;

import java.util.Scanner;

class Options {

    static void option1(int option, DataBaseManager dbManager) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                System.out.println("dept_name: ");
                String dept_name = scanner.nextLine();
                if (dbManager.createDepartment(new Department(dept_no, dept_name)))
                    System.out.println("Exito.");
                else
                    System.out.println("Error.");
                break;
            }
            case 2: {
                System.out.println("Departamento a modificar: ");
                String dept_no = scanner.nextLine();
                if(dbManager.existDepartment(dept_no)){
                    Department department = dbManager.getDepartment(dept_no);
                    System.out.println("Nuevo nombre: ");
                    department.setName(scanner.nextLine());
                    if (dbManager.modifyDepartment(dep_no, dept_name))
                        System.out.println("Exito.");
                    else
                        System.out.println("Error.");
                }
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
                if(dbManager.existDepartment(dept_no)){
                    Department department = dbManager.getDepartment(dept_no);
                    System.out.println(department.toString());
                }
            }
        }
    }
}
