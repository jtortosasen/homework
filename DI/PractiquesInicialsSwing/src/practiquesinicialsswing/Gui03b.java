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
public class Gui03b extends JFrame{
    
    public Gui03b(){
        super("Ejemplo de Layout");
        
        setLayout(new GridLayout(4,3,5,5));
        for(int i = 1; i<=10; i++){
            add(new JButton(Integer.toString(i)));
        }
        
        setSize(200,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }
    
    public static void main(String args[]){
        Gui03b gui = new Gui03b();
    }
}
