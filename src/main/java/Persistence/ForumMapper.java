package Persistence;

import Dependencies.MysqlConnection;
import Models.Forum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumMapper {

    private static ForumMapper instance;
    private Connection connection;
    private MysqlConnection mysqlCon = new MysqlConnection();

    public static ForumMapper getInstance() {
        if (instance == null) { instance = new ForumMapper(); }
        return instance;
    }

    public Forum createForum(Forum forum) throws Exception {
        connection = mysqlCon.connect();
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
            throw new Exception("Something went wrong");
        }
        throw new Exception("Couldn't save your forum post.");
    }

    public Forum getForum(int id) throws Exception {
        connection = mysqlCon.connect();
        connection.close();
        return null;
    }

    public List<Forum> getAll() throws Exception {
        connection = mysqlCon.connect();
        ArrayList<Forum> forums = new ArrayList<>();
        String selectSql = "SELECT * FROM forums";
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String username = result.getString(2);
                String title = result.getString(3);
                String content = result.getString(4);
                forums.add(new Forum(id, username, title, content));
            }
        } catch (SQLException e) {
            throw new Exception("Something went wrong");
        }
        return forums;
    }
}
