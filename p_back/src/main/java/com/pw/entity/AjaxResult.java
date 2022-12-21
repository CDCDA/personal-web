package com.pw.entity;

import java.util.List;

public class AjaxResult {
    private String code;
    private List<?> data;
    private String msg;
    private String success;
    private int total;

    public AjaxResult(String code, List<?> data, String msg, String success, int total) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = success;
        this.total = total;
    }

    public AjaxResult() {

    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", success='" + success + '\'' +
                ", total='" + total + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
