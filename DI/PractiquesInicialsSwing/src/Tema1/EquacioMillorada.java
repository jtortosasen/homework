package Tema1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class PanelImprovedEquation extends JPanel{

    int a, b, c;

    public void setA(int a){
        this.a = a;
    }

    public void setB(int b){
        this.b = b;
    }
    public void setC(int c){
        this.c = c;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int windowSizeY = getBounds().height;
        int windowSizeX = getBounds().width;

        int centerAxisX = windowSizeX/2;
        int centerAxisY = windowSizeY/2;

        g.setColor(new Color(0,0,0));
        g.drawLine(centerAxisX,centerAxisY,centerAxisX,0);
        g.drawLine(centerAxisX,centerAxisY,0,centerAxisY);
        g.drawLine(centerAxisX,centerAxisY,centerAxisX,centerAxisY*2);
        g.drawLine(centerAxisX,centerAxisY,centerAxisX*2,centerAxisY);



//        parabola y = ax^2 + bx + c
        g.setColor(Color.BLUE);
        for(int i = 0; i<300; i++){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - ( a * (int)Math.pow(i,2) + (b * i) + c);
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - ( a * (int)Math.pow(i+1,2) + (b * i+1) + c);
            g.drawLine(x1,y1,x2,y2);
        }

        for(int i = 0; i>-300; i--){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - ( a * (int)Math.pow(i,2) + (b * i) + c);
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - ( a * (int)Math.pow(i+1,2) + (b * i+1) + c);
            g.drawLine(x1,y1,x2,y2);
        }

    }
}

public class EquacioMillorada extends JFrame{

    private JTextField text1, text2, text3;
    private JButton button1;
    private PanelImprovedEquation panelImprovedEquation;

    public EquacioMillorada(){
        super("Equacio Millorada");
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel label1 = new JLabel("a: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        this.getContentPane().add(label1, constraints);

        text1 = new JTextField("0");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(text1, constraints);

        JLabel label2 = new JLabel("b: ");
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.NONE;
        this.getContentPane().add(label2, constraints);

        text2 = new JTextField("0");
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(text2, constraints);

        JLabel label3 = new JLabel("c: ");
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.NONE;
        this.getContentPane().add(label3, constraints);

        text3 = new JTextField("0");
        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(text3, constraints);

        button1 = new JButton("Calcular");
        constraints.gridx = 6;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.NONE;
        this.getContentPane().add(button1, constraints);

        panelImprovedEquation = new PanelImprovedEquation();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        this.getContentPane().add(panelImprovedEquation, constraints);

        button1.addActionListener(new ListenBUtton());
        setSize(500,500);
        setVisible(true);

    }


    class ListenBUtton implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            int a, b, c;
            a = Integer.parseInt(text1.getText());
            b = Integer.parseInt(text2.getText());
            c = Integer.parseInt(text3.getText());

            panelImprovedEquation.setA(a);
            panelImprovedEquation.setB(b);
            panelImprovedEquation.setC(c);
        }

    }

    public static void main(String[] args) {
        EquacioMillorada equacioMillorada = new EquacioMillorada();
    }
}

