package com.home.homework13.dao;

import com.home.homework13.database.DB;
import com.home.homework13.entity.DelStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoDelStatus implements CarRentInterface<DelStatus> {

    private DB db;

    public DaoDelStatus(DB db) {
        this.db = db;
    }

    @Override
    public void insert(DelStatus ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO del_status VALUES(?,?)");
            preparedStatement.setInt(1, ob.getId());
            preparedStatement.setString(2, ob.getValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(DelStatus ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE del_status SET value=? WHERE id=?");
            preparedStatement.setString(1, ob.getValue());
            preparedStatement.setInt(2, ob.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM del_status WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet get(int id) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM del_status WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultSet;
    }
}
