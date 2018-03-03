package com.wasdf.view;

import com.wasdf.model.Departments;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListSelectionDepartmentPanel extends JPanel {
    MainView mainView;
    JPanel jPanel;

    public ListSelectionDepartmentPanel(MainView mainView, JPanel jPanel) {
        this(mainView);
        this.jPanel = jPanel;
    }

    public ListSelectionDepartmentPanel(MainView mainView) {
        super();
        this.mainView = mainView;
        setLayout(new FlowLayout());
        ArrayList<Departments> listDepartments = mainView.getDepartments();
        String[] choices = new String[listDepartments.size()];
        for (int i = 0; i < listDepartments.size(); i++) {
            choices[i] = listDepartments.get(i).getDeptNo() + " " + listDepartments.get(i).getDeptName();
        }

        JComboBox departments = new JComboBox(choices);
        departments.setSelectedIndex(0);
        departments.addActionListener(e -> {
            for (int i = 0; i < listDepartments.size(); i++) {
                if (departments.getSelectedItem().toString().contains(String.valueOf(listDepartments.get(i).getDeptNo()))) {
//
                    if (jPanel == null) {
                        mainView.closeSelectedInternalFrame();
                        mainView.setActualDepartment(listDepartments.get(i).getDeptNo(), true);
                    } else if (jPanel instanceof EmployeeRegistrerPanel) {
                        ((EmployeeRegistrerPanel) jPanel).setDepartment(listDepartments.get(i).getDeptNo());
                        ((EmployeeRegistrerPanel) jPanel).closeSelectedInternalFrame();
                    }
                }
            }
        });
        add(departments);
    }
}
