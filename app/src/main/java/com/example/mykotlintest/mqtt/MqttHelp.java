package com.example.mykotlintest.mqtt;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.net.ssl.SSLSocketFactory;

public class MqttHelp {

    private static String TAG = "MqttHelp";
    // 订阅主题Topic
    public static String subscriptionTopic = "qaiot/mqtt/user/4MFVN28CZYAY3RPC111A";
    // 发布主题Topic
    public static String publishTopic = "qaiot/mqtt/4MFVN28CZYAY3RPC111A";

    /**
     * 初始化
     */
    public static MqttConnectOptions mqttInit(Context context) {
        // 连接
        String username = "b4a36b0707a4326da70cd7717d070c6c";
        String pwd = "2835a2f0122049a2b3dd";

        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);// 清除缓存
        conOpt.setConnectionTimeout(30);// 设置超时时间，单位：秒
        // 心跳包发送间隔: 如果在这个时间内没有任何数据通信，客户端会发送一个 PINGREQ 消息到服务器，以维持连接。如果心跳失败，则连接将自动断开。
        conOpt.setKeepAliveInterval(60);// 心跳包发送间隔，单位：秒
        conOpt.setUserName(username);// 用户名
        conOpt.setPassword(pwd.toCharArray());// 密码
        conOpt.setAutomaticReconnect(true);// 是否自动连接

        try {
//            SSLSocketFactory sslSocketFactory = SSLHandler.getSSLSocket(this, SSLConstant.CA_PATH, SSLConstant.PASSWORD); // 集群0
            SSLSocketFactory sslSocketFactory = SSLHandler.getSSLSocket(context, SSLConstant.Mqtt_QacloudCA_PATH, SSLConstant.PASSWORD);// 集群1
            conOpt.setSocketFactory(sslSocketFactory);// SSL支持
            conOpt.setHttpsHostnameVerificationEnabled(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return conOpt;
    }

    /**
     * mqtt连接
     */
    public static void mqttConnect(MqttAndroidClient mqttAndroidClient, MqttConnectOptions mqttConnectOptions) {
        try {
//            Log.e(TAG, "connect是否连接 " + mqttAndroidClient.isConnected());
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.e(TAG, "connect 成功");
//                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "Failed to connect to: " + " ,exception = " + exception.toString());
                }
            });
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 订阅主题 ： 可以一次订阅一个或者多个主题
     *
     * @param qos 发布消息的服务质量，即：保证消息传递的次数 , 1：默认1，表示传递一次
     */
    public static void subscribeToTopic(String[] topic, int[] qos, MqttAndroidClient mqttAndroidClient) {
        try {
            mqttAndroidClient.subscribe(topic, qos, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "订阅成功");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "订阅失败");
                }
            });
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发布消息
     */
    public static void publishMessage(MqttAndroidClient mqttAndroidClient, String msg,String publishTopic) {
//        MqttMessage message = new MqttMessage();
        Log.e(TAG, "msg = " + msg);
        if (mqttAndroidClient.isConnected()) {
            try {
                // 使用默认值： qos = 1, retained = false
                mqttAndroidClient.publish(publishTopic, msg.getBytes(), 1, false);
            } catch (MqttException e) {
                try {
                    // 发布主题失败这里断开mqtt 连接
                    mqttAndroidClient.disconnect();
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


//    public void publish(MqttAndroidClient mqttAndroidClient, String msg, String topic, int qos, boolean retained) {
//        try {
//            if (mqttAndroidClient != null)
//                mqttAndroidClient.publish(topic, msg.getBytes(), qos, retained);
//        } catch (Exception e) {
//            try {
//                mqttAndroidClient.disconnect();
//            } catch (MqttException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            if (mqttAndroidClient != null) mqttAndroidClient.close();
//        }
//    }
}
