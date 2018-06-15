package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean;

import java.util.List;

/**
 * Created by admin on 2018/5/22.
 */

public class CardSecondItemBeanObj {
    private List<CardSecondItemBean> content;

    public List<CardSecondItemBean> getContent() {
        return content;
    }

    public void setContent(List<CardSecondItemBean> content) {
        this.content = content;
    }

    public static class CardSecondItemBean {
        private String changye;
        private List<String> lingyuList;

        @Override
        public String toString() {
            return "CardSecondItemBean{" +
                    "changye='" + changye + '\'' +
                    ", lingyuList=" + lingyuList +
                    '}';
        }

        public CardSecondItemBean(String str, List<String> listStr) {
            this.changye = str;
            this.lingyuList = listStr;
        }

        public CardSecondItemBean() {

        }

        public String getChangye() {
            return changye;
        }

        public void setChangye(String changye) {
            this.changye = changye;
        }

        public List<String> getLingyuList() {
            return lingyuList;
        }

        public void setLingyuList(List<String> lingyuList) {
            this.lingyuList = lingyuList;
        }
    }
}
