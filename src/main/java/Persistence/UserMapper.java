package Persistence;

import Dependencies.MysqlConnection;
import Models.User;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserMapper {

    private static UserMapper instance;
    private Connection connection;
    private MysqlConnection mysqlCon = new MysqlConnection();
    private static String pepper;

    public static UserMapper getInstance() throws IOException {
        if (instance == null) { instance = new UserMapper(); }
        if (pepper == null) { setPepper();}
        return instance;
    }
    public User login(String username, String password) throws SQLException {
        connection = mysqlCon.connect();
        String selectSql = "SELECT u_pass FROM users "
                + "WHERE u_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, username);

        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            String hashedPw = result.getString(1);
            if (BCrypt.checkpw(pepper + password, hashedPw)) {
                return new User(username, password);
            }
        }
        return null;
    }
    public void register(User user) throws SQLException {
        connection = mysqlCon.connect();
        String insertSql = "INSERT INTO users VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(insertSql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.executeUpdate();
    }

    private static void setPepper() throws IOException {
        InputStream in = UserMapper.class.getClassLoader().getResourceAsStream("pepper.properties");
        Properties props = new Properties();
        props.load(in);
        pepper = props.getProperty("pepper");
    }
}
