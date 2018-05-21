package com.mango.leo.zsproject.industrialservice.createrequirements.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class AllProjectsBean {

    /**
     * responseObject : [{"id":"5afe612abc2ab975d270096b","name":"ddd","description":"dfsdfs","utilityFee":null,"photos":[],"location":null,"resourceFeeInfo":null,"industries":null,"contacts":null,"advantages":null,"relatedFiles":null,"joinAsAFirmCriteria":null,"investmentCriteria":null,"standard":null,"status":null,"stage":0,"createdBy":"18318836309","createdOn":null},{"id":"5afe83d0bc2ab975d270096d","name":"换届大会尽快","description":"山东矿机安防科技","utilityFee":null,"photos":[],"location":null,"resourceFeeInfo":null,"industries":null,"contacts":null,"advantages":null,"relatedFiles":null,"joinAsAFirmCriteria":null,"investmentCriteria":null,"standard":null,"status":null,"stage":0,"createdBy":"18318836309","createdOn":null}]
     * responseList : null
     * totalRecords : null
     * currentPage : null
     * totalPages : null
     */

    private Object responseList;
    private Object totalRecords;
    private Object currentPage;
    private Object totalPages;
    private List<ResponseObjectBean> responseObject;

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

    public List<ResponseObjectBean> getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(List<ResponseObjectBean> responseObject) {
        this.responseObject = responseObject;
    }

    public static class ResponseObjectBean {
        /**
         * id : 5afe612abc2ab975d270096b
         * name : ddd
         * description : dfsdfs
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

        public List<?> getPhotos() {
            return photos;
        }

        public void setPhotos(List<?> photos) {
            this.photos = photos;
        }
    }
}
