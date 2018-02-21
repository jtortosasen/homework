package PracticaPrimerInternalFrame;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrimerInternalFrame extends JFrame{

    JDesktopPane dp;
    private JPopupMenu popupMenu;
    private JMenuItem popupMenuItem1, popupMenuItem2;
    private boolean windowOpen1 = false, windowOpen2 = false;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem option1MenuItem, exitMenuItem, showGridMenuItem, a4MenuItem, a3MenuItem, aboutMenuItem;

    public PrimerInternalFrame(){
        super("Primer internal frame");
        dp = new JDesktopPane();
        menuBar = new JMenuBar();
        popupMenu = new JPopupMenu();
        popupMenuItem1 = new JMenuItem("Opcio1");
        popupMenuItem2 = new JMenuItem("Opcio2");
        popupMenu.add(popupMenuItem1);
        popupMenu.add(popupMenuItem2);
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
        popupMenuItem1.addActionListener(e -> showPanel1());
        popupMenuItem2.addActionListener(e -> showPanel2());
        setJMenuBar(menuBar);
        getContentPane().add(dp);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setVisible(true);
    }

    private void showPanel1(){
        if(!windowOpen1){
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout());
            p.add(new JLabel("Una etiqueta"));
            p.add(new JTextField(10));
            JInternalFrame internalFrame = new JInternalFrame("Un Internal Frame");
            internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
                                public void internalFrameClosed(InternalFrameEvent e) {
                                    windowOpen1 = false;
                                    menuBar.remove(fileMenu);
                                    menuBar.remove(editMenu);
                                    menuBar.remove(helpMenu);
                                    repaint();
                                }
                                public void internalFrameActivated(InternalFrameEvent e){
                                    fileMenu.setVisible(true);
                                    editMenu.setVisible(true);
                                    helpMenu.setVisible(true);
                                    repaint();
                                }
                                public void internalFrameDeactivated(InternalFrameEvent e){
                                    fileMenu.setVisible(false);
                                    editMenu.setVisible(false);
                                    helpMenu.setVisible(false);
                                    repaint();
                                }
                            });
            internalFrame.add(p);
            internalFrame.pack();
            internalFrame.setResizable(true);
            internalFrame.setClosable(true);
            internalFrame.setVisible(true);
            dp.add(internalFrame);
            showMenuBar();
            windowOpen1 = true;
        }
    }

    private void showMenuBar(){
        fileMenu = new JMenu("Arxiu");
        editMenu = new JMenu("Editar");
        helpMenu = new JMenu("Ajuda");

        option1MenuItem = new JMenuItem("Option1");
        exitMenuItem = new JMenuItem("Eixir");

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
    }

    private void showPanel2(){
        if(!windowOpen2){
            JPanel p2 = new JPanel();
            p2.setLayout(new FlowLayout());
            p2.add(new JLabel("Una etiqueta2"));
            p2.add(new JTextField(10));
            JInternalFrame internalFrame2 = new JInternalFrame("Un Internal Frame");
            internalFrame2.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameClosed(InternalFrameEvent e) {
                    windowOpen2 = false;
                }
            });
            internalFrame2.add(p2);
            internalFrame2.pack();
            internalFrame2.setResizable(true);
            internalFrame2.setClosable(true);
            internalFrame2.setVisible(true);
            dp.add(internalFrame2);
            windowOpen2 = true;
        }

    }



    public static void main(String[] args) {
        new PrimerInternalFrame();
    }

}
