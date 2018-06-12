package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/16.
 */

public class CardNinthItemBean {
    private String moshi;
    private String money;
    private List<String> why;
    private List<String> type;
    private String qita;

    public CardNinthItemBean(String moshi, String money, List<String> why, List<String> type, String qita) {
        this.moshi = moshi;
        this.money = money;
        this.why = why;
        this.type = type;
        this.qita = qita;
    }

    public CardNinthItemBean() {

    }

    public String getMoshi() {
        return moshi;
    }

    public void setMoshi(String moshi) {
        this.moshi = moshi;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<String> getWhy() {
        return why;
    }

    public void setWhy(List<String> why) {
        this.why = why;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getQita() {
        return qita;
    }

    public void setQita(String qita) {
        this.qita = qita;
    }

    @Override
    public String toString() {
        return "CardNinthItemBean{" +
                "moshi='" + moshi + '\'' +
                ", money='" + money + '\'' +
                ", why=" + why +
                ", type=" + type +
                ", qita='" + qita + '\'' +
                '}';
    }
}
