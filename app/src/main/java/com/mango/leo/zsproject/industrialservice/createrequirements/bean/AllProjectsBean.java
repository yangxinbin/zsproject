package com.mango.leo.zsproject.industrialservice.createrequirements.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class AllProjectsBean {

    /**
     * responseObject : {"content":[{"id":"5b04d38dbc2ab974e775309b","name":"和煤化工宣传部非常","description":"还","utilityFee":null,"photos":[],"location":null,"resourceFeeInfo":null,"industries":null,"contacts":null,"advantages":null,"relatedFiles":null,"joinAsAFirmCriteria":null,"investmentCriteria":null,"standard":null,"status":null,"stage":0,"createdBy":"18318836309","createdOn":null,"recommended":null},{"id":"5b04d425bc2ab974e775309c","name":"非常","description":"还","utilityFee":null,"photos":[],"location":null,"resourceFeeInfo":null,"industries":null,"contacts":null,"advantages":null,"relatedFiles":null,"joinAsAFirmCriteria":null,"investmentCriteria":null,"standard":null,"status":null,"stage":0,"createdBy":"18318836309","createdOn":null,"recommended":null}],"pageable":{"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":1,"offset":20,"paged":true,"unpaged":false},"last":true,"totalElements":22,"totalPages":2,"first":false,"sort":{"unsorted":true,"sorted":false},"numberOfElements":2,"size":20,"number":1}
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
         * content : [{"id":"5b04d38dbc2ab974e775309b","name":"和煤化工宣传部非常","description":"还","utilityFee":null,"photos":[],"location":null,"resourceFeeInfo":null,"industries":null,"contacts":null,"advantages":null,"relatedFiles":null,"joinAsAFirmCriteria":null,"investmentCriteria":null,"standard":null,"status":null,"stage":0,"createdBy":"18318836309","createdOn":null,"recommended":null},{"id":"5b04d425bc2ab974e775309c","name":"非常","description":"还","utilityFee":null,"photos":[],"location":null,"resourceFeeInfo":null,"industries":null,"contacts":null,"advantages":null,"relatedFiles":null,"joinAsAFirmCriteria":null,"investmentCriteria":null,"standard":null,"status":null,"stage":0,"createdBy":"18318836309","createdOn":null,"recommended":null}]
         * pageable : {"sort":{"unsorted":true,"sorted":false},"pageSize":20,"pageNumber":1,"offset":20,"paged":true,"unpaged":false}
         * last : true
         * totalElements : 22
         * totalPages : 2
         * first : false
         * sort : {"unsorted":true,"sorted":false}
         * numberOfElements : 2
         * size : 20
         * number : 1
         */

        private PageableBean pageable;
        private boolean last;
        private int totalElements;
        private int totalPages;
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

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
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
             * pageNumber : 1
             * offset : 20
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
             * id : 5b04d38dbc2ab974e775309b
             * name : 和煤化工宣传部非常
             * description : 还
             * utilityFee : null
             * photos : []
             * location : null
             * resourceFeeInfo : null
             * industries : null
             * contacts : null
             * advantages : null
             * relatedFiles : null
             * joinAsAFirmCriteria : null
             * investmentCriteria : null
             * standard : null
             * status : null
             * stage : 0
             * createdBy : 18318836309
             * createdOn : null
             * recommended : null
             */

            private String id;
            private String name;
            private String description;
            private Object utilityFee;
            private Object location;
            private Object resourceFeeInfo;
            private Object industries;
            private Object contacts;
            private Object advantages;
            private Object relatedFiles;
            private Object joinAsAFirmCriteria;
            private Object investmentCriteria;
            private Object standard;
            private Object status;
            private int stage;
            private String createdBy;
            private Object createdOn;
            private Object recommended;
            private List<?> photos;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getUtilityFee() {
                return utilityFee;
            }

            public void setUtilityFee(Object utilityFee) {
                this.utilityFee = utilityFee;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public Object getResourceFeeInfo() {
                return resourceFeeInfo;
            }

            public void setResourceFeeInfo(Object resourceFeeInfo) {
                this.resourceFeeInfo = resourceFeeInfo;
            }

            public Object getIndustries() {
                return industries;
            }

            public void setIndustries(Object industries) {
                this.industries = industries;
            }

            public Object getContacts() {
                return contacts;
            }

            public void setContacts(Object contacts) {
                this.contacts = contacts;
            }

            public Object getAdvantages() {
                return advantages;
            }

            public void setAdvantages(Object advantages) {
                this.advantages = advantages;
            }

            public Object getRelatedFiles() {
                return relatedFiles;
            }

            public void setRelatedFiles(Object relatedFiles) {
                this.relatedFiles = relatedFiles;
            }

            public Object getJoinAsAFirmCriteria() {
                return joinAsAFirmCriteria;
            }

            public void setJoinAsAFirmCriteria(Object joinAsAFirmCriteria) {
                this.joinAsAFirmCriteria = joinAsAFirmCriteria;
            }

            public Object getInvestmentCriteria() {
                return investmentCriteria;
            }

            public void setInvestmentCriteria(Object investmentCriteria) {
                this.investmentCriteria = investmentCriteria;
            }

            public Object getStandard() {
                return standard;
            }

            public void setStandard(Object standard) {
                this.standard = standard;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
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

            public Object getRecommended() {
                return recommended;
            }

            public void setRecommended(Object recommended) {
                this.recommended = recommended;
            }

            public List<?> getPhotos() {
                return photos;
            }

            public void setPhotos(List<?> photos) {
                this.photos = photos;
            }
        }
    }
}
