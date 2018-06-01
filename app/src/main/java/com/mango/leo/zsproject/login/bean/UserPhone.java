package com.mango.leo.zsproject.login.bean;

/**
 * Created by admin on 2018/5/18.
 */

public class UserPhone {
    private String phoneN;
    private String phoneC;
    private String phonePwd;

    public UserPhone(String phoneN, String phoneC, String phonePwd) {
        this.phoneN = phoneN;
        this.phoneC = phoneC;
        this.phonePwd = phonePwd;
    }

    public UserPhone(String string, String string1) {
        this.phoneN = string;
        this.phoneC = string1;
    }

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

    public String getPhonePwd() {
        return phonePwd;
    }

    public void setPhonePwd(String phonePwd) {
        this.phonePwd = phonePwd;
    }
}
