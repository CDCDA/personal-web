package com.pw.entity;

import java.util.Date;

public class BlogRecord {
    private String blogId;
    private String blogName;
    private String recodeType;
    private String blogRecordId;
    private Date recordTime;
    
    private String recordOperation;

    public BlogRecord(String blogId, String blogName, String recodeType, String blogRecordId, Date recordTime) {
        this.blogId = blogId;
        this.blogName = blogName;
        this.recodeType = recodeType;
        this.blogRecordId = blogRecordId;
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "BlogRecord{" +
                "blogId='" + blogId + '\'' +
                ", blogName='" + blogName + '\'' +
                ", recodeType='" + recodeType + '\'' +
                ", blogRecordId='" + blogRecordId + '\'' +
                ", recordTime=" + recordTime +
                '}';
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getRecodeType() {
        return recodeType;
    }

    public void setRecodeType(String recodeType) {
        this.recodeType = recodeType;
    }

    public String getBlogRecordId() {
        return blogRecordId;
    }

    public void setBlogRecordId(String blogRecordId) {
        this.blogRecordId = blogRecordId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
