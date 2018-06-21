package com.mango.leo.zsproject.industrialservice.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class DemandManagementBean{

    /**
     * responseObject : {"content":[{"id":"5b1fdda3dae69c0b5879092c","name":"生态养生养老综合示范区建设项目","summary":"","organizerDepartment":"江源区人民政府","totalInvestmentRequired":"20000.0","phase1InvestmentRequired":null,"numOfInvestmentPhases":1,"location":{"country":"中国","province":"吉林省","city":"白山","district":"","address":"","lon":null,"lat":null},"contacts":[{"id":null,"name":"吃过家家","department":"风格化很","position":"富家女","mobile":"156636558","phone":"156636558","email":"tghjjh@qq.co\u2006m"},{"id":null,"name":"吃过家家呢吧","department":"非公经济呢","position":"复活节纠结","mobile":"55666452","phone":"55666452","email":"efhj@qq.co\u2006m"}],"cooperationStyles":null,"industries":[{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"铁路工程建筑","parent":"建筑业","level":null},{"id":null,"name":"铜矿采选","parent":"采矿业","level":null},{"id":null,"name":"铁矿采选","parent":"采矿业","level":null},{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"房屋建筑业","parent":"建筑业","level":null},{"id":null,"name":"铁路工程建筑","parent":"建筑业","level":null},{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"房屋建筑业","parent":"建筑业","level":null}],"typesOfAvailableDox":[],"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["典当公司"],"investmentType":["独资","合作"],"investmentSize":{"caption":null,"min":5000,"max":10000},"other":"许不能买"},"utilityFee":{"waterFeeInfo":"","electricityFeeInfo":"","gasFeeInfo":"","landFeeInfo":""},"resourceFeeInfo":[],"advantages":[],"detail":"","photos":[],"relatedFiles":[],"stage":2,"createdBy":"5b1f9b361233c53ddc1ea1fd","createdOn":null,"updatedBy":"5b1f9b361233c53ddc1ea1fd","updatedOn":1529033451833,"recommended":false,"tenant":"1528798005945"}],"pageable":{"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true},"totalElements":1,"totalPages":1,"last":true,"size":20,"number":0,"sort":{"sorted":false,"unsorted":true},"first":true,"numberOfElements":1}
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
         * content : [{"id":"5b1fdda3dae69c0b5879092c","name":"生态养生养老综合示范区建设项目","summary":"","organizerDepartment":"江源区人民政府","totalInvestmentRequired":"20000.0","phase1InvestmentRequired":null,"numOfInvestmentPhases":1,"location":{"country":"中国","province":"吉林省","city":"白山","district":"","address":"","lon":null,"lat":null},"contacts":[{"id":null,"name":"吃过家家","department":"风格化很","position":"富家女","mobile":"156636558","phone":"156636558","email":"tghjjh@qq.co\u2006m"},{"id":null,"name":"吃过家家呢吧","department":"非公经济呢","position":"复活节纠结","mobile":"55666452","phone":"55666452","email":"efhj@qq.co\u2006m"}],"cooperationStyles":null,"industries":[{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"铁路工程建筑","parent":"建筑业","level":null},{"id":null,"name":"铜矿采选","parent":"采矿业","level":null},{"id":null,"name":"铁矿采选","parent":"采矿业","level":null},{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"房屋建筑业","parent":"建筑业","level":null},{"id":null,"name":"铁路工程建筑","parent":"建筑业","level":null},{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"房屋建筑业","parent":"建筑业","level":null}],"typesOfAvailableDox":[],"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["典当公司"],"investmentType":["独资","合作"],"investmentSize":{"caption":null,"min":5000,"max":10000},"other":"许不能买"},"utilityFee":{"waterFeeInfo":"","electricityFeeInfo":"","gasFeeInfo":"","landFeeInfo":""},"resourceFeeInfo":[],"advantages":[],"detail":"","photos":[],"relatedFiles":[],"stage":2,"createdBy":"5b1f9b361233c53ddc1ea1fd","createdOn":null,"updatedBy":"5b1f9b361233c53ddc1ea1fd","updatedOn":1529033451833,"recommended":false,"tenant":"1528798005945"}]
         * pageable : {"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true}
         * totalElements : 1
         * totalPages : 1
         * last : true
         * size : 20
         * number : 0
         * sort : {"sorted":false,"unsorted":true}
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
             * id : 5b1fdda3dae69c0b5879092c
             * name : 生态养生养老综合示范区建设项目
             * summary :
             * organizerDepartment : 江源区人民政府
             * totalInvestmentRequired : 20000.0
             * phase1InvestmentRequired : null
             * numOfInvestmentPhases : 1
             * location : {"country":"中国","province":"吉林省","city":"白山","district":"","address":"","lon":null,"lat":null}
             * contacts : [{"id":null,"name":"吃过家家","department":"风格化很","position":"富家女","mobile":"156636558","phone":"156636558","email":"tghjjh@qq.co\u2006m"},{"id":null,"name":"吃过家家呢吧","department":"非公经济呢","position":"复活节纠结","mobile":"55666452","phone":"55666452","email":"efhj@qq.co\u2006m"}]
             * cooperationStyles : null
             * industries : [{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"铁路工程建筑","parent":"建筑业","level":null},{"id":null,"name":"铜矿采选","parent":"采矿业","level":null},{"id":null,"name":"铁矿采选","parent":"采矿业","level":null},{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"房屋建筑业","parent":"建筑业","level":null},{"id":null,"name":"铁路工程建筑","parent":"建筑业","level":null},{"id":null,"name":"海洋工程建筑","parent":"建筑业","level":null},{"id":null,"name":"房屋建筑业","parent":"建筑业","level":null}]
             * typesOfAvailableDox : []
             * icr : {"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"引资","cooperationStyles":["典当公司"],"investmentType":["独资","合作"],"investmentSize":{"caption":null,"min":5000,"max":10000},"other":"许不能买"}
             * utilityFee : {"waterFeeInfo":"","electricityFeeInfo":"","gasFeeInfo":"","landFeeInfo":""}
             * resourceFeeInfo : []
             * advantages : []
             * detail :
             * photos : []
             * relatedFiles : []
             * stage : 2
             * createdBy : 5b1f9b361233c53ddc1ea1fd
             * createdOn : null
             * updatedBy : 5b1f9b361233c53ddc1ea1fd
             * updatedOn : 1529033451833
             * recommended : false
             * tenant : 1528798005945
             */

            private String id;
            private String name;
            private String summary;
            private String organizerDepartment;
            private String totalInvestmentRequired;
            private Object phase1InvestmentRequired;
            private int numOfInvestmentPhases;
            private LocationBean location;
            private Object cooperationStyles;
            private IcrBean icr;
            private UtilityFeeBean utilityFee;
            private String detail;
            private int stage;
            private String createdBy;
            private Object createdOn;
            private String updatedBy;
            private long updatedOn;
            private boolean recommended;
            private String tenant;
            private List<ContactsBean> contacts;
            private List<IndustriesBean> industries;
            private List<?> typesOfAvailableDox;
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

            public int getNumOfInvestmentPhases() {
                return numOfInvestmentPhases;
            }

            public void setNumOfInvestmentPhases(int numOfInvestmentPhases) {
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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
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

            public Object getCreatedOn() {
                return createdOn;
            }

            public void setCreatedOn(Object createdOn) {
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

            public boolean isRecommended() {
                return recommended;
            }

            public void setRecommended(boolean recommended) {
                this.recommended = recommended;
            }

            public String getTenant() {
                return tenant;
            }

            public void setTenant(String tenant) {
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

            public List<?> getTypesOfAvailableDox() {
                return typesOfAvailableDox;
            }

            public void setTypesOfAvailableDox(List<?> typesOfAvailableDox) {
                this.typesOfAvailableDox = typesOfAvailableDox;
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
                 * country : 中国
                 * province : 吉林省
                 * city : 白山
                 * district :
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

            public static class IcrBean {
                /**
                 * totalInvestmentRequired : null
                 * phase1InvestmentRequired : null
                 * numOfInvestmentPhases : null
                 * cooperationModel : 引资
                 * cooperationStyles : ["典当公司"]
                 * investmentType : ["独资","合作"]
                 * investmentSize : {"caption":null,"min":5000,"max":10000}
                 * other : 许不能买
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
                     * min : 5000
                     * max : 10000
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
                 * waterFeeInfo :
                 * electricityFeeInfo :
                 * gasFeeInfo :
                 * landFeeInfo :
                 */

                private String waterFeeInfo;
                private String electricityFeeInfo;
                private String gasFeeInfo;
                private String landFeeInfo;

                public String getWaterFeeInfo() {
                    return waterFeeInfo;
                }

                public void setWaterFeeInfo(String waterFeeInfo) {
                    this.waterFeeInfo = waterFeeInfo;
                }

                public String getElectricityFeeInfo() {
                    return electricityFeeInfo;
                }

                public void setElectricityFeeInfo(String electricityFeeInfo) {
                    this.electricityFeeInfo = electricityFeeInfo;
                }

                public String getGasFeeInfo() {
                    return gasFeeInfo;
                }

                public void setGasFeeInfo(String gasFeeInfo) {
                    this.gasFeeInfo = gasFeeInfo;
                }

                public String getLandFeeInfo() {
                    return landFeeInfo;
                }

                public void setLandFeeInfo(String landFeeInfo) {
                    this.landFeeInfo = landFeeInfo;
                }
            }

            public static class ContactsBean {
                /**
                 * id : null
                 * name : 吃过家家
                 * department : 风格化很
                 * position : 富家女
                 * mobile : 156636558
                 * phone : 156636558
                 * email : tghjjh@qq.co m
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
                 * name : 海洋工程建筑
                 * parent : 建筑业
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
    }
}
