/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema1;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author wasdf
 */
public class Gui01 extends JFrame{
    private Container panel;
    private JButton miboton;
    
    public Gui01(){
        super("EJemplo 01 con boton");
        miboton = new JButton("Aceptar");
        panel = getContentPane();
        panel.add(miboton);
        
        setSize(200,100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String args[]){
        Gui01 aplication = new Gui01();
    }
    
}
