package com.example.mykotlintest.network;

import java.util.List;

public class FirmwareUpdateRequest {
    /**
     * header : {"device_list":["4DBU5JKAL77ZPTEP111A","4MFVN28CZYAY3RPC111A","63U2AHNTSU3JEL5U111A","91E9SAJJL9E96WUA111A","H9ZEWGA5TCFN1KBV111A","YKEATM1LH4ABEXV4111A"],"seqno":"202401161354098530013187582630","user_id":"9ebd9623ece23d2fa4920d68dc144712","package_name":"com.jooan.cowelf","language":"zh_CN","client_version":"5.3.16.10","token":"ede730fe75f8421b8327","phone_model":"PHM110"}
     */

    private HeaderBean header;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public static class HeaderBean {
        /**
         * device_list : ["4DBU5JKAL77ZPTEP111A","4MFVN28CZYAY3RPC111A","63U2AHNTSU3JEL5U111A","91E9SAJJL9E96WUA111A","H9ZEWGA5TCFN1KBV111A","YKEATM1LH4ABEXV4111A"]
         * seqno : 202401161354098530013187582630
         * user_id : 9ebd9623ece23d2fa4920d68dc144712
         * package_name : com.jooan.cowelf
         * language : zh_CN
         * client_version : 5.3.16.10
         * token : ede730fe75f8421b8327
         * phone_model : PHM110
         */

        private String seqno;
        private String user_id;
        private String package_name;
        private String language;
        private String client_version;
        private String token;
        private String phone_model;
        private List<String> device_list;

        public String getSeqno() {
            return seqno;
        }

        public void setSeqno(String seqno) {
            this.seqno = seqno;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getClient_version() {
            return client_version;
        }

        public void setClient_version(String client_version) {
            this.client_version = client_version;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhone_model() {
            return phone_model;
        }

        public void setPhone_model(String phone_model) {
            this.phone_model = phone_model;
        }

        public List<String> getDevice_list() {
            return device_list;
        }

        public void setDevice_list(List<String> device_list) {
            this.device_list = device_list;
        }
    }
}
