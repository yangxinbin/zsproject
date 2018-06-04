package com.mango.leo.zsproject.login.bean;

/**
 * Created by admin on 2018/6/4.
 */

public class BeForeUserMesBean {
    private String token;
    private String username;
    private String company;
    private String name;
    private String department;
    private String email;
    private String position;
    private String type;
    private String country;
    private String province;
    private String city;
    private String district;
    private String address;
    private String lon;
    private String lat;

    public BeForeUserMesBean(String token, String username, String company, String name, String department, String email, String position, String type, String country, String province, String city, String district, String address, String lon, String lat) {
        this.token = token;
        this.username = username;
        this.company = company;
        this.name = name;
        this.department = department;
        this.email = email;
        this.position = position;
        this.type = type;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.lon = lon;
        this.lat = lat;
    }

    public BeForeUserMesBean() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
}
