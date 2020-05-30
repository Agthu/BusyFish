package data;

import java.io.Serializable;

public class Comment implements Serializable{
    public static final long serialVersionUID = 1L;
    private int commentId;
    private String publisher_id;
    private String publisher_name;
    private String content;
    private int productId;

    public Comment(int commentId, String publisher_id, String content) {
        this.commentId = commentId;
        this.publisher_id = publisher_id;
        this.content = content;
    }

    public Comment(int productId, String content) {
        this.productId = productId;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getProductId() {
        return productId;
    }
}
