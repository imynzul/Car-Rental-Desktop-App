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
    private JButton back;

    public FrameModerator(DB db){
        this.db = db;
        setSize(1000, 600);
        setTitle("Страница Модератора");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        activation();

        setVisible(true);
    }

    public void initComponents(){
        panel = new JPanel();

        back = new JButton("Назад");
        back.setPreferredSize(new Dimension(150, 35));

        DaoUser daoUser = new DaoUser(db);
        table = new Table(daoUser.getAll());
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(920, 200));

        panel.add(scroll);
        panel.add(back);

        add(panel);
    }

    public void activation(){
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);
                dispose();
            }
        });




    }

}
