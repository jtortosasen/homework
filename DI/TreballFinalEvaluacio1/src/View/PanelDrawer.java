package View;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

class PanelDrawer extends JPanel {

    private class Pointers{
        public int a, b, c, d;
        public Pointers(int a, int b, int c, int d){
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
    ArrayList<Pointers> lines = new ArrayList<>();
    ArrayList<Pointers> circles = new ArrayList<>();
    boolean linesBoolean = false;
    boolean circlesBoolean = false;


    void drawLine(int a, int b, int c, int d) {
        lines.add(new Pointers(a,b,c,d));
        linesBoolean = true;
    }

    void drawCircle(int a, int b, int c, int d) {
        circles.add(new Pointers(a,b,c,d));
        circlesBoolean = true;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(linesBoolean){
            for(Pointers line : lines){
                g.drawLine(line.a,line.b,line.c,line.d);
            }
        }
        if(circlesBoolean){
            for(Pointers circle : circles){
                g.drawOval(circle.a,circle.b,circle.c,circle.d);
            }
        }

    }



}