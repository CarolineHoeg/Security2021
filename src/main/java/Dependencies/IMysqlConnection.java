package Dependencies;

import java.sql.Connection;

public interface IMysqlConnection {
    public Connection connect();
    public void disconnect();
}
