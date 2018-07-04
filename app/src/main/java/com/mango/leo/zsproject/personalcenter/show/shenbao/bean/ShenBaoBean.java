package com.mango.leo.zsproject.personalcenter.show.shenbao.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/9.
 */

public class ShenBaoBean {

    /**
     * responseObject : {"content":[{"id":"5b3c9c9bb0aeba06643705fd","project":{"id":"5b29d389f8aa41068479a369","name":"体育","summary":"法国还会过分","organizerDepartment":"任天堂与if","totalInvestmentRequired":"6666888","phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":"白山市第一中学","lon":126.43246711783735,"lat":41.94246235788125},"contacts":[{"id":null,"name":"该好好","department":"uuu","position":"由于各个规划","mobile":"6666551","phone":"6666551","email":"fgyy.com"}],"cooperationStyles":null,"industries":[{"id":null,"name":"风力发电","parent":"电力、热力、燃气及水生产和供应业","level":null},{"id":null,"name":"核力发电","parent":"电力、热力、燃气及水生产和供应业","level":null}],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["合作","独资"],"investmentType":["商业银行","投资公司"],"investmentSize":{"caption":null,"min":1000,"max":5000},"other":"痕迹UI解决吧"},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":2,"createdBy":"5b2265c81233c546a8bead0e","createdOn":1529467785370,"updatedBy":"5b2265c81233c546a8bead0e","updatedOn":1529468250718,"recommended":null,"tenant":null},"description":null,"requesterType":"investor","contactInfo":{"id":null,"name":"uuu","department":"mm","position":"mm","mobile":null,"phone":"18456686960","email":"658987852@qq.com"},"requestedBy":null,"timestamp":null}],"pageable":{"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true},"totalElements":4,"totalPages":1,"last":true,"size":20,"number":0,"sort":{"sorted":false,"unsorted":true},"numberOfElements":4,"first":true}
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
         * content : [{"id":"5b3c9c9bb0aeba06643705fd","project":{"id":"5b29d389f8aa41068479a369","name":"体育","summary":"法国还会过分","organizerDepartment":"任天堂与if","totalInvestmentRequired":"6666888","phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":"白山市第一中学","lon":126.43246711783735,"lat":41.94246235788125},"contacts":[{"id":null,"name":"该好好","department":"uuu","position":"由于各个规划","mobile":"6666551","phone":"6666551","email":"fgyy.com"}],"cooperationStyles":null,"industries":[{"id":null,"name":"风力发电","parent":"电力、热力、燃气及水生产和供应业","level":null},{"id":null,"name":"核力发电","parent":"电力、热力、燃气及水生产和供应业","level":null}],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["合作","独资"],"investmentType":["商业银行","投资公司"],"investmentSize":{"caption":null,"min":1000,"max":5000},"other":"痕迹UI解决吧"},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":2,"createdBy":"5b2265c81233c546a8bead0e","createdOn":1529467785370,"updatedBy":"5b2265c81233c546a8bead0e","updatedOn":1529468250718,"recommended":null,"tenant":null},"description":null,"requesterType":"investor","contactInfo":{"id":null,"name":"uuu","department":"mm","position":"mm","mobile":null,"phone":"18456686960","email":"658987852@qq.com"},"requestedBy":null,"timestamp":null}]
         * pageable : {"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true}
         * totalElements : 4
         * totalPages : 1
         * last : true
         * size : 20
         * number : 0
         * sort : {"sorted":false,"unsorted":true}
         * numberOfElements : 4
         * first : true
         */

