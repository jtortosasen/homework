package com.wasdf.view;

import com.wasdf.model.Departments;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InformationDepartmentTablePanel extends JPanel {

    private MainView mainView;
    JTable table;
    ArrayList<Departments> listDepartments;
    JTextField searchTextField;

    public InformationDepartmentTablePanel(ArrayList<Departments> departments, MainView mainView) {
        setLayout(new GridBagLayout());
        listDepartments = departments;
        Departments[] array = departments.toArray(new Departments[departments.size()]);
        this.mainView = mainView;
        table = new JTable(new MyTableModel(array));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
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
                    ArrayList<Departments> finded = new ArrayList<>();
                    System.out.println(listDepartments.get(0).toString());

                    for(Departments department : listDepartments){
                        if (department.toString().contains(searchTextField.getText()))
                        {
                            finded.add(department);
                        }
                    }
                    if(!finded.isEmpty()){
                        Departments[] array = finded.toArray(new Departments[finded.size()]);
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
        c.gridwidth = 5;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        add(scrollPane, c);

        JButton deleteButton = new JButton("Eliminar");
        JButton refreshButton = new JButton("Refrescar");
        JButton saveButton = new JButton("Guardar cambio");
        JButton addButton = new JButton("Anadir departamento");

        deleteButton.addActionListener(e -> deleteEvent());
        refreshButton.addActionListener(e -> refreshEvent());
        saveButton.addActionListener(e -> saveEvent());
        addButton.addActionListener(e -> addEvent());

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 5, 5);
        add(refreshButton, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        add(addButton, c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        add(saveButton, c);

        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.insets = new Insets(0,30,5,0);
        add(deleteButton, c);
    }



    private void saveEvent() {
        if(!table.getSelectionModel().isSelectionEmpty()){
            if (JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres guardar?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int index = table.getSelectedRow();
                if(!String.valueOf(table.getModel().getValueAt(index,0)).isEmpty() && !String.valueOf(table.getModel().getValueAt(index,1)).isEmpty()){
                    Departments department = new Departments(String.valueOf(table.getModel().getValueAt(index,0)),String.valueOf(table.getModel().getValueAt(index,1)));
                    if(index == listDepartments.size()){
                        if(!mainView.createDepartment(department)){
                            JOptionPane.showMessageDialog(new JFrame(), "Error al crear departamento", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }else if(index < listDepartments.size()){
                        if(!mainView.updateDepartment(department)){
                            JOptionPane.showMessageDialog(new JFrame(), "Error al actualizar departamento", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    private void refreshEvent() {
        listDepartments = mainView.getDepartments();
        Departments[] array = listDepartments.toArray(new Departments[listDepartments.size()]);
        MyTableModel tableModel = new MyTableModel(array);
        table.setModel(tableModel);
        System.out.println(this.getSize());
    }

    private void deleteEvent() {

        if(!table.getSelectionModel().isSelectionEmpty()){
            int index = table.getSelectedRow();
            Departments department = listDepartments.get(index);

            if (JOptionPane.showConfirmDialog(null, "Estás seguro?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (mainView.removeDepartment(department)) {
                    listDepartments.remove(index);
                    Departments[] array = listDepartments.toArray(new Departments[listDepartments.size()]);
                    MyTableModel tableModel = new MyTableModel(array);
                    table.setModel(tableModel);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Error al borrar departamento", "Error",
                            JOptionPane.ERROR_MESSAGE);            }
            }
        }
    }

    private void addEvent(){
        listDepartments = mainView.getDepartments();
        Departments[] array = listDepartments.toArray(new Departments[listDepartments.size() + 1]);
        array[array.length -1] = new Departments("","");
        MyTableModel tableModel = new MyTableModel(array);
        table.setModel(tableModel);
    }

    class MyTableModel extends AbstractTableModel {
        private boolean DEBUG = true;

        private String[] columnNames = {
                "Emp_no",
                "Nombre departamento",
        };
        private Object[][] data;

        public MyTableModel(Departments[] array) {
            data = new String[array.length][columnNames.length];
            for (int i = 0; i < array.length; i++) {
                data[i][0] = array[i].getDeptNo();
                data[i][1] = array[i].getDeptName();
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
            if(col == 0 && row < listDepartments.size()){
                return false;
            }else
                return true;
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
