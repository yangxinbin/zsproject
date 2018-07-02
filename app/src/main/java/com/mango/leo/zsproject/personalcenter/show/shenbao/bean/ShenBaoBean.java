package com.mango.leo.zsproject.personalcenter.show.shenbao.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/9.
 */

public class ShenBaoBean {

    /**
     * responseObject : {"content":[{"id":"5b39e955b0aeba145495bb37","project":{"id":"5b39e7d0b0aeba145495bb36","name":"vvnnvcc","summary":"让法国百年","organizerDepartment":"效果很好吧","totalInvestmentRequired":"大概哈哈过","phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":"中国","province":"广东省","city":"深圳","district":"","address":"","lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b20ca4af8aa41149858e8f1","createdOn":1530521552153,"updatedBy":"5b20ca4af8aa41149858e8f1","updatedOn":1530521552153,"recommended":null,"tenant":null},"description":null,"requesterType":"investor","contactInfo":{"id":null,"name":"ceo","department":"m","position":"ceo","mobile":null,"phone":"18318836309","email":"609758690@qq.com"},"requestedBy":"ceo","timestamp":null},{"id":"5b39ee83b0aeba145495bb38","project":{"id":"5b1fdda3dae69c0b58790925","name":"硅藻土产业园区基础设施建设项目","summary":"","organizerDepartment":"白山市浑江区商务局","totalInvestmentRequired":"30000.0","phase1InvestmentRequired":null,"numOfInvestmentPhases":1,"location":{"country":"中国","province":"广东省","city":"深圳","district":"","address":"","lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":[],"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"","cooperationStyles":[],"investmentType":[],"investmentSize":null,"other":""},"utilityFee":{"waterFeeInfo":"","electricityFeeInfo":"","gasFeeInfo":"","landFeeInfo":""},"resourceFeeInfo":[],"advantages":[],"detail":"","photos":[],"relatedFiles":[],"stage":2,"createdBy":null,"createdOn":null,"updatedBy":null,"updatedOn":null,"recommended":false,"tenant":"1528809880444"},"description":null,"requesterType":"investor","contactInfo":{"id":null,"name":null,"department":"ou","position":"oui","mobile":null,"phone":"18318836309","email":"653265982@qq.com"},"requestedBy":null,"timestamp":null}],"pageable":{"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true},"totalPages":1,"last":true,"totalElements":2,"size":20,"number":0,"sort":{"sorted":false,"unsorted":true},"first":true,"numberOfElements":2}
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
         * content : [{"id":"5b39e955b0aeba145495bb37","project":{"id":"5b39e7d0b0aeba145495bb36","name":"vvnnvcc","summary":"让法国百年","organizerDepartment":"效果很好吧","totalInvestmentRequired":"大概哈哈过","phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":"中国","province":"广东省","city":"深圳","district":"","address":"","lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b20ca4af8aa41149858e8f1","createdOn":1530521552153,"updatedBy":"5b20ca4af8aa41149858e8f1","updatedOn":1530521552153,"recommended":null,"tenant":null},"description":null,"requesterType":"investor","contactInfo":{"id":null,"name":"ceo","department":"m","position":"ceo","mobile":null,"phone":"18318836309","email":"609758690@qq.com"},"requestedBy":"ceo","timestamp":null},{"id":"5b39ee83b0aeba145495bb38","project":{"id":"5b1fdda3dae69c0b58790925","name":"硅藻土产业园区基础设施建设项目","summary":"","organizerDepartment":"白山市浑江区商务局","totalInvestmentRequired":"30000.0","phase1InvestmentRequired":null,"numOfInvestmentPhases":1,"location":{"country":"中国","province":"广东省","city":"深圳","district":"","address":"","lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":[],"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"","cooperationStyles":[],"investmentType":[],"investmentSize":null,"other":""},"utilityFee":{"waterFeeInfo":"","electricityFeeInfo":"","gasFeeInfo":"","landFeeInfo":""},"resourceFeeInfo":[],"advantages":[],"detail":"","photos":[],"relatedFiles":[],"stage":2,"createdBy":null,"createdOn":null,"updatedBy":null,"updatedOn":null,"recommended":false,"tenant":"1528809880444"},"description":null,"requesterType":"investor","contactInfo":{"id":null,"name":null,"department":"ou","position":"oui","mobile":null,"phone":"18318836309","email":"653265982@qq.com"},"requestedBy":null,"timestamp":null}]
         * pageable : {"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true}
         * totalPages : 1
         * last : true
         * totalElements : 2
         * size : 20
         * number : 0
         * sort : {"sorted":false,"unsorted":true}
         * first : true
         * numberOfElements : 2
         */

        private PageableBean pageable;
        private int totalPages;
        private boolean last;
        private int totalElements;
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
             * id : 5b39e955b0aeba145495bb37
             * project : {"id":"5b39e7d0b0aeba145495bb36","name":"vvnnvcc","summary":"让法国百年","organizerDepartment":"效果很好吧","totalInvestmentRequired":"大概哈哈过","phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"location":{"country":"中国","province":"广东省","city":"深圳","district":"","address":"","lon":null,"lat":null},"contacts":[],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":null,"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":null,"cooperationStyles":null,"investmentType":null,"investmentSize":null,"other":null},"utilityFee":{"waterFeeInfo":null,"electricityFeeInfo":null,"gasFeeInfo":null,"landFeeInfo":null},"resourceFeeInfo":[],"advantages":[],"detail":null,"photos":[],"relatedFiles":[],"stage":0,"createdBy":"5b20ca4af8aa41149858e8f1","createdOn":1530521552153,"updatedBy":"5b20ca4af8aa41149858e8f1","updatedOn":1530521552153,"recommended":null,"tenant":null}
             * description : null
             * requesterType : investor
             * contactInfo : {"id":null,"name":"ceo","department":"m","position":"ceo","mobile":null,"phone":"18318836309","email":"609758690@qq.com"}
             * requestedBy : ceo
             * timestamp : null
             */

            private String id;
            private ProjectBean project;
            private Object description;
            private String requesterType;
            private ContactInfoBean contactInfo;
            private String requestedBy;
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

            public String getRequestedBy() {
                return requestedBy;
            }

            public void setRequestedBy(String requestedBy) {
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
                 * id : 5b39e7d0b0aeba145495bb36
                 * name : vvnnvcc
                 * summary : 让法国百年
                 * organizerDepartment : 效果很好吧
                 * totalInvestmentRequired : 大概哈哈过
                 * phase1InvestmentRequired : null
                 * numOfInvestmentPhases : null
                 * location : {"country":"中国","province":"广东省","city":"深圳","district":"","address":"","lon":null,"lat":null}
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
                 * createdBy : 5b20ca4af8aa41149858e8f1
                 * createdOn : 1530521552153
                 * updatedBy : 5b20ca4af8aa41149858e8f1
                 * updatedOn : 1530521552153
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
                     * country : 中国
                     * province : 广东省
                     * city : 深圳
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

            public static class ContactInfoBean {
                /**
                 * id : null
                 * name : ceo
                 * department : m
                 * position : ceo
                 * mobile : null
                 * phone : 18318836309
                 * email : 609758690@qq.com
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
