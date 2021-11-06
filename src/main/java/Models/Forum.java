package Models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Forum {

    private int id;
    private Timestamp created;
    private String username;
    private String title;
    private String content;
    private String imageUrl;
    private ArrayList<Comment> comments;

    public Forum(String username, String title, String content, String imageUrl) {
        this.created = new Timestamp(new Date().getTime());
        this.username = username;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Forum(int id, Timestamp created, String username, String title,
                 String content, String imageUrl) {
        this.created = created;
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Forum(int id, String username, String title, String content,
                 String imageUrl, ArrayList<Comment> comments) {
        this.created = new Timestamp(new Date().getTime());
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
