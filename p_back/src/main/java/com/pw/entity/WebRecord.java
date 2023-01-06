package com.pw.entity;

public class WebRecord {
    private String recordId;
    private String recordType;
    private String recordTime;
    private String operation;

    public WebRecord(String recordId, String recordType, String recordTime, String operation) {
        this.recordId = recordId;
        this.recordType = recordType;
        this.recordTime = recordTime;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "WebRecord{" +
                "recordId='" + recordId + '\'' +
                ", recordType='" + recordType + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", operation='" + operation + '\'' +
                '}';
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
