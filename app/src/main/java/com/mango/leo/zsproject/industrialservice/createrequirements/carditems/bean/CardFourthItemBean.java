package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by admin on 2018/5/16.
 */

public class CardFourthItemBean {
    private String projectId;
    private String name;
    private String company;
    private String phoneNumber;
    private String position;
    private String email;

    public CardFourthItemBean() {
    }

    public CardFourthItemBean(String name, String company, String phoneNumber, String position, String email,String projectId) {
        this.projectId = projectId;
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.email = email;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
