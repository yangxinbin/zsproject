package com.mango.leo.zsproject.industrialservice.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class DemandManagementBean{
    /**
     * content : [{"id":"5b3d944ddae5c52e48678a37","matchingIndex":null,"project":{"id":"5b231348f8aa411a1cafa4f6","name":"刚刚","summary":null,"organizerDepartment":null,"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":null,"createdOn":null,"updatedBy":null,"updatedOn":null,"recommended":null,"tenant":null},"tenant":"1528798005945","events":["5b225b06f8aa411990e1e01f","5b22599d1233c52010ad1725"],"investmentPlan":["5b20c6581233c5226c392c57","5b223908f8aa411990e70d25"],"businessPlan":["5b28714ff8aa411a1cafa52b","5b2884d5f8aa411a1cafa532"],"timestamp":null}]
     * pageable : {"sort":{"unsorted":true,"sorted":false},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true}
     * totalElements : 1
     * totalPages : 1
     * last : true
     * size : 20
     * number : 0
     * sort : {"unsorted":true,"sorted":false}
     * first : true
     * numberOfElements : 1
     */

    private PageableBean pageable;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private int size;
    private int number;
    private SortBeanX sort;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;

    public PageableBean getPageable() {
        return pageable;
    }

    public void setPageable(PageableBean pageable) {
        this.pageable = pageable;
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

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
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
         * id : 5b3d944ddae5c52e48678a37
         * matchingIndex : null
         * project : {"id":"5b231348f8aa411a1cafa4f6","name":"刚刚","summary":null,"organizerDepartment":null,"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":null,"createdOn":null,"updatedBy":null,"updatedOn":null,"recommended":null,"tenant":null}
         * tenant : 1528798005945
         * events : ["5b225b06f8aa411990e1e01f","5b22599d1233c52010ad1725"]
         * investmentPlan : ["5b20c6581233c5226c392c57","5b223908f8aa411990e70d25"]
         * businessPlan : ["5b28714ff8aa411a1cafa52b","5b2884d5f8aa411a1cafa532"]
         * timestamp : null
         */

        private String id;
        private Object matchingIndex;
        private ProjectBean project;
        private String tenant;
        private Object timestamp;
        private List<String> events;
        private List<String> investmentPlan;
        private List<String> businessPlan;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getMatchingIndex() {
            return matchingIndex;
        }

        public void setMatchingIndex(Object matchingIndex) {
            this.matchingIndex = matchingIndex;
        }

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public String getTenant() {
            return tenant;
        }

        public void setTenant(String tenant) {
            this.tenant = tenant;
        }

        public Object getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Object timestamp) {
            this.timestamp = timestamp;
        }

        public List<String> getEvents() {
            return events;
        }

        public void setEvents(List<String> events) {
            this.events = events;
        }

        public List<String> getInvestmentPlan() {
            return investmentPlan;
        }

        public void setInvestmentPlan(List<String> investmentPlan) {
            this.investmentPlan = investmentPlan;
        }

        public List<String> getBusinessPlan() {
            return businessPlan;
        }

        public void setBusinessPlan(List<String> businessPlan) {
            this.businessPlan = businessPlan;
        }

        public static class ProjectBean {
            /**
             * id : 5b231348f8aa411a1cafa4f6
             * name : 刚刚
             * summary : null
             * organizerDepartment : null
             * totalInvestmentRequired : null
             * phase1InvestmentRequired : null
             * numOfInvestmentPhases : null
             * location : {"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null}
             * contacts : []
             * cooperationStyles : null
             * industries : []
             * typesOfAvailableDox : null
             * icr : {"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null}
             * utilityFee : {"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null}
             * resourceFeeInfo : []
             * advantages : []
             * detail : null
             * photos : []
             * relatedFiles : []
             * stage : 0
             * createdBy : null
             * createdOn : null
             * updatedBy : null
             * updatedOn : null
             * recommended : null
             * tenant : null
             */

            private String id;
            private String name;
            private Object summary;
            private Object organizerDepartment;
            private Object totalInvestmentRequired;
            private Object phase1InvestmentRequired;
            private Object numOfInvestmentPhases;
            private LocationBean location;
            private Object cooperationStyles;
            private Object typesOfAvailableDox;
            private IcrBean icr;
            private UtilityFeeBean utilityFee;
            private Object detail;
            private int stage;
            private Object createdBy;
            private Object createdOn;
            private Object updatedBy;
            private Object updatedOn;
            private Object recommended;
            private Object tenant;
            private List<?> contacts;
            private List<?> industries;
            private List<?> resourceFeeInfo;
            private List<?> advantages;
            private List<?> photos;
            private List<?> relatedFiles;

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

            public Object getSummary() {
                return summary;
            }

            public void setSummary(Object summary) {
                this.summary = summary;
            }

            public Object getOrganizerDepartment() {
                return organizerDepartment;
            }

            public void setOrganizerDepartment(Object organizerDepartment) {
                this.organizerDepartment = organizerDepartment;
            }

            public Object getTotalInvestmentRequired() {
                return totalInvestmentRequired;
            }

            public void setTotalInvestmentRequired(Object totalInvestmentRequired) {
                this.totalInvestmentRequired = totalInvestmentRequired;
            }

            public Object getPhase1InvestmentRequired() {
                return phase1InvestmentRequired;
            }

            public void setPhase1InvestmentRequired(Object phase1InvestmentRequired) {
                this.phase1InvestmentRequired = phase1InvestmentRequired;
            }

            public Object getNumOfInvestmentPhases() {
                return numOfInvestmentPhases;
            }

            public void setNumOfInvestmentPhases(Object numOfInvestmentPhases) {
                this.numOfInvestmentPhases = numOfInvestmentPhases;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public Object getCooperationStyles() {
                return cooperationStyles;
            }

            public void setCooperationStyles(Object cooperationStyles) {
                this.cooperationStyles = cooperationStyles;
            }

            public Object getTypesOfAvailableDox() {
                return typesOfAvailableDox;
            }

            public void setTypesOfAvailableDox(Object typesOfAvailableDox) {
                this.typesOfAvailableDox = typesOfAvailableDox;
            }

            public IcrBean getIcr() {
                return icr;
            }

            public void setIcr(IcrBean icr) {
                this.icr = icr;
            }

            public UtilityFeeBean getUtilityFee() {
                return utilityFee;
            }

            public void setUtilityFee(UtilityFeeBean utilityFee) {
                this.utilityFee = utilityFee;
            }

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
            }

            public Object getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(Object createdBy) {
                this.createdBy = createdBy;
            }

            public Object getCreatedOn() {
                return createdOn;
            }

            public void setCreatedOn(Object createdOn) {
                this.createdOn = createdOn;
            }

            public Object getUpdatedBy() {
                return updatedBy;
            }

            public void setUpdatedBy(Object updatedBy) {
                this.updatedBy = updatedBy;
            }

            public Object getUpdatedOn() {
                return updatedOn;
            }

            public void setUpdatedOn(Object updatedOn) {
                this.updatedOn = updatedOn;
            }

            public Object getRecommended() {
                return recommended;
            }

            public void setRecommended(Object recommended) {
                this.recommended = recommended;
            }

            public Object getTenant() {
                return tenant;
            }

            public void setTenant(Object tenant) {
                this.tenant = tenant;
            }

            public List<?> getContacts() {
                return contacts;
            }

            public void setContacts(List<?> contacts) {
                this.contacts = contacts;
            }

            public List<?> getIndustries() {
                return industries;
            }

            public void setIndustries(List<?> industries) {
                this.industries = industries;
            }

            public List<?> getResourceFeeInfo() {
                return resourceFeeInfo;
            }

            public void setResourceFeeInfo(List<?> resourceFeeInfo) {
                this.resourceFeeInfo = resourceFeeInfo;
            }

            public List<?> getAdvantages() {
                return advantages;
            }

            public void setAdvantages(List<?> advantages) {
                this.advantages = advantages;
            }

            public List<?> getPhotos() {
                return photos;
            }

            public void setPhotos(List<?> photos) {
                this.photos = photos;
            }

            public List<?> getRelatedFiles() {
                return relatedFiles;
            }

            public void setRelatedFiles(List<?> relatedFiles) {
                this.relatedFiles = relatedFiles;
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

            public static class IcrBean {
                /**
                 * totalInvestmentRequired : null
                 * phase1InvestmentRequired : null
                 * numOfInvestmentPhases : null
                 * cooperationModel : null
                 * cooperationStyles : null
                 * investmentType : null
                 * investmentSize : null
                 * other : null
                 */

                private Object totalInvestmentRequired;
                private Object phase1InvestmentRequired;
                private Object numOfInvestmentPhases;
                private Object cooperationModel;
                private Object cooperationStyles;
                private Object investmentType;
                private Object investmentSize;
                private Object other;

                public Object getTotalInvestmentRequired() {
                    return totalInvestmentRequired;
                }

                public void setTotalInvestmentRequired(Object totalInvestmentRequired) {
                    this.totalInvestmentRequired = totalInvestmentRequired;
                }

                public Object getPhase1InvestmentRequired() {
                    return phase1InvestmentRequired;
                }

                public void setPhase1InvestmentRequired(Object phase1InvestmentRequired) {
                    this.phase1InvestmentRequired = phase1InvestmentRequired;
                }

                public Object getNumOfInvestmentPhases() {
                    return numOfInvestmentPhases;
                }

                public void setNumOfInvestmentPhases(Object numOfInvestmentPhases) {
                    this.numOfInvestmentPhases = numOfInvestmentPhases;
                }

                public Object getCooperationModel() {
                    return cooperationModel;
                }

                public void setCooperationModel(Object cooperationModel) {
                    this.cooperationModel = cooperationModel;
                }

                public Object getCooperationStyles() {
                    return cooperationStyles;
                }

                public void setCooperationStyles(Object cooperationStyles) {
                    this.cooperationStyles = cooperationStyles;
                }

                public Object getInvestmentType() {
                    return investmentType;
                }

                public void setInvestmentType(Object investmentType) {
                    this.investmentType = investmentType;
                }

                public Object getInvestmentSize() {
                    return investmentSize;
                }

                public void setInvestmentSize(Object investmentSize) {
                    this.investmentSize = investmentSize;
                }

                public Object getOther() {
                    return other;
                }

                public void setOther(Object other) {
                    this.other = other;
                }
            }

            public static class UtilityFeeBean {
                /**
                 * waterFeeInfo : null
                 * electricityFeeInfo : null
                 * gasFeeInfo : null
                 * landFeeInfo : null
                 */

                private Object waterFeeInfo;
                private Object electricityFeeInfo;
                private Object gasFeeInfo;
                private Object landFeeInfo;

                public Object getWaterFeeInfo() {
                    return waterFeeInfo;
                }

                public void setWaterFeeInfo(Object waterFeeInfo) {
                    this.waterFeeInfo = waterFeeInfo;
                }

                public Object getElectricityFeeInfo() {
                    return electricityFeeInfo;
                }

                public void setElectricityFeeInfo(Object electricityFeeInfo) {
                    this.electricityFeeInfo = electricityFeeInfo;
                }

                public Object getGasFeeInfo() {
                    return gasFeeInfo;
                }

                public void setGasFeeInfo(Object gasFeeInfo) {
                    this.gasFeeInfo = gasFeeInfo;
                }

                public Object getLandFeeInfo() {
                    return landFeeInfo;
                }

                public void setLandFeeInfo(Object landFeeInfo) {
                    this.landFeeInfo = landFeeInfo;
                }
            }
        }
    }
}
