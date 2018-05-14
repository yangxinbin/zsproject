package com.mango.leo.zsproject.industrialservice.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/5/11.
 */

public class AllItemBean implements Serializable {
    private String allItemName;
    private String allItemContent;

    public AllItemBean(String allItemName, String allItemContent) {
        this.allItemName = allItemName;
        this.allItemContent = allItemContent;
    }
    public String getAllItemName() {
        return allItemName;
    }

    public void setAllItemName(String allItemName) {
        this.allItemName = allItemName;
    }

    public String getAllItemContent() {
        return allItemContent;
    }

    public void setAllItemContent(String allItemContent) {
        this.allItemContent = allItemContent;
    }
    @Override
    public String toString() {
        return "AllItemBean{" +
                "allItemName='" + allItemName + '\'' +
                ", allItemContent='" + allItemContent + '\'' +
                '}';
    }
}
