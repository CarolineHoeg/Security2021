package Models;

public class Comment {

    private int id;
    private String username;
    private int forumId;
    private String content;

    public Comment(String username, int forumId, String content){
        this.username = username;
        this.forumId = forumId;
        this.content = content;
    }

    public Comment(int id, String username, int forumId, String content){
        this.id = id;
        this.username = username;
        this.forumId = forumId;
        this.content = content;
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

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
