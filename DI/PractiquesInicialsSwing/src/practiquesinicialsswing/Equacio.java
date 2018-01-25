package practiquesinicialsswing;

import javax.swing.*;
import java.awt.*;

class MiPanel3 extends JPanel {


    public void paintComponent(Graphics g) {

        int windowSizeY = getBounds().height;
        int windowSizeX = getBounds().width;

    }
}

public class Equacio extends JFrame {
    public Equacio() {
        super("Equacio");
        add(new MiPanel2());
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Equacio equacio = new Equacio();
    }
}
