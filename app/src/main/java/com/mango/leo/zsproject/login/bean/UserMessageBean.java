package com.mango.leo.zsproject.login.bean;

/**
 * Created by admin on 2018/6/2.
 */

public class UserMessageBean {

    /**
     * responseObject : {"id":"5b10f1b2bc2ab90f3c3cff15","username":"13417304551","name":"tom","company":"芒果","position":"深圳","department":"市场","email":"295249259@qq.com","type":"0","avator":null,"location":{"country":"中国","province":"广东","city":"深圳","district":"南山","address":"东方科技大厦","lon":34.1,"lat":45.2},"govtTenant":null,"token":"eyJhbGciOiJIUzUxMiJ9.eyJnb3Z0VGVuYW50IjpudWxsLCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1Mjc4MzgzMTE1OTksImlkIjoiNWIxMGYxYjJiYzJhYjkwZjNjM2NmZjE1IiwiZXhwIjo0MTE5ODM4MzExLCJ1c2VybmFtZSI6IjEzNDE3MzA0NTUwIn0.xKfy-0sxpSgyNOmPcrP0JFet1KlZokOBDKRMXBCnx1jkTTb5_vJBZCghimy6plEqnWMgLLb9GDUHVQ1v9CzNkA"}
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
         * name : tom
         * company : 芒果
         * position : 深圳
         * department : 市场
         * email : 295249259@qq.com
         * type : 0
         * avator : null
         * location : {"country":"中国","province":"广东","city":"深圳","district":"南山","address":"东方科技大厦","lon":34.1,"lat":45.2}
         * govtTenant : null
         * token : eyJhbGciOiJIUzUxMiJ9.eyJnb3Z0VGVuYW50IjpudWxsLCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1Mjc4MzgzMTE1OTksImlkIjoiNWIxMGYxYjJiYzJhYjkwZjNjM2NmZjE1IiwiZXhwIjo0MTE5ODM4MzExLCJ1c2VybmFtZSI6IjEzNDE3MzA0NTUwIn0.xKfy-0sxpSgyNOmPcrP0JFet1KlZokOBDKRMXBCnx1jkTTb5_vJBZCghimy6plEqnWMgLLb9GDUHVQ1v9CzNkA
         */

        private String id;
        private String username;
        private String name;
        private String company;
        private String position;
        private String department;
        private String email;
        private String type;
        private Object avator;
        private LocationBean location;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getAvator() {
            return avator;
        }

        public void setAvator(Object avator) {
            this.avator = avator;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
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

        public static class LocationBean {
            /**
             * country : 中国
             * province : 广东
             * city : 深圳
             * district : 南山
             * address : 东方科技大厦
             * lon : 34.1
             * lat : 45.2
             */

            private String country;
            private String province;
            private String city;
            private String district;
            private String address;
            private double lon;
            private double lat;

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

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }
}
