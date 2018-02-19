package PracticaPanellTabullat;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class PanellTabullat extends JFrame {

    public PanellTabullat(){
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel jPanel1 = new JPanel();
        JLabel labelName = new JLabel("Nom i Cognoms");
        JLabel labelEmail = new JLabel("e-mail");
        JLabel labelPhone = new JLabel("Telèfon");
        JLabel labelUrl = new JLabel("URL");
        JTextField textName = new JTextField();
        JTextField textEmail = new JTextField();
        JTextField textPhone = new JTextField();
        JTextField textUrl = new JTextField();
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        jPanel1.add(labelName,c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        jPanel1.add(textName,c);


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        jPanel1.add(labelEmail,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        jPanel1.add(textEmail,c);


        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        jPanel1.add(labelPhone,c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        jPanel1.add(textPhone,c);


        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        jPanel1.add(labelUrl,c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        jPanel1.add(textUrl,c);

        tabbedPane.addTab("Dades personals", jPanel1);
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridBagLayout());


        JPanel panelAmbit = new JPanel();
        JLabel labelStreet = new JLabel("Adreca 1");
        JTextField textStreet = new JTextField();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        jPanel2.add(labelStreet,c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        jPanel2.add(textStreet,c);


        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        jPanel2.add(panelAmbit,c);

        panelAmbit.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                        "Ambit")
        );
        panelAmbit.setLayout(new GridLayout(2,1));
        panelAmbit.add(new JRadioButton("Local"));
        panelAmbit.add(new JRadioButton("Automatic"));

        tabbedPane.addTab("Adreces", jPanel2);
        getContentPane().add(tabbedPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(250,150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PanellTabullat();
    }

}
