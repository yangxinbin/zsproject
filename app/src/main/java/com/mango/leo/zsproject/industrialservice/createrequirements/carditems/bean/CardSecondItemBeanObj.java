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
