package com.home.homework13.database;

public class DBwork {
    public static void createDB(String url, String name, String login, String password ){
        DB db = new DB(url, name, login, password);

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
                "passport_number TEXT NOT NULL," +
                "auto INT NOT NULL," +
                "PRIMARY KEY(id)," +
                "FOREIGN KEY(user_id) REFERENCES user(id)," +
                "FOREIGN KEY(auto) REFERENCES auto(id))");
        db.update("INSERT INTO status (value) VALUES ('active'), ('blocked'), ('deleted'), ('archived')");
        db.update("INSERT INTO role (value) VALUES ('admin'), ('client'), ('moderator')");
        db.update("INSERT INTO user (login, password, role, status) VALUES ('imynzul@gmail.com', '1234', '1', '1')," +
                "('k.chertovich@mail.ru', '5678', '1', '4')," +
                "('alexm@yandex.com', '1357', '2', '3')," +
                "('tanya@mail.inbox', '0864', '3', '2')");
        db.update("INSERT INTO auto (model) VALUES ('Toyota'), ('Mercedez'), ('BMW'), ('Mazda'), ('Volvo')");
        db.update("INSERT INTO orders (user_id, passport_number, auto) VALUES ('1', 'ty5674', '4')," +
                "('2', 'gh5643', '3')," +
                "('3', 'mp9876', '2')," +
                "('4', 'nb9045', '1')");


    }

    public static void deleteDB(String url, String name, String login, String password){
        DB db = new DB(url, name, login, password);

        db.update("DROP TABLE orders");
        db.update("DROP TABLE auto");
        db.update("DROP TABLE user");
        db.update("DROP TABLE role");
        db.update("DROP TABLE status");
    }
}
