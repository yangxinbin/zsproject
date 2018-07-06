package com.mango.leo.zsproject.datacenter.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/9.
 */

public class TouZiBean {

    /**
     * content : [{"id":"5b20c6581233c5226c392c57","assetSize":null,"projectAmount":null,"planName":null,"investmentPhases":null,"cooperationStyles":null,"title":"Wed Jun 13 15:23:04 CST 2018","logo":{"id":"5b20c6581233c5226c392c55","alias":"1527564707525.png","fileName":"15288745847093317.png","contentType":"image/png","size":12947,"createdOn":1528874584709},"investmentSize":null,"fundType":null,"investmentMethod":null,"investmentPeriod":null,"investorLocation":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null},"investmentRegion":[],"riskAssuranse":null,"minProfitRatio":null,"industries":[],"description":null,"investmentCase":null,"contactInfo":{"id":null,"name":null,"department":null,"position":null,"mobile":null,"phone":null,"email":null},"createdBy":null,"createdOn":null},{"id":"5b223908f8aa411990e70d25","assetSize":"","projectAmount":null,"planName":null,"investmentPhases":["种子轮","天使轮","IPO上市","其它"],"cooperationStyles":["合资","合作"],"title":"Test for tom1","logo":{"id":"5b223908f8aa411990e70d23","alias":"test.jpg","fileName":"15289694806764488.jpg","contentType":"image/jpeg","size":13693,"createdOn":1528969480676},"investmentSize":{"caption":"（含）1000万-5000万","min":1000,"max":5000},"fundType":["个人资金"],"investmentMethod":["债权投资"],"investmentPeriod":"1000","investorLocation":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null},"investmentRegion":[{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null}],"riskAssuranse":"warafa","minProfitRatio":null,"industries":[{"id":"5b1f311581c6d52b14c953a6","name":"小麦种植","parent":"农、林、牧、渔业","level":"two"}],"description":"afdafa","investmentCase":"fsafds","contactInfo":{"id":null,"name":"fafa","department":null,"position":null,"mobile":"15617389009","phone":null,"email":"352089546@qq.com"},"createdBy":null,"createdOn":null}]
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
         * id : 5b20c6581233c5226c392c57
         * assetSize : null
         * projectAmount : null
         * planName : null
         * investmentPhases : null
         * cooperationStyles : null
         * title : Wed Jun 13 15:23:04 CST 2018
         * logo : {"id":"5b20c6581233c5226c392c55","alias":"1527564707525.png","fileName":"15288745847093317.png","contentType":"image/png","size":12947,"createdOn":1528874584709}
         * investmentSize : null
         * fundType : null
         * investmentMethod : null
         * investmentPeriod : null
         * investorLocation : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null}
         * investmentRegion : []
         * riskAssuranse : null
         * minProfitRatio : null
         * industries : []
         * description : null
         * investmentCase : null
         * contactInfo : {"id":null,"name":null,"department":null,"position":null,"mobile":null,"phone":null,"email":null}
         * createdBy : null
         * createdOn : null
         */

        private String id;
        private Object assetSize;
        private Object projectAmount;
        private Object planName;
        private Object investmentPhases;
        private Object cooperationStyles;
        private String title;
        private LogoBean logo;
        private Object investmentSize;
        private Object fundType;
        private Object investmentMethod;
        private Object investmentPeriod;
        private InvestorLocationBean investorLocation;
        private Object riskAssuranse;
        private Object minProfitRatio;
        private Object description;
        private Object investmentCase;
        private ContactInfoBean contactInfo;
        private Object createdBy;
        private Object createdOn;
        private List<?> investmentRegion;
        private List<?> industries;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getAssetSize() {
            return assetSize;
        }

        public void setAssetSize(Object assetSize) {
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

        public Object getInvestmentPhases() {
            return investmentPhases;
        }

        public void setInvestmentPhases(Object investmentPhases) {
            this.investmentPhases = investmentPhases;
        }

        public Object getCooperationStyles() {
            return cooperationStyles;
        }

        public void setCooperationStyles(Object cooperationStyles) {
            this.cooperationStyles = cooperationStyles;
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

        public Object getInvestmentSize() {
            return investmentSize;
        }

        public void setInvestmentSize(Object investmentSize) {
            this.investmentSize = investmentSize;
        }

        public Object getFundType() {
            return fundType;
        }

        public void setFundType(Object fundType) {
            this.fundType = fundType;
        }

        public Object getInvestmentMethod() {
            return investmentMethod;
        }

        public void setInvestmentMethod(Object investmentMethod) {
            this.investmentMethod = investmentMethod;
        }

        public Object getInvestmentPeriod() {
            return investmentPeriod;
        }

        public void setInvestmentPeriod(Object investmentPeriod) {
            this.investmentPeriod = investmentPeriod;
        }

        public InvestorLocationBean getInvestorLocation() {
            return investorLocation;
        }

        public void setInvestorLocation(InvestorLocationBean investorLocation) {
            this.investorLocation = investorLocation;
        }

        public Object getRiskAssuranse() {
            return riskAssuranse;
        }

        public void setRiskAssuranse(Object riskAssuranse) {
            this.riskAssuranse = riskAssuranse;
        }

        public Object getMinProfitRatio() {
            return minProfitRatio;
        }

        public void setMinProfitRatio(Object minProfitRatio) {
            this.minProfitRatio = minProfitRatio;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getInvestmentCase() {
            return investmentCase;
        }

        public void setInvestmentCase(Object investmentCase) {
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

        public List<?> getInvestmentRegion() {
            return investmentRegion;
        }

        public void setInvestmentRegion(List<?> investmentRegion) {
            this.investmentRegion = investmentRegion;
        }

        public List<?> getIndustries() {
            return industries;
        }

        public void setIndustries(List<?> industries) {
            this.industries = industries;
        }

        public static class LogoBean {
            /**
             * id : 5b20c6581233c5226c392c55
             * alias : 1527564707525.png
             * fileName : 15288745847093317.png
             * contentType : image/png
             * size : 12947
             * createdOn : 1528874584709
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
             * name : null
             * department : null
             * position : null
             * mobile : null
             * phone : null
             * email : null
             */

            private Object id;
            private Object name;
            private Object department;
            private Object position;
            private Object mobile;
            private Object phone;
            private Object email;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
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

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }
        }
    }
}
