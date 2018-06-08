package com.mango.leo.zsproject.personalcenter.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/8.
 */

public class MyEventBean {

    /**
     * responseObject : {"content":[{"id":"5b19e126bc2ab953ac4f8fb1","createdBy":"5b191b23bc2ab90367d15e8c","entity":{"_id":{"timestamp":1528168224,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683336,"timeSecond":1528168224,"date":1528168224000,"time":1528168224000},"name":"test from josn","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong"},"price":0,"startTime":1528128000000,"endTime":1529424000000,"popular":true,"organizer":"kk","banner":{"_id":{"timestamp":1528168188,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683334,"timeSecond":1528168188,"date":1528168188000,"time":1528168188000},"alias":"1527563930588.png","fileName":"15281681883213574.png","contentType":"image/png","size":12947,"createdOn":1528168188321},"coorganizers":["mangosis"],"relatedIndustries":["it"],"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}},{"id":"5b19f361bc2ab953ac4f8fb4","createdBy":"5b191b23bc2ab90367d15e8c","entity":{"_id":{"timestamp":1528111996,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384312,"timeSecond":1528111996,"date":1528111996000,"time":1528111996000},"name":"test from kk","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong"},"price":0,"startTime":1528041600000,"endTime":1530115200000,"popular":false,"organizer":"kk","banner":{"_id":{"timestamp":1528112132,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384316,"timeSecond":1528112132,"date":1528112132000,"time":1528112132000},"alias":"1527564707525.png","fileName":"15281121321544994.png","contentType":"image/png","size":12947,"createdOn":1528112132154},"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}},{"id":"5b19f364bc2ab953ac4f8fb5","createdBy":"5b191b23bc2ab90367d15e8c","entity":{"_id":{"timestamp":1528112036,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384314,"timeSecond":1528112036,"date":1528112036000,"time":1528112036000},"name":"test from ali","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"深圳"},"price":99,"startTime":1528041600000,"endTime":1530115200000,"popular":false,"organizer":"ali","banner":{"_id":{"timestamp":1528112153,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384322,"timeSecond":1528112153,"date":1528112153000,"time":1528112153000},"alias":"1527563930588.png","fileName":"15281121539442973.png","contentType":"image/png","size":12947,"createdOn":1528112153944},"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}}],"pageable":{"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false},"last":true,"totalPages":1,"totalElements":3,"first":true,"sort":{"unsorted":true,"sorted":false},"numberOfElements":3,"size":20,"number":0}
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
         * content : [{"id":"5b19e126bc2ab953ac4f8fb1","createdBy":"5b191b23bc2ab90367d15e8c","entity":{"_id":{"timestamp":1528168224,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683336,"timeSecond":1528168224,"date":1528168224000,"time":1528168224000},"name":"test from josn","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong"},"price":0,"startTime":1528128000000,"endTime":1529424000000,"popular":true,"organizer":"kk","banner":{"_id":{"timestamp":1528168188,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683334,"timeSecond":1528168188,"date":1528168188000,"time":1528168188000},"alias":"1527563930588.png","fileName":"15281681883213574.png","contentType":"image/png","size":12947,"createdOn":1528168188321},"coorganizers":["mangosis"],"relatedIndustries":["it"],"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}},{"id":"5b19f361bc2ab953ac4f8fb4","createdBy":"5b191b23bc2ab90367d15e8c","entity":{"_id":{"timestamp":1528111996,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384312,"timeSecond":1528111996,"date":1528111996000,"time":1528111996000},"name":"test from kk","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong"},"price":0,"startTime":1528041600000,"endTime":1530115200000,"popular":false,"organizer":"kk","banner":{"_id":{"timestamp":1528112132,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384316,"timeSecond":1528112132,"date":1528112132000,"time":1528112132000},"alias":"1527564707525.png","fileName":"15281121321544994.png","contentType":"image/png","size":12947,"createdOn":1528112132154},"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}},{"id":"5b19f364bc2ab953ac4f8fb5","createdBy":"5b191b23bc2ab90367d15e8c","entity":{"_id":{"timestamp":1528112036,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384314,"timeSecond":1528112036,"date":1528112036000,"time":1528112036000},"name":"test from ali","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"深圳"},"price":99,"startTime":1528041600000,"endTime":1530115200000,"popular":false,"organizer":"ali","banner":{"_id":{"timestamp":1528112153,"machineIdentifier":8505045,"processIdentifier":16668,"counter":10384322,"timeSecond":1528112153,"date":1528112153000,"time":1528112153000},"alias":"1527563930588.png","fileName":"15281121539442973.png","contentType":"image/png","size":12947,"createdOn":1528112153944},"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}}]
         * pageable : {"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false}
         * last : true
         * totalPages : 1
         * totalElements : 3
         * first : true
         * sort : {"unsorted":true,"sorted":false}
         * numberOfElements : 3
         * size : 20
         * number : 0
         */

