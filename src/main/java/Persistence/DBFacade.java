package Persistence;

import Models.Comment;
import Models.Forum;
import Models.User;
import java.util.ArrayList;

public class DBFacade implements IDBFacade{

    private UserMapper uMapper;
    private ForumMapper fMapper;
    private CommentMapper cMapper;

    public DBFacade() {
        uMapper = UserMapper.getInstance();
        fMapper = ForumMapper.getInstance();
        cMapper = CommentMapper.getInstance();
    }

    @Override
    public User login(String username, String password) throws Exception {
        return uMapper.login(username, password);
    }

    @Override
    public User register(String username, String password) throws Exception {
        User user = new User(username, password);
        uMapper.register(user);
        return user;
    }

    @Override
    public Forum createForum(String username, String title, String content) throws Exception {
        return fMapper.createForum(new Forum(username, title, content));
    }

    @Override
    public Forum getForum(int forumId) throws Exception {
        Forum forum = fMapper.getForum(forumId);
        ArrayList<Comment> comments = cMapper.getCommentsToForum(forumId);
        forum.setComments(comments);
        return forum;
    }

    @Override
    public ArrayList<Forum> getAllForums() throws Exception {
        return fMapper.getAll();
    }

    @Override
    public Comment createComment(String username, int forumId, String content) throws Exception {
        return cMapper.create(new Comment(username, forumId, content));
    }
}
