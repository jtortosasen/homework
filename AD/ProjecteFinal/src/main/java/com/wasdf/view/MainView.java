package com.wasdf.view;

import com.wasdf.Util.Util;
import com.wasdf.logic.DatabaseManager;
import com.wasdf.model.Departments;
import com.wasdf.model.Employees;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainView extends JFrame {

    public static String OS = System.getProperty("os.name").toLowerCase();

    private EmployeeRegistrerPanel employeeRegistrerPanel;

    private CustomDesktopPane desktopPane;
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


        desktopPane = new CustomDesktopPane();

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
        setFocusable(true);
        loginPanel = new LoginPanel(this);
        internalFrameLogin = new JInternalFrame("Login");
        internalFrameLogin.add(loginPanel);
        internalFrameLogin.setMaximizable(false);
        internalFrameLogin.setIconifiable(false);
        internalFrameLogin.setClosable(false);
        internalFrameLogin.setResizable(false);
        internalFrameLogin.pack();
        internalFrameLogin.setVisible(true);
        desktopPane.add(internalFrameLogin);
    }


    class CustomDesktopPane extends JDesktopPane{
        private String OS = System.getProperty("os.name").toLowerCase();
        private BufferedImage tile;

        CustomDesktopPane() {
            super();
            try {
                if(OS.contains("win")){
                    tile = ImageIO.read(new FileInputStream("src\\main\\resources\\background.png"));
                }else if(OS.contains("linux")){
                    tile = ImageIO.read(new FileInputStream("src/main/resources/background.png"));
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int tileWidth = tile.getWidth();
            int tileHeight = tile.getHeight();
            for (int y = 0; y < getHeight(); y += tileHeight) {
                for (int x = 0; x < getWidth(); x += tileWidth) {
                    g2d.drawImage(tile, x, y, this);
                }
            }
            g2d.dispose();
        }
    }


    private void showPrintPanel() {
        PrintPanel printPanel = new PrintPanel(this);
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
        InformationDepartmentTablePanel panel = new InformationDepartmentTablePanel(databaseManager.getDepartments(), this);
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
        internalFrame.setSize(new Dimension(650,310));
        internalFrame.setMaximizable(true);
        internalFrame.setIconifiable(true);
        internalFrame.setClosable(true);
        internalFrame.setResizable(true);
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
        if(input != null){
            for (int i = 0; i < listDepartments.size(); i++) {
                if (input.contains(String.valueOf(listDepartments.get(i).getDeptNo()))){
                    actualDepartment = String.valueOf(listDepartments.get(i).getDeptNo());
                    drawDepartmentTable(databaseManager.getEmployeesFromDepartment(actualDepartment));
                }
            }
        }
    }

    private void drawDepartmentTable(ArrayList<Employees> employees){

        InformationEmployeeTablePanel panel = new InformationEmployeeTablePanel(employees, this);
        JInternalFrame internalFrame = new JInternalFrame("Empleados");
        internalFrame.add(panel);
        desktopPane.add(internalFrame);
        internalFrame.setSize(new Dimension(650,310));
        internalFrame.setMaximizable(true); // maximize
        internalFrame.setIconifiable(true); // set minimize
        internalFrame.setClosable(true); // set closed
        internalFrame.setResizable(true); // set resizable
        internalFrame.pack();
        internalFrame.setVisible(true);
    }


    private void showEmployeesPanel() {

        employeeRegistrerPanel = new EmployeeRegistrerPanel(this);
        Object[] options = {"Guardar", "Cancelar"};
        int result = JOptionPane.showInternalOptionDialog(desktopPane, employeeRegistrerPanel,"Hola",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,"");
        if (result == JOptionPane.YES_OPTION) {
            if(!employeeRegistrerPanel.getEmpNo().isEmpty() && !employeeRegistrerPanel.getFirstName().isEmpty()){
                if(Util.isInteger(employeeRegistrerPanel.getEmpNo())){
                    Employees employee = new Employees(Integer.parseInt(employeeRegistrerPanel.getEmpNo()), Util.stringToDate(employeeRegistrerPanel.getBirthDate()), employeeRegistrerPanel.getFirstName(), employeeRegistrerPanel.getLastName(), employeeRegistrerPanel.getGender(), Util.stringToDate(employeeRegistrerPanel.getHireDate()));
                    createEmployee(employee);
                    if(!employeeRegistrerPanel.getDepartment().isEmpty())
                        recordEmployee(employee.getEmpNo(), employeeRegistrerPanel.getDepartment());
                }else{
                    JOptionPane.showInternalMessageDialog(desktopPane,"Código empleado no válido","Atención!",JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showInternalMessageDialog(desktopPane,"Error en los campos empno/nombre","Atención!",JOptionPane.ERROR_MESSAGE);
            }
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

    public boolean createDepartment(Departments department) {
        return databaseManager.createDepartment(department);
    }
}
