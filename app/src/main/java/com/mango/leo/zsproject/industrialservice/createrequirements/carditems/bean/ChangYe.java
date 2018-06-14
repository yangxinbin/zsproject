package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import java.util.List;

/**
 * Created by admin on 2018/6/14.
 */

public class ChangYe {

    /**
     * responseObject : {"采矿业":["铜矿采选","褐煤开采洗选"]}
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
        private List<String> 采矿业;

        public List<String> get采矿业() {
            return 采矿业;
        }

        public void set采矿业(List<String> 采矿业) {
            this.采矿业 = 采矿业;
        }
    }
}
