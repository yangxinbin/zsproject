package com.mango.leo.zsproject.industrialservice.createrequirements.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/21.
 */

public class AllProjectsBean {

    /**
     * responseObject : {"content":[{"id":"5b1b4a20bc2ab930c751e8a4","name":"医院","summary":"得好吃","organizerDepartment":"唱的好","totalInvestmentRequired":565,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[{"id":null,"name":"的很好","department":"发的","position":"fre","mobile":"855","phone":"855","email":"ffd"}],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528515104482,"updatedBy":"5b191b23bc2ab90367d15e8c","updatedOn":1528519836878,"recommended":null,"tenant":null},{"id":"5b1b4bd5bc2ab930c751e8a6","name":"虚虚","summary":"飞歌导航","organizerDepartment":"长号","totalInvestmentRequired":7845,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":"深圳大学好的宝贝很方便","lon":113.94279559763588,"lat":22.5378642078624},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528515541242,"updatedBy":"5b191b23bc2ab90367d15e8c","updatedOn":1528515627621,"recommended":null,"tenant":null},{"id":"5b1b4d14bc2ab930c751e8a7","name":"复合弓","summary":"果皇果后","organizerDepartment":"国际版","totalInvestmentRequired":588,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528515860008,"updatedBy":null,"updatedOn":null,"recommended":null,"tenant":null},{"id":"5b1b4f64bc2ab930c751e8a9","name":"wer","summary":"123","organizerDepartment":"231","totalInvestmentRequired":312,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528516452943,"updatedBy":null,"updatedOn":null,"recommended":null,"tenant":null}],"pageable":{"sort":{"sorted":false,"unsorted":true},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false},"totalElements":4,"totalPages":1,"last":true,"first":true,"sort":{"sorted":false,"unsorted":true},"numberOfElements":4,"size":20,"number":0}
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
         * content : [{"id":"5b1b4a20bc2ab930c751e8a4","name":"医院","summary":"得好吃","organizerDepartment":"唱的好","totalInvestmentRequired":565,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[{"id":null,"name":"的很好","department":"发的","position":"fre","mobile":"855","phone":"855","email":"ffd"}],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528515104482,"updatedBy":"5b191b23bc2ab90367d15e8c","updatedOn":1528519836878,"recommended":null,"tenant":null},{"id":"5b1b4bd5bc2ab930c751e8a6","name":"虚虚","summary":"飞歌导航","organizerDepartment":"长号","totalInvestmentRequired":7845,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":"深圳大学好的宝贝很方便","lon":113.94279559763588,"lat":22.5378642078624},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528515541242,"updatedBy":"5b191b23bc2ab90367d15e8c","updatedOn":1528515627621,"recommended":null,"tenant":null},{"id":"5b1b4d14bc2ab930c751e8a7","name":"复合弓","summary":"果皇果后","organizerDepartment":"国际版","totalInvestmentRequired":588,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528515860008,"updatedBy":null,"updatedOn":null,"recommended":null,"tenant":null},{"id":"5b1b4f64bc2ab930c751e8a9","name":"wer","summary":"123","organizerDepartment":"231","totalInvestmentRequired":312,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b191b23bc2ab90367d15e8c","createdOn":1528516452943,"updatedBy":null,"updatedOn":null,"recommended":null,"tenant":null}]
         * pageable : {"sort":{"sorted":false,"unsorted":true},"pageSize":20,"pageNumber":0,"offset":0,"paged":true,"unpaged":false}
         * totalElements : 4
         * totalPages : 1
         * last : true
         * first : true
         * sort : {"sorted":false,"unsorted":true}
         * numberOfElements : 4
         * size : 20
         * number : 0
         */

        private PageableBean pageable;
        private int totalElements;
        private int totalPages;
        private boolean last;
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
             * sort : {"sorted":false,"unsorted":true}
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
             * id : 5b1b4a20bc2ab930c751e8a4
             * name : 医院
             * summary : 得好吃
             * organizerDepartment : 唱的好
             * totalInvestmentRequired : 565
             * phase1InvestmentRequired : null
             * numOfInvestmentPhases : null
             * location : {"country":null,"province":null,"city":null,"district":null,"address":null,"lon":null,"lat":null}
             * contacts : [{"id":null,"name":"的很好","department":"发的","position":"fre","mobile":"855","phone":"855","email":"ffd"}]
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
             * createdBy : 5b191b23bc2ab90367d15e8c
             * createdOn : 1528515104482
             * updatedBy : 5b191b23bc2ab90367d15e8c
             * updatedOn : 1528519836878
             * recommended : null
             * tenant : null
             */

            private String id;
            private String name;
            private String summary;
            private String organizerDepartment;
            private int totalInvestmentRequired;
            private Object phase1InvestmentRequired;
            private Object numOfInvestmentPhases;
            private LocationBean location;
            private Object cooperationStyles;
            private Object typesOfAvailableDox;
            private IcrBean icr;
            private UtilityFeeBean utilityFee;
            private Object detail;
            private int stage;
            private String createdBy;
            private long createdOn;
            private String updatedBy;
            private long updatedOn;
            private Object recommended;
            private Object tenant;
            private List<ContactsBean> contacts;
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

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getOrganizerDepartment() {
                return organizerDepartment;
            }

            public void setOrganizerDepartment(String organizerDepartment) {
                this.organizerDepartment = organizerDepartment;
            }

            public int getTotalInvestmentRequired() {
                return totalInvestmentRequired;
            }

            public void setTotalInvestmentRequired(int totalInvestmentRequired) {
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

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public long getCreatedOn() {
                return createdOn;
            }

            public void setCreatedOn(long createdOn) {
                this.createdOn = createdOn;
            }

            public String getUpdatedBy() {
                return updatedBy;
            }

            public void setUpdatedBy(String updatedBy) {
                this.updatedBy = updatedBy;
            }

            public long getUpdatedOn() {
                return updatedOn;
            }

            public void setUpdatedOn(long updatedOn) {
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

            public List<ContactsBean> getContacts() {
                return contacts;
            }

            public void setContacts(List<ContactsBean> contacts) {
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

            public static class ContactsBean {
                /**
                 * id : null
                 * name : 的很好
                 * department : 发的
                 * position : fre
                 * mobile : 855
                 * phone : 855
                 * email : ffd
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
