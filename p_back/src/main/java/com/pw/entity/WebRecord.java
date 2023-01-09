package com.pw.entity;

public class WebRecord {
    private String recordId;
    private String recordType;
    private String recordTime;
    private String recordOperation;

    public WebRecord(String recordId, String recordType, String recordTime, String recordOperation) {
        this.recordId = recordId;
        this.recordType = recordType;
        this.recordTime = recordTime;
        this.recordOperation = recordOperation;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordOperation() {
        return recordOperation;
    }

    public void setRecordOperation(String recordOperation) {
        this.recordOperation = recordOperation;
    }

    @Override
    public String toString() {
        return "WebRecord{" +
                "recordId='" + recordId + '\'' +
                ", recordType='" + recordType + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", recordOperation='" + recordOperation + '\'' +
                '}';
    }
}
