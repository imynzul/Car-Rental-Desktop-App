package com.home.homework13.gui;

import com.home.homework13.database.DB;

import javax.swing.*;

public class FrameModerator extends JFrame {

    private DB db;

    public FrameModerator(DB db){
        this.db = db;
        setSize(400, 200);
        setTitle("Страница Модератора");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }

}
