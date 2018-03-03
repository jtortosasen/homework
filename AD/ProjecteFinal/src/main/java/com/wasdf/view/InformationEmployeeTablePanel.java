package com.wasdf.view;

import com.wasdf.Util.Util;
import com.wasdf.model.Employees;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InformationEmployeeTablePanel extends JPanel {

    private MainView mainView;
    JTable table;
    ArrayList<Employees> listEmployees;
    JTextField searchTextField;

    public InformationEmployeeTablePanel(ArrayList<Employees> employees, MainView mainView) {
        setLayout(new GridBagLayout());
        listEmployees = employees;
        Employees[] array = employees.toArray(new Employees[employees.size()]);
        this.mainView = mainView;
        table = new JTable(new MyTableModel(array));
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints c = new GridBagConstraints();

        JLabel searchLabel = new JLabel("Buscar");
        searchTextField = new JTextField();
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (searchTextField.getText().equals("")){
                    refreshEvent();
                }
                else {
                    ArrayList<Employees> finded = new ArrayList<>();
                    System.out.println(listEmployees.get(0).toString());

                    for(Employees employee : listEmployees){
                        if (employee.toString().contains(searchTextField.getText()))
                        {
                            finded.add(employee);
                        }
                    }
                    if(!finded.isEmpty()){
                        Employees[] array = finded.toArray(new Employees[finded.size()]);
                        MyTableModel tableModel = new MyTableModel(array);
                        table.setModel(tableModel);
                    }
                }

            }
        });

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(5, 0, 5, 0);
        add(searchLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        c.weighty = 0;
        add(searchTextField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        add(scrollPane, c);

        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> refreshEvent());

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 5, 5);
        add(refreshButton, c);
    }

    private void refreshEvent() {
        listEmployees = mainView.getEmployees();
        Employees[] array = listEmployees.toArray(new Employees[listEmployees.size()]);
        MyTableModel tableModel = new MyTableModel(array);
        table.setModel(tableModel);
    }

    class MyTableModel extends AbstractTableModel {
        private boolean DEBUG = false;

        private String[] columnNames = {
                "Emp_no",
                "Fecha Nacimiento",
                "Nombre",
                "Apellido",
                "Genero",
                "F. Contrato"
        };
        private Object[][] data;

        public MyTableModel(Employees[] array) {
            data = new String[array.length][columnNames.length];
            for (int i = 0; i < array.length; i++) {
                data[i][0] = String.valueOf(array[i].getEmpNo());
                data[i][1] = Util.dateToString(array[i].getBirthDate());
                data[i][2] = array[i].getFirstName();
                data[i][3] = array[i].getLastName();
                data[i][4] = array[i].getGender();
                data[i][5] = Util.dateToString(array[i].getHireDate());
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {

            return false;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */

        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
}
