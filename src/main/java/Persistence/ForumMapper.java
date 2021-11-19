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
        String insertSql = "INSERT INTO forums (created, u_name, f_title, f_content, f_image) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setTimestamp(1, forum.getCreated());
            pstmt.setString(2, forum.getUsername());
            pstmt.setString(3, forum.getTitle());
            pstmt.setString(4, forum.getContent());
            pstmt.setString(5, forum.getImageUrl());
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

    public Forum update(Forum forum) throws Exception {
        String updateSql = "UPDATE forums SET f_title = ?, f_content = ? WHERE id = ?";
        try{
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, forum.getTitle());
            pstmt.setString(2, forum.getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return forum;
    }

    public void delete(int forumId) throws Exception {
        String deleteSql = "DELETE FROM forums WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, forumId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
    }

    public Forum getForum(int id) throws Exception {
        Forum forum = null;
        String selectSql = "SELECT * FROM forums WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            getForumFromQuery(pstmt);
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
            getForumsFromResultSet(forums, pstmt);
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return forums;
    }

    public List<Forum> getAllByUser(String username) throws Exception {
        ArrayList<Forum> forums = new ArrayList<>();
        String selectSql = "SELECT * FROM forums WHERE u_name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            getForumsFromResultSet(forums, pstmt);
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return forums;
    }

    public List<Forum> searchForums(String searchStr) {
        ArrayList<Forum> forums = new ArrayList<>();
        String selectSql = "SELECT * FROM forums WHERE f_content LIKE ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, '%' + searchStr + '%');
            getForumsFromResultSet(forums, pstmt);
        } catch (SQLException e) {
        }
        return forums;
    }

    private Forum getForumFromQuery(PreparedStatement pstmt) throws SQLException {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            Timestamp created = rs.getTimestamp(2);
            String username = rs.getString(3);
            String title = rs.getString(4);
            String content = rs.getString(5);
            String imageUrl = rs.getString(6);
            return new Forum(id, created, username, title, content, imageUrl);
        }
        return null;
    }

    private void getForumsFromResultSet(ArrayList<Forum> forums, PreparedStatement pstmt) throws SQLException {
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            Timestamp created = rs.getTimestamp(2);
            String username = rs.getString(3);
            String title = rs.getString(4);
            String content = rs.getString(5);
            String imageUrl = rs.getString(6);
            forums.add(new Forum(id, created, username, title, content, imageUrl));
        }
    }
}
