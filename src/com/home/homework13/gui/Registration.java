package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Registration extends JFrame {

    private JPanel panel;
    private JTextField login;
    private JPasswordField password, password_conf;
    private JLabel loginLabel, passwordLabel, password_confLabel;
    private JButton enter;
    private DB db;

    public Registration(DB db){
        this.db = db;
        setSize(400, 300);
        setTitle("Регистрация");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
        activation();

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

        enter = new JButton("Регистрироваться");
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

    public void activation(){
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner in = new Scanner(System.in);
                System.out.println("Введите роль для нового пользователя! 1 - админ, 2 - клиент, 3 - модератор");
                int role = in.nextInt();
                User newUser = new User(login.getText(), String.valueOf(password.getPassword()), role, 1);
                DaoUser daoUser = new DaoUser(db);
                daoUser.insert(newUser);
                dispose();
                Authorization.checkRole(newUser);
            }
        });

    }

}
