package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;
import jdk.nashorn.internal.scripts.JO;

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
    private DB db;

    public Authorization(DB db){
        this.db = db;
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
                new Registration(db);
                dispose();
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DaoUser daoUser = new DaoUser(db);
                User user = daoUser.findByLoginAndPassword(login.getText(), String.valueOf(password.getPassword()));
                User user2 = checkDelStatus(user);
                if(user2 != null){
                    checkRoleAndEnter(user);
                }


            }
        });

    }

   public User checkDelStatus(User user){
        if(user != null){
            switch(user.getDelStatus()){
                case 1:
                    dispose();
                    return user;
                case 2:
                   JOptionPane.showMessageDialog(panel, "Пользователь заблокирован", "ACCESS DENIED", JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(panel, "Пользоваетль удален", "ACCESS DENIED", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(panel, "Пользователь находится в архиве", "ACCESS DENIED", JOptionPane.INFORMATION_MESSAGE);
                    break;

            }
        }


       return null;
   }

   public static void checkRoleAndEnter(User user){
        if(user != null){
            switch(user.getRole()){
                case 1:
                    new FrameAdmin();
                    break;
                case 2:
                    new FrameClient();
                    break;
                case 3:
                    new FrameModerator();
                    break;
            }
        }

   }




}
