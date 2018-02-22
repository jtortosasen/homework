package PracticaJTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Tables extends JFrame {
    private boolean DEBUG = false;

    JTabbedPane tabbedPane;
    JPanel jPanel1, jPanel2, jPanel3, jPanel4;

    public Tables() {
        tabbedPane = new JTabbedPane();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        getRootPane().setBorder(new EmptyBorder(20,20,20,20));

        practica1();
        practica2();
        practica3();
        practica4();

        tabbedPane.addTab("JTable Básico",jPanel1);
        tabbedPane.addTab("JTable Combobox", jPanel2);
        tabbedPane.addTab("Table renderer", jPanel3);
        tabbedPane.addTab("Table sorted", jPanel4);
        add(tabbedPane);
        setSize(600, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    private void practica1() {
        jPanel1.setLayout(new GridBagLayout());

        JTable table = new JTable(new Tables.MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        jPanel1.add(scrollPane,c);

        JButton button1 = new JButton("Anadir Fila");
        JButton button2 = new JButton("Borrar Fila");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 1;
        c.weighty = 0;
        new Insets(5, 5, 5, 5);
        jPanel1.add(button1,c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(button2,c);

    }

    @SuppressWarnings("Duplicates")
    private void practica2(){
        jPanel2.setLayout(new GridBagLayout());

        JTable table = new JTable(new Tables.MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        TableColumn sportColumn = table.getColumnModel().getColumn(3);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Esqui");
        comboBox.addItem("Basket");
        comboBox.addItem("Tenis");
        comboBox.addItem("Ajedrez");
        comboBox.addItem("Ninguno");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        jPanel2.add(scrollPane,c);

        JButton button1 = new JButton("Anadir Fila");
        JButton button2 = new JButton("Borrar Fila");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 1;
        c.weighty = 0;
        new Insets(5, 5, 5, 5);
        jPanel2.add(button1,c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(button2,c);
    }

    @SuppressWarnings("Duplicates")
    private void practica3(){
        jPanel3.setLayout(new GridBagLayout());

        JTable table = new JTable(new Tables.MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        TableColumn sportColumn = table.getColumnModel().getColumn(3);
        TableColumn ageColumn = table.getColumnModel().getColumn(2);
        ageColumn.setCellRenderer(new ColorRenderer());
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Esqui");
        comboBox.addItem("Basket");
        comboBox.addItem("Tenis");
        comboBox.addItem("Ajedrez");
        comboBox.addItem("Ninguno");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        jPanel3.add(scrollPane,c);

        JButton button1 = new JButton("Anadir Fila");
        JButton button2 = new JButton("Borrar Fila");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 1;
        c.weighty = 0;
        new Insets(5, 5, 5, 5);
        jPanel3.add(button1,c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(button2,c);
    }

    @SuppressWarnings("Duplicates")
    private void practica4(){
        jPanel4.setLayout(new GridBagLayout());

        MyTableModel model = new MyTableModel();
        TableSorter sorter = new TableSorter(model);
        JTable table = new JTable(sorter);
        JTableHeader header = table.getTableHeader();
        sorter.setTableHeader(header);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        TableColumn sportColumn = table.getColumnModel().getColumn(3);
        TableColumn ageColumn = table.getColumnModel().getColumn(2);
        ageColumn.setCellRenderer(new ColorRenderer());
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Esqui");
        comboBox.addItem("Basket");
        comboBox.addItem("Tenis");
        comboBox.addItem("Ajedrez");
        comboBox.addItem("Ninguno");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        jPanel4.add(scrollPane,c);

        JButton button1 = new JButton("Anadir Fila");
        JButton button2 = new JButton("Borrar Fila");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 1;
        c.weighty = 0;
        new Insets(5, 5, 5, 5);
        jPanel4.add(button1,c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        jPanel4.add(button2,c);
    }


    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name",
                "Apellidos",
                "Edad",
                "Deporte",
                "Vegetariano"};
        private Object[][] data = {
                {"Andres", "Garcia Hacia",
                        new Integer(28), "Basket", new Boolean(false)},
                {"Alfonso", "Garcia Hacia",
                        new Integer(34), "Futbol", new Boolean(true)},
                {"Aledano", "Hace dano",
                        new Integer(28), "Ajedrez", new Boolean(true)},
                {"Sponge", "Bob Squarepants",
                        new Integer(28), "Futbol", new Boolean(false)},
        };

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
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
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

    private static void createAndShowGUI() {
        //Create and set up the window.
        new Tables();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
