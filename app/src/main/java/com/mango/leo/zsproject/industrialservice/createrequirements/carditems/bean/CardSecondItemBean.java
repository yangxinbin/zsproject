package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public class CardSecondItemBean {
    private String changye;
    private List<String> lingyuList;

    public CardSecondItemBean(String changye, List<String> lingyuList) {
        this.changye = changye;
        this.lingyuList = lingyuList;
    }

    public CardSecondItemBean() {
        
    }

    public String getChanye() {
        return changye;
    }

    public void setChanye(String changye) {
        this.changye = changye;
    }

    public List<String> getLingyuList() {
        return lingyuList;
    }

    public void setLingyuList(List<String> lingyuList) {
        this.lingyuList = lingyuList;
    }

    @Override
    public String toString() {
        return "CardSecondItemBean{" +
                "changye='" + changye + '\'' +
                ", lingyuList=" + lingyuList +
                '}';
    }
}
