package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/14.
 */

public class ChangYe {


    public ChangYe(String chanYe, List<String> lingYu) {
        this.chanYe = chanYe;
        this.lingYu = lingYu;
    }

    private String chanYe;
    private List<String> lingYu;

    public String getChanYe() {
        return chanYe;
    }

    public void setChanYe(String chanYe) {
        this.chanYe = chanYe;
    }

    public List<String> getLingYu() {
        return lingYu;
    }

    public void setLingYu(List<String> lingYu) {
        this.lingYu = lingYu;
    }

    @Override
    public String toString() {
        return "ChangYe{" +
                "chanYe='" + chanYe + '\'' +
                ", lingYu=" + lingYu +
                '}';
    }
}
