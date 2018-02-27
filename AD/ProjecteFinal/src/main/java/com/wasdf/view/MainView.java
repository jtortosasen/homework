package com.wasdf.view;

import com.wasdf.Util.Util;
import com.wasdf.logic.DatabaseManager;
import com.wasdf.model.Departments;
import com.wasdf.model.Employees;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class MainView extends JFrame {

    private EmployeePanel employeePanel;

    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu employeeMenu, departmentMenu, printMenu;
    private JMenuItem addEmployeeMenuItem, showDepartmentsMenuItem, showManageDepartmentsMenuItem, printMenuItem;
    private JInternalFrame internalFrameLogin;
    private LoginPanel loginPanel;

    private DatabaseManager databaseManager;
    private String actualDepartment;

    public MainView(String title, DatabaseManager databaseManager) {
        super(title);
        this.databaseManager = databaseManager;
        desktopPane = new JDesktopPane();
        menuBar = new JMenuBar();
        printMenu = new JMenu("Imprimir");
        employeeMenu = new JMenu("Empleados");
        departmentMenu = new JMenu("Departamentos");
        printMenuItem = new JMenuItem("Imprimir a PDF");
        addEmployeeMenuItem = new JMenuItem("Anadir empleado");
        showDepartmentsMenuItem = new JMenuItem("Ver departamento");
        showManageDepartmentsMenuItem =  new JMenuItem("Gestionar departamentos");


        printMenu.add(printMenuItem);
        employeeMenu.add(addEmployeeMenuItem);
        departmentMenu.add(showDepartmentsMenuItem);
        departmentMenu.add(showManageDepartmentsMenuItem);


        printMenu.addActionListener(e -> showPrintPanel());
        addEmployeeMenuItem.addActionListener(e -> showEmployeesPanel());
        showDepartmentsMenuItem.addActionListener(e -> showDepartmentsPanel());
        showManageDepartmentsMenuItem.addActionListener(e -> showManageDepartmentsPanel());

        setJMenuBar(menuBar);
        getContentPane().add(desktopPane);

        this.setFocusable(true);

        loginPanel = new LoginPanel(this);
        internalFrameLogin = new JInternalFrame("Login");
        internalFrameLogin.add(loginPanel);
        desktopPane.add(internalFrameLogin);
        internalFrameLogin.setMinimumSize(new Dimension(600, 600));
        internalFrameLogin.setMaximizable(false); // maximize
        internalFrameLogin.setIconifiable(false); // set minimize
        internalFrameLogin.setClosable(false); // set closed
        internalFrameLogin.setResizable(false); // set resizable
        internalFrameLogin.pack();
        internalFrameLogin.setVisible(true);
    }

    private void showPrintPanel() {
        PrintPanel printPanel = new PrintPanel();
        JInternalFrame internalFrame = new JInternalFrame("Imprimir");
        internalFrame.add(printPanel);
        desktopPane.add(internalFrame);
        internalFrame.setMaximizable(false);
        internalFrame.setIconifiable(true);
        internalFrame.setClosable(true);
        internalFrame.setResizable(false);
        internalFrame.pack();
        internalFrame.setVisible(true);
    }

    private void showManageDepartmentsPanel(){
        menuBar.add(printMenu);
        DepartmentTablePanel panel = new DepartmentTablePanel(databaseManager.getDepartments(), this);
        JInternalFrame internalFrame = new JInternalFrame("Departamentos");
        internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosed(InternalFrameEvent e) {
                menuBar.remove(printMenu);
                repaint();
            }
            public void internalFrameActivated(InternalFrameEvent e){
                printMenu.setVisible(true);
                repaint();
            }
            public void internalFrameDeactivated(InternalFrameEvent e){
                printMenu.setVisible(false);
                repaint();
            }
        });
        internalFrame.add(panel);
        desktopPane.add(internalFrame);
        internalFrame.setMaximizable(true); // maximize
        internalFrame.setIconifiable(true); // set minimize
        internalFrame.setClosable(true); // set closed
        internalFrame.setResizable(true); // set resizable
        internalFrame.pack();
        internalFrame.setVisible(true);
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
        internalFrame.setMaximizable(true); // maximize
        internalFrame.setIconifiable(true); // set minimize
        internalFrame.setClosable(true); // set closed
        internalFrame.setResizable(true); // set resizable
        internalFrame.pack();
        internalFrame.setVisible(true);
    }


    private void showEmployeesPanel() {

        employeePanel = new EmployeePanel(this);
        Object[] options = {"Guardar", "Cancelar"};
        int result = JOptionPane.showOptionDialog(null, employeePanel, "Enter a Number",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
        if (result == JOptionPane.YES_OPTION) {
            Employees employee = new Employees(Integer.parseInt(employeePanel.getEmpNo()), Util.stringToDate(employeePanel.getBirthDate()), employeePanel.getFirstName(),employeePanel.getLastName(),employeePanel.getGender(), Util.stringToDate(employeePanel.getHireDate()));
            createEmployee(employee);
            recordEmployee(employee.getEmpNo(), employeePanel.getDepartment());
        }
    }

    private void recordEmployee(int empNo, String department) {
        databaseManager.recordEmployee(empNo,department);
    }

    public boolean createEmployee(Employees employee){
        return databaseManager.createEmployee(employee);
    }

    public boolean modifyEmployee(Employees employee) {
        return databaseManager.modifyEmployee(employee);
    }

    public boolean removeEmployee(Employees employee) {
        return databaseManager.deleteEmployee(employee);
    }

    public ArrayList<Employees> getEmployees() {
        return databaseManager.getEmployeesFromDepartment(actualDepartment);
    }

    public void loginSuccess(LoginPanel loginPanel) {
        if(loginPanel == this.loginPanel && loginPanel.getMainView() == this){
            startApplication();
        }
    }
    private void startApplication(){
        try {
            internalFrameLogin.setClosed(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        menuBar.add(employeeMenu);
        menuBar.add(departmentMenu);
    }

    public ArrayList<Departments> getDepartments() {
        return databaseManager.getDepartments();
    }

    public boolean removeDepartment(Departments department) {
        return databaseManager.deleteDepartment(department);
    }

    public Departments getDepartmentFromEmployee(int empNo) {
        Departments departments = databaseManager.getDepartmentFromEmployee(empNo);
        return departments;
    }

    public boolean updateDepartment(Departments department) {
        System.out.println(department.toString());
        return databaseManager.updateDepartment(department);
    }
}
