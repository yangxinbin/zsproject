package com.mango.leo.zsproject.industrialpanorama.bean;

import com.mango.leo.zsproject.industrialpanorama.address.CityInterface;

import java.util.List;

/**
 * Created by admin on 2018/6/29.
 */

public class ChooseBean{


    /**
     * responseObject : null
     * responseList : [{"id":"5b1f9f2796dde1139825b70d","name":"广东省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b710","name":"安徽省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b711","name":"北京","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b712","name":"福建省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b713","name":"甘肃省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b714","name":"河北省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b715","name":"黑龙江省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b716","name":"湖北省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b717","name":"湖南省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b718","name":"吉林省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b719","name":"江苏省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b71a","name":"江西省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b71b","name":"宁夏省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b71c","name":"山东省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b71d","name":"山西省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b71e","name":"陕西省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b71f","name":"上海","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b720","name":"天津省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b721","name":"新疆省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b722","name":"云南省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b723","name":"浙江省","type":"province","parent":"中国"},{"id":"5b1f9f2796dde1139825b724","name":"重庆","type":"province","parent":"中国"},{"id":"5b345851f435f5170c42de67","name":"广东省","type":"province","parent":"中国"}]
     * totalRecords : null
     * currentPage : null
     * totalPages : null
     */

    private Object responseObject;
    private Object totalRecords;
    private Object currentPage;
    private Object totalPages;
    private List<ResponseListBean> responseList;

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
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

    public List<ResponseListBean> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<ResponseListBean> responseList) {
        this.responseList = responseList;
    }

    public static class ResponseListBean  implements CityInterface{
        /**
         * id : 5b1f9f2796dde1139825b70d
         * name : 广东省
         * type : province
         * parent : 中国
         */

        private String id;
        private String name;
        private String type;
        private String parent;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        @Override
        public String getCityName() {
            return name;
        }
    }
}
