package com.mango.leo.zsproject.industrialservice.createrequirements.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/12.
 */

public class ChanyLingyuBean {

    /**
     * responseObject : null
     * responseList : [{"id":"5b1e227581c6d53060a748e5","name":"ABC","parent":null,"level":"one"},{"id":"5b1e227581c6d53060a748e6","name":"DEF","parent":null,"level":"one"},{"id":"5b1e235381c6d53060a748e9","name":"农、林、牧、渔业","parent":null,"level":"one"}]
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

    public static class ResponseListBean {
        /**
         * id : 5b1e227581c6d53060a748e5
         * name : ABC
         * parent : null
         * level : one
         */

        private String id;
        private String name;
        private Object parent;
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

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
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
