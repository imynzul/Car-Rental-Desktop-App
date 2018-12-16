package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameModerator extends JFrame {

    private JPanel panel;
    private Table table;
    private JScrollPane scroll;
    private DB db;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem item1, item2;

    public FrameModerator(DB db){
        this.db = db;
        setSize(1000, 275);
        setTitle("Страница Модератора");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        activation();
        initComponentsDesign();
        JOptionPane.showMessageDialog(panel, "Окно модератора позволяет видеть список клиентов, но не позволяет вносить изменения!", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

        setVisible(true);
    }

    public void initComponents(){
        panel = new JPanel();

        menuBar = new JMenuBar();
        item1 = new JMenuItem("Назад");
        item2 = new JMenuItem("Выйти");

        menu = new JMenu("Выполнить");

        DaoUser daoUser = new DaoUser(db);
        table = new Table(daoUser.getAll());
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(920, 200));

        panel.add(scroll);

        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        add(panel);
    }

    public void initComponentsDesign(){
        panel.setBackground(Color.decode("#C3FF8F"));
        menuBar.setBackground(Color.decode("#AC66B2"));
        item1.setBackground(Color.decode("#A6B29B"));
        item2.setBackground(Color.decode("#A6B29B"));
    }

    public void activation(){
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);
                dispose();
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
