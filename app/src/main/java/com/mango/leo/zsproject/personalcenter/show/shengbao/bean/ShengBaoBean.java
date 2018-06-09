package com.mango.leo.zsproject.personalcenter.show.shengbao.bean;

/**
 * Created by admin on 2018/6/9.
 */

public class ShengBaoBean {
    private String name;
    private String ShengBaoName;

    public ShengBaoBean(String name, String shengBaoName) {
        this.name = name;
        ShengBaoName = shengBaoName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShengBaoName() {
        return ShengBaoName;
    }

    public void setShengBaoName(String shengBaoName) {
        ShengBaoName = shengBaoName;
    }
}
