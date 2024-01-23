package com.example.mykotlintest.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MMqttCallback implements MqttCallbackExtended {
    private String TAG = "MMqttCallback";

    /**
     * 连接成功会进入到这里
     *
     * @param reconnect 如果为 true，则连接是自动重新连接的结果
     * @param serverURI The server URI that the connection was made to.
     */
    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        // 可以做订阅主题
        Log.i(TAG, "MqttAndroidClient 回调， 是否是自动连接成功 = " + reconnect + " , serverURI = " + serverURI);
    }

    /**
     * 失去连接,连接断开
     *
     * @param cause the reason behind the loss of connection.
     */
    @Override
    public void connectionLost(Throwable cause) {
        // 连接断开
        Log.i(TAG, "connectionLost");
    }

    /**
     * 发布消息回调处理
     *
     * @param topic   name of the topic on the message was published to
     * @param message the actual message.
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.i(TAG, "message = " + message.toString());
        // TODO 这里对到达的消息处理

    }

    /**
     * 消息发送完成
     *
     * @param token the delivery token associated with the message.
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.i(TAG, "deliveryComplete");

    }
}
