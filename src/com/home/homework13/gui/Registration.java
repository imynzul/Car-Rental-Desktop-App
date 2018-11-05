package com.home.homework13.gui;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import java.awt.*;

public class Registration extends JFrame {

    private JPanel panel;
    private JTextField login;
    private JPasswordField password, password_conf;
    private JLabel loginLabel, passwordLabel, password_confLabel;
    private JButton enter;

    public Registration(){
        setSize(400, 300);
        setTitle("Регистрация");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();

        setVisible(true);

    }

    public void initComponents(){
        panel = new JPanel();

        login = new JTextField(33);
        password = new JPasswordField(33);
        password_conf = new JPasswordField(33);

        loginLabel = new JLabel("Логин");
        passwordLabel = new JLabel("Пароль");
        password_confLabel = new JLabel("Повторите пароль");

        enter = new JButton("Войти");
        enter.setPreferredSize(new Dimension(200, 20));

        panel.add(loginLabel);
        panel.add(login);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(password_confLabel);
        panel.add(password_conf);
        panel.add(enter);

        add(panel);

    }

}
