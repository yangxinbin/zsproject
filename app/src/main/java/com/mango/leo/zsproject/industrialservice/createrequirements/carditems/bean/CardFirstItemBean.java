package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by admin on 2018/5/16.
 */

public class CardFirstItemBean {
    //private String projectId;
    private String itemName;
    private String itemContent;
    private List<LocalMedia> itemImagePath;

    public CardFirstItemBean(/*String projectId,*/ String itemName, String itemContent, List<LocalMedia> itemImagePath) {
        //this.projectId = projectId;
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.itemImagePath = itemImagePath;
    }

    public CardFirstItemBean() {
    }

    /*public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }*/

    public String getItemContent() {

        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public List<LocalMedia> getItemImagePath() {
        return itemImagePath;
    }

    public void setItemImagePath(List<LocalMedia> itemImagePath) {
        this.itemImagePath = itemImagePath;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
