/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Utils.Posicio;
import Especies.Habitant;
import Especies.Avatar;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import pandorum_jg.*;

/**
 *
 * @author Joan Gerard Camarena
 */
public class PandorumVista extends JFrame {

    private JLabel[][] quadres;
    private ImageIcon iconaAvatarMascle;
    private ImageIcon iconaAvatarFemella;
    private ImageIcon iconaHumaMascle;
    private ImageIcon iconaHumaFemella;

    private int humans, avatars;
    private final int TAM;

    private Planeta elPlaneta;

    public PandorumVista(int av, int hum) throws InterruptedException {
        // System.out.println(getRootPane();
        TAM = Pandorum_JG.TAM;
        quadres = new JLabel[TAM][TAM];

        iconaAvatarMascle = new ImageIcon(getClass().getResource("AV_HOM.png"));
        iconaAvatarFemella = new ImageIcon(getClass().getResource("AV_FEM.png"));
        iconaHumaMascle = new ImageIcon(getClass().getResource("HU_HOM.png"));
        iconaHumaFemella = new ImageIcon(getClass().getResource("HU_FEM.png"));

        /* 
        iconaAvatarMascle =new ImageIcon(getClass().getResource("satelite.jpg")).getImage().getScaledInstance(80, 80, 1));
        iconaAvatarMascle = new ImageIcon(new ImageIcon("AV_HOM.png").getImage().getScaledInstance(80, 80, 1));
        iconaHumaMascle = new ImageIcon(new ImageIcon("HU_HOM.png").getImage().getScaledInstance(80, 80, 1));
        iconaHumaFemella = new ImageIcon(new ImageIcon("HU_FEM.png").getImage().getScaledInstance(80, 80, 1));
         */
        humans = hum;
        avatars = av;

        setLayout(new GridLayout(TAM, TAM));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                quadres[i][j] = new JLabel("");
                quadres[i][j].setSize(80, 80);
                quadres[i][j].setBackground(Color.GREEN);
                quadres[i][j].setBorder(((Border) BasicBorders.getButtonBorder()));
                add(quadres[i][j]);
            }
        }

        setVisible(true);

        elPlaneta = Planeta.getPlaneta();
        setTitle("Creant vida...");
        Thread.sleep(1000);
        
        elPlaneta.omplirPlaneta(avatars, humans);
        refrescarPlaneta();
        setTitle("Iniciant vida...");
        Thread.sleep(300);
        
        
        elPlaneta.iniciarPlaneta();
        setTitle("Vivint... ");
        
        //durant 10 segons
        long fi = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < fi) {
            int q = refrescarPlaneta();
            setTitle("Vivint amb " + q + " habitants.");
            Thread.sleep(500);
        }
        
        // aturem totes les criatures
        elPlaneta.aturarPlaneta();
        setTitle("Vida aturada...Pot tancar el planeta");

    }

    private int refrescarPlaneta() {
        int quants = 0;
        elPlaneta.setMenejant_se(false);
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                Posicio p = new Posicio(i, j);
                Habitant h = elPlaneta.getHabitantAt(p);
                if (h == null) {
                    quadres[i][j].setIcon(null);
                    continue;
                }
                quants++;
                if (h instanceof Avatar) {
                    if (h.getSexe() == 'H') {
                        quadres[i][j].setIcon(iconaAvatarMascle);
                    } else {
                        quadres[i][j].setIcon(iconaAvatarFemella);
                    }
                } else if (h.getSexe() == 'H') {
                    quadres[i][j].setIcon(iconaHumaMascle);
                } else {
                    quadres[i][j].setIcon(iconaHumaFemella);
                }
            }
        }
        elPlaneta.setMenejant_se(true);
        return quants;
    }
}
