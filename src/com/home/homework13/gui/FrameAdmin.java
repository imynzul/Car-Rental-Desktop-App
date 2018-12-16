package com.home.homework13.gui;

import com.home.homework13.dao.DaoUser;
import com.home.homework13.database.DB;
import com.home.homework13.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAdmin extends JFrame {

    private JPanel panelBackground, panel, panel1, panel2;
    private Table table;
    private JScrollPane scroll;
    private DB db;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem item1, item2, item3;
    private JTextArea textArea;


    public FrameAdmin(DB db){
        this.db = db;
        setSize(1000, 480);
        setTitle("Страница Администратора");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        activation();
        initComponentsDesign(); //

        setVisible(true);
    }

    public void initComponents(){
        panelBackground = new JPanel(); //
        panelBackground.setLayout(new BorderLayout()); //
        panel = new JPanel();
        panel1 = new JPanel(); //
        panel1.setPreferredSize(new Dimension(20, 0)); //
        panel2 = new JPanel(); //
        panel2.setPreferredSize(new Dimension(20, 0)); //

        menuBar = new JMenuBar(); //
        menuBar.setPreferredSize(new Dimension(0, 18)); //
        menu = new JMenu("Выполнить"); //
        item1 = new JMenuItem("Обновить"); //
        item2 = new JMenuItem("Назад"); //
        item3 = new JMenuItem("Выйти"); //

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 200));

        DaoUser daoUser = new DaoUser(db);
        table = new Table(daoUser.getAll());
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(920, 200));

        panelBackground.add(panel, BorderLayout.CENTER); //
        panelBackground.add(panel1, BorderLayout.EAST); //
        panelBackground.add(panel2, BorderLayout.WEST); //

        menu.add(item1); //
        menu.add(item2); //
        menu.add(item3); //
        menuBar.add(menu); //
        setJMenuBar(menuBar); //

        panel.add(scroll);
        panel.add(textArea); //

        add(panelBackground); //
    }

    public void initComponentsDesign(){
        panel.setBackground(Color.decode("#FF5700")); //
        panel1.setBackground(Color.decode("#FF6819")); //
        panel2.setBackground(Color.decode("#FF6819")); //
        menuBar.setBackground(Color.decode("#00FFF1")); //
        item1.setBackground(Color.decode("#2CB2AB"));
        item2.setBackground(Color.decode("#B23D00"));
        item3.setBackground(Color.LIGHT_GRAY);
        textArea.setBackground(Color.decode("#FFF98F"));
    }

    public void activation(){
        item1.addActionListener(new ActionListener() {
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

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);
                dispose();
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    public void updateTable(){
        panel.remove(scroll);
        panel.remove(textArea);
        DaoUser daoUser = new DaoUser(db);
        table = new Table(daoUser.getAll());
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(920, 200));
        panel.add(scroll);
        panel.add(textArea);

        panel.updateUI();
    }
}
