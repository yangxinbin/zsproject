package com.mango.leo.zsproject.login.bean;

/**
 * Created by admin on 2018/6/1.
 */

public class TokenFromLonginBean {

    /**
     * responseObject : {"id":"5b10f1b2bc2ab90f3c3cff15","username":"13417304551","name":null,"company":null,"position":null,"department":null,"email":null,"type":null,"avator":null,"location":null,"govtTenant":null,"token":"eyJhbGciOiJIUzUxMiJ9.eyJnb3Z0VGVuYW50IjpudWxsLCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1Mjc4Mzc2NTk0NjgsImlkIjoiNWIxMGYxYjJiYzJhYjkwZjNjM2NmZjE1IiwiZXhwIjo0MTE5ODM3NjU5LCJ1c2VybmFtZSI6IjEzNDE3MzA0NTUwIn0.x2rJgnrEd-t7a1UUWxiYdM2jbDc0sdj36NbVxrdevVPilSo4_zSjAh1cNwx1l4PbklnS_Ugmhw8DHuiGAzwpwA"}
     * responseList : null
     * totalRecords : null
     * currentPage : null
     * totalPages : null
     */

    private ResponseObjectBean responseObject;
    private Object responseList;
    private Object totalRecords;
    private Object currentPage;
    private Object totalPages;

    public ResponseObjectBean getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(ResponseObjectBean responseObject) {
        this.responseObject = responseObject;
    }

    public Object getResponseList() {
        return responseList;
    }

    public void setResponseList(Object responseList) {
        this.responseList = responseList;
    }

    public Object getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Object totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Object getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Object currentPage) {
        this.currentPage = currentPage;
    }

    public Object getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Object totalPages) {
        this.totalPages = totalPages;
    }

    public static class ResponseObjectBean {
        /**
         * id : 5b10f1b2bc2ab90f3c3cff15
         * username : 13417304551
         * name : null
         * company : null
         * position : null
         * department : null
         * email : null
         * type : null
         * avator : null
         * location : null
         * govtTenant : null
         * token : eyJhbGciOiJIUzUxMiJ9.eyJnb3Z0VGVuYW50IjpudWxsLCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1Mjc4Mzc2NTk0NjgsImlkIjoiNWIxMGYxYjJiYzJhYjkwZjNjM2NmZjE1IiwiZXhwIjo0MTE5ODM3NjU5LCJ1c2VybmFtZSI6IjEzNDE3MzA0NTUwIn0.x2rJgnrEd-t7a1UUWxiYdM2jbDc0sdj36NbVxrdevVPilSo4_zSjAh1cNwx1l4PbklnS_Ugmhw8DHuiGAzwpwA
         */

        private String id;
        private String username;
        private Object name;
        private Object company;
        private Object position;
        private Object department;
        private Object email;
        private Object type;
        private Object avator;
        private Object location;
        private Object govtTenant;
        private String token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Object getDepartment() {
            return department;
        }

        public void setDepartment(Object department) {
            this.department = department;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getAvator() {
            return avator;
        }

        public void setAvator(Object avator) {
            this.avator = avator;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getGovtTenant() {
            return govtTenant;
        }

        public void setGovtTenant(Object govtTenant) {
            this.govtTenant = govtTenant;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
