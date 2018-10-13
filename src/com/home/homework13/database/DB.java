package com.home.homework13.database;

import java.sql.*;

public class DB {

    private Connection connection;
    private Statement statement;

    public DB(String url, String name, String login, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url + name, login, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update (String sql){
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet query(String sql){
        ResultSet resultset = null;
        try {
            resultset = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultset;
    }
    public void showTable(ResultSet resultSet){
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                System.out.print(resultSetMetaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while(resultSet.next()){
                System.out.println();
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
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
