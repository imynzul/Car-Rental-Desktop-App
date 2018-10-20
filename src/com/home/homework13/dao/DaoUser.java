package com.home.homework13.dao;

import com.home.homework13.database.DB;
import com.home.homework13.entity.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUser implements CarRentInterface<User> {

    private DB db;

    public DaoUser(DB db) {
        this.db = db;
    }

    @Override
    public void insert(User ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO user VALUES(?,?, ?, ?, ?)");
            preparedStatement.setInt(1, ob.getId() );
            preparedStatement.setString(2, ob.getLogin());
            preparedStatement.setString(3, ob.getPassword());
            preparedStatement.setInt(4, ob.getRole());
            preparedStatement.setInt(5, ob.getDelStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE user SET login=? WHERE id=?");
            preparedStatement.setString(1, ob.getLogin());
            preparedStatement.setInt(2, ob.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM user WHERE id=?");
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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultSet;
    }
}
