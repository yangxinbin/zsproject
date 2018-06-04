package com.mango.leo.zsproject.eventexhibition.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/1.
 */

public class EventBean {

    /**
     * responseObject : {"content":[{"id":"5b0511acbc2ab928b4a7ab9c","name":"光展6.30","location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"description":"hndiugh21432","price":112.94,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":[],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null},{"id":"5b0511cebc2ab928b4a7ab9f","name":"光展6.30","location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"description":"hndiugh21432","price":112.94,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null},{"id":"5b051fbebc2ab92d729348aa","name":"光展6.30","location":{"country":null,"province":"广东","city":"深圳","district":null,"address":"park1,futian","lon":60.3,"lat":45.2},"description":"hndiugh21432","price":112.94,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null},{"id":"5b053d80bc2ab931f07f864c","name":"免费活动","location":{"country":null,"province":"广东","city":"深圳","district":null,"address":"park1,futian","lon":60.3,"lat":45.2},"description":"dfsd1","price":0,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null}],"pageable":{"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false},"last":true,"totalPages":1,"totalElements":4,"first":true,"sort":{"unsorted":true,"sorted":false},"numberOfElements":4,"size":20,"number":0}
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
         * content : [{"id":"5b0511acbc2ab928b4a7ab9c","name":"光展6.30","location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"description":"hndiugh21432","price":112.94,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":[],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null},{"id":"5b0511cebc2ab928b4a7ab9f","name":"光展6.30","location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"description":"hndiugh21432","price":112.94,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null},{"id":"5b051fbebc2ab92d729348aa","name":"光展6.30","location":{"country":null,"province":"广东","city":"深圳","district":null,"address":"park1,futian","lon":60.3,"lat":45.2},"description":"hndiugh21432","price":112.94,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null},{"id":"5b053d80bc2ab931f07f864c","name":"免费活动","location":{"country":null,"province":"广东","city":"深圳","district":null,"address":"park1,futian","lon":60.3,"lat":45.2},"description":"dfsd1","price":0,"startTime":null,"endTime":null,"popular":null,"organizer":"Aio","banner":null,"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"introductions":null,"createdBy":"tom8","createdOn":null,"published":null,"tenant":null}]
         * pageable : {"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false}
         * last : true
         * totalPages : 1
         * totalElements : 4
         * first : true
         * sort : {"unsorted":true,"sorted":false}
         * numberOfElements : 4
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
             * id : 5b0511acbc2ab928b4a7ab9c
             * name : 光展6.30
             * location : {"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null}
             * description : hndiugh21432
             * price : 112.94
             * startTime : null
             * endTime : null
             * popular : null
             * organizer : Aio
             * banner : null
             * coorganizers : []
             * relatedIndustries : ["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"]
             * introductions : null
             * createdBy : tom8
             * createdOn : null
             * published : null
             * tenant : null
             */

            private String id;
            private String name;
            private LocationBean location;
            private String description;
            private double price;
            private Object startTime;
            private Object endTime;
            private Object popular;
            private String organizer;
            private Object banner;
            private Object introductions;
            private String createdBy;
            private Object createdOn;
            private Object published;
            private Object tenant;
            private List<?> coorganizers;
            private List<String> relatedIndustries;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getPopular() {
                return popular;
            }

            public void setPopular(Object popular) {
                this.popular = popular;
            }

            public String getOrganizer() {
                return organizer;
            }

            public void setOrganizer(String organizer) {
                this.organizer = organizer;
            }

            public Object getBanner() {
                return banner;
            }

            public void setBanner(Object banner) {
                this.banner = banner;
            }

            public Object getIntroductions() {
                return introductions;
            }

            public void setIntroductions(Object introductions) {
                this.introductions = introductions;
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

            public Object getTenant() {
                return tenant;
            }

            public void setTenant(Object tenant) {
                this.tenant = tenant;
            }

            public List<?> getCoorganizers() {
                return coorganizers;
            }

            public void setCoorganizers(List<?> coorganizers) {
                this.coorganizers = coorganizers;
            }

            public List<String> getRelatedIndustries() {
                return relatedIndustries;
            }

            public void setRelatedIndustries(List<String> relatedIndustries) {
                this.relatedIndustries = relatedIndustries;
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
}
