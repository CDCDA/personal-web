package com.pw.entity;

public class BlogLabel {
    private String id;
    private String name;
    private String blogId;

    public BlogLabel(String id, String name, String blogId) {
        this.id = id;
        this.name = name;
        this.blogId = blogId;
    }


    @Override
    public String toString() {
        return "BlogLabel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogId=" + blogId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
}
