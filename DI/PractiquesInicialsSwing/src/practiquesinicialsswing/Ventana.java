/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practiquesinicialsswing;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author wasdf
 */
public class Ventana extends JFrame {

    public Ventana() {
        super("Ejemplo 1");
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JTextArea cajaTexto = new JTextArea("Area texto");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        this.getContentPane().add(cajaTexto, constraints);
        constraints.weighty = 0.0;
        
        JButton boton1 = new JButton ("Boton 1");
        constraints.gridx = 2;
        constraints.gridwidth = 0;
        constraints.gridheight = 1;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        this.getContentPane().add(boton1, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weighty = 0.0;

        JButton boton2 = new JButton("Boton 2");
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        this.getContentPane().add(boton2, constraints);
        constraints.weighty = 0.0;
        constraints.anchor = GridBagConstraints.CENTER;

        JButton boton3 = new JButton("Boton 3");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(boton3, constraints);

        JButton boton4 = new JButton("Boton 4");
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.getContentPane().add(boton4, constraints);

        JTextField campoTexto = new JTextField("Campo Texto");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(campoTexto, constraints);
        setSize(400,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
    }
}
