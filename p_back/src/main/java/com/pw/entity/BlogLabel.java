package com.pw.entity;

public class BlogLabel {
    private int id;
    private String name;
    private int blogId;

    public BlogLabel(int id, String name, int blogId) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}
