/*
 * Importo todo lo necesario para empezar
 */
package PracticasMenu;

import java.awt.CheckboxMenuItem;
import java.awt.event.*;

import javax.swing.*;


public class PrimerMenu extends JFrame {


    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem option1MenuItem, exitMenuItem, showGridMenuItem, a4MenuItem, a3MenuItem, aboutMenuItem;

    public PrimerMenu() {

        super("Ejemplo JMenuBar");

        menuBar = new JMenuBar();

        fileMenu = new JMenu("Arxiu");
        editMenu = new JMenu("Editar");
        helpMenu = new JMenu("Ajuda");

        option1MenuItem = new JMenuItem("Option1");
        exitMenuItem = new JMenuItem("Eixir");
        exitMenuItem.addActionListener(e -> exitMenuEvent());

        showGridMenuItem = new JMenuItem("Mostrar quadricula");
        a4MenuItem = new JMenuItem("A4");

        a3MenuItem = new JMenuItem("A3");
        aboutMenuItem = new JMenuItem("Acerca de...");

        menuBar.add(fileMenu);
        fileMenu.add(option1MenuItem);
        fileMenu.add(exitMenuItem);
        fileMenu.add(showGridMenuItem);
        fileMenu.add(a4MenuItem);
        fileMenu.add(a3MenuItem);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        helpMenu.add(aboutMenuItem);

        setJMenuBar(menuBar);
        setSize(250, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void exitMenuEvent(){
        System.exit(0);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        new PrimerMenu();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}