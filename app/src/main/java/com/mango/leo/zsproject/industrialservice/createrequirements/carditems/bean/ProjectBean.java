package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/7.
 */

public class ProjectBean {

    /**
     * responseObject : {"id":"5b1e1e0a81c6d547ecdadfaa","name":"这是专门给阿才测试创建的项目","summary":"这条信息专门为阿才创建的，独一无二","organizerDepartment":"double KK","totalInvestmentRequired":10000,"phase1InvestmentRequired":100,"numOfInvestmentPhases":2,"location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"东方科技大厦1002","lon":null,"lat":null},"contacts":[{"id":null,"name":"nasion","department":"芒果软件","position":"boss","mobile":"13428969654","phone":"13428969654","email":"1371400483@qq.com"}],"cooperationStyles":null,"industries":[],"typesOfAvailableDox":["项目简介","可行性研究报告"],"icr":{"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"","cooperationStyles":["asdf","asdf","nihao"],"investmentType":["asdfasdf","fdsfdfasdfdsf","fdfd"],"investmentSize":{"caption":null,"min":21,"max":1232},"other":null},"utilityFee":{"waterFeeInfo":"test from kk","electricityFeeInfo":"test from kk\r\n","gasFeeInfo":"test from kk","landFeeInfo":"test from kk"},"resourceFeeInfo":[{"id":1528776218492,"resourceType":"test from kk","senior":10000,"medium":8888,"junior":6666}],"advantages":[{"id":1528700435834,"title":"test from kk only one","detail":"<p>test from kk test from kk15277157545<\/p>","photos":[{"id":"5b1e1e2981c6d547ecdadfad","alias":"1527563930588.png","fileName":"15287004572812495.png","contentType":"image/png","size":12947,"createdOn":1528700457281}]}],"detail":"<p>h很好<\/p>","photos":[],"relatedFiles":[{"id":"5b1e1e1181c6d547ecdadfab","alias":"1527564707525.png","fileName":"15287004332413444.png","contentType":"image/png","size":12947,"createdOn":1528700433241}],"stage":2,"createdBy":"5b1e6bcabc2ab941b1c1524b","createdOn":null,"updatedBy":"5b1e6bcabc2ab941b1c1524b","updatedOn":1528776648813,"recommended":false,"tenant":"@super"}
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
         * id : 5b1e1e0a81c6d547ecdadfaa
         * name : 这是专门给阿才测试创建的项目
         * summary : 这条信息专门为阿才创建的，独一无二
         * organizerDepartment : double KK
         * totalInvestmentRequired : 10000
         * phase1InvestmentRequired : 100
         * numOfInvestmentPhases : 2
         * location : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"东方科技大厦1002","lon":null,"lat":null}
         * contacts : [{"id":null,"name":"nasion","department":"芒果软件","position":"boss","mobile":"13428969654","phone":"13428969654","email":"1371400483@qq.com"}]
         * cooperationStyles : null
         * industries : []
         * typesOfAvailableDox : ["项目简介","可行性研究报告"]
         * icr : {"totalInvestmentRequired":null,"phase1InvestmentRequired":null,"numOfInvestmentPhases":null,"cooperationModel":"","cooperationStyles":["asdf","asdf","nihao"],"investmentType":["asdfasdf","fdsfdfasdfdsf","fdfd"],"investmentSize":{"caption":null,"min":21,"max":1232},"other":null}
         * utilityFee : {"waterFeeInfo":"test from kk","electricityFeeInfo":"test from kk\r\n","gasFeeInfo":"test from kk","landFeeInfo":"test from kk"}
         * resourceFeeInfo : [{"id":1528776218492,"resourceType":"test from kk","senior":10000,"medium":8888,"junior":6666}]
         * advantages : [{"id":1528700435834,"title":"test from kk only one","detail":"<p>test from kk test from kk15277157545<\/p>","photos":[{"id":"5b1e1e2981c6d547ecdadfad","alias":"1527563930588.png","fileName":"15287004572812495.png","contentType":"image/png","size":12947,"createdOn":1528700457281}]}]
         * detail : <p>h很好</p>
         * photos : []
         * relatedFiles : [{"id":"5b1e1e1181c6d547ecdadfab","alias":"1527564707525.png","fileName":"15287004332413444.png","contentType":"image/png","size":12947,"createdOn":1528700433241}]
         * stage : 2
         * createdBy : 5b1e6bcabc2ab941b1c1524b
         * createdOn : null
         * updatedBy : 5b1e6bcabc2ab941b1c1524b
         * updatedOn : 1528776648813
         * recommended : false
         * tenant : @super
         */

        private String id;
        private String name;
        private String summary;
        private String organizerDepartment;
        private int totalInvestmentRequired;
        private int phase1InvestmentRequired;
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
        private List<?> industries;
        private List<String> typesOfAvailableDox;
        private List<ResourceFeeInfoBean> resourceFeeInfo;
        private List<AdvantagesBean> advantages;
        private List<?> photos;
        private List<RelatedFilesBean> relatedFiles;

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

