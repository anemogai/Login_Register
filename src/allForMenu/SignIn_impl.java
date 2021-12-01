package allForMenu;

import interfaces.connection_DataBase;
import interfaces.signIn;
import usersInfo.User;

import java.io.*;
import java.sql.*;

public class SignIn_impl implements connection_DataBase, signIn {

    private static final String SQL_SELECT = "select * from anemogai.clients where login = ? and password = ?";

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public User signIn(String login, String password) {
        try {
            connect();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");

                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setName(name);
                user.setRole(role);

                disconnect();
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Wrong login or password, try again...");
        }
        return null;
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
