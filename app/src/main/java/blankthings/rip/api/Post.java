package blankthings.rip.api;

/**
 * Created by iosifvilcea on 6/22/16.
 */
public class Post {

    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(final int userId, final int id, final String title, final String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(final Post post) {
        this.userId = post.getUserId();
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
