package PracticasMenu;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class ProvaMenus extends JFrame{
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem option1MenuItem, exitMenuItem, showGridMenuItem, a4MenuItem, a3MenuItem, aboutMenuItem, popupMenuItem1, popupMenuItem2;
    private JToolBar toolBar;
    private JButton buttonToolbar1, buttonToolbar2,buttonToolbar3;
    private JPopupMenu popupMenu;
    private JCheckBoxMenuItem checkBoxMenuItem1, checkBoxMenuItem2;



    public ProvaMenus() {

        super("Ejemplo JMenuBar");

        menuBar = new JMenuBar();
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        popupMenu = new JPopupMenu();

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

        buttonToolbar1 = new JButton(new ImageIcon(getClass().getResource("psyduck.png")));
        buttonToolbar2 = new JButton("text", new ImageIcon(getClass().getResource("psyduck.png")));
        buttonToolbar3 = new JButton("text");

        popupMenuItem1 = new JMenuItem("Opcio1",new ImageIcon(getClass().getResource("psyduck.png")));
        popupMenuItem2 = new JMenuItem("Nomes Text");
        checkBoxMenuItem1 = new JCheckBoxMenuItem("Check 1");
        checkBoxMenuItem2 = new JCheckBoxMenuItem("Check 2", new ImageIcon(getClass().getResource("psyduck.png")));


        menuBar.add(fileMenu);
        fileMenu.add(option1MenuItem);
        fileMenu.add(exitMenuItem);
        fileMenu.add(showGridMenuItem);
        fileMenu.add(a4MenuItem);
        fileMenu.add(a3MenuItem);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        helpMenu.add(aboutMenuItem);

        toolBar.add(buttonToolbar1);
        toolBar.add(buttonToolbar2);
        toolBar.add(buttonToolbar3);

        popupMenu.add(popupMenuItem1);
        popupMenu.add(popupMenuItem2);
        popupMenu.add(checkBoxMenuItem1);
        popupMenu.add(checkBoxMenuItem2);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(),
                            e.getX(), e.getY());
                }
            }
        });

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.PAGE_START);

        setSize(250, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void exitMenuEvent(){
        System.exit(0);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        new ProvaMenus();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
