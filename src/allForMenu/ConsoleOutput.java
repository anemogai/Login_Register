package allForMenu;
import usersInfo.AllUsersInfo;
import usersInfo.User;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class ConsoleOutput {
    private Registration_impl registration;
    private SignIn_impl signInImpl;
    private AllUsersInfo allUsersInfo;
    private boolean a = true;
    private int choice;
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleOutput(Registration_impl registration, SignIn_impl signInImpl, AllUsersInfo allUsersInfo) {
        this.registration = registration;
        this.signInImpl = signInImpl;
        this.allUsersInfo = allUsersInfo;
    }

    public void menu() throws IOException, SQLException {

        while (a) {
            System.out.println("Choose the option: ");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            choice = Integer.parseInt(in.readLine());

            switch (choice) {
                case 1:
                    signInPage();
                    break;
                case 2:
                    registrationPage();
                    break;
                case 3:
                    a = false;
                    System.out.println("Bye, darling :) ");
                    break;
                default:
                    System.out.println("Wrong number, try again...");
            }
        }
    }

        public void registrationPage () throws IOException {
            System.out.println("Login: ");
            String login = in.readLine();
            System.out.println("Name: ");
            String name = in.readLine();
            System.out.println("Password: ");
            String password = in.readLine();
            registration.register(login, name, password);
        }

        public void signInPage() throws IOException {
            System.out.println("Login: ");
            String login = in.readLine();
            System.out.println("Password: ");
            String password = in.readLine();
            User user = signInImpl.signIn(login, password);
            if (user != null){
                System.out.println("Hello, " + user.getName() + "!!!");
                if (user.getRole().equals("admin")){
                    List<User> usersList = allUsersInfo.allUsersList();
                    for (User user1 : usersList){
                        System.out.println(user1.toString());
                        System.out.println();
                    }
                }
            }
            else {
                System.out.println("Such user doesn't exist, register please...");
            }
        }
    }

