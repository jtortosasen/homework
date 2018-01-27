package practiquesinicialsswing;

import javax.swing.*;
import java.awt.*;

class MiPanel3 extends JPanel {


    public void paintComponent(Graphics g) {


        int windowSizeY = getBounds().height;
        int windowSizeX = getBounds().width;

        int centerAxisX = windowSizeX/2;
        int centerAxisY = windowSizeY/2;

        g.setColor(new Color(0,0,0));
        g.drawLine(centerAxisX,centerAxisY,centerAxisX,0);
        g.drawLine(centerAxisX,centerAxisY,0,centerAxisY);
        g.drawLine(centerAxisX,centerAxisY,centerAxisX,centerAxisY*2);
        g.drawLine(centerAxisX,centerAxisY,centerAxisX*2,centerAxisY);



//        parabola 1 y=x^2
        g.setColor(Color.BLUE);
        for(int i = 0; i<20; i++){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - (int)Math.pow(i,2);
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - (int)Math.pow(i+1,2);
            g.drawLine(x1,y1,x2,y2);
        }

        for(int i = 0; i>-50; i--){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - (int)Math.pow(i,2);
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - (int)Math.pow(i+1,2);
            g.drawLine(x1,y1,x2,y2);
        }

//        parabola2 y=x^2 + 2*x
        g.setColor(Color.GREEN);
        for(int i = 0; i<20; i++){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - ((int) Math.pow(i,2) + (4*i));
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - ((int)Math.pow(i+1,2) + (4 * i+1));
            g.drawLine(x1,y1,x2,y2);
        }

        for(int i = 0; i>-50; i--){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - ((int) Math.pow(i,2) + (4*i));
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - ((int)Math.pow(i+1,2) + (4 * i+1));
            g.drawLine(x1,y1,x2,y2);
        }

//        parabola2 y = 4 - x^2
        g.setColor(Color.RED);
        for(int i = 0; i<20; i++){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - (4 - (int) Math.pow(i,2));
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - (4 - (int)Math.pow(i+1,2));
            g.drawLine(x1,y1,x2,y2);
        }

        for(int i = 0; i>-50; i--){
            int x1,y1;
            int x2,y2;
            x1 = centerAxisX + i;
            y1 = centerAxisY - (4 - (int) Math.pow(i,2));
            x2 = centerAxisX + (i+1);
            y2 = centerAxisY - (4 - (int)Math.pow(i+1,2));
            g.drawLine(x1,y1,x2,y2);
        }


    }
}

public class Equacio extends JFrame {
    public Equacio() {
        super("Equacio");
        add(new MiPanel3());
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Equacio equacio = new Equacio();
    }
}
