package com.mango.leo.zsproject.login.bean;

/**
 * Created by admin on 2018/6/2.
 */

public class UserMessageBean {

    /**
     * responseObject : {"id":"5b165741bc2ab91bdf66afa2","username":"18318836309","name":"tt","company":"tt","position":"杭州市","department":"tt","email":"tt","type":null,"avator":{"id":"5b165bd6bc2ab941d047a170","alias":null,"fileName":"crop_photo.jpg","contentType":null,"size":0,"createdOn":0},"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"tenant":null,"token":"eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1MjgxOTE5OTMwMzAsImlkIjoiNWIxNjU3NDFiYzJhYjkxYmRmNjZhZmEyIiwiZXhwIjo0MTIwMTkxOTkzLCJ0ZW5hbnQiOm51bGwsInVzZXJuYW1lIjoiMTgzMTg4MzYzMDkifQ.S0wv6sSS4F_EPYlVz7uv6kILNHuxJuHkP7cZbTrYYIa9RGnrEvK7ySpfTSlioiO8y_RS_ADNhqROcjn15UbQzA"}
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
         * id : 5b165741bc2ab91bdf66afa2
         * username : 18318836309
         * name : tt
         * company : tt
         * position : 杭州市
         * department : tt
         * email : tt
         * type : null
         * avator : {"id":"5b165bd6bc2ab941d047a170","alias":null,"fileName":"crop_photo.jpg","contentType":null,"size":0,"createdOn":0}
         * location : {"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null}
         * tenant : null
         * token : eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1MjgxOTE5OTMwMzAsImlkIjoiNWIxNjU3NDFiYzJhYjkxYmRmNjZhZmEyIiwiZXhwIjo0MTIwMTkxOTkzLCJ0ZW5hbnQiOm51bGwsInVzZXJuYW1lIjoiMTgzMTg4MzYzMDkifQ.S0wv6sSS4F_EPYlVz7uv6kILNHuxJuHkP7cZbTrYYIa9RGnrEvK7ySpfTSlioiO8y_RS_ADNhqROcjn15UbQzA
         */

        private String id;
        private String username;
        private String name;
        private String company;
        private String position;
        private String department;
        private String email;
        private Object type;
        private AvatorBean avator;
        private LocationBean location;
        private Object tenant;
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

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
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

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public AvatorBean getAvator() {
            return avator;
        }

        public void setAvator(AvatorBean avator) {
            this.avator = avator;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public Object getTenant() {
            return tenant;
        }

        public void setTenant(Object tenant) {
            this.tenant = tenant;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class AvatorBean {
            /**
             * id : 5b165bd6bc2ab941d047a170
             * alias : null
             * fileName : crop_photo.jpg
             * contentType : null
             * size : 0
             * createdOn : 0
             */

            private String id;
            private Object alias;
            private String fileName;
            private Object contentType;
            private int size;
            private int createdOn;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getAlias() {
                return alias;
            }

            public void setAlias(Object alias) {
                this.alias = alias;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public Object getContentType() {
                return contentType;
            }

            public void setContentType(Object contentType) {
                this.contentType = contentType;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getCreatedOn() {
                return createdOn;
            }

            public void setCreatedOn(int createdOn) {
                this.createdOn = createdOn;
            }
        }

        public static class LocationBean {
            /**
             * country : null
             * province : null
             * city : null
             * district : null
             * address : null
             * lon : null
             * lat : null
             */

            private Object country;
            private Object province;
            private Object city;
            private Object district;
            private Object address;
            private Object lon;
            private Object lat;

            public Object getCountry() {
                return country;
            }

            public void setCountry(Object country) {
                this.country = country;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getDistrict() {
                return district;
            }

            public void setDistrict(Object district) {
                this.district = district;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getLon() {
                return lon;
            }

            public void setLon(Object lon) {
                this.lon = lon;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }
        }
    }
}
