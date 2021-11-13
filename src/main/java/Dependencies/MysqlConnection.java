package Dependencies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static Logger LOG = LogManager.getLogger(MysqlConnection.class);

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
            LOG.error("Something went wrong! ", e);
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
            LOG.error("Something went wrong! ", e);
        }
    }
}
