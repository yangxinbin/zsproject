package com.mango.leo.zsproject.eventexhibition.bean;

/**
 * Created by admin on 2018/6/9.
 */

public class ShaiXuanEvent {
    private String timePast;
    private String timeFuture;
    private String city;
    private String typePay;

    public ShaiXuanEvent(String timePast, String timeFuture, String city, String typePay) {
        this.timePast = timePast;
        this.timeFuture = timeFuture;
        this.city = city;
        this.typePay = typePay;
    }

    public ShaiXuanEvent() {

    }

    public String getTimePast() {
        return timePast;
    }

    public void setTimePast(String timePast) {
        this.timePast = timePast;
    }

    public String getTimeFuture() {
        return timeFuture;
    }

    public void setTimeFuture(String timeFuture) {
        this.timeFuture = timeFuture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTypePay() {
        return typePay;
    }

    public void setTypePay(String typePay) {
        this.typePay = typePay;
    }
}
