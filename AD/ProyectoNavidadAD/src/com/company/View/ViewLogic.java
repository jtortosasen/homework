package com.company.View;

import com.company.Controller.DataBaseManager;
import com.company.Model.Department;
import com.company.Model.Employee;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class ViewLogic {
    private static final Logger LOG = Logger.getLogger(ViewLogic.class.getName());

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
                if (dbManager.existDepartment(dept_no)) {
                    Department department = dbManager.getDepartment(dept_no);
                    if (department != null) {
                        System.out.println("Nuevo nombre: ");
                        department.setName(scanner.nextLine());
                        if (dbManager.modifyDepartment(department))
                            System.out.println("Exito.");
                        else
                            System.out.println("Error.");
                    } else LOG.log(Level.WARNING, "Department null");
                }
                break;
            }
            case 3: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existDepartment(dept_no)) {
                    if (dbManager.deleteDepartment(dept_no))
                        System.out.println("Exito.");
                    else
                        System.out.println("No se ha podido borrar");
                    break;
                } else System.out.println("No existe");
            }
            case 4: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existDepartment(dept_no)) {
                    Department department = dbManager.getDepartment(dept_no);
                    System.out.println(department.toString());
                }
                break;
            }
        }
    }

    static void options2(int option, DataBaseManager dbManager) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1: {
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                if (!dbManager.existEmployee(emp_no)) {
                    System.out.println("first_name: ");
                    String first_name = scanner.nextLine();

                    System.out.println("last_name: ");
                    String last_name = scanner.nextLine();
                    System.out.println("birth_name: ");
                    String birth_name = scanner.nextLine();

                    System.out.println("gender: ");
                    String gender = scanner.nextLine();
                    System.out.println("hire_date: ");
                    String hire_date = scanner.nextLine();
                    if (dbManager.createEmployee(new Employee(emp_no, birth_name, first_name, last_name, gender, hire_date)))
                        System.out.println("Exito.");
                    else
                        System.out.println("Error.");
                    break;
                } else System.out.println("Ya existe el empleado");
                break;

            }
            case 2: {
                System.out.println("Empleado a modificar: ");
                String emp_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no)) {
                    Employee employee = dbManager.getEmployee(emp_no);
                    if (employee != null) {
                        System.out.println("Nuevo nombre: ");
                        employee.setFirstName(scanner.nextLine());
                        System.out.println("Nuevo apellido: ");
                        employee.setLastName(scanner.nextLine());
                        System.out.println("Nueva fecha de nacim.: ");
                        employee.setBirthDate(scanner.nextLine());
                        System.out.println("Nuevo género: ");
                        employee.setGender(scanner.nextLine());
                        System.out.println("Nueva fecha de contratación: ");
                        employee.setHireDate(scanner.nextLine());
                        if (dbManager.modifyEmployee(employee))
                            System.out.println("Exito.");
                        else
                            System.out.println("Error.");
                    }else LOG.log(Level.WARNING, "Empleado null");
                    break;
                }

            }
            case 3: {
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                if(dbManager.existEmployee(emp_no)){
                    if (dbManager.deleteEmployee(emp_no))
                        System.out.println("Exito.");
                    else
                        System.out.println("Error");
                    break;
                }
                System.out.println("Usuario no existe.");
                break;
            }
            case 4: {
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no)) {
                    Employee employee = dbManager.getEmployee(emp_no);
                    System.out.println(employee.toString());
                }
                break;
            }
        }
    }

    static void options3(int option, DataBaseManager dbManager) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1: {
                System.out.println("Emp_no: ");
                String emp_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no)) {
                    System.out.println(dbManager.getEmployee(emp_no).toString());
                } else System.out.println("No existe");
                break;
            }
            case 2: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existDepartment(dept_no)) {
                    System.out.println(dbManager.getDepartment(dept_no).toString());
                } else System.out.println("No existe");
                break;
            }
            case 3: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existDepartment(dept_no)) {
                    ArrayList<Employee> employees = dbManager.getEmployeesFromDepartment(dept_no);
                    if(employees != null){
                        for (Employee employee : employees) {
                            System.out.println(employee.toString());
                        }
                    }else LOG.log(Level.WARNING, "Arraylist empleados null");
                } else System.out.println("No existe");
                break;
            }
            case 4: {
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no)) {
                    Department department = dbManager.getDepartmentFromEmployee(emp_no);
                    if(department != null)
                        System.out.println(department.toString());
                    else
                        LOG.log(Level.WARNING, "Department null");
                } else System.out.println("No existe");
                break;
            }
            case 5: {
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no) && dbManager.existDepartment(dept_no)) {
                    if (dbManager.isEmployeeOnDateHistoryDepartment(emp_no, dept_no)) {
                        System.out.println("Afirmativo.");
                        String date = dbManager.getDatesEmployeeOnDepartment(emp_no, dept_no);
                        if(date != null)
                            System.out.println(date);
                    }
                } else System.out.println("Employee or department doesnt exist");
                break;
            }
            case 6: {
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no) && dbManager.existDepartment(dept_no)) {
                    if (dbManager.employeeOnDepartment(emp_no, dept_no)) {
                        System.out.println("El empleado ya está en el departamento");
                        break;
                    } else {
                        if(dbManager.moveEmployeeToDepartment(emp_no, dept_no))
                            System.out.println("Exito.");
                        else System.out.println("No se ha podido mover el empleado");
                    }
                } else System.out.println("Employee or department doesnt exist");
                break;
            }

            case 7: {
                System.out.println("dept_no: ");
                String dept_no = scanner.nextLine();
                if (dbManager.existDepartment(dept_no)) {
                    ArrayList<Employee> actualEmployees = dbManager.getActualEmployeesFromDepartment(dept_no);
                    ArrayList<Employee> historicEmployees = dbManager.getHistoricEmployeesFromDepartment(dept_no);
                    System.out.println("Empleados actuales: ");
                    for (Employee actualEmployee : actualEmployees) {
                        System.out.println(actualEmployee.toString());
                    }
                    System.out.println("");
                    System.out.println("Histórico de empleados: ");
                    for (Employee employee : historicEmployees) {
                        System.out.println(employee.toString());
                    }
                } else System.out.println("No existe el departamento");
                break;
            }
            case 8:
                System.out.println("emp_no: ");
                String emp_no = scanner.nextLine();
                if (dbManager.existEmployee(emp_no)) {
                    ArrayList<Department> historicDepartments = dbManager.getHistoricDepartmentsFromEmployee(emp_no);
                    Department actualDepartment = dbManager.getDepartmentFromEmployee(emp_no);
                    System.out.print("Actual department: ");
                    System.out.println(actualDepartment);
                    System.out.println("");
                    System.out.println("Historic departments: ");
                    for (Department department : historicDepartments)
                        System.out.println(department.toString());
                } else System.out.println("No existe el empleado");

        }

    }
}
