package View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInfo extends JPanel {

    private JTextField parrotId;
    private JTextField parrotName;
    private JTextField parrotRace;
    private JTextField parrotColor;
    private JTextField parrotOwner;

    private JTextField ownerId;
    private JTextField ownerName;
    private JTextField ownerLastName;
    private JTextField ownerPhone;

    private JTextField aDrawer;
    private JTextField bDrawer;
    private JTextField cDrawer;
    private JTextField dDrawer;

    private JButton firstButton;
    private JButton lastButton;
    private JButton previousButton;
    private JButton nextButton;

    private JButton drawLineButton;
    private JButton drawCircleButton;

    public PanelInfo() {
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel jPanelInfoParrots = new JPanel();
        JPanel jPanelInfoOwners = new JPanel();
        JPanel jPanelDrawer = new JPanel();

        // place the JPanels on the main Layout
        //parrots
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        this.add(jPanelInfoParrots, c);

        //owners
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        this.add(jPanelInfoOwners, c);

        //drawer
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.VERTICAL;
        // to add lateral space when resize
//        c.weightx = 1;
        c.weighty = 1;
        this.add(jPanelDrawer, c);

        drawInfoParrots(jPanelInfoParrots);
        drawInfoOwners(jPanelInfoOwners);
        drawDrawer(jPanelDrawer);
    }

    private void drawInfoParrots(JPanel jPanelInfoParrots) {
        JPanel jPanelForm = new JPanel();
        JPanel jPanelButtons = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        jPanelInfoParrots.setLayout(new GridBagLayout());

        //form
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        jPanelInfoParrots.add(jPanelForm, c);
        drawFormParrots(jPanelForm);

        //buttons
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        jPanelInfoParrots.add(jPanelButtons, c);
        drawButtonsParrots(jPanelButtons);
    }

    private void drawInfoOwners(JPanel jPanelInfoOwners) {
        JPanel jPanelForm = new JPanel();
        JPanel jPanelButtonsCoordenates = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        jPanelInfoOwners.setLayout(new GridBagLayout());

        //form
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        jPanelInfoOwners.add(jPanelForm, c);
        drawFormOwners(jPanelForm);

        //coordenates
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        jPanelInfoOwners.add(jPanelButtonsCoordenates, c);
        drawButtonsCoordenates(jPanelButtonsCoordenates);
    }

    private void drawFormParrots(JPanel jPanelForm) {

        jPanelForm.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                        "Loros")
        );
        jPanelForm.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("id"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        parrotId = new JTextField();
        jPanelForm.add(parrotId, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Nom"), c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        parrotName = new JTextField();
        jPanelForm.add(parrotName, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Raza"), c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        parrotRace = new JTextField();
        jPanelForm.add(parrotRace, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Color"), c);
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        parrotColor = new JTextField();
        jPanelForm.add(parrotColor, c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Propietari"), c);
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        parrotOwner = new JTextField();
        jPanelForm.add(parrotOwner, c);
    }

    private void drawButtonsParrots(JPanel jPanelButtons){
        jPanelButtons.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        firstButton = new JButton("<<");
        firstButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        fistButtonEvent();
                    }
                }
        );
        jPanelButtons.add(firstButton, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        previousButton = new JButton("<");
        previousButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        previousButtonEvent();
                    }
                }
        );
        jPanelButtons.add(previousButton, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        nextButton = new JButton(">");
        nextButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        nextButtonEvent();
                    }
                }
        );
        jPanelButtons.add(nextButton, c);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        lastButton = new JButton(">>");
        lastButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        fistButtonEvent();
                    }
                }
        );
        jPanelButtons.add(lastButton, c);
    }

    private void drawFormOwners(JPanel jPanelForm){
        jPanelForm.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                        "Propietarios")
        );
        jPanelForm.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("id"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        ownerId = new JTextField();
        jPanelForm.add(ownerId, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Nom"), c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        ownerName = new JTextField();
        jPanelForm.add(ownerName, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Cognoms"), c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        ownerLastName = new JTextField();
        jPanelForm.add(ownerLastName, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        jPanelForm.add(new JLabel("Telefon"), c);
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        ownerPhone = new JTextField();
        jPanelForm.add(ownerPhone, c);
    }

    private void drawButtonsCoordenates(JPanel jPanelButtonsCoordenates){
        jPanelButtonsCoordenates.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        drawLineButton = new JButton("Dibuixar recta");
        drawLineButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        drawLineEvent();
                    }
                }
        );
        jPanelButtonsCoordenates.add(drawLineButton, c);
        c.gridx = 5;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        drawCircleButton = new JButton("Dibuixar cercle");
        drawCircleButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        drawCercleEvent();
                    }
                }
        );
        jPanelButtonsCoordenates.add(drawCircleButton, c);

//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        jPanelButtonsCoordenates.add(new JLabel("a: "), c);
//        c.gridx = 1;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        aDrawer = new JTextField();
//        jPanelButtonsCoordenates.add(aDrawer, c);
//
//        c.gridx = 2;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        jPanelButtonsCoordenates.add(new JLabel("b: "), c);
//        c.gridx = 3;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        bDrawer = new JTextField();
//        jPanelButtonsCoordenates.add(bDrawer, c);
//
//        c.gridx = 4;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        jPanelButtonsCoordenates.add(new JLabel("c: "), c);
//        c.gridx = 5;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        cDrawer = new JTextField();
//        jPanelButtonsCoordenates.add(cDrawer, c);
//
//        c.gridx = 6;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        jPanelButtonsCoordenates.add(new JLabel("d: "), c);
//        c.gridx = 7;
//        c.gridy = 1;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        dDrawer = new JTextField();
//        jPanelButtonsCoordenates.add(dDrawer, c);
    }

    private void drawDrawer(JPanel jPanelDrawer){

    }

}
