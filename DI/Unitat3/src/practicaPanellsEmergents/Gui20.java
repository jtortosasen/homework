package practicaPanellsEmergents;

import java.awt.FlowLayout;

import javax.swing.*;

public class Gui20 extends JFrame{
    JTextField textField;

    public Gui20(){
        super("Panells emergents");
        setLayout(new FlowLayout());
        textField = new JTextField();
        add(textField);
        PanelDatos pd = new PanelDatos();
        PanelDatos pd2 = new PanelDatos();
        add(pd2);

        if(JOptionPane.showConfirmDialog(this,pd,"Introduzca datos",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION){
            String tumadre = pd.getGrupoBotones().getSelection().getActionCommand();
            String result = pd.getCampoNP() + ", " + pd.getCampoNombre() + " " + pd.getCampoApellidos() + " " + tumadre;
            textField.setText(result);
        }




        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Gui20();
    }
}
