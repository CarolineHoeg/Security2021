package Persistence;

import Dependencies.MysqlConnection;
import Models.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    private static CommentMapper instance;
    private static Connection connection;
    private static MysqlConnection mysqlCon = new MysqlConnection();

    public static CommentMapper getInstance() {
        if (instance == null) { instance = new CommentMapper(); }
        connection = mysqlCon.connect();
        return instance;
    }

    public Comment create(Comment comment) throws Exception {
        String insertSql = "INSERT INTO comments (u_name, created, f_id, c_content) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, comment.getUsername());
            pstmt.setTimestamp(2, comment.getCreated());
            pstmt.setInt(3, comment.getForumId());
            pstmt.setString(4, comment.getContent());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                comment.setId(rs.getInt(1));
                return comment;
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        throw new Exception("Couldn't save your comment.");
    }

    public Comment update(Comment comment) throws Exception {
        String updateSql = "UPDATE comments SET c_content = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return comment;
    }

    public void delete(int commentId) throws Exception {
        String deleteSql = "DELETE FROM comments WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, commentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
    }

    public ArrayList<Comment> getCommentsToForum(int forumId) throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        String selectSql = "SELECT * FROM comments WHERE f_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, forumId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                Timestamp created = rs.getTimestamp(2);
                String username = rs.getString(3);
                String content = rs.getString(5);
                comments.add(new Comment(id, created, username, forumId, content));
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return comments;
    }

    public List<Comment> getAllByUser(String username) throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        String selectSql = "SELECT * FROM comments WHERE u_name = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                Timestamp created = rs.getTimestamp(2);
                int forumId = rs.getInt(4);
                String content = rs.getString(5);
                comments.add(new Comment(id, created, username, forumId, content));
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return comments;
    }
}
