package com.home.homework13.dao;

import com.home.homework13.database.DB;
import com.home.homework13.entity.UserOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUserOrder implements CarRentInterface<UserOrder> {

    private DB db;

    public DaoUserOrder(DB db) {
        this.db = db;
    }

    @Override
    public void insert(UserOrder ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO user_order VALUES (?,?, ?, ?)");
            preparedStatement.setInt(1, ob.getId());
            preparedStatement.setInt(2, ob.getUserId());
            preparedStatement.setString(3, ob.getPassportNumber());
            preparedStatement.setInt(4, ob.getAuto());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(UserOrder ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE user_order SET passport_number=? WHERE id=?");
            preparedStatement.setString(1, ob.getPassportNumber());
            preparedStatement.setInt(2, ob.getId());
            preparedStatement.execute();
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM user_order WHERE id=?");
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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user_order WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultSet;
    }
}
