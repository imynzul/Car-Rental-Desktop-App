package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAdmin extends JFrame {

    private JPanel panel;
    private Table table;
    private JScrollPane scroll;
    private DB db;
    private JButton update, back;


    public FrameAdmin(DB db){
        this.db = db;
        setSize(1000, 600);
        setTitle("Страница Администратора");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        activation();

        setVisible(true);

    }

    public void initComponents(){
        panel = new JPanel();

        update = new JButton("Обновить");
        back = new JButton("Назад");
        update.setPreferredSize(new Dimension(200, 50));
        back.setPreferredSize(new Dimension(150, 35));


        DaoUser daoUser = new DaoUser(db);
        table = new Table(daoUser.getAll());
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(920, 200));

        panel.add(update);
        panel.add(scroll);

        panel.add(back);

        add(panel);
    }

    public void activation(){
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    new DaoUser(db).update(new User(Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
                            String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
                            String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
                            Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
                            Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)))));
                    updateTable();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);
                dispose();
            }
        });

    }
    public void updateTable(){
        panel.remove(scroll);
        DaoUser daoUser = new DaoUser(db);
        table = new Table(daoUser.getAll());
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(920, 200));
        panel.add(update);
        panel.add(scroll);
        panel.add(back);
        panel.updateUI();

    }
}
