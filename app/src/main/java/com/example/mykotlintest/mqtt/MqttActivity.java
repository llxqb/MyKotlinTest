package com.example.mykotlintest.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mykotlintest.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttActivity extends AppCompatActivity {

    private String TAG = "MqttActivity";
    String serverUri = "commserver1.qalink.cn:30521";
    String clientId = "1";
    String subscriptionTopic = "qaiot/mqtt/user/4MFVN28CZYAY3RPC111A";
    String publishTopic = "qaiot/mqtt/4MFVN28CZYAY3RPC111A";
    MqttAndroidClient mqttAndroidClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);

        mqttAndroidClient = new MqttAndroidClient(this, serverUri, clientId);

        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.i(TAG,"reconnect = "+reconnect);
                if (reconnect) {
                    // Because Clean Session is true, we need to re-subscribe
                    subscribeToTopic();
                } else {
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.i(TAG,"connectionLost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.i(TAG,"message = "+message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.i(TAG,"deliveryComplete");
            }
        });


    }


    /**
     * 开始订阅
     */
    private void subscribeToTopic() {
        try {
            mqttAndroidClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG,"订阅成功");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG,"订阅失败");
                }
            });
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发布消息
     */
    private void publishMessage() {
        MqttMessage message = new MqttMessage();
        if (mqttAndroidClient.isConnected()) {
            try {
                mqttAndroidClient.publish(publishTopic, message);
            } catch (MqttException e) {
                throw new RuntimeException(e);
            }
        }
    }
}