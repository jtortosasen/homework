/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author wasdf
 */
public class Gui04 extends JFrame{
    
    public Gui04(){
        setTitle("Horno microondas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel p1 = new JPanel();
        p1.setLayout( new GridLayout(4,3));
        
        for(int i = 1; i <= 9; i++){
            p1.add(new JButton("" + i));
        }
        p1.add(new JButton("" + 0));
        p1.add(new JButton("Start"));
        p1.add(new JButton("Stop"));
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(new JTextField("Aquí el tiempo"),BorderLayout.NORTH);
        p2.add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.EAST);
        add(new Button("Aquí la comida"),BorderLayout.CENTER);
        
        setSize(400,250);
        setVisible(true);
    }
    
    public static void main(String[] args){
        Gui04 frame = new Gui04();
    }
}
