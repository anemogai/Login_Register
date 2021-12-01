package usersInfo;

import interfaces.connection_DataBase;

import java.sql.*;
import java.util.*;

public class AllUsersInfo implements connection_DataBase {

    private static final String SQL_SELECTALL = "select * from anemogai.clients where role = ?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<User> allUsersList(){
        List<User> usersList = new ArrayList<>();
        try {
            connect();
            preparedStatement = connection.prepareStatement(SQL_SELECTALL);
            preparedStatement.setString(1, "user");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");

                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setName(name);
                user.setRole(role);

                usersList.add(user);
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
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
