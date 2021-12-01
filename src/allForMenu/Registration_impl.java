package allForMenu;

import interfaces.connection_DataBase;
import interfaces.register;

import java.io.*;
import java.sql.*;


public class Registration_impl implements register, connection_DataBase {

    private static final String SQL_INSERT = "insert into anemogai.clients (login, password, name, role) values (?, ?, ?, ?)";

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public void register(String login, String name, String password){
        try {
            connect();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, "user");
            preparedStatement.executeUpdate();
            System.out.println("Registration done, " + name + "!");
            disconnect();
        } catch (SQLException e) {
            System.out.println("Login is already used, enter again");
        }
    }

    @Override
    public boolean connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");
        } catch (SQLException e) {
            System.out.println("Connection to database failed...");
            return false;
        }
        return true;
    }

    @Override
    public boolean disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected from database!");
        } catch (SQLException e) {
            System.out.println("Didn't disconnect, failure");
            return false;
        }
        return true;
    }
}
