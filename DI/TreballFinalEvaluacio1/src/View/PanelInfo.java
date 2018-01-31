package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelInfo extends JPanel {

    public PanelInfo(){
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel jPanelInfoParrots = new JPanel();
        JPanel jPanelInfoOwners  = new JPanel();
        JPanel jPanelDrawer      = new JPanel();

        // place the JPanels on the main Layout

        //parrots
        c.gridx     = 0;
        c.gridy     = 0;
        c.gridheight= 1;
        c.gridwidth = 1;
        c.fill      = GridBagConstraints.BOTH;
        c.weightx   = 0.5;
        add(jPanelInfoParrots, c);

        //owners
        c.gridx     = 1;
        c.gridy     = 0;
        c.gridheight= 1;
        c.gridwidth = 1;
        c.fill      = GridBagConstraints.BOTH;
        c.weightx   = 0.5;
        add(jPanelInfoOwners, c);

        //drawer
        c.gridx     = 0;
        c.gridy     = 1;
        c.gridheight= 1;
        c.gridwidth = 2;
        c.fill      = GridBagConstraints.BOTH;
        c.anchor    = GridBagConstraints.NORTHEAST;
        // to add lateral space when resize
        c.weightx   = 1;
        add(jPanelDrawer, c);

        drawInfoParrots(jPanelInfoParrots);
        drawInfoOwners(jPanelInfoOwners);
        drawDrawer(jPanelDrawer);

    }

    private void drawInfoParrots(JPanel jPanelInfoParrots){
        JPanel jPanelForm    = new JPanel();
        JPanel jPanelButtons = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

//        jPanelForm.setBorder(
//                BorderFactory.createTitledBorder(
//                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
//                        "Loros")
//        );

        jPanelInfoParrots.setLayout(new GridBagLayout());

        //form
        c.gridx     = 0;
        c.gridy     = 0;
        c.gridheight= 1;
        c.gridwidth = 1;
        c.fill      = GridBagConstraints.BOTH;
        c.weightx   = 0.5;
        add(jPanelForm, c);
        drawFormParrots(jPanelForm);

        //buttons
        c.gridx     = 0;
        c.gridy     = 1;
        c.gridheight= 1;
        c.gridwidth = 1;
        c.fill      = GridBagConstraints.BOTH;
        c.weightx   = 0.5;
        add(jPanelButtons, c);
        drawButtonsParrots(jPanelButtons);


    }

    private void drawInfoOwners(JPanel jPanelInfoOwners){
        JPanel jPanelForm       = new JPanel();
        JPanel jPanelCoordenates= new JPanel();
        GridBagConstraints c    = new GridBagConstraints();

//        jPanelForm.setBorder(
//                BorderFactory.createTitledBorder(
//                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
//                        "Propietarios")
//        );

        jPanelInfoOwners.setLayout(new GridBagLayout());

        //form
        c.gridx     = 0;
        c.gridy     = 0;
        c.gridheight= 1;
        c.gridwidth = 1;
        c.fill      = GridBagConstraints.BOTH;
        c.weightx   = 0.5;
        add(jPanelForm, c);
        drawFormOwners(jPanelForm);


        //coordenates
        c.gridx     = 0;
        c.gridy     = 1;
        c.gridheight= 1;
        c.gridwidth = 1;
        c.fill      = GridBagConstraints.BOTH;
        c.weightx   = 0.5;
        add(jPanelCoordenates, c);
        drawCoordenates(jPanelCoordenates);
    }

}
