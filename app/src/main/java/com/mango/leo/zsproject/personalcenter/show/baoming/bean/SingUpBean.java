package com.mango.leo.zsproject.personalcenter.show.baoming.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/30.
 */

public class SingUpBean {

    /**
     * responseObject : {"content":[{"id":"5b374a87b0aeba14407e146a","event":{"id":"5b29d27df8aa4119909b38b9","name":"jetc","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"","lon":null,"lat":null},"price":0,"startTime":1529467440000,"endTime":1535092200000,"popular":false,"organizer":"科苑路东方科技大厦","banner":{"id":"5b29d265f8aa4119909b38b7","alias":"1527564707525.png","fileName":"15294674932316664.png","contentType":"image/png","size":12947,"createdOn":1529467493231},"coorganizers":["jrtc"],"industries":[{"id":"5b1f31c881c6d52b14c953c4","name":"饲料批发","parent":"批发和零售业","level":"two"},{"id":"5b1f31c881c6d52b14c953c5","name":"棉、麻批发","parent":"批发和零售业","level":"two"}],"detail":"","createdBy":"5b1cff92c119361dec0fa533","createdOn":null,"published":null,"tenant":"@super","favorite":false},"contact":{"id":null,"name":"yy","department":"yy","position":"y","mobile":"18318836309","phone":"18318836309","email":"yy"},"feePaid":0,"paymentMethod":null,"paymentOn":1530350215588,"purchasedBy":"5b2265c81233c546a8bead0e","wechatOpenId":null,"status":1,"numberOfTickets":1}],"pageable":{"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true},"last":true,"totalPages":1,"totalElements":6,"size":20,"number":0,"sort":{"sorted":false,"unsorted":true},"numberOfElements":6,"first":true}
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
         * content : [{"id":"5b374a87b0aeba14407e146a","event":{"id":"5b29d27df8aa4119909b38b9","name":"jetc","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"","lon":null,"lat":null},"price":0,"startTime":1529467440000,"endTime":1535092200000,"popular":false,"organizer":"科苑路东方科技大厦","banner":{"id":"5b29d265f8aa4119909b38b7","alias":"1527564707525.png","fileName":"15294674932316664.png","contentType":"image/png","size":12947,"createdOn":1529467493231},"coorganizers":["jrtc"],"industries":[{"id":"5b1f31c881c6d52b14c953c4","name":"饲料批发","parent":"批发和零售业","level":"two"},{"id":"5b1f31c881c6d52b14c953c5","name":"棉、麻批发","parent":"批发和零售业","level":"two"}],"detail":"","createdBy":"5b1cff92c119361dec0fa533","createdOn":null,"published":null,"tenant":"@super","favorite":false},"contact":{"id":null,"name":"yy","department":"yy","position":"y","mobile":"18318836309","phone":"18318836309","email":"yy"},"feePaid":0,"paymentMethod":null,"paymentOn":1530350215588,"purchasedBy":"5b2265c81233c546a8bead0e","wechatOpenId":null,"status":1,"numberOfTickets":1}]
         * pageable : {"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true}
         * last : true
         * totalPages : 1
         * totalElements : 6
         * size : 20
         * number : 0
         * sort : {"sorted":false,"unsorted":true}
         * numberOfElements : 6
         * first : true
         */

        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private int size;
        private int number;
        private SortBeanX sort;
        private int numberOfElements;
        private boolean first;
        private List<ContentBean> content;

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public SortBeanX getSort() {
            return sort;
        }

