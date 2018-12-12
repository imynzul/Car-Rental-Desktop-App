package com.home.homework13.gui;

import com.home.homework13.database.DB;
import com.home.homework13.database.DBwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {

        private JPanel panelBackground, panel, panel1, panel2;
        private JTextField url, login, name;
        private JPasswordField password;
        private JLabel labelUrl, labelName, labelLogin, labelPassword;
        private JButton connect;
        private JMenuBar bar;
        private JMenuItem item1, item2, item3;
        private JMenu menu;

        public StartFrame(){
            setSize(310,290);
            setTitle("Стартовая страница");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);

            initComponents();
            activation();
            initComponentsLocation();
            initComponentsDesign();

            setVisible(true);
        }

        public void initComponents(){
            panelBackground = new JPanel();
            panel = new JPanel();
            panel1 = new JPanel();
            panel2 = new JPanel();

            panelBackground.setLayout(new BorderLayout());

            bar = new JMenuBar();
            bar.setPreferredSize(new Dimension(0, 18));

            menu = new JMenu("Выполнить");

            item1 = new JMenuItem("Выйти");
            item2 = new JMenuItem("Создать БД");
            item3 = new JMenuItem("Удалить БД");

            url = new JTextField("jdbc:mysql://127.0.0.1/",25);
            name = new JTextField("car_rent",25);
            login = new JTextField("root",25);
            password = new JPasswordField("root",25);

            labelUrl = new JLabel("Путь");
            labelName = new JLabel("Имя");
            labelLogin = new JLabel("Логин");
            labelPassword = new JLabel("Пароль");

            connect = new JButton("Подключиться");
            connect.setPreferredSize(new Dimension(280, 40));

            panel.add(labelUrl);
            panel.add(url);
            panel.add(labelName);
            panel.add(name);
            panel.add(labelLogin);
            panel.add(login);
            panel.add(labelPassword);
            panel.add(password);

            panel.add(connect);

            add(panelBackground);
        }

        public void initComponentsDesign(){

            panel1.setPreferredSize(new Dimension(5, 0));
            panel2.setPreferredSize(new Dimension(5, 0));

            panel.setBackground(Color.decode("#999999"));
            panel1.setBackground(Color.decode("#b6b6b6"));
            panel2.setBackground(Color.decode("#b6b6b6"));
            connect.setBackground(Color.decode("#85c888"));
            bar.setBackground(Color.decode("#c88585"));
        }

        public void initComponentsLocation(){

            panelBackground.add(panel, BorderLayout.CENTER);
            panelBackground.add(panel1, BorderLayout.EAST);
            panelBackground.add(panel2, BorderLayout.WEST);

            menu.add(item2);
            menu.add(item3);
            menu.add(item1);
            bar.add(menu);

            setJMenuBar(bar);
        }

        public void activation(){

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

            item2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DBwork.createDB(url.getText(), name.getText(), login.getText(), String.valueOf(password.getPassword()));
                }
            });

            item3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DBwork.deleteDB(url.getText(), name.getText(),login.getText(),String.valueOf(password.getPassword()));
                }
            });
        }
    }

