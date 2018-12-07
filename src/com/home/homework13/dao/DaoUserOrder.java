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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO orders (user_id, auto) VALUES (?, ?)");
            preparedStatement.setInt(1, ob.getUserId());
            preparedStatement.setInt(2, ob.getAuto());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(UserOrder ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE orders SET auto=? WHERE id=?");
            preparedStatement.setInt(1, ob.getAuto());
            preparedStatement.setInt(2, ob.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM orders WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public UserOrder get(int id) {
        UserOrder userOrder = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM orders WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                userOrder = new UserOrder(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userOrder;
    }

    public ResultSet getUserOrder(int userId){
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT orders.id,  auto.model FROM orders, auto WHERE orders.user_id=? AND orders.auto=auto.id ");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
