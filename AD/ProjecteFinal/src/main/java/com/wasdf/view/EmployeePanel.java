package com.wasdf.view;

import com.wasdf.Util.Util;
import com.wasdf.main.Main;
import com.wasdf.model.Departments;
import com.wasdf.model.Employees;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EmployeePanel extends JPanel {

    MainView mainView;

    JTextField textEmpNo;
    JTextField textFirstName;
    JTextField textLastName;
    JTextField textBirthDate;
    JTextField textGender ;
    JTextField textHireDate;
    JTextField textDepartment;

    public String getEmpNo(){
        return textEmpNo.getText();
    }
    public String getFirstName(){
        return textFirstName.getText();
    }
    public String getLastName(){
        return textLastName.getText();
    }
    public String getBirthDate(){
        return textBirthDate.getText();
    }
    public String getGender(){
        return textGender.getText();
    }
    public String getHireDate(){
        return textHireDate.getText();
    }
    public String getDepartment(){
        return textDepartment.getText();
    }

    public EmployeePanel(Employees employee){
        this();
        textEmpNo.setText(String.valueOf(employee.getEmpNo()));
        textFirstName.setText(employee.getFirstName());
        textBirthDate.setText(Util.dateToString(employee.getBirthDate()));
        textLastName.setText(employee.getLastName());
        textGender.setText(employee.getGender());
        textHireDate.setText(Util.dateToString(employee.getHireDate()));
        textDepartment.setText(mainView.getDepartmentFromEmployee(employee.getEmpNo()).getDeptNo());
    }

    public EmployeePanel(MainView mainView){
        this();
        this.mainView = mainView;
    }

    public EmployeePanel(){

        super();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel empNo = new JLabel("Emp_no");
        JLabel firstName = new JLabel("Nombre");
        JLabel lastName = new JLabel("Apellidos");
        JLabel birthDate = new JLabel("Fecha nac.");
        JLabel gender = new JLabel("Género");
        JLabel hireDate = new JLabel("Fecha despido");
        JLabel department = new JLabel("Departamento");

        JButton jButton = new JButton("Buscar");
        jButton.addActionListener(e -> selectionDeparmentEvent());

        textEmpNo = new JTextField();
        textFirstName = new JTextField();
        textLastName = new JTextField();
        textBirthDate = new JTextField();
        textGender = new JTextField();
        textHireDate = new JTextField();
        textDepartment = new JTextField();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        add(empNo,c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textEmpNo,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(firstName,c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textFirstName,c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(lastName,c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textLastName,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(birthDate,c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textBirthDate,c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(gender,c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textGender,c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(hireDate,c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textHireDate,c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(department,c);

        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textDepartment,c);

        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        add(jButton,c);
    }

    private void selectionDeparmentEvent(){
        ArrayList<Departments> listDepartments = mainView.getDepartments();
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
                    textDepartment.setText(listDepartments.get(i).getDeptNo());
                }
            }
        }
    }
}
