import allForMenu.ConsoleOutput;
import allForMenu.Registration_impl;
import allForMenu.SignIn_impl;
import usersInfo.AllUsersInfo;

import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        Registration_impl registration = new Registration_impl();
        SignIn_impl signInImpl = new SignIn_impl();
        AllUsersInfo allUsersInfo = new AllUsersInfo();
        ConsoleOutput consoleOutput = new ConsoleOutput(registration, signInImpl, allUsersInfo);

        consoleOutput.menu();







    }
}
