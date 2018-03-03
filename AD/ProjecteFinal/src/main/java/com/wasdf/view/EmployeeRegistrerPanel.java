package com.wasdf.view;

import com.wasdf.Util.Util;
import com.wasdf.model.Employees;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.text.ParseException;

public class EmployeeRegistrerPanel extends JPanel {

    MainView mainView;

    private JTextField textEmpNo;
    private JTextField textFirstName;
    private JTextField textLastName;
    private JFormattedTextField textBirthDate;
    private JFormattedTextField textHireDate;
    private JTextField textDepartment;
    private ButtonGroup buttonGroupGender;
    private JRadioButton radioButtonMale;
    private JRadioButton radioButtonFemale;


    public EmployeeRegistrerPanel(Employees employee) {
        this();
        textEmpNo.setText(String.valueOf(employee.getEmpNo()));
        textFirstName.setText(employee.getFirstName());
        textBirthDate.setText(Util.dateToString(employee.getBirthDate()));
        textLastName.setText(employee.getLastName());
        radioButtonFemale.setSelected(employee.getGender().equals("F"));
        radioButtonMale.setSelected(employee.getGender().equals("M"));
        textHireDate.setText(Util.dateToString(employee.getHireDate()));
        textDepartment.setText(mainView.getDepartmentFromEmployee(employee.getEmpNo()).getDeptNo());
    }

    public EmployeeRegistrerPanel(MainView mainView) {
        this();
        this.mainView = mainView;
    }

    public EmployeeRegistrerPanel() {

        super();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel empNo = new JLabel("Emp_no");
        JLabel firstName = new JLabel("Nombre");
        JLabel lastName = new JLabel("Apellidos");
        JLabel birthDate = new JLabel("Fecha nac.");
        JLabel hireDate = new JLabel("Fecha contrato");
        JLabel department = new JLabel("Departamento");

        JButton jButton = new JButton("Buscar");
        jButton.addActionListener(e -> selectionDeparmentEvent());

        textEmpNo = new JTextField();
        textFirstName = new JTextField();
        textLastName = new JTextField();
        MaskFormatter dateMask = null;
        try {
            dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('-');
            dateMask.setValidCharacters("0123456789");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        textBirthDate = new JFormattedTextField(dateMask);
        textBirthDate.setHorizontalAlignment(JTextField.RIGHT);
        textHireDate = new JFormattedTextField(dateMask);
        textHireDate.setHorizontalAlignment(JTextField.RIGHT);
        buttonGroupGender = new ButtonGroup();
        textDepartment = new JTextField(4);
        radioButtonFemale = new JRadioButton("Mujer");
        radioButtonFemale.setActionCommand("F");
        radioButtonMale = new JRadioButton("Hombre");
        radioButtonMale.setActionCommand("M");
        buttonGroupGender.add(radioButtonFemale);
        buttonGroupGender.add(radioButtonMale);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.insets = new Insets(0, 0, 0, 5);
        add(empNo, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textEmpNo, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(firstName, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textFirstName, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(lastName, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textLastName, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(birthDate, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textBirthDate, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(radioButtonFemale, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.WEST;
        add(radioButtonMale, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(hireDate, c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textHireDate, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        add(department, c);

        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textDepartment, c);

        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        add(jButton, c);
    }

    public String getEmpNo() {
        return textEmpNo.getText();
    }

    public String getFirstName() {
        return textFirstName.getText();
    }

    public String getLastName() {
        return textLastName.getText();
    }

    public String getBirthDate() {
        return textBirthDate.getText();
    }

    public String getGender() {
        return buttonGroupGender.getSelection().getActionCommand();
    }

    public String getHireDate() {
        return textHireDate.getText();
    }

    public String getDepartment() {
        return textDepartment.getText();
    }

    public void setDepartment(String department) {
        textDepartment.setText(department);
    }

    private void selectionDeparmentEvent() {
        JOptionPane.showInternalOptionDialog(mainView.getDesktopPane(), new ListSelectionDepartmentPanel(mainView, this), "Selecciona departamento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, "");
    }


    public void closeSelectedInternalFrame() {
        try {
            mainView.getDesktopPane().getSelectedFrame().setClosed(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
