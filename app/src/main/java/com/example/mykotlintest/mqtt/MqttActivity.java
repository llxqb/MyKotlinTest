package com.example.mykotlintest.mqtt;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mykotlintest.R;

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
    }

    public void btnConnect(View view) {
        MqttHelp.mqttConnect(mqttAndroidClient, mqttConnectOptions);
    }

    public void btnSubscribe(View view) {
        // 可以同时订阅多个主题
        String[] topic = {MqttHelp.subscriptionTopic};
        int[] qos = {1}; // 发布消息的服务质量
        MqttHelp.subscribeToTopic(topic, qos, mqttAndroidClient);
    }

    public void btnPublish(View view) {
        String msg = "{\"cmd\":66352,\"cmd_type\":\"request\",\"sessionid\":0,\"value\":3}";
        MqttHelp.publishMessage(mqttAndroidClient, msg, MqttHelp.publishTopic);
    }

}