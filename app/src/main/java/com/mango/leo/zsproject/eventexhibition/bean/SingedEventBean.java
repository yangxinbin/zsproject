package com.mango.leo.zsproject.eventexhibition.bean;

/**
 * Created by admin on 2018/6/30.
 */

public class SingedEventBean {

    /**
     * timestamp : 1530343863064
     * status : 500
     * error : Internal Server Error
     * message : you hava already signed up for this event
     * path : /event/buy/free/ticket
     */

    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
