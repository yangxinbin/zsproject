package com.mango.leo.zsproject.industrialpanorama.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/1.
 */

public class CityBean {

    /**
     * responseObject : {"id":"5b1a057681c6d51e387ee139","banner":{"id":"5b1a053581c6d51e387ee134","alias":"1527563930588.png","fileName":"15284319258441397.png","contentType":"image/png","size":12947,"createdOn":1528431925844},"location":{"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null},"map":null,"introduction":[{"title":"test from kk","detail":"dsafsdljflaksdjf","deletable":false,"photo":{"id":"5b1a054581c6d51e387ee136","alias":"1527241586471.png","fileName":"15284319416736735.png","contentType":"image/png","size":459702,"createdOn":1528431941673}},{"title":"test from jack","detail":"lsdkjflksdjfljslkfjaslkdsafsdasfd","deletable":false,"photo":{"id":"5b1a05b381c6d51e387ee13a","alias":"1527241586471.png","fileName":"15284320510296834.png","contentType":"image/png","size":459702,"createdOn":1528432051029}},{"title":"交通运输 ","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null},{"title":"自然生态","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null},{"title":"旅游资源","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null}]}
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
         * id : 5b1a057681c6d51e387ee139
         * banner : {"id":"5b1a053581c6d51e387ee134","alias":"1527563930588.png","fileName":"15284319258441397.png","contentType":"image/png","size":12947,"createdOn":1528431925844}
         * location : {"country":"中国","province":"广东省","city":"深圳","district":"深圳","address":null,"lon":null,"lat":null}
         * map : null
         * introduction : [{"title":"test from kk","detail":"dsafsdljflaksdjf","deletable":false,"photo":{"id":"5b1a054581c6d51e387ee136","alias":"1527241586471.png","fileName":"15284319416736735.png","contentType":"image/png","size":459702,"createdOn":1528431941673}},{"title":"test from jack","detail":"lsdkjflksdjfljslkfjaslkdsafsdasfd","deletable":false,"photo":{"id":"5b1a05b381c6d51e387ee13a","alias":"1527241586471.png","fileName":"15284320510296834.png","contentType":"image/png","size":459702,"createdOn":1528432051029}},{"title":"交通运输 ","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null},{"title":"自然生态","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null},{"title":"旅游资源","detail":"Lorem ipsum dolor sit amet, vix corpora suscipiantur in, vix causae scripserit ut. Ei duis admodum gloriatur eam, cum doctus timeam laboramus id. Vis ex mundi tantas semper, consul nemore sed an, quis percipit id pri. Movet homero cu sit.\r\n\r\nEi interesset theophrastus sed. Vis ubique consulatu an. Cum ei persius scaevola necessitatibus. Facete theophrastus et vis, at nam modus option blandit, at nusquam mentitum duo. Cu sea alia viderer moderatius, bonorum apeirian te mea.\r\n\r\nEi est erroribus hendrerit, ius an quod regione alienum. Cu dolore definiebas qui. Ad qui aliquam tractatos. Et usu audiam laboramus, eius noster tamquam pri ea, ne suas accusata nam. Ex duo eleifend quaerendum persequeris.","deletable":false,"photo":null}]
         */

        private String id;
        private BannerBean banner;
        private LocationBean location;
        private Object map;
        private List<IntroductionBean> introduction;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BannerBean getBanner() {
            return banner;
        }

        public void setBanner(BannerBean banner) {
            this.banner = banner;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public Object getMap() {
            return map;
        }

        public void setMap(Object map) {
            this.map = map;
        }

        public List<IntroductionBean> getIntroduction() {
            return introduction;
        }

        public void setIntroduction(List<IntroductionBean> introduction) {
            this.introduction = introduction;
        }

        public static class BannerBean {
            /**
             * id : 5b1a053581c6d51e387ee134
             * alias : 1527563930588.png
             * fileName : 15284319258441397.png
             * contentType : image/png
             * size : 12947
             * createdOn : 1528431925844
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

        public static class LocationBean {
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

        public static class IntroductionBean {
            /**
             * title : test from kk
             * detail : dsafsdljflaksdjf
             * deletable : false
             * photo : {"id":"5b1a054581c6d51e387ee136","alias":"1527241586471.png","fileName":"15284319416736735.png","contentType":"image/png","size":459702,"createdOn":1528431941673}
             */

            private String title;
            private String detail;
            private boolean deletable;
            private PhotoBean photo;

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

            public PhotoBean getPhoto() {
                return photo;
            }

            public void setPhoto(PhotoBean photo) {
                this.photo = photo;
            }

            public static class PhotoBean {
                /**
                 * id : 5b1a054581c6d51e387ee136
                 * alias : 1527241586471.png
                 * fileName : 15284319416736735.png
                 * contentType : image/png
                 * size : 459702
                 * createdOn : 1528431941673
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
}
