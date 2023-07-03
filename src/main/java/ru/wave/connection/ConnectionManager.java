package ru.wave.connection;

import ru.wave.users.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionManager {

    FileInputStream fls;
    Properties properties = new Properties();

    private String url = "jdbc:sqlserver://localhost:1433;databaseName=consoleprogram";
    private String name = "Wave";
    private String password = "root";

    public static Connection connection;


    public Connection getConnection(){
        try{
            if(connection != null){
                enterLogged();
                return connection;
            } else {
            connection = DriverManager.getConnection(url,name,password);
            enterLogged();
            return connection;}

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void enterLogged() throws SQLException {
        User.getUsers().clear();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            User.getUsers().add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("date"),
                    resultSet.getInt("salary"),
                    resultSet.getString("departamentname"),
                    resultSet.getString("post"),
                    resultSet.getString("bossName")
            ));
        }
        preparedStatement.close();;
    }
}
