package com.mango.leo.zsproject.eventexhibition.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/7.
 */

public class ContentBean {
    public ContentBean() {
    }

    /**
     * id : 5b15237c81c6d5411c9e73b8
     * name : test from kk
     * location : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":"Guangdong","lon":null,"lat":null}
     * description : null
     * price : 0
     * startTime : 1528041600000
     * endTime : 1530115200000
     * popular : false
     * organizer : kk
     * banner : {"id":"5b15240481c6d5411c9e73bc","alias":"1527564707525.png","fileName":"15281121321544994.png","contentType":"image/png","size":12947,"createdOn":1528112132154}
     * coorganizers : []
     * relatedIndustries : []
     * introductions : [{"title":"Introduction","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null}]
     * createdBy : null
     * createdOn : null
     * published : null
     * tenant : @super
     */

    private String id;
    private String name;
    private EventBean.ResponseObjectBean.ContentBean.LocationBean location;
    private Object description;
    private int price;
    private long startTime;
    private long endTime;
    private boolean popular;
    private String organizer;
    private EventBean.ResponseObjectBean.ContentBean.BannerBean banner;
    private Object createdBy;
    private Object createdOn;
    private Object published;
    private String tenant;
    private List<?> coorganizers;
    private List<?> relatedIndustries;
    private List<EventBean.ResponseObjectBean.ContentBean.IntroductionsBean> introductions;

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

    public EventBean.ResponseObjectBean.ContentBean.LocationBean getLocation() {
        return location;
    }

    public void setLocation(EventBean.ResponseObjectBean.ContentBean.LocationBean location) {
        this.location = location;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public EventBean.ResponseObjectBean.ContentBean.BannerBean getBanner() {
        return banner;
    }

    public void setBanner(EventBean.ResponseObjectBean.ContentBean.BannerBean banner) {
        this.banner = banner;
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

    public Object getPublished() {
        return published;
    }

    public void setPublished(Object published) {
        this.published = published;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<?> getCoorganizers() {
        return coorganizers;
    }

    public void setCoorganizers(List<?> coorganizers) {
        this.coorganizers = coorganizers;
    }

    public List<?> getRelatedIndustries() {
        return relatedIndustries;
    }

    public void setRelatedIndustries(List<?> relatedIndustries) {
        this.relatedIndustries = relatedIndustries;
    }

    public List<EventBean.ResponseObjectBean.ContentBean.IntroductionsBean> getIntroductions() {
        return introductions;
    }

    public void setIntroductions(List<EventBean.ResponseObjectBean.ContentBean.IntroductionsBean> introductions) {
        this.introductions = introductions;
    }

    public static class LocationBean {
        /**
         * country : 中国
         * province : 广东省
         * city : 深圳
         * district : 深圳
         * address : Guangdong
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

    public static class BannerBean {
        /**
         * id : 5b15240481c6d5411c9e73bc
         * alias : 1527564707525.png
         * fileName : 15281121321544994.png
         * contentType : image/png
         * size : 12947
         * createdOn : 1528112132154
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

    public static class IntroductionsBean {
        /**
         * title : Introduction
         * detail : Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.

         Ei interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.

         Ei est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.
         * deletable : false
         * photo : null
         */

        private String title;
        private String detail;
        private boolean deletable;
        private Object photo;

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

        public boolean isDeletable() {
            return deletable;
        }

        public void setDeletable(boolean deletable) {
            this.deletable = deletable;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }
    }
}
