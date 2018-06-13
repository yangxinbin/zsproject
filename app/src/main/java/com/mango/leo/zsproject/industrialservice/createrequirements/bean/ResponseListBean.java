package com.mango.leo.zsproject.industrialservice.createrequirements.bean;

/**
 * Created by admin on 2018/6/12.
 */

public class ResponseListBean {
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
