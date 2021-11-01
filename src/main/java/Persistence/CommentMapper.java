package Persistence;

import Dependencies.MysqlConnection;
import Models.Comment;
import java.sql.*;
import java.util.ArrayList;

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
        String insertSql = "INSERT INTO comments (u_name, f_id, c_content) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, comment.getUsername());
            pstmt.setInt(2, comment.getForumId());
            pstmt.setString(3, comment.getContent());
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

    public ArrayList<Comment> getCommentsToForum(int forumId) throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        String selectSql = "SELECT * FROM comments WHERE f_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, forumId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String content = rs.getString(4);
                comments.add(new Comment(id, username, forumId, content));
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong.");
        }
        return comments;
    }
}
