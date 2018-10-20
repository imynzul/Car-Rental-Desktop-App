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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO status VALUES(?,?)");
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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE status SET value=? WHERE id=?");
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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM status WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public DelStatus get(int id) {
        DelStatus delStatus = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM status WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                delStatus = new DelStatus(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return delStatus;
    }
}
