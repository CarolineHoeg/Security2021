package Persistence;

import Dependencies.MysqlConnection;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private static UserMapper instance;
    private static Connection connection;
    private static MysqlConnection mysqlCon = new MysqlConnection();

    public static UserMapper getInstance() {
        if (instance == null) { instance = new UserMapper(); }
        connection = mysqlCon.connect();
        return instance;
    }

    public User login(String username, String password) throws Exception {
        String selectSql = "SELECT u_pass FROM users "
                + "WHERE u_name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String hashedPw = rs.getString(1);
                User temp = new User(username, password);
                if (temp.validatePassword(password, hashedPw)) {
                    return new User(username, password);
                }
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong.");
        }
        throw new Exception("Username or password incorrect.");
    }

    public void register(User user) throws Exception {
        String insertSql = "INSERT INTO users VALUES (?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Username already taken.");
        }
    }
}
