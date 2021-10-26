package Persistence;

import Dependencies.MysqlConnection;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private static UserMapper instance;
    private Connection connection;
    private MysqlConnection mysqlCon = new MysqlConnection();

    public static UserMapper getInstance() {
        if (instance == null) { instance = new UserMapper(); }
        return instance;
    }
    public User login(String username, String password) throws Exception {
        connection = mysqlCon.connect();
        String selectSql = "SELECT u_pass FROM users "
                + "WHERE u_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(selectSql);
        pstmt.setString(1, username);

        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            String hashedPw = result.getString(1);
            User temp = new User(username, password);
            if (temp.validatePassword(password, hashedPw)) {
                connection.close();
                return new User(username, password);
            }
        }
        connection.close();
        throw new Exception("Username or password incorrect.");
    }
    public void register(User user) throws SQLException {
        connection = mysqlCon.connect();
        String insertSql = "INSERT INTO users VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(insertSql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.executeUpdate();
        connection.close();
    }
}
