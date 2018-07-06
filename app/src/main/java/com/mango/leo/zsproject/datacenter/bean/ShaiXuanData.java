package com.mango.leo.zsproject.datacenter.bean;

/**
 * Created by admin on 2018/6/9.
 */

public class ShaiXuanData {
    private String hangye;
    private String type;
    private String momey;
    private String way;


    public ShaiXuanData(String hangye, String type, String momey, String way) {
        this.hangye = hangye;
        this.type = type;
        this.momey = momey;
        this.way = way;
    }

    public String getHangye() {
        return hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMomey() {
        return momey;
    }

    public void setMomey(String momey) {
        this.momey = momey;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Override
    public String toString() {
        return "ShaiXuanData{" +
                "hangye='" + hangye + '\'' +
                ", type='" + type + '\'' +
                ", momey='" + momey + '\'' +
                ", way='" + way + '\'' +
                '}';
    }
}
