package Persistence;

import Models.Comment;
import Models.Forum;
import Models.User;

import java.io.File;
import java.util.List;

public interface IDBFacade {
    public User login(String username, String password) throws Exception;
    public User register(String username, String password) throws Exception;
    public Forum createForum(String username, String title, String content, String imageUrl)  throws Exception;
    public Forum updateForum(Forum forum) throws Exception;
    public void deleteForum(Forum forum) throws Exception;
    public Forum getForum(int forumId) throws Exception;
    public List<Forum> getAllForums() throws Exception;
    public List<Forum> getForumsByUser(User user) throws Exception;
    public Comment createComment(String username, int forumId, String content) throws Exception;
    public Comment updateComment(Comment comment) throws Exception;
    public void deleteComment(Comment comment) throws Exception;
    public List<Comment> getCommentsByUser(User user) throws Exception;
    public String uploadImage(File file) throws Exception;
}
