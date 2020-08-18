package com.maiia.test.jsondb;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "posts", schemaVersion= "1.0")
public class Post {
    @Id
    private String id;
    private String userId;
    private String title;
    private String body;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
