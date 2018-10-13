package com.home.homework13.dao;

import com.home.homework13.database.DB;
import com.home.homework13.entity.Role;
import com.sun.applet2.preloader.event.PreloaderEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoRole implements CarRentInterface<Role> {

    private DB db;

    public DaoRole(DB db) {
        this.db = db;
    }


    @Override
    public void insert(Role ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO role VALUE (?,?)");
            preparedStatement.setInt(1, ob.getId());
            preparedStatement.setString(2, ob.getValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Role ob) {
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE role SET value=? WHERE id=?");
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
            PreparedStatement preparedStatement = db.getConnection().prepareStatement("DELETE FROM role WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Role get(int id) {
        return null;
    }
}
