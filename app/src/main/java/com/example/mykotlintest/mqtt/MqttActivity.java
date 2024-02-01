package com.example.mykotlintest.mqtt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mykotlintest.R;
import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * MQTT
 */
public class MqttActivity extends AppCompatActivity {

    private String TAG = "MqttActivity";
    String serverUri = "ssl://commserver1.qalink.cn:30521";
    String clientId = "b4a36b0707a4326da70cd7717d070c6c1705982342260";

    MqttAndroidClient mqttAndroidClient;
    MqttConnectOptions mqttConnectOptions;
    String[] subscriptionTopic;// 订阅主题集群

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);

        initView();
    }

    private void initView() {
        mqttAndroidClient = new MqttAndroidClient(this, serverUri, clientId);
        mqttConnectOptions = MqttHelp.mqttInit(this);

        // MQTT 状态回调（连上、断开）
        mqttAndroidClient.setCallback(new MMqttCallback());

        subscriptionTopic = new String[]{MqttHelp.subscriptionTopic + MqttHelp.uids[0], MqttHelp.subscriptionTopic + MqttHelp.uids[1]};
    }


    boolean isConnect = false;

    /**
     * mqtt连接
     */
    public void btnConnect(View view) {
        if (!isConnect) {
            isConnect = true;
            MqttHelp.mqttConnect(mqttAndroidClient, mqttConnectOptions);
        } else {
            isConnect = false;
            // 断开连接
            MqttHelp.disconnect(mqttAndroidClient);
        }
    }

    boolean isSubscribe = false;

    /**
     * 开始订阅
     */
    public void btnSubscribe(View view) {
        // 可以同时订阅多个主题
        Log.e(TAG, "topic = " + new Gson().toJson(subscriptionTopic));
        int[] qos = {1, 1}; // 发布消息的服务质量

        if (!isSubscribe) {
            isSubscribe = true;
            MqttHelp.subscribeToTopic(subscriptionTopic, qos, mqttAndroidClient);

        } else {
            isSubscribe = false;
            // 取消订阅
            MqttHelp.unsubscribe(mqttAndroidClient, subscriptionTopic);
        }
    }

    /**
     * 开始发布
     */
    public void btnPublish(View view) {
        String msg = "{\"cmd\":66352,\"cmd_type\":\"request\",\"sessionid\":0,\"value\":3}";
        MqttHelp.publishMessage(mqttAndroidClient, msg, MqttHelp.publishTopic + MqttHelp.uids[0]);
    }

    @Override
    public void finish() {
        super.finish();
        if (mqttAndroidClient.isConnected()) {
            // 断开连接
            MqttHelp.disconnect(mqttAndroidClient);
        }
    }
}