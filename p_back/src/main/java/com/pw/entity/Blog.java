package com.pw.entity;

import java.util.List;

public class Blog {
    private int userId;
    private String blogTitle;
    private String blogText;
    private String blogId;
    private String blogCreateTime;
    private String blogUpdateTime;
    private String blogType;
    private List<BlogLabel> blogLabelList;
    private String blogLabel;
    private String blogMk;

    private int blogViewCount;

    private int blogCollectCount;
    private int blogPraiseCount;
    private int blogUnPraiseCount;

    public Blog(int userId, String blogTitle, String blogText, String blogId, String blogCreateTime, String blogUpdateTime, String blogType, String blogMk, int blogViewCount, int blogCollectCount, int blogPraiseCount, int blogUnPraiseCount) {
        this.userId = userId;
        this.blogTitle = blogTitle;
        this.blogText = blogText;
        this.blogId = blogId;
        this.blogCreateTime = blogCreateTime;
        this.blogUpdateTime = blogUpdateTime;
        this.blogType = blogType;
        this.blogMk = blogMk;
        this.blogViewCount = blogViewCount;
        this.blogCollectCount = blogCollectCount;
        this.blogPraiseCount = blogPraiseCount;
        this.blogUnPraiseCount = blogUnPraiseCount;
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
                ", blogLabelList=" + blogLabelList +
                ", blogLabel='" + blogLabel + '\'' +
                ", blogMk='" + blogMk + '\'' +
                ", blogViewCount=" + blogViewCount +
                ", blogCollectCount=" + blogCollectCount +
                ", blogPraiseCount=" + blogPraiseCount +
                ", blogUnPraiseCount=" + blogUnPraiseCount +
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

    public List<BlogLabel> getBlogLabelList() {
        return blogLabelList;
    }

    public void setBlogLabelList(List<BlogLabel> blogLabelList) {
        this.blogLabelList = blogLabelList;
    }

    public String getBlogLabel() {
        return blogLabel;
    }

    public void setBlogLabel(String blogLabel) {
        this.blogLabel = blogLabel;
    }

    public String getBlogMk() {
        return blogMk;
    }

    public void setBlogMk(String blogMk) {
        this.blogMk = blogMk;
    }

    public int getBlogViewCount() {
        return blogViewCount;
    }

    public void setBlogViewCount(int blogViewCount) {
        this.blogViewCount = blogViewCount;
    }

    public int getBlogCollectCount() {
        return blogCollectCount;
    }

    public void setBlogCollectCount(int blogCollectCount) {
        this.blogCollectCount = blogCollectCount;
    }

    public int getBlogPraiseCount() {
        return blogPraiseCount;
    }

    public void setBlogPraiseCount(int blogPraiseCount) {
        this.blogPraiseCount = blogPraiseCount;
    }

    public int getBlogUnPraiseCount() {
        return blogUnPraiseCount;
    }

    public void setBlogUnPraiseCount(int blogUnPraiseCount) {
        this.blogUnPraiseCount = blogUnPraiseCount;
    }
}
