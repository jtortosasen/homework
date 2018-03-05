package com.wasdf.view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    MainView mainView;
    JTextField userTextField;
    JTextField passwordTextField;
    JButton acceptButton;

    public LoginPanel(MainView mainView) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.mainView = mainView;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        add(new JLabel("User: "), c);

        userTextField = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        add(userTextField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        add(new JLabel("Password: "), c);

        passwordTextField = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        add(passwordTextField, c);

        acceptButton = new JButton("Aceptar");
        acceptButton.addActionListener(e -> eventListener());
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        add(acceptButton, c);

    }

    private void eventListener() {
        if (userTextField.getText().equals("admin") && passwordTextField.getText().equals("admin")) {
            mainView.loginSuccess(this);
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
