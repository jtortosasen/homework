package Tema1;

import javax.swing.*;
import java.awt.*;

class MiPanel2 extends JPanel {




    public void paintComponent(Graphics g){

        int windowSizeY =  getBounds().height;
        int windowSizeX =  getBounds().width;
        int baseLineY = (int) (windowSizeY - (windowSizeY * 0.15));

        int posLinePercentX = (int) (windowSizeX*0.2);
        int posStartLine1Y = baseLineY-170;             // 40 coches - 170px
        int posStartLine2Y = baseLineY - (170*14)/40;   // 14 coches
        int posStartLine3Y = baseLineY- (170*80)/40;    // 80 coches

        int rectSize = 10;



        g.setColor(new Color(0,0,0));
        g.drawLine(0,baseLineY, windowSizeX,baseLineY);
        g.setColor(new Color(255,30,0));
//        g.drawLine(posLinePercentX,posStartLine1Y,posLinePercentX,baseLineY);
        g.fillRect(posLinePercentX,posStartLine1Y,rectSize,baseLineY - posStartLine1Y);
        g.setColor(new Color(20,255,30));
//        g.drawLine(posLinePercentX*2,posStartLine2Y,posLinePercentX*2,baseLineY);
        g.fillRect(posLinePercentX*2,posStartLine2Y,rectSize,baseLineY - posStartLine2Y);
        g.setColor(new Color(0,30,255));
//        g.drawLine(posLinePercentX*3,posStartLine3Y,posLinePercentX*3,baseLineY);
        g.fillRect(posLinePercentX*3,posStartLine3Y,rectSize,baseLineY - posStartLine3Y);
    }
}


public class GraficEstadistic2 extends JFrame {
    public GraficEstadistic2(){
        super("Grafic Estadistic 1");
        add(new MiPanel2());
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GraficEstadistic2 graficEstadistic2 = new GraficEstadistic2();
    }
}