package com.wasdf.view;

import com.wasdf.Util.Util;
import com.wasdf.logic.DatabaseManager;
import com.wasdf.model.Departments;
import com.wasdf.model.Employees;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {

    EmployeePanel employeePanel;

    JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu employeeMenu, departmentMenu;
    private JMenuItem addEmployeeMenuItem, showDepartmentsMenuItem;

    DatabaseManager databaseManager;
    String actualDepartment;


    public MainView(String title, DatabaseManager databaseManager) {
        super(title);
        this.databaseManager = databaseManager;
        desktopPane = new JDesktopPane();
        menuBar = new JMenuBar();
        employeeMenu = new JMenu("Empleados");
        departmentMenu = new JMenu("Departamentos");
        addEmployeeMenuItem = new JMenuItem("Anadir empleado");
        showDepartmentsMenuItem = new JMenuItem("Ver departamento");

        employeeMenu.add(addEmployeeMenuItem);
        departmentMenu.add(showDepartmentsMenuItem);

        menuBar.add(employeeMenu);
        menuBar.add(departmentMenu);

        addEmployeeMenuItem.addActionListener(e -> showEmployeesPanel());
        showDepartmentsMenuItem.addActionListener(e -> showDepartmentsPanel());

        setJMenuBar(menuBar);
        getContentPane().add(desktopPane);
    }

    private void showDepartmentsPanel() {
        ArrayList <Departments> listDepartments = databaseManager.getDepartments();
        String[] choices = new String[listDepartments.size()];
        for(int i = 0; i < listDepartments.size(); i++){
            choices[i] = listDepartments.get(i).getDeptNo() + " " + listDepartments.get(i).getDeptName();
        }
        String input = (String) JOptionPane.showInputDialog(null, "Departamento:",
                "Selecciona departamento", JOptionPane.QUESTION_MESSAGE, null,
                choices,
                choices[0]);
        for (int i = 0; i < listDepartments.size(); i++) {
            if (input.contains(String.valueOf(listDepartments.get(i).getDeptNo()))){
                actualDepartment = String.valueOf(listDepartments.get(i).getDeptNo());
                drawDepartmentTable(databaseManager.getEmployeesFromDepartment(actualDepartment));
            }
        }
    }

    private void drawDepartmentTable(ArrayList<Employees> employees){

        DepartmentEmployeeTablePanel panel = new DepartmentEmployeeTablePanel(employees, this);
        JInternalFrame internalFrame = new JInternalFrame("Empleados");
        internalFrame.add(panel);
        desktopPane.add(internalFrame);
        internalFrame.setMinimumSize(new Dimension(600, 600));
        internalFrame.setMaximizable(true); // maximize
        internalFrame.setIconifiable(true); // set minimize
        internalFrame.setClosable(true); // set closed
        internalFrame.setResizable(true); // set resizable
        internalFrame.pack();
        internalFrame.setVisible(true);
    }


    private void showEmployeesPanel() {
        employeePanel = new EmployeePanel();
        Object[] options = {"Guardar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, employeePanel, "Enter a Number",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
        if (result == JOptionPane.YES_OPTION) {
            Employees employee = new Employees(Integer.parseInt(employeePanel.getEmpNo()), Util.stringToDate(employeePanel.getBirthDate()), employeePanel.getFirstName(),employeePanel.getLastName(),employeePanel.getGender(), Util.stringToDate(employeePanel.getHireDate()));
            createEmployee(employee);
        }
    }

    public boolean createEmployee(Employees employee){
        return databaseManager.createEmployee(employee);
    }

    public boolean modifyEmployee(Employees employee) {
        return databaseManager.modifyEmployee(employee);
    }

    public boolean removeEmployee(Employees employee) {
        return databaseManager.deleteEmployee(String.valueOf(employee.getEmpNo()));
    }

    public ArrayList<Employees> getEmployees() {
        return databaseManager.getEmployeesFromDepartment(actualDepartment);
    }
}
