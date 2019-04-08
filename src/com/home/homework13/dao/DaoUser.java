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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO user (login, password, role, status) VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, ob.getLogin());
            preparedStatement.setString(2, ob.getPassword());
            preparedStatement.setInt(3, ob.getRole());
            preparedStatement.setInt(4, ob.getDelStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE user SET login=?, password=?, role=?, status=? WHERE id=?");
            preparedStatement.setString(1, ob.getLogin());
            preparedStatement.setString(2, ob.getPassword());
            preparedStatement.setInt(3, ob.getRole());
            preparedStatement.setInt(4, ob.getDelStatus());
            preparedStatement.setInt(5, ob.getId());
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
    public User get(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public ResultSet getAll() {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user");
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultSet;
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = null;
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user WHERE login=? AND password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    public boolean checkLogin(User ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user WHERE login=?");
            preparedStatement.setString(1, ob.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLoginByString(String login){
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * FROM user WHERE login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validationLogin(String login) {
        if (login.length() > 2 && login.length() < 30) {
            if(login.contains(" ")){
                return false;
                }
            return true;
            }
        return false;
        }


    public boolean validationPassword(String password) {
        if (password.length() > 2 && password.length() < 30) {
            if (password.contains(" ")){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean passwordsIdentity(String password, String password2){
        if(password.equals(password2)){
            return true;
        }else{
            return false;
        }

    }
}
