package com.mango.leo.zsproject.eventexhibition.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/1.
 */

public class EventBean {

    /**
     * responseObject : {"content":[{"id":"5b0511acbc2ab928b4a7ab9c","name":"光展6.30","location":null,"description":"hndiugh21432","price":112.94,"time":20180908,"popularity":"10002","organizer":"Aio","city":null,"photos":[{"id":"5b0511acbc2ab928b4a7ab9a","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}],"coorganizers":[],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"createdBy":"tom8","createdOn":null,"published":null},{"id":"5b0511cebc2ab928b4a7ab9f","name":"光展6.30","location":null,"description":"hndiugh21432","price":112.94,"time":20180908,"popularity":"10002","organizer":"Aio","city":null,"photos":[{"id":"5b0511cebc2ab928b4a7ab9d","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}],"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"createdBy":"tom8","createdOn":null,"published":null},{"id":"5b051fbebc2ab92d729348aa","name":"光展6.30","location":{"id":null,"province":"广东","city":"深圳","address":"park1,futian","lon":60.3,"lat":45.2},"description":"hndiugh21432","price":112.94,"time":20180908,"popularity":"10002","organizer":"Aio","city":"深圳","photos":[{"id":"5b051fbebc2ab92d729348a8","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}],"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"createdBy":"tom8","createdOn":null,"published":null}],"pageable":{"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false},"last":true,"totalPages":1,"totalElements":3,"first":true,"sort":{"unsorted":true,"sorted":false},"numberOfElements":3,"size":20,"number":0}
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
         * content : [{"id":"5b0511acbc2ab928b4a7ab9c","name":"光展6.30","location":null,"description":"hndiugh21432","price":112.94,"time":20180908,"popularity":"10002","organizer":"Aio","city":null,"photos":[{"id":"5b0511acbc2ab928b4a7ab9a","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}],"coorganizers":[],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"createdBy":"tom8","createdOn":null,"published":null},{"id":"5b0511cebc2ab928b4a7ab9f","name":"光展6.30","location":null,"description":"hndiugh21432","price":112.94,"time":20180908,"popularity":"10002","organizer":"Aio","city":null,"photos":[{"id":"5b0511cebc2ab928b4a7ab9d","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}],"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"createdBy":"tom8","createdOn":null,"published":null},{"id":"5b051fbebc2ab92d729348aa","name":"光展6.30","location":{"id":null,"province":"广东","city":"深圳","address":"park1,futian","lon":60.3,"lat":45.2},"description":"hndiugh21432","price":112.94,"time":20180908,"popularity":"10002","organizer":"Aio","city":"深圳","photos":[{"id":"5b051fbebc2ab92d729348a8","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}],"coorganizers":["[{\"name\":\"y12\"","\"description\":\"yhfth213\"}]"],"relatedIndustries":["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"],"createdBy":"tom8","createdOn":null,"published":null}]
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
             * id : 5b0511acbc2ab928b4a7ab9c
             * name : 光展6.30
             * location : null
             * description : hndiugh21432
             * price : 112.94
             * time : 20180908
             * popularity : 10002
             * organizer : Aio
             * city : null
             * photos : [{"id":"5b0511acbc2ab928b4a7ab9a","alias":null,"fileName":"test.jpg","contentType":null,"size":0,"createdOn":0}]
             * coorganizers : []
             * relatedIndustries : ["[{\n\"name\":\"12y\"","\"parent\":\"5ter\"","\"tag\":\"y678\"\n}]"]
             * createdBy : tom8
             * createdOn : null
             * published : null
             */

            private String id;
            private String name;
            private Object location;
            private String description;
            private double price;
            private int time;
            private String popularity;
            private String organizer;
            private Object city;
            private String createdBy;
            private Object createdOn;
            private Object published;
            private List<PhotosBean> photos;
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

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
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

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getPopularity() {
                return popularity;
            }

            public void setPopularity(String popularity) {
                this.popularity = popularity;
            }

            public String getOrganizer() {
                return organizer;
            }

            public void setOrganizer(String organizer) {
                this.organizer = organizer;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
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

            public List<PhotosBean> getPhotos() {
                return photos;
            }

            public void setPhotos(List<PhotosBean> photos) {
                this.photos = photos;
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

            public static class PhotosBean {
                /**
                 * id : 5b0511acbc2ab928b4a7ab9a
                 * alias : null
                 * fileName : test.jpg
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
        }
    }
}
