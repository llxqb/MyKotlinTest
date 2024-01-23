package com.example.mykotlintest.mqtt;

/**
 * @描述： @SSLConstant
 * @作者： @黄卫旗
 * @创建时间： @05/09/2018
 */
public class SSLConstant {

    //"file:///android_asset/rootCA.crt";
    public static final String CA_PATH = "mqtt/rootCA.crt";//0集群
    public static final String Mqtt_QacloudCA_PATH = "mqtt/qacloudCA.crt";//新的钢铁侠mqtt 1集群证书
    public static final String Mqtt2 = "mqtt/jooanmqtt2.crt";//新的钢铁侠mqtt 2集群证书
    public static final String Mqtt3 = "mqtt/jooanmqtt3.crt";//新的钢铁侠mqtt 3集群证书
    public static final String Mqtt4 = "mqtt/jooanmqtt4.crt";//新的钢铁侠mqtt 4集群证书
    public static final String Mqtt5 = "mqtt/jooanmqtt5.crt";//新的钢铁侠mqtt 5集群证书

    //"file:///android_asset/commserver.qalink.cn-client.crt";
    public static final String CRT_PATH = "mqtt/commserver.qalink.cn-client.crt";

    public static final String PASSWORD = "VeB3DVJcm1Xw5Ucu";

    public static final String LENOVO_CRT_PAHT= "lenovo/huiyanCA.crt";
}