package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by admin on 2018/5/16.
 */

public class CardFirstItemBean {
    //private String projectId;
    private String itemName;
    private String departmentName;
    private String money;
    private String time;
    private String itemContent;

    public CardFirstItemBean(String itemName, String departmentName, String money, String time, String itemContent) {
        this.itemName = itemName;
        this.departmentName = departmentName;
        this.money = money;
        this.time = time;
        this.itemContent = itemContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CardFirstItemBean() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}
