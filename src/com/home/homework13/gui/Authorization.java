package com.home.homework13.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authorization extends JFrame {

    private JPanel panel;
    private JTextField login;
    private JPasswordField password;
    private JLabel loginLabel, passwordlabel;
    private JButton enter, registration;

    public Authorization(){
        setSize(300, 300);
        setTitle("Авторизация");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
        activation();

        setVisible(true);
    }

    public void initComponents(){
        panel = new JPanel();

        login = new JTextField(25);
        password = new JPasswordField(25);

        loginLabel = new JLabel("Логин");
        passwordlabel = new JLabel("Пароль");

        enter = new JButton("Войти");
        enter.setPreferredSize(new Dimension(180, 20));
        registration = new JButton("Регистрация");
        registration.setPreferredSize(new Dimension(180, 20));

        panel.add(loginLabel);
        panel.add(login);
        panel.add(passwordlabel);
        panel.add(password);
        panel.add(enter);
        panel.add(registration);

        add(panel);
    }

    public void activation(){
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration();
                dispose();
            }
        });

    }
}
