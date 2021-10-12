package Dependencies;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnection implements IMysqlConnection {

    private Connection connection;
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String url;
    private static String user;
    private static String password;

    @Override
    public Connection connect() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    @Override
    public void disconnect() {
        connection = null;
    }

    private void setConnection() {
        getProperties();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            //TODO
        }
    }

    private void getProperties() {
        InputStream in = MysqlConnection.class.getClassLoader().getResourceAsStream("db.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        } catch (IOException e) {
            //TODO
        }
    }
}
