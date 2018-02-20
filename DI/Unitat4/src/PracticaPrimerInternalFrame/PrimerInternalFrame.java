package PracticaPrimerInternalFrame;

import java.awt.FlowLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PrimerInternalFrame extends JFrame{

    public PrimerInternalFrame(){
        super("Primer internal frame");
        JDesktopPane dp = new JDesktopPane();


        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(new JLabel("Una etiqueta"));
        p.add(new JTextField(10));
        JInternalFrame internalFrame = new JInternalFrame("Un Internal Frame");
        internalFrame.add(p);
        internalFrame.pack();
        internalFrame.setResizable(true);
        internalFrame.setClosable(true);
        internalFrame.setVisible(true);
        dp.add(internalFrame);


        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(new JLabel("Una etiqueta2"));
        p2.add(new JTextField(10));
        JInternalFrame internalFrame2 = new JInternalFrame("Un Internal Frame");
        internalFrame2.add(p2);
        internalFrame2.pack();
        internalFrame2.setResizable(true);
        internalFrame2.setClosable(true);
        internalFrame2.setVisible(true);
        dp.add(internalFrame2);

        getContentPane().add(dp);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PrimerInternalFrame();
    }

}
