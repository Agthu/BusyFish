package data;

import java.io.Serializable;

public class Comment implements Serializable {
    private String publisher_id;
    private String publisher_name;
    private String content;

    public Comment(String publisher_id, String publisher_name, String content) {
        this.publisher_id = publisher_id;
        this.publisher_name = publisher_name;
        this.content = content;
    }
}
