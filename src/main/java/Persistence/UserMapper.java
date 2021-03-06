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
        String selectSql = "SELECT u_pass, isvalid FROM users "
                + "WHERE u_name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String hashedPw = rs.getString(1);
                boolean isValid = rs.getBoolean(2);
                User temp = new User(username, password);
                if (temp.validatePassword(password, hashedPw)) {
                    temp.setValidated(isValid);
                    return temp;
                }
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong.");
        }
        throw new Exception("Username or password incorrect.");
    }

    public void register(User user) throws Exception {
        String insertSql = "INSERT INTO users VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setBoolean(4, user.isValidated());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Username or e-mail already in use.");
        }
    }

    public void updateValidatedUser(User user) throws Exception {
        String updateSql = "UPDATE users SET isvalid = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setBoolean(1, user.isValidated());
        } catch (SQLException e) {
            throw new Exception("Could not authenticate your account. Will send new email.");
        }
    }
}
