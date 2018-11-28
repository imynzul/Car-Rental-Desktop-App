package com.home.homework13.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Table extends JTable {

    public Table(ResultSet rs){

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount(); i++){
                defaultTableModel.addColumn(rsmd.getColumnName(i));
            }
            while (rs.next()){
                Vector <String> vector = new Vector<>();
                for(int i = 1; i <= rsmd.getColumnCount(); i++){
                    vector.add(rs.getString(i));
                }
                defaultTableModel.addRow(vector);

            }
            setModel(defaultTableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
