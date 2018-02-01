package View;

import javax.swing.*;
import java.awt.*;

class PanelDrawer extends JPanel {

    private int a, b, c, d;
    private boolean line = false, circle = false;

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setD(int d) {
        this.d = d;
    }
    public void drawLine(int a, int b, int c, int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        circle = false;
        line = true;
    }
    public void drawCircle(int a, int b, int c, int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        line = false;
        circle = true;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(line)
            g.drawLine(a,b,c,d);
        if(circle)
            g.drawOval(a,b,c,d);
    }

}