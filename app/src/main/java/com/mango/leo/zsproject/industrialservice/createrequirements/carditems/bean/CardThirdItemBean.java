package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

/**
 * Created by admin on 2018/5/16.
 */

public class CardThirdItemBean {
    //private String projectId;
    private String province;
    private String city;
    private String district;
    private String address;
    private String lon;
    private String lat;

    public CardThirdItemBean(String province, String city, String district, String address, String lon, String lat) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.lon = lon;
        this.lat = lat;
    }

    public CardThirdItemBean() {

    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "CardThirdItemBean{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
