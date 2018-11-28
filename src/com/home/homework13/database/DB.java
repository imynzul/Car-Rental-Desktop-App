package com.home.homework13.database;

import java.sql.*;

public class DB {

    private Connection connection;  //создание переменной для соединение с DB
    private Statement statement;  //создание переменной для запросов для работы с DB

    public DB(String url, String name, String login, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");  //подклюсение к DB
            connection = DriverManager.getConnection(url + name, login, password);  //заполнение переменной connection и соединение с DB
            statement = connection.createStatement();  //заполнение переменной statement и создание запроса для работы с DB
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update (String sql){  //данный метод вносит изменения в таблицу, вроде добавление/удаление пользователей. этот метод не может отправлять запросы, возвращающие информацию из DB. пример запросов для этого метода INSERT INTO car_rent (values) VALUES ('');
        try {
            statement.executeUpdate(sql); //отправка запроса через вызов встроенного метода executeUpdate(String sql) припомощи переменной statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet query(String sql){  //данный метода возвращает запросы, он используется для запросов вроде "SELECT * FROM user"
        ResultSet resultset = null;      //создание переменной, в которую будет сохраняться вся информация, которую мы достаем из DB. Заполнение этой переменной null
        try {
            resultset = statement.executeQuery(sql); //вызов метода executeQuery(String sql), который отправялет возвращающиеся запросы в DB при помощи переменной statement, и сохранение всей инофрмации, который достал метод, в переменную resulset
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultset;
    }
    public void showTable(ResultSet resultSet){  //данный метод достатет всю информация из resultset, в который мы сохранили всю информацию об объекте в методе выше query и красиво выводит эту инфу на консоль
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();  //используем встроенный метод getMetaData, который вызывается при помощи resultset и который достает всю служебную информацию из resultset. Затем сохраняем всю эту инфу в отдельную переменную resultSetMetaData

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){  //данный цикл выводит названия столбцов
                System.out.print(resultSetMetaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while(resultSet.next()){  //проверяет наличие след элемента. В данном случае след элемента в таблице
                System.out.println();
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){  //данный цикл выводит содержание каждой ячейки таблицы
                    System.out.print(resultSet.getString(i) + "\t");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