        private PageableBean pageable;
        private boolean last;
        private int totalPages;
        private int totalElements;
        private boolean first;
        private SortBeanX sort;
        private int numberOfElements;
        private int size;
        private int number;
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

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
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

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageableBean {
            /**
             * sort : {"unsorted":true,"sorted":false}
             * pageSize : 20
             * pageNumber : 0
             * offset : 0
             * paged : true
             * unpaged : false
             */

            private SortBean sort;
            private int pageSize;
            private int pageNumber;
            private int offset;
            private boolean paged;
            private boolean unpaged;

            public SortBean getSort() {
                return sort;
            }

            public void setSort(SortBean sort) {
                this.sort = sort;
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

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public static class SortBean {
                /**
                 * unsorted : true
                 * sorted : false
                 */

                private boolean unsorted;
                private boolean sorted;

                public boolean isUnsorted() {
                    return unsorted;
                }

                public void setUnsorted(boolean unsorted) {
                    this.unsorted = unsorted;
                }

                public boolean isSorted() {
                    return sorted;
                }

                public void setSorted(boolean sorted) {
                    this.sorted = sorted;
                }
            }
        }

        public static class SortBeanX {
            /**
             * unsorted : true
             * sorted : false
             */

            private boolean unsorted;
            private boolean sorted;

            public boolean isUnsorted() {
                return unsorted;
            }

            public void setUnsorted(boolean unsorted) {
                this.unsorted = unsorted;
            }

            public boolean isSorted() {
                return sorted;
            }

            public void setSorted(boolean sorted) {
                this.sorted = sorted;
            }
        }

        public static class ContentBean {
            /**
             * id : 5b19e126bc2ab953ac4f8fb1
             * createdBy : 5b191b23bc2ab90367d15e8c
             * entity : {"_id":{"timestamp":1528168224,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683336,"timeSecond":1528168224,"date":1528168224000,"time":1528168224000},"name":"test from josn","location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong"},"price":0,"startTime":1528128000000,"endTime":1529424000000,"popular":true,"organizer":"kk","banner":{"_id":{"timestamp":1528168188,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683334,"timeSecond":1528168188,"date":1528168188000,"time":1528168188000},"alias":"1527563930588.png","fileName":"15281681883213574.png","contentType":"image/png","size":12947,"createdOn":1528168188321},"coorganizers":["mangosis"],"relatedIndustries":["it"],"introductions":[{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}],"tenant":"@super"}
             */

            private String id;
            private String createdBy;
            private EntityBean entity;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public EntityBean getEntity() {
                return entity;
            }

            public void setEntity(EntityBean entity) {
                this.entity = entity;
            }

            public static class EntityBean {
                /**
                 * _id : {"timestamp":1528168224,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683336,"timeSecond":1528168224,"date":1528168224000,"time":1528168224000}
                 * name : test from josn
                 * location : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong"}
                 * price : 0
                 * startTime : 1528128000000
                 * endTime : 1529424000000
                 * popular : true
                 * organizer : kk
                 * banner : {"_id":{"timestamp":1528168188,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683334,"timeSecond":1528168188,"date":1528168188000,"time":1528168188000},"alias":"1527563930588.png","fileName":"15281681883213574.png","contentType":"image/png","size":12947,"createdOn":1528168188321}
                 * coorganizers : ["mangosis"]
                 * relatedIndustries : ["it"]
                 * introductions : [{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false}]
                 * tenant : @super
                 */

                private IdBean _id;
                private String name;
                private LocationBean location;
                private int price;
                private long startTime;
                private long endTime;
                private boolean popular;
                private String organizer;
                private BannerBean banner;
                private String tenant;
                private List<String> coorganizers;
                private List<String> relatedIndustries;
                private List<IntroductionsBean> introductions;

                public IdBean get_id() {
                    return _id;
                }

                public void set_id(IdBean _id) {
                    this._id = _id;
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

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
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

                public String getTenant() {
                    return tenant;
                }

                public void setTenant(String tenant) {
                    this.tenant = tenant;
                }

                public List<String> getCoorganizers() {
                    return coorganizers;
                }

                public void setCoorganizers(List<String> coorganizers) {
                    this.coorganizers = coorganizers;
                }

                public List<String> getRelatedIndustries() {
                    return relatedIndustries;
                }

                public void setRelatedIndustries(List<String> relatedIndustries) {
                    this.relatedIndustries = relatedIndustries;
                }

                public List<IntroductionsBean> getIntroductions() {
                    return introductions;
                }

                public void setIntroductions(List<IntroductionsBean> introductions) {
                    this.introductions = introductions;
                }

                public static class IdBean {
                    /**
                     * timestamp : 1528168224
                     * machineIdentifier : 8505045
                     * processIdentifier : 3380
                     * counter : 6683336
                     * timeSecond : 1528168224
                     * date : 1528168224000
                     * time : 1528168224000
                     */

                    private int timestamp;
                    private int machineIdentifier;
                    private int processIdentifier;
                    private int counter;
                    private int timeSecond;
                    private long date;
                    private long time;

                    public int getTimestamp() {
                        return timestamp;
                    }

                    public void setTimestamp(int timestamp) {
                        this.timestamp = timestamp;
                    }

                    public int getMachineIdentifier() {
                        return machineIdentifier;
                    }

                    public void setMachineIdentifier(int machineIdentifier) {
                        this.machineIdentifier = machineIdentifier;
                    }

                    public int getProcessIdentifier() {
                        return processIdentifier;
                    }

                    public void setProcessIdentifier(int processIdentifier) {
                        this.processIdentifier = processIdentifier;
                    }

                    public int getCounter() {
                        return counter;
                    }

                    public void setCounter(int counter) {
                        this.counter = counter;
                    }

                    public int getTimeSecond() {
                        return timeSecond;
                    }

                    public void setTimeSecond(int timeSecond) {
                        this.timeSecond = timeSecond;
                    }

                    public long getDate() {
                        return date;
                    }

                    public void setDate(long date) {
                        this.date = date;
                    }

                    public long getTime() {
                        return time;
                    }

                    public void setTime(long time) {
                        this.time = time;
                    }
                }

                public static class LocationBean {
                    /**
                     * country : 中国
                     * province : 广东省
                     * city : 深圳
                     * district : 深圳
                     * address : Guangdong
                     */

                    private String country;
                    private String province;
                    private String city;
                    private String district;
                    private String address;

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
                }

                public static class BannerBean {
                    /**
                     * _id : {"timestamp":1528168188,"machineIdentifier":8505045,"processIdentifier":3380,"counter":6683334,"timeSecond":1528168188,"date":1528168188000,"time":1528168188000}
                     * alias : 1527563930588.png
                     * fileName : 15281681883213574.png
                     * contentType : image/png
                     * size : 12947
                     * createdOn : 1528168188321
                     */

                    private IdBeanX _id;
                    private String alias;
                    private String fileName;
                    private String contentType;
                    private int size;
                    private long createdOn;

                    public IdBeanX get_id() {
                        return _id;
                    }

                    public void set_id(IdBeanX _id) {
                        this._id = _id;
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

                    public static class IdBeanX {
                        /**
                         * timestamp : 1528168188
                         * machineIdentifier : 8505045
                         * processIdentifier : 3380
                         * counter : 6683334
                         * timeSecond : 1528168188
                         * date : 1528168188000
                         * time : 1528168188000
                         */

                        private int timestamp;
                        private int machineIdentifier;
                        private int processIdentifier;
                        private int counter;
                        private int timeSecond;
                        private long date;
                        private long time;

                        public int getTimestamp() {
                            return timestamp;
                        }

                        public void setTimestamp(int timestamp) {
                            this.timestamp = timestamp;
                        }

                        public int getMachineIdentifier() {
                            return machineIdentifier;
                        }

                        public void setMachineIdentifier(int machineIdentifier) {
                            this.machineIdentifier = machineIdentifier;
                        }

                        public int getProcessIdentifier() {
                            return processIdentifier;
                        }

                        public void setProcessIdentifier(int processIdentifier) {
                            this.processIdentifier = processIdentifier;
                        }

                        public int getCounter() {
                            return counter;
                        }

                        public void setCounter(int counter) {
                            this.counter = counter;
                        }

                        public int getTimeSecond() {
                            return timeSecond;
                        }

                        public void setTimeSecond(int timeSecond) {
                            this.timeSecond = timeSecond;
                        }

                        public long getDate() {
                            return date;
                        }

                        public void setDate(long date) {
                            this.date = date;
                        }

                        public long getTime() {
                            return time;
                        }

                        public void setTime(long time) {
                            this.time = time;
                        }
                    }
                }

                public static class IntroductionsBean {
                    /**
                     * title : Introduction
                     * detail : Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.

                     Ei interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.

                     Ei est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.
                     * deletable : false
                     */

                    private String title;
                    private String detail;
                    private boolean deletable;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public void setDetail(String detail) {
                        this.detail = detail;
                    }

                    public boolean isDeletable() {
                        return deletable;
                    }

                    public void setDeletable(boolean deletable) {
                        this.deletable = deletable;
                    }
                }
            }
        }
    }
}
