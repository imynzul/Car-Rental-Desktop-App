package com.home.homework13.database;

import java.sql.SQLException;

public class DBwork {
    public static void createDB(String url, String name, String login, String password) throws SQLException {
        DB db = new DB(url, "", login, password);
            db.update("CREATE DATABASE " + name);
            db.update("USE " + name);
            db.update("CREATE TABLE status(id INT AUTO_INCREMENT," +
                    "value VARCHAR(15) NOT NULL," +
                    "PRIMARY KEY (id));");
            db.update("CREATE TABLE role(id INT AUTO_INCREMENT," +
                    "value VARCHAR(15) NOT NULL," +
                    "PRIMARY KEY (id))");
            db.update("CREATE TABLE user(id INT AUTO_INCREMENT," +
                    "login VARCHAR(50) UNIQUE NOT NULL," +
                    "password VARCHAR(30) NOT NULL," +
                    "role INT NOT NULL," +
                    "status INT NOT NULL," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY(role) REFERENCES role(id)," +
                    "FOREIGN KEY(status) REFERENCES status(id))");
            db.update("CREATE TABLE auto(id INT AUTO_INCREMENT," +
                    "model TEXT NOT NULL," +
                    "PRIMARY KEY(id))");
            db.update("CREATE TABLE orders(id INT AUTO_INCREMENT," +
                    "user_id INT NOT NULL," +

                    "auto INT NOT NULL," +
                    "PRIMARY KEY(id)," +
                    "FOREIGN KEY(user_id) REFERENCES user(id)," +
                    "FOREIGN KEY(auto) REFERENCES auto(id))");
            db.update("INSERT INTO status (value) VALUES ('active'), ('blocked'), ('deleted'), ('archived')");
            db.update("INSERT INTO role (value) VALUES ('admin'), ('client'), ('moderator')");
            db.update("INSERT INTO user (login, password, role, status) VALUES ('imynzul@gmail.com', '1234', '2', '1')," +
                    "('k.chertovich@mail.ru', '5678', '2', '1')," +
                    "('alexm@yandex.com', '1357', '2', '1')," +
                    "('tanya@mail.inbox', '0864', '2', '1')," +
                    "('eugene', '0987', '2', '1')");
            db.update("INSERT INTO auto (model) VALUES ('Toyota'), ('Mercedez'), ('BMW'), ('Mazda'), ('Volvo')");
            db.update("INSERT INTO orders (user_id, auto) VALUES ('1', '4')," +
                    "('2', '3')," +
                    "('3', '2')," +
                    "('4', '1')," +
                    "('3', '3')," +
                    "('1', '1')");
    }

    public static void deleteDB(String url, String name, String login, String password) throws SQLException{
        DB db = new DB(url, "", login, password);

        db.update("DROP DATABASE car_rent");
    }
}
