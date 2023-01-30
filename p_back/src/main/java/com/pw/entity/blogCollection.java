package com.pw.entity;

public class blogCollection {
    private int authorId;
    private int blogId;

    private int blogCollectionId;

    private String blogName;

    public blogCollection(int authorId, int blogId, int blogCollectionId, String blogName) {
        this.authorId = authorId;
        this.blogId = blogId;
        this.blogCollectionId = blogCollectionId;
        this.blogName = blogName;
    }

    @Override
    public String toString() {
        return "blogCollection{" +
                "authorId=" + authorId +
                ", blogId=" + blogId +
                ", blogCollectionId=" + blogCollectionId +
                ", blogName='" + blogName + '\'' +
                '}';
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getBlogCollectionId() {
        return blogCollectionId;
    }

    public void setBlogCollectionId(int blogCollectionId) {
        this.blogCollectionId = blogCollectionId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }
}
