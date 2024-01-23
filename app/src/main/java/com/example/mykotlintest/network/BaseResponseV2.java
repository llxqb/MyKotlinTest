package com.example.mykotlintest.network;

public class BaseResponseV2<T> {
    /**
     * error_code : 0
     * error_msg : success
     * subscribe_id : 81b138467b84300280535eb259d0e71e
     * card_ticket_info : {"card_ticket_no":"10012571","card_ticket_pwd":"AEXQBYLBB6SCNPJ5T47P","owner_id":"yfCDUhyrQmqTp4YwIkv6bA","card_ticket_name":"sdfsd","card_ticket_desc":"safdsa","product_name":"事件型7天云存储(月)","product_desc":"一个月","product_duration_unit":"1","product_duration":"1","card_ticket_discount":0.88,"card_ticket_deduct_amount":0,"expire_at":"2019-12-12 14:00:00","card_ticket_type":0,"mode":4}
     */
    private String error_code;
    private String error_msg;
    private T body_info;

    public T getBody_info() {
        return body_info;
    }

    public void setBody_info(T body_info) {
        this.body_info = body_info;
    }

    public String getErrorCode() {
        return error_code;
    }

    public void setErrorCode(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getBodyInfo() {
        return body_info;
    }

    public void setBodyInfo(T body_info) {
        this.body_info = body_info;
    }
}
