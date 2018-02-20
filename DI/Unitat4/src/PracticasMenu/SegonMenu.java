package PracticasMenu;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

public class SegonMenu extends JFrame{

    private JMenuBar menuBar;
    private JMenu menu1, menu2, subMenu;
    private JMenuItem menuItem1, menuItem2, menuItem3, subMenuItem1,subMenuItem2;
    private JCheckBoxMenuItem checkBoxMenuItem1, checkBoxMenuItem2;
    private JRadioButtonMenuItem radioButtonMenuItem1, radioButtonMenuItem2;

    public SegonMenu() {

        super("Ejemplo de uso de menus y barras de menus");

        menuBar = new JMenuBar();

        menu1 = new JMenu("Un menu");
        menu2 = new JMenu("Otro Menu");
        subMenu = new JMenu("Un submenu");
        menuBar.add(menu1);
        menuBar.add(menu2);

        menuItem1 = new JMenuItem("Menu de solo texto");
        menuItem2 = new JMenuItem("texto con imagen", new ImageIcon(getClass().getResource("psyduck.png")));
        menuItem3 = new JMenuItem("", new ImageIcon(getClass().getResource("psyduck.png")));
        radioButtonMenuItem1 = new JRadioButtonMenuItem("Ejemplo radio button");
        radioButtonMenuItem2 = new JRadioButtonMenuItem("Otro radio button");
        checkBoxMenuItem1 = new JCheckBoxMenuItem("Ejemplo checkbox menu");
        checkBoxMenuItem2 = new JCheckBoxMenuItem("Otro checkbox menu");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menu1.add(radioButtonMenuItem1);
        menu1.add(radioButtonMenuItem2);
        menu1.add(checkBoxMenuItem1);
        menu1.add(checkBoxMenuItem2);
        menu1.add(subMenu);

        subMenuItem1 = new JMenuItem("Submenu 1");
        subMenuItem2 = new JMenuItem("Submenu 2");
        subMenu.add(subMenuItem1);
        subMenu.add(subMenuItem2);


        setJMenuBar(menuBar);
        setSize(250, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        new SegonMenu();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
