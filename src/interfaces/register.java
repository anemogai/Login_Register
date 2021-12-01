package interfaces;

import java.sql.SQLException;

public interface register {

    void register (String login, String name, String password) throws SQLException;
}
