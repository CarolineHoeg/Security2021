package Persistence;

import Models.Comment;
import Models.Forum;
import Models.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DBFacade implements IDBFacade{

    private final UserMapper USERMAPPER = UserMapper.getInstance();
    private final ForumMapper FORUMMAPPER = ForumMapper.getInstance();
    private final CommentMapper COMMENTMAPPER = CommentMapper.getInstance();
    private final ImageMapper IMAGEMAPPER = ImageMapper.getInstance();

    @Override
    public User login(String username, String password) throws Exception {
        return USERMAPPER.login(username, password);
    }

    @Override
    public User register(String username, String email, String password) throws Exception {
        User user = new User(username, email, password);
        USERMAPPER.register(user);
        return user;
    }

    @Override
    public Forum createForum(String username, String title, String content, String imageUrl) throws Exception {
        return FORUMMAPPER.create(new Forum(username, title, content, imageUrl));
    }

    @Override
    public Forum updateForum(Forum forum) throws Exception {
        return FORUMMAPPER.update(forum);
    }

    @Override
    public void deleteForum(Forum forum) throws Exception {
        FORUMMAPPER.delete(forum.getId());
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
    public List<Forum> getForumsByUser(User user) throws Exception {
        List<Forum> forums = FORUMMAPPER.getAllByUser(user.getUsername());
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

    @Override
    public Comment updateComment(Comment comment) throws Exception {
        return COMMENTMAPPER.update(comment);
    }

    @Override
    public void deleteComment(Comment comment) throws Exception {
        COMMENTMAPPER.delete(comment.getId());
    }

    @Override
    public List<Comment> getCommentsByUser(User user) throws Exception {
        return COMMENTMAPPER.getAllByUser(user.getUsername());
    }

    @Override
    public String uploadImage(File file) throws Exception {
        return IMAGEMAPPER.upload(file);
    }

    @Override
    public List<Forum> searchForums(String searchStr) {
        return FORUMMAPPER.searchForums(searchStr);
    }

    @Override
    public void updateValidatedUser(User user) throws Exception {
        USERMAPPER.updateValidatedUser(user);
    }
}
