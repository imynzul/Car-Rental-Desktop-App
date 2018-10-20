package com.home.homework13.main;

import com.home.homework13.dao.*;
import com.home.homework13.database.DB;
import com.home.homework13.database.DBwork;
import com.home.homework13.entity.*;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        DB db = new DB("jdbc:mysql://127.0.0.1/", "car_rent", "root", "root");

        /*DBwork.createDB("jdbc:mysql://127.0.0.1/", "car_rent", "root", "root");*/


       /* db.update("INSERT INTO del_status (value) VALUES ('active'), ('blocked'), ('deleted')");*/

        /*db.update("INSERT INTO role (value) VALUES ('admin'), ('client'), ('moderator')");*/

        /*db.update("INSERT INTO user (login, password, role, del_status) VALUES ('imynzul@gmail.com', '1234', '1', '1')," +
                "('k.chertovich@mail.ru', '5678', '1', '2')," +
                "('alexm@yandex.com', '1357', '2', '3')," +
                "('tanya@mail.inbox', '0864', '3', '2')");*/

       /* db.update("INSERT INTO auto (model) VALUES ('Toyota'), ('Mercedez'), ('BMW'), ('Mazda'), ('Volvo')");*/

        /*db.update("INSERT INTO user_order (user_id, passport_number, auto) VALUES ('1', 'ty5674', '4')," +
                "('2', 'gh5643', '3')," +
                "('3', 'mp9876', '2')," +
                "('4', 'nb9045', '1')");*/

        /*db.update("AlTER TABLE auto CHANGE model car_model CHAR(20)");*/

       /* ResultSet resultSet = db.query("SELECT * FROM role");

        db.showTable(resultSet);*/

      /* DaoRole daoRole = new DaoRole(db);
       Role role = new Role(5, "user");
       daoRole.delete(role);*/


        /*daoUserOrder.insert(userOrder);
        daoUserOrder.insert(userOrder1);
        daoUserOrder.insert(userOrder2);*/


       /* Auto auto = new Auto(1, "Lada");
        DaoAuto daoAuto = new DaoAuto(db);
        daoAuto.delete(1);*/

       /* DaoDelStatus daoDelStatus = new DaoDelStatus(db);
        DelStatus delStatus = daoDelStatus.get(2);
        System.out.println(delStatus);*/



       /* DaoRole daoRole = new DaoRole(db);
        Role role = daoRole.get(1);
        System.out.println(role);*/


        /*DaoUser daoUser = new DaoUser(db);
        User user = daoUser.get(3);
        System.out.println(user);*/


        /*DaoUserOrder daoUserOrder = new DaoUserOrder(db);
        UserOrder userOrder3 = new UserOrder(3, 1, "BBB", 1);
        daoUserOrder.update(userOrder3);

        /*DaoAuto daoAuto = new DaoAuto(db);
        Auto auto = daoAuto.get(5);
        System.out.println(auto);*/

        /*DaoUserOrder daoUserOrder = new DaoUserOrder(db);
        UserOrder userOrder = daoUserOrder.get(2);
        System.out.println(userOrder);*/































    }
}
