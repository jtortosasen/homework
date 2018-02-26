package PracticaEmployee.view;

import PracticaEmployee.model.Entity.Department;
import PracticaEmployee.model.Entity.Employee;
import PracticaEmployee.model.dao.DataBaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView extends JFrame {

    EmployeePanel employeePanel;

    JDesktopPane desktopPane;
    private JMenuBar menuBar;
    private JMenu employeeMenu, departmentMenu;
    private JMenuItem addEmployeeMenuItem, showDepartmentsMenuItem;

    DataBaseManager dbManager;
    String actualDepartment;


    public MainView(String title, DataBaseManager dbManager) {
        super(title);
        this.dbManager = dbManager;
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
        ArrayList <Department> listDepartments = dbManager.getDepartments();
        String[] choices = new String[listDepartments.size()];
        for(int i = 0; i < listDepartments.size(); i++){
            choices[i] = listDepartments.get(i).getNo() + " " + listDepartments.get(i).getName();
        }
        String input = (String) JOptionPane.showInputDialog(null, "Departamento:",
                "Selecciona departamento", JOptionPane.QUESTION_MESSAGE, null,
                choices,
                choices[0]);
        for (int i = 0; i < listDepartments.size(); i++) {
            if (input.contains(listDepartments.get(i).getNo())){
                actualDepartment = listDepartments.get(i).getNo();
                drawDepartmentTable(dbManager.getEmployeesFromDepartment(actualDepartment));
            }
        }
    }

    private void drawDepartmentTable(ArrayList<Employee> employees){

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
            Employee employee = new Employee(employeePanel.getEmpNo(),employeePanel.getBirthDate(), employeePanel.getFirstName(),employeePanel.getLastName(),employeePanel.getGender(), employeePanel.getHireDate());
            createEmployee(employee);
        }
    }

    public boolean createEmployee(Employee employee){
        return dbManager.createEmployee(employee);
    }

    public boolean modifyEmployee(Employee employee) {
        return dbManager.modifyEmployee(employee);
    }

    public boolean removeEmployee(Employee employee) {
        return dbManager.deleteEmployee(employee.getEmpNo());
    }

    public ArrayList<Employee> getEmployees() {
        return dbManager.getEmployeesFromDepartment(actualDepartment);
    }
}
