package Persistence;

import Models.Comment;
import Models.Forum;
import Models.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DBFacade implements IDBFacade{

    private final UserMapper USERMAPPER = UserMapper.getInstance();
    private final ForumMapper FORUMMAPPER = ForumMapper.getInstance();
    private final CommentMapper COMMENTMAPPER = CommentMapper.getInstance();

    @Override
    public User login(String username, String password) throws Exception {
        return USERMAPPER.login(username, password);
    }

    @Override
    public User register(String username, String password) throws Exception {
        User user = new User(username, password);
        USERMAPPER.register(user);
        return user;
    }

    @Override
    public Forum createForum(String username, String title, String content) throws Exception {
        return FORUMMAPPER.create(new Forum(username, title, content));
    }

    @Override
    public Forum getForum(int forumId) throws Exception {
        Forum forum = FORUMMAPPER.getForum(forumId);
        ArrayList<Comment> comments = COMMENTMAPPER.getCommentsToForum(forumId);
        forum.setComments(comments);
        return forum;
    }

    @Override
    public List<Forum> getAllForums() throws Exception {
        List<Forum> forums = FORUMMAPPER.getAll();
        for (Forum forum : forums) {
            ArrayList<Comment> comments = COMMENTMAPPER.getCommentsToForum(forum.getId());
            forum.setComments(comments);
        }
        forums.sort((f1, f2) -> f2.getCreated().compareTo(f1.getCreated()));
        return forums;
    }

    @Override
    public Comment createComment(String username, int forumId, String content) throws Exception {
        return COMMENTMAPPER.create(new Comment(username, forumId, content));
    }
}
