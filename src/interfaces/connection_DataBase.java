package interfaces;

public interface connection_DataBase {
    String url = "jdbc:mysql://localhost:3306/?user=root";
    String user = "root";
    String password = "n.ananas1616";

    boolean connect();
    boolean disconnect();









    /*
    default boolean con(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");
        } catch (SQLException e) {
            System.out.println("Connection to database failed...");
            return false;
        }
        return true;
    }
    default boolean dis(){
        try {
            Connection connection;
            connection.close();
            System.out.println("Disconnected from database!");
        } catch (SQLException e) {
            System.out.println("Didn't disconnect, failure");
            return false;
        }
        return true;
    }

     */
}
