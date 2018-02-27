package practicaPanellsEmergents;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelDatos extends JPanel {

    public String getCampoNP() {
        return campoNP.getText();
    }

    public String getCampoApellidos() {
        return campoApellidos.getText();
    }

    public String getCampoNombre() {
        return campoNombre.getText();
    }

    public ButtonGroup getGrupoBotones() {
        return grupoBotones;
    }

    private JTextField campoNP;
    private JTextField campoApellidos;
    private JTextField campoNombre;
    private ButtonGroup grupoBotones;
    private JRadioButton manana;
    private JRadioButton tarde;


    public PanelDatos(){
        super();
        setLayout(new GridLayout(4,2));
        JLabel etiquetaNP = new JLabel("Numero Personal: ", JLabel.RIGHT);
        campoNP = new JTextField();
        add(etiquetaNP);
        add(campoNP);
        JLabel etiquetaApellidos = new JLabel("Apellidos: ", JLabel.RIGHT);
        campoApellidos = new JTextField();
        add(etiquetaApellidos);
        add(campoApellidos);
        JLabel etiquetaNombre = new JLabel("Nombre: ", JLabel.RIGHT);
        campoNombre = new JTextField();
        add(etiquetaNombre);
        add(campoNombre);
        grupoBotones = new ButtonGroup();
        manana = new JRadioButton("Grupo manana");
        tarde = new JRadioButton("Grupo tarde");
        manana.setActionCommand("manana");
        tarde.setActionCommand("tarde");
        grupoBotones.add(tarde);
        grupoBotones.add(manana);
        add(tarde);
        add(manana);
    }


}
