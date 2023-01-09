package com.pw.entity;

import java.util.Date;

public class BlogRecord {

    private String blogId;
    private String blogName;
    private String recordType;
    private String blogRecordId;
    private String recordTime;

    private String userId;
    
    private String recordOperation;

    public BlogRecord(String blogId, String blogName, String recordType, String blogRecordId, String recordTime, String userId, String recordOperation) {
        this.blogId = blogId;
        this.blogName = blogName;
        this.recordType = recordType;
        this.blogRecordId = blogRecordId;
        this.recordTime = recordTime;
        this.userId = userId;
        this.recordOperation = recordOperation;
    }

    @Override
    public String toString() {
        return "BlogRecord{" +
                "blogId='" + blogId + '\'' +
                ", blogName='" + blogName + '\'' +
                ", recodeType='" + recordType + '\'' +
                ", blogRecordId='" + blogRecordId + '\'' +
                ", recordTime=" + recordTime +
                ", user_id='" + userId + '\'' +
                ", recordOperation='" + recordOperation + '\'' +
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

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recodeType) {
        this.recordType = recordType;
    }

    public String getBlogRecordId() {
        return blogRecordId;
    }

    public void setBlogRecordId(String blogRecordId) {
        this.blogRecordId = blogRecordId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }

    public String getRecordOperation() {
        return recordOperation;
    }

    public void setRecordOperation(String recordOperation) {
        this.recordOperation = recordOperation;
    }
}
