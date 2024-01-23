package com.example.mykotlintest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mykotlintest.mqtt.MqttActivity
import com.example.mykotlintest.network.ApiRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun testNetwork() {
        ApiRequest.requestNotice()
    }

    fun testMqtt(view: View) {
        val intent = Intent(this, MqttActivity::class.java)
        startActivity(intent)
    }
}