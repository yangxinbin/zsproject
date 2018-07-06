package com.mango.leo.zsproject.industrialservice.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/6.
 */

public class MatchDataBean {

    /**
     * content : [{"id":"5b223908f8aa411990e70d25","assetSize":"","projectAmount":null,"planName":null,"investmentPhases":["种子轮","天使轮","IPO上市","其它"],"cooperationStyles":["合资","合作"],"title":"Test for tom1","logo":{"id":"5b223908f8aa411990e70d23","alias":"test.jpg","fileName":"15289694806764488.jpg","contentType":"image/jpeg","size":13693,"createdOn":1528969480676},"investmentSize":{"caption":"（含）1000万-5000万","min":1000,"max":5000},"fundType":["个人资金"],"investmentMethod":["债权投资"],"investmentPeriod":"1000","investorLocation":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null},"investmentRegion":[{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null}],"riskAssuranse":"warafa","minProfitRatio":null,"industries":[{"id":"5b1f311581c6d52b14c953a6","name":"小麦种植","parent":"农、林、牧、渔业","level":"two"}],"description":"afdafa","investmentCase":"fsafds","contactInfo":{"id":null,"name":"fafa","department":null,"position":null,"mobile":"15617389009","phone":null,"email":"352089546@qq.com"},"createdBy":null,"createdOn":null}]
     * pageable : {"sort":{"sorted":false,"unsorted":true},"offset":0,"pageSize":20,"pageNumber":0,"paged":true,"unpaged":false}
     * last : true
     * totalElements : 2
     * totalPages : 1
     * size : 20
     * number : 0
     * sort : {"sorted":false,"unsorted":true}
     * first : true
     * numberOfElements : 2
     */

    private PageableBean pageable;
    private boolean last;
    private int totalElements;
    private int totalPages;
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
         * paged : true
         * unpaged : false
         */

