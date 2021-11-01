package Persistence;

import Dependencies.MysqlConnection;
import Models.Forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumMapper {

    private static ForumMapper instance;
    private static Connection connection;
    private static MysqlConnection mysqlCon = new MysqlConnection();

    public static ForumMapper getInstance() {
        if (instance == null) { instance = new ForumMapper(); }
        connection = mysqlCon.connect();
        return instance;
    }

    public Forum create(Forum forum) throws Exception {
        String insertSql = "INSERT INTO forums (u_name, f_title, f_content) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, forum.getUsername());
            pstmt.setString(2, forum.getTitle());
            pstmt.setString(3, forum.getContent());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                forum.setId(rs.getInt(1));
                return forum;
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        throw new Exception("Couldn't save your forum post.");
    }

    public Forum getForum(int id) throws Exception {
        Forum forum = null;
        String selectSql = "SELECT * FROM forums WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String username = rs.getString(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                forum = new Forum(id, username, title, content);
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return forum;
    }

    public List<Forum> getAll() throws Exception {
        ArrayList<Forum> forums = new ArrayList<>();
        String selectSql = "SELECT * FROM forums";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String title = rs.getString(3);
                String content = rs.getString(4);
                forums.add(new Forum(id, username, title, content));
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return forums;
    }
}