        public void setSort(SortBeanX sort) {
            this.sort = sort;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageableBean {
            /**
             * sort : {"sorted":false,"unsorted":true}
             * offset : 0
             * pageSize : 20
             * pageNumber : 0
             * unpaged : false
             * paged : true
             */

            private SortBean sort;
            private int offset;
            private int pageSize;
            private int pageNumber;
            private boolean unpaged;
            private boolean paged;

            public SortBean getSort() {
                return sort;
            }

            public void setSort(SortBean sort) {
                this.sort = sort;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }

            public static class SortBean {
                /**
                 * sorted : false
                 * unsorted : true
                 */

                private boolean sorted;
                private boolean unsorted;

                public boolean isSorted() {
                    return sorted;
                }

                public void setSorted(boolean sorted) {
                    this.sorted = sorted;
                }

                public boolean isUnsorted() {
                    return unsorted;
                }

                public void setUnsorted(boolean unsorted) {
                    this.unsorted = unsorted;
                }
            }
        }

        public static class SortBeanX {
            /**
             * sorted : false
             * unsorted : true
             */

            private boolean sorted;
            private boolean unsorted;

            public boolean isSorted() {
                return sorted;
            }

            public void setSorted(boolean sorted) {
                this.sorted = sorted;
            }

            public boolean isUnsorted() {
                return unsorted;
            }

            public void setUnsorted(boolean unsorted) {
                this.unsorted = unsorted;
            }
        }

        public static class ContentBean {
            /**
             * id : 5b374a87b0aeba14407e146a
             * event : {"id":"5b29d27df8aa4119909b38b9","name":"jetc","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"","lon":null,"lat":null},"price":0,"startTime":1529467440000,"endTime":1535092200000,"popular":false,"organizer":"科苑路东方科技大厦","banner":{"id":"5b29d265f8aa4119909b38b7","alias":"1527564707525.png","fileName":"15294674932316664.png","contentType":"image/png","size":12947,"createdOn":1529467493231},"coorganizers":["jrtc"],"industries":[{"id":"5b1f31c881c6d52b14c953c4","name":"饲料批发","parent":"批发和零售业","level":"two"},{"id":"5b1f31c881c6d52b14c953c5","name":"棉、麻批发","parent":"批发和零售业","level":"two"}],"detail":"","createdBy":"5b1cff92c119361dec0fa533","createdOn":null,"published":null,"tenant":"@super","favorite":false}
             * contact : {"id":null,"name":"yy","department":"yy","position":"y","mobile":"18318836309","phone":"18318836309","email":"yy"}
             * feePaid : 0
             * paymentMethod : null
             * paymentOn : 1530350215588
             * purchasedBy : 5b2265c81233c546a8bead0e
             * wechatOpenId : null
             * status : 1
             * numberOfTickets : 1
             */

            private String id;
            private EventBean event;
            private ContactBean contact;
            private int feePaid;
            private Object paymentMethod;
            private long paymentOn;
            private String purchasedBy;
            private Object wechatOpenId;
            private int status;
            private int numberOfTickets;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public EventBean getEvent() {
                return event;
            }

            public void setEvent(EventBean event) {
                this.event = event;
            }

            public ContactBean getContact() {
                return contact;
            }

            public void setContact(ContactBean contact) {
                this.contact = contact;
            }

            public int getFeePaid() {
                return feePaid;
            }

            public void setFeePaid(int feePaid) {
                this.feePaid = feePaid;
            }

            public Object getPaymentMethod() {
                return paymentMethod;
            }

            public void setPaymentMethod(Object paymentMethod) {
                this.paymentMethod = paymentMethod;
            }

            public long getPaymentOn() {
                return paymentOn;
            }

            public void setPaymentOn(long paymentOn) {
                this.paymentOn = paymentOn;
            }

            public String getPurchasedBy() {
                return purchasedBy;
            }

            public void setPurchasedBy(String purchasedBy) {
                this.purchasedBy = purchasedBy;
            }

            public Object getWechatOpenId() {
                return wechatOpenId;
            }

            public void setWechatOpenId(Object wechatOpenId) {
                this.wechatOpenId = wechatOpenId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getNumberOfTickets() {
                return numberOfTickets;
            }

            public void setNumberOfTickets(int numberOfTickets) {
                this.numberOfTickets = numberOfTickets;
            }

            public static class EventBean {
                /**
                 * id : 5b29d27df8aa4119909b38b9
                 * name : jetc
                 * location : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"","lon":null,"lat":null}
                 * price : 0.0
                 * startTime : 1529467440000
                 * endTime : 1535092200000
                 * popular : false
                 * organizer : 科苑路东方科技大厦
                 * banner : {"id":"5b29d265f8aa4119909b38b7","alias":"1527564707525.png","fileName":"15294674932316664.png","contentType":"image/png","size":12947,"createdOn":1529467493231}
                 * coorganizers : ["jrtc"]
                 * industries : [{"id":"5b1f31c881c6d52b14c953c4","name":"饲料批发","parent":"批发和零售业","level":"two"},{"id":"5b1f31c881c6d52b14c953c5","name":"棉、麻批发","parent":"批发和零售业","level":"two"}]
                 * detail :
                 * createdBy : 5b1cff92c119361dec0fa533
                 * createdOn : null
                 * published : null
                 * tenant : @super
                 * favorite : false
                 */

                private String id;
                private String name;
                private LocationBean location;
                private double price;
                private long startTime;
                private long endTime;
                private boolean popular;
                private String organizer;
                private BannerBean banner;
                private String detail;
                private String createdBy;
                private Object createdOn;
                private Object published;
                private String tenant;
                private boolean favorite;
                private List<String> coorganizers;
                private List<IndustriesBean> industries;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public LocationBean getLocation() {
                    return location;
                }

                public void setLocation(LocationBean location) {
                    this.location = location;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public long getStartTime() {
                    return startTime;
                }

                public void setStartTime(long startTime) {
                    this.startTime = startTime;
                }

                public long getEndTime() {
                    return endTime;
                }

                public void setEndTime(long endTime) {
                    this.endTime = endTime;
                }

                public boolean isPopular() {
                    return popular;
                }

                public void setPopular(boolean popular) {
                    this.popular = popular;
                }

                public String getOrganizer() {
                    return organizer;
                }

                public void setOrganizer(String organizer) {
                    this.organizer = organizer;
                }

                public BannerBean getBanner() {
                    return banner;
                }

                public void setBanner(BannerBean banner) {
                    this.banner = banner;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public Object getCreatedOn() {
                    return createdOn;
                }

                public void setCreatedOn(Object createdOn) {
                    this.createdOn = createdOn;
                }

                public Object getPublished() {
                    return published;
                }

                public void setPublished(Object published) {
                    this.published = published;
                }

                public String getTenant() {
                    return tenant;
                }

                public void setTenant(String tenant) {
                    this.tenant = tenant;
                }

                public boolean isFavorite() {
                    return favorite;
                }

                public void setFavorite(boolean favorite) {
                    this.favorite = favorite;
                }

                public List<String> getCoorganizers() {
                    return coorganizers;
                }

                public void setCoorganizers(List<String> coorganizers) {
                    this.coorganizers = coorganizers;
                }

                public List<IndustriesBean> getIndustries() {
                    return industries;
                }

                public void setIndustries(List<IndustriesBean> industries) {
                    this.industries = industries;
                }

                public static class LocationBean {
                    /**
                     * country : 中国
                     * province : 广东省
                     * city : 深圳
                     * district : 深圳
                     * address :
                     * lon : null
                     * lat : null
                     */

                    private String country;
                    private String province;
                    private String city;
                    private String district;
                    private String address;
                    private Object lon;
                    private Object lat;

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

                public static class BannerBean {
                    /**
                     * id : 5b29d265f8aa4119909b38b7
                     * alias : 1527564707525.png
                     * fileName : 15294674932316664.png
                     * contentType : image/png
                     * size : 12947
                     * createdOn : 1529467493231
                     */

                    private String id;
                    private String alias;
                    private String fileName;
                    private String contentType;
                    private int size;
                    private long createdOn;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getAlias() {
                        return alias;
                    }

                    public void setAlias(String alias) {
                        this.alias = alias;
                    }

                    public String getFileName() {
                        return fileName;
                    }

                    public void setFileName(String fileName) {
                        this.fileName = fileName;
                    }

                    public String getContentType() {
                        return contentType;
                    }

                    public void setContentType(String contentType) {
                        this.contentType = contentType;
                    }

                    public int getSize() {
                        return size;
                    }

                    public void setSize(int size) {
                        this.size = size;
                    }

                    public long getCreatedOn() {
                        return createdOn;
                    }

                    public void setCreatedOn(long createdOn) {
                        this.createdOn = createdOn;
                    }
                }

                public static class IndustriesBean {
                    /**
                     * id : 5b1f31c881c6d52b14c953c4
                     * name : 饲料批发
                     * parent : 批发和零售业
                     * level : two
                     */

                    private String id;
                    private String name;
                    private String parent;
                    private String level;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getParent() {
                        return parent;
                    }

                    public void setParent(String parent) {
                        this.parent = parent;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }
                }
            }

            public static class ContactBean {
                /**
                 * id : null
                 * name : yy
                 * department : yy
                 * position : y
                 * mobile : 18318836309
                 * phone : 18318836309
                 * email : yy
                 */

                private Object id;
                private String name;
                private String department;
                private String position;
                private String mobile;
                private String phone;
                private String email;

                public Object getId() {
                    return id;
                }

                public void setId(Object id) {
                    this.id = id;
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

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }
            }
        }
    }
}