        private SortBean sort;
        private int offset;
        private int pageSize;
        private int pageNumber;
        private boolean paged;
        private boolean unpaged;

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
         * id : 5b223908f8aa411990e70d25
         * assetSize :
         * projectAmount : null
         * planName : null
         * investmentPhases : ["种子轮","天使轮","IPO上市","其它"]
         * cooperationStyles : ["合资","合作"]
         * title : Test for tom1
         * logo : {"id":"5b223908f8aa411990e70d23","alias":"test.jpg","fileName":"15289694806764488.jpg","contentType":"image/jpeg","size":13693,"createdOn":1528969480676}
         * investmentSize : {"caption":"（含）1000万-5000万","min":1000,"max":5000}
         * fundType : ["个人资金"]
         * investmentMethod : ["债权投资"]
         * investmentPeriod : 1000
         * investorLocation : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null}
         * investmentRegion : [{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null}]
         * riskAssuranse : warafa
         * minProfitRatio : null
         * industries : [{"id":"5b1f311581c6d52b14c953a6","name":"小麦种植","parent":"农、林、牧、渔业","level":"two"}]
         * description : afdafa
         * investmentCase : fsafds
         * contactInfo : {"id":null,"name":"fafa","department":null,"position":null,"mobile":"15617389009","phone":null,"email":"352089546@qq.com"}
         * createdBy : null
         * createdOn : null
         */

        private String id;
        private String assetSize;
        private Object projectAmount;
        private Object planName;
        private String title;
        private LogoBean logo;
        private InvestmentSizeBean investmentSize;
        private String investmentPeriod;
        private InvestorLocationBean investorLocation;
        private String riskAssuranse;
        private Object minProfitRatio;
        private String description;
        private String investmentCase;
        private ContactInfoBean contactInfo;
        private Object createdBy;
        private Object createdOn;
        private List<String> investmentPhases;
        private List<String> cooperationStyles;
        private List<String> fundType;
        private List<String> investmentMethod;
        private List<InvestmentRegionBean> investmentRegion;
        private List<IndustriesBean> industries;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAssetSize() {
            return assetSize;
        }

        public void setAssetSize(String assetSize) {
            this.assetSize = assetSize;
        }

        public Object getProjectAmount() {
            return projectAmount;
        }

        public void setProjectAmount(Object projectAmount) {
            this.projectAmount = projectAmount;
        }

        public Object getPlanName() {
            return planName;
        }

        public void setPlanName(Object planName) {
            this.planName = planName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LogoBean getLogo() {
            return logo;
        }

        public void setLogo(LogoBean logo) {
            this.logo = logo;
        }

        public InvestmentSizeBean getInvestmentSize() {
            return investmentSize;
        }

        public void setInvestmentSize(InvestmentSizeBean investmentSize) {
            this.investmentSize = investmentSize;
        }

        public String getInvestmentPeriod() {
            return investmentPeriod;
        }

        public void setInvestmentPeriod(String investmentPeriod) {
            this.investmentPeriod = investmentPeriod;
        }

        public InvestorLocationBean getInvestorLocation() {
            return investorLocation;
        }

        public void setInvestorLocation(InvestorLocationBean investorLocation) {
            this.investorLocation = investorLocation;
        }

        public String getRiskAssuranse() {
            return riskAssuranse;
        }

        public void setRiskAssuranse(String riskAssuranse) {
            this.riskAssuranse = riskAssuranse;
        }

        public Object getMinProfitRatio() {
            return minProfitRatio;
        }

        public void setMinProfitRatio(Object minProfitRatio) {
            this.minProfitRatio = minProfitRatio;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInvestmentCase() {
            return investmentCase;
        }

        public void setInvestmentCase(String investmentCase) {
            this.investmentCase = investmentCase;
        }

        public ContactInfoBean getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(ContactInfoBean contactInfo) {
            this.contactInfo = contactInfo;
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

        public List<String> getInvestmentPhases() {
            return investmentPhases;
        }

        public void setInvestmentPhases(List<String> investmentPhases) {
            this.investmentPhases = investmentPhases;
        }

        public List<String> getCooperationStyles() {
            return cooperationStyles;
        }

        public void setCooperationStyles(List<String> cooperationStyles) {
            this.cooperationStyles = cooperationStyles;
        }

        public List<String> getFundType() {
            return fundType;
        }

        public void setFundType(List<String> fundType) {
            this.fundType = fundType;
        }

        public List<String> getInvestmentMethod() {
            return investmentMethod;
        }

        public void setInvestmentMethod(List<String> investmentMethod) {
            this.investmentMethod = investmentMethod;
        }

        public List<InvestmentRegionBean> getInvestmentRegion() {
            return investmentRegion;
        }

        public void setInvestmentRegion(List<InvestmentRegionBean> investmentRegion) {
            this.investmentRegion = investmentRegion;
        }

        public List<IndustriesBean> getIndustries() {
            return industries;
        }

        public void setIndustries(List<IndustriesBean> industries) {
            this.industries = industries;
        }

        public static class LogoBean {
            /**
             * id : 5b223908f8aa411990e70d23
             * alias : test.jpg
             * fileName : 15289694806764488.jpg
             * contentType : image/jpeg
             * size : 13693
             * createdOn : 1528969480676
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

        public static class InvestmentSizeBean {
            /**
             * caption : （含）1000万-5000万
             * min : 1000
             * max : 5000
             */

            private String caption;
            private int min;
            private int max;

            public String getCaption() {
                return caption;
            }

            public void setCaption(String caption) {
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

        public static class InvestorLocationBean {
            /**
             * country : 中国
             * province : 广东省
             * city : 深圳
             * district : 深圳
             * address : null
             * lon : null
             * lat : null
             */

            private String country;
            private String province;
            private String city;
            private String district;
            private Object address;
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

        public static class ContactInfoBean {
            /**
             * id : null
             * name : fafa
             * department : null
             * position : null
             * mobile : 15617389009
             * phone : null
             * email : 352089546@qq.com
             */

            private Object id;
            private String name;
            private Object department;
            private Object position;
            private String mobile;
            private Object phone;
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

            public Object getDepartment() {
                return department;
            }

            public void setDepartment(Object department) {
                this.department = department;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }

        public static class InvestmentRegionBean {
            /**
             * country : 中国
             * province : 广东省
             * city : 深圳
             * district : 深圳
             * address : null
             * lon : null
             * lat : null
             */

            private String country;
            private String province;
            private String city;
            private String district;
            private Object address;
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

        public static class IndustriesBean {
            /**
             * id : 5b1f311581c6d52b14c953a6
             * name : 小麦种植
             * parent : 农、林、牧、渔业
             * level : two
             */

            private String id;
            private String name;
            private String parent;
            private String level;

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

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
