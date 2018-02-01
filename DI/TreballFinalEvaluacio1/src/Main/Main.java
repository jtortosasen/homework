package Main;

import javax.swing.*;

import Controller.Controller;
import View.PanelInfo;
import Model.ModelParrots;

public class Main {

    private static final String WINDOW_TITLE = "Treball Final eva";
    private static final int WIDTH_WINDOW = 700;
    private static final int HEIGHT_WINDOW = 500;


    public static void main(String[] args) {

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        PanelInfo panelInfo = new PanelInfo();
        ModelParrots modelParrots = new ModelParrots();
        new Controller(modelParrots,panelInfo);

        JFrame jFrame = new JFrame(WINDOW_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(panelInfo);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        jFrame.setVisible(true);
    }
}
