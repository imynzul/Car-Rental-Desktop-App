package com.home.homework13.gui;

import com.home.homework13.database.DB;

import javax.swing.*;

public class FrameClient extends JFrame {

    private DB db;

    public FrameClient(DB db){
        this.db = db;
        setSize(400, 200);
        setTitle("Страница Клиента");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }
}
