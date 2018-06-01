package com.mango.leo.zsproject.login.bean;

/**
 * Created by admin on 2018/5/18.
 */

public class UserPhone {
    private String phoneN;
    private String phoneC;

    public String getPhoneN() {
        return phoneN;
    }

    public void setPhoneN(String phoneN) {
        this.phoneN = phoneN;
    }

    public String getPhoneC() {
        return phoneC;
    }

    public void setPhoneC(String phoneC) {
        this.phoneC = phoneC;
    }

    public UserPhone(String phoneN, String phoneC) {
        this.phoneN = phoneN;
        this.phoneC = phoneC;
    }
}