        public int getPhase1InvestmentRequired() {
            return phase1InvestmentRequired;
        }

        public void setPhase1InvestmentRequired(int phase1InvestmentRequired) {
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

        public List<?> getIndustries() {
            return industries;
        }

        public void setIndustries(List<?> industries) {
            this.industries = industries;
        }

        public List<String> getTypesOfAvailableDox() {
            return typesOfAvailableDox;
        }

        public void setTypesOfAvailableDox(List<String> typesOfAvailableDox) {
            this.typesOfAvailableDox = typesOfAvailableDox;
        }

        public List<ResourceFeeInfoBean> getResourceFeeInfo() {
            return resourceFeeInfo;
        }

        public void setResourceFeeInfo(List<ResourceFeeInfoBean> resourceFeeInfo) {
            this.resourceFeeInfo = resourceFeeInfo;
        }

        public List<AdvantagesBean> getAdvantages() {
            return advantages;
        }

        public void setAdvantages(List<AdvantagesBean> advantages) {
            this.advantages = advantages;
        }

        public List<?> getPhotos() {
            return photos;
        }

        public void setPhotos(List<?> photos) {
            this.photos = photos;
        }

        public List<RelatedFilesBean> getRelatedFiles() {
            return relatedFiles;
        }

        public void setRelatedFiles(List<RelatedFilesBean> relatedFiles) {
            this.relatedFiles = relatedFiles;
        }

        public static class LocationBean {
            /**
             * country : 中国
             * province : 广东省
             * city : 深圳
             * district : 深圳
             * address : 东方科技大厦1002
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
             * cooperationModel :
             * cooperationStyles : ["asdf","asdf","nihao"]
             * investmentType : ["asdfasdf","fdsfdfasdfdsf","fdfd"]
             * investmentSize : {"caption":null,"min":21,"max":1232}
             * other : null
             */

            private Object totalInvestmentRequired;
            private Object phase1InvestmentRequired;
            private Object numOfInvestmentPhases;
            private String cooperationModel;
            private InvestmentSizeBean investmentSize;
            private Object other;
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

            public Object getOther() {
                return other;
            }

            public void setOther(Object other) {
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
                 * min : 21
                 * max : 1232
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
             * waterFeeInfo : test from kk
             * electricityFeeInfo : test from kk

             * gasFeeInfo : test from kk
             * landFeeInfo : test from kk
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
             * name : nasion
             * department : 芒果软件
             * position : boss
             * mobile : 13428969654
             * phone : 13428969654
             * email : 1371400483@qq.com
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

        public static class ResourceFeeInfoBean {
            /**
             * id : 1528776218492
             * resourceType : test from kk
             * senior : 10000
             * medium : 8888
             * junior : 6666
             */

            private long id;
            private String resourceType;
            private int senior;
            private int medium;
            private int junior;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getResourceType() {
                return resourceType;
            }

            public void setResourceType(String resourceType) {
                this.resourceType = resourceType;
            }

            public int getSenior() {
                return senior;
            }

            public void setSenior(int senior) {
                this.senior = senior;
            }

            public int getMedium() {
                return medium;
            }

            public void setMedium(int medium) {
                this.medium = medium;
            }

            public int getJunior() {
                return junior;
            }

            public void setJunior(int junior) {
                this.junior = junior;
            }
        }

        public static class AdvantagesBean {
            /**
             * id : 1528700435834
             * title : test from kk only one
             * detail : <p>test from kk test from kk15277157545</p>
             * photos : [{"id":"5b1e1e2981c6d547ecdadfad","alias":"1527563930588.png","fileName":"15287004572812495.png","contentType":"image/png","size":12947,"createdOn":1528700457281}]
             */

            private long id;
            private String title;
            private String detail;
            private List<PhotosBean> photos;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

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

            public List<PhotosBean> getPhotos() {
                return photos;
            }

            public void setPhotos(List<PhotosBean> photos) {
                this.photos = photos;
            }

            public static class PhotosBean {
                /**
                 * id : 5b1e1e2981c6d547ecdadfad
                 * alias : 1527563930588.png
                 * fileName : 15287004572812495.png
                 * contentType : image/png
                 * size : 12947
                 * createdOn : 1528700457281
                 */

                private String id;
                private String alias;
                private String fileName;
                private String contentType;
                private int size;
                private long createdOn;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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
            }
        }

        public static class RelatedFilesBean {
            /**
             * id : 5b1e1e1181c6d547ecdadfab
             * alias : 1527564707525.png
             * fileName : 15287004332413444.png
             * contentType : image/png
             * size : 12947
             * createdOn : 1528700433241
             */

            private String id;
            private String alias;
            private String fileName;
            private String contentType;
            private int size;
            private long createdOn;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
        }
    }
}
