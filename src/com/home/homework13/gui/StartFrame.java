package com.home.homework13.gui;

import com.home.homework13.database.DB;
import com.home.homework13.database.DBwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class StartFrame extends JFrame {

        private JPanel panel;
        private JTextField url, login, name;
        private JPasswordField password;
        private JLabel labelUrl, labelName, labelLogin, labelPassword;
        private JButton create, delete, connect;

        public StartFrame(){
            setSize(310,400);
            setTitle("Стартовая страница");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);

            initComponents();

            activation();

            setVisible(true);
        }

        public void initComponents(){
            panel = new JPanel();

            url = new JTextField("jdbc:mysql://127.0.0.1/",25);
            name = new JTextField("car_rent",25);
            login = new JTextField("root",25);
            password = new JPasswordField("root",25);

            labelUrl = new JLabel("Путь");
            labelName = new JLabel("Имя");
            labelLogin = new JLabel("Логин");
            labelPassword = new JLabel("Пароль");

            create = new JButton("Создать");
            create.setPreferredSize(new Dimension(280,20));
            delete = new JButton("Удалить");
            delete.setPreferredSize(new Dimension(280, 20));
            connect = new JButton("Подключиться");
            connect.setPreferredSize(new Dimension(280, 20));


            panel.add(labelUrl);
            panel.add(url);
            panel.add(labelName);
            panel.add(name);
            panel.add(labelLogin);
            panel.add(login);
            panel.add(labelPassword);
            panel.add(password);

            panel.add(create);
            panel.add(delete);
            panel.add(connect);

            add(panel);
        }

        public void activation(){

            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DBwork.createDB(url.getText(), name.getText(), login.getText(), String.valueOf(password.getPassword()));
                }
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DBwork.deleteDB(url.getText(), name.getText(),login.getText(),String.valueOf(password.getPassword()));
            }
            });

            connect.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DB db = new DB(url.getText(), name.getText(), login.getText(), String.valueOf(password.getPassword()));
                    new Authorization(db);
                    dispose();
                }
            });

        }


    }

