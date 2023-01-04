package com.pw.entity;

public class BlogType {
    private int id;
    private int parentId;
    private String name;
    private int userId;

    public BlogType(int id, int parentId, String name, int userId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
