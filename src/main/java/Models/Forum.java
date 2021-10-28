package Models;

import java.util.ArrayList;

public class Forum {

    private int id;
    private String username;
    private String title;
    private String content;
    private ArrayList<Comment> comments;

    public Forum(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Forum(int id, String username, String title, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Forum(int id, String username, String title, String content,
                 ArrayList<Comment> comments) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
