package com.pw.entity;

public class Blog {
    private int userId;
    private String blogTitle;
    private String blogText;
    private String blogId;
    private String blogCreateTime;
    private String blogUpdateTime;
    private String blogType;
    private String blogMk;

    public Blog(int userId, String blogTitle, String blogText, String blogId, String blogCreateTime, String blogUpdateTime, String blogType, String blogMk) {
        this.userId = userId;
        this.blogTitle = blogTitle;
        this.blogText = blogText;
        this.blogId = blogId;
        this.blogCreateTime = blogCreateTime;
        this.blogUpdateTime = blogUpdateTime;
        this.blogType = blogType;
        this.blogMk = blogMk;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "userId=" + userId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogText='" + blogText + '\'' +
                ", blogId='" + blogId + '\'' +
                ", blogCreateTime='" + blogCreateTime + '\'' +
                ", blogUpdateTime='" + blogUpdateTime + '\'' +
                ", blogType='" + blogType + '\'' +
                ", blogMk='" + blogMk + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(String blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }

    public String getBlogUpdateTime() {
        return blogUpdateTime;
    }

    public void setBlogUpdateTime(String blogUpdateTime) {
        this.blogUpdateTime = blogUpdateTime;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public String getBlogMk() {
        return blogMk;
    }

    public void setBlogMk(String blogMk) {
        this.blogMk = blogMk;
    }
}
