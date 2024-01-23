package com.example.mykotlintest.network;

import java.util.List;

public class FirmwareUpdateResponse {

    private List<UpgradeTaskBean> upgrade_task;

    public List<UpgradeTaskBean> getUpgrade_task() {
        return upgrade_task;
    }

    public void setUpgrade_task(List<UpgradeTaskBean> upgrade_task) {
        this.upgrade_task = upgrade_task;
    }

    public static class UpgradeTaskBean {
        /**
         * device_id : 120000000A
         * is_force : N
         * main_version : {"device_version":"02.27.47","version_type":"0","url":"http://baidu.com","remark":""}
         * sub_versions : []
         */
        private String device_id;
        private String is_force;
        private MainVersionBean main_version;
        private List<SubVersionBean> sub_versions;
        /**
         * 是否是从”不支持SD卡预分配”到”支持SD卡预分配版本”切换
         * Y:是
         * N:否
         */
        private String switchto_sdcard_prealloc;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getIs_force() {
            return is_force;
        }

        public void setIs_force(String is_force) {
            this.is_force = is_force;
        }

        public MainVersionBean getMain_version() {
            return main_version;
        }

        public void setMain_version(MainVersionBean main_version) {
            this.main_version = main_version;
        }

        public List<SubVersionBean> getSub_versions() {
            return sub_versions;
        }

        public void setSub_versions(List<SubVersionBean> sub_versions) {
            this.sub_versions = sub_versions;
        }

        public String getSwitchto_sdcard_prealloc() {
            return switchto_sdcard_prealloc;
        }

        public void setSwitchto_sdcard_prealloc(String switchto_sdcard_prealloc) {
            this.switchto_sdcard_prealloc = switchto_sdcard_prealloc;
        }

        public static class MainVersionBean {
            /**
             * device_version : 02.27.47
             * version_type : 0
             * url : http://baidu.com
             * remark :
             */

            private String device_version;
            private String version_type;
            private String url;
            private String remark;
            private String md5sum;

            public String getDevice_version() {
                return device_version;
            }

            public void setDevice_version(String device_version) {
                this.device_version = device_version;
            }

            public String getVersion_type() {
                return version_type;
            }

            public void setVersion_type(String version_type) {
                this.version_type = version_type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getMd5sum() {
                return md5sum;
            }

            public void setMd5sum(String md5sum) {
                this.md5sum = md5sum;
            }
        }

        public static class SubVersionBean {
            private String subversion;
            private String device_version;
            private String component;
            private String url;
            private String remark;

            public String getSubversion() {
                return subversion;
            }

            public void setSubversion(String subversion) {
                this.subversion = subversion;
            }

            public String getDevice_version() {
                return device_version;
            }

            public void setDevice_version(String device_version) {
                this.device_version = device_version;
            }

            public String getComponent() {
                return component;
            }

            public void setComponent(String component) {
                this.component = component;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }

    }
}