        private PageableBean pageable;
        private int totalElements;
        private int totalPages;
        private boolean last;
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
             * id : 5b3c9c9bb0aeba06643705fd
             * project : {"id":"5b29d389f8aa41068479a369","name":"体育","summary":"法国还会过分","organizerDepartment":"任天堂与if","totalInvestmentRequired":"6666888","phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":null,"province":null,"city":null,"district":null,"address":"白山市第一中学","lon":126.43246711783735,"lat":41.94246235788125},"contacts":[{"id":null,"name":"该好好","department":"uuu","position":"由于各个规划","mobile":"6666551","phone":"6666551","email":"fgyy.com"}],"cooperationStyles":null,"industries":[{"id":null,"name":"风力发电","parent":"电力、热力、燃气及水生产和供应业","level":null},{"id":null,"name":"核力发电","parent":"电力、热力、燃气及水生产和供应业","level":null}],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["合作","独资"],"investmentType":["商业银行","投资公司"],"investmentSize":{"caption":null,"min":1000,"max":5000},"other":"痕迹UI解决吧"},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":2,"createdBy":"5b2265c81233c546a8bead0e","createdOn":1529467785370,"updatedBy":"5b2265c81233c546a8bead0e","updatedOn":1529468250718,"recommended":null,"tenant":null}
             * description : null
             * requesterType : investor
             * contactInfo : {"id":null,"name":"uuu","department":"mm","position":"mm","mobile":null,"phone":"18456686960","email":"658987852@qq.com"}
             * requestedBy : null
             * timestamp : null
             */

            private String id;
            private ProjectBean project;
            private Object description;
            private String requesterType;
            private ContactInfoBean contactInfo;
            private Object requestedBy;
            private Object timestamp;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public ProjectBean getProject() {
                return project;
            }

            public void setProject(ProjectBean project) {
                this.project = project;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getRequesterType() {
                return requesterType;
            }

            public void setRequesterType(String requesterType) {
                this.requesterType = requesterType;
            }

            public ContactInfoBean getContactInfo() {
                return contactInfo;
            }

            public void setContactInfo(ContactInfoBean contactInfo) {
                this.contactInfo = contactInfo;
            }

            public Object getRequestedBy() {
                return requestedBy;
            }

            public void setRequestedBy(Object requestedBy) {
                this.requestedBy = requestedBy;
            }

            public Object getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(Object timestamp) {
                this.timestamp = timestamp;
            }

            public static class ProjectBean {
                /**
                 * id : 5b29d389f8aa41068479a369
                 * name : 体育
                 * summary : 法国还会过分
                 * organizerDepartment : 任天堂与if
                 * totalInvestmentRequired : 6666888
                 * phase1InvestmentRequired : null
                 * numOfInvestmentPhases : null
                 * location : {"country":null,"province":null,"city":null,"district":null,"address":"白山市第一中学","lon":126.43246711783735,"lat":41.94246235788125}
                 * contacts : [{"id":null,"name":"该好好","department":"uuu","position":"由于各个规划","mobile":"6666551","phone":"6666551","email":"fgyy.com"}]
                 * cooperationStyles : null
                 * industries : [{"id":null,"name":"风力发电","parent":"电力、热力、燃气及水生产和供应业","level":null},{"id":null,"name":"核力发电","parent":"电力、热力、燃气及水生产和供应业","level":null}]
                 * typesOfAvailableDox : null
                 * icr : {"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["合作","独资"],"investmentType":["商业银行","投资公司"],"investmentSize":{"caption":null,"min":1000,"max":5000},"other":"痕迹UI解决吧"}
                 * utilityFee : {"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null}
                 * resourceFeeInfo : []
                 * advantages : []
                 * detail : null
                 * photos : []
                 * relatedFiles : []
                 * stage : 2
                 * createdBy : 5b2265c81233c546a8bead0e
                 * createdOn : 1529467785370
                 * updatedBy : 5b2265c81233c546a8bead0e
                 * updatedOn : 1529468250718
                 * recommended : null
                 * tenant : null
                 */

                private String id;
                private String name;
                private String summary;
                private String organizerDepartment;
                private String totalInvestmentRequired;
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
                private List<IndustriesBean> industries;
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

                public String getTotalInvestmentRequired() {
                    return totalInvestmentRequired;
                }

                public void setTotalInvestmentRequired(String totalInvestmentRequired) {
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

                public List<IndustriesBean> getIndustries() {
                    return industries;
                }

                public void setIndustries(List<IndustriesBean> industries) {
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
                     * address : 白山市第一中学
                     * lon : 126.43246711783735
                     * lat : 41.94246235788125
                     */

                    private Object country;
                    private Object province;
                    private Object city;
                    private Object district;
                    private String address;
                    private double lon;
                    private double lat;

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

                public static class IcrBean {
                    /**
                     * totalInvestmentRequired : null
                     * phase1InvestmentRequired : null
                     * numOfInvestmentPhases : null
                     * cooperationModel : 引资
                     * cooperationStyles : ["合作","独资"]
                     * investmentType : ["商业银行","投资公司"]
                     * investmentSize : {"caption":null,"min":1000,"max":5000}
                     * other : 痕迹UI解决吧
                     */

                    private Object totalInvestmentRequired;
                    private Object phase1InvestmentRequired;
                    private Object numOfInvestmentPhases;
                    private String cooperationModel;
                    private InvestmentSizeBean investmentSize;
                    private String other;
                    private List<String> cooperationStyles;
                    private List<String> investmentType;

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

                    public String getCooperationModel() {
                        return cooperationModel;
                    }

                    public void setCooperationModel(String cooperationModel) {
                        this.cooperationModel = cooperationModel;
                    }

                    public InvestmentSizeBean getInvestmentSize() {
                        return investmentSize;
                    }

                    public void setInvestmentSize(InvestmentSizeBean investmentSize) {
                        this.investmentSize = investmentSize;
                    }

                    public String getOther() {
                        return other;
                    }

                    public void setOther(String other) {
                        this.other = other;
                    }

                    public List<String> getCooperationStyles() {
                        return cooperationStyles;
                    }

                    public void setCooperationStyles(List<String> cooperationStyles) {
                        this.cooperationStyles = cooperationStyles;
                    }

                    public List<String> getInvestmentType() {
                        return investmentType;
                    }

                    public void setInvestmentType(List<String> investmentType) {
                        this.investmentType = investmentType;
                    }

                    public static class InvestmentSizeBean {
                        /**
                         * caption : null
                         * min : 1000
                         * max : 5000
                         */

                        private Object caption;
                        private int min;
                        private int max;

                        public Object getCaption() {
                            return caption;
                        }

                        public void setCaption(Object caption) {
                            this.caption = caption;
                        }

                        public int getMin() {
                            return min;
                        }

                        public void setMin(int min) {
                            this.min = min;
                        }

                        public int getMax() {
                            return max;
                        }

                        public void setMax(int max) {
                            this.max = max;
                        }
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
                     * name : 该好好
                     * department : uuu
                     * position : 由于各个规划
                     * mobile : 6666551
                     * phone : 6666551
                     * email : fgyy.com
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

                public static class IndustriesBean {
                    /**
                     * id : null
                     * name : 风力发电
                     * parent : 电力、热力、燃气及水生产和供应业
                     * level : null
                     */

                    private Object id;
                    private String name;
                    private String parent;
                    private Object level;

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

                    public String getParent() {
                        return parent;
                    }

                    public void setParent(String parent) {
                        this.parent = parent;
                    }

                    public Object getLevel() {
                        return level;
                    }

                    public void setLevel(Object level) {
                        this.level = level;
                    }
                }
            }

            public static class ContactInfoBean {
                /**
                 * id : null
                 * name : uuu
                 * department : mm
                 * position : mm
                 * mobile : null
                 * phone : 18456686960
                 * email : 658987852@qq.com
                 */

                private Object id;
                private String name;
                private String department;
                private String position;
                private Object mobile;
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

                public Object getMobile() {
                    return mobile;
                }

                public void setMobile(Object mobile) {
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
