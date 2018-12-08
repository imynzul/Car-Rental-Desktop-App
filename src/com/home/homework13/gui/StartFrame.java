package com.home.homework13.gui;

import com.home.homework13.database.DB;
import com.home.homework13.database.DBwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.*;

public class StartFrame extends JFrame {

        private JPanel panelBackground, panel, panel1;
        private JTextField url, login, name;
        private JPasswordField password;
        private JLabel labelUrl, labelName, labelLogin, labelPassword;
        private JButton create, delete, connect;
        private JMenuBar bar;
        private JMenuItem item1;
        private JMenu menu;

        public StartFrame(){
            setSize(310,400);
            setTitle("Стартовая страница");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);

            initComponents();
            activation();
            initComponentsLocation();

            setVisible(true);
        }

        public void initComponents(){
            panelBackground = new JPanel();
            panel = new JPanel();
            panel1 = new JPanel();

            panelBackground.setLayout(new BorderLayout());
            panel.setBackground(Color.LIGHT_GRAY);
            panel1.setBackground(Color.DARK_GRAY);
            panel1.setPreferredSize(new Dimension(0, 10));

            bar = new JMenuBar();

            menu = new JMenu("Файл");

            item1 = new JMenuItem("Выйти");

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
            create.setBackground(Color.decode("#85c888"));
            delete = new JButton("Удалить");
            delete.setPreferredSize(new Dimension(280, 20));
            delete.setBackground(Color.decode("#85c888"));
            connect = new JButton("Подключиться");
            connect.setPreferredSize(new Dimension(280, 20));
            connect.setBackground(Color.decode("#c89085"));


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



            add(panelBackground);
        }

        public void initComponentsLocation(){

            panelBackground.add(panel, BorderLayout.CENTER);
            panelBackground.add(panel1, BorderLayout.NORTH);

            menu.add(item1);
            bar.add(menu);

            setJMenuBar(bar);
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

            item1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

        }


    }

