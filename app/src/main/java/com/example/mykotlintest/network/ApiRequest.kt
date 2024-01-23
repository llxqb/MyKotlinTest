package com.example.mykotlintest.network

import com.example.mykotlintest.util.LogUtils
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ApiRequest {
    fun requestNotice() {
        val apiService = RetrofitClient.retrofit.create(ApiService::class.java)

        val firmwareUpdateRequest: FirmwareUpdateRequest = FirmwareUpdateRequest()
        val headerBean: FirmwareUpdateRequest.HeaderBean = FirmwareUpdateRequest.HeaderBean()
        headerBean.seqno = "202401161354098530013187582630"
        headerBean.user_id = "9ebd9623ece23d2fa4920d68dc144712"
        headerBean.package_name = "com.jooan.cowelf"
        headerBean.language = "zh_CN"
        headerBean.client_version = "5.3.16.10"
        headerBean.token = "ede730fe75f8421b8327"
        headerBean.phone_model = "PHM110"
        val list = listOf("4DBU5JKAL77ZPTEP111A", "4MFVN28CZYAY3RPC111A", "63U2AHNTSU3JEL5U111A")
        headerBean.device_list = list

        firmwareUpdateRequest.header = headerBean
        val value = Gson().toJson(firmwareUpdateRequest)
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), value)

        // 方法1：
        val call = apiService.queryNotice(requestBody)


        call.enqueue(object : Callback<BaseResponseV2<FirmwareUpdateResponse>> {
            override fun onResponse(
                call: Call<BaseResponseV2<FirmwareUpdateResponse>>,
                response: Response<BaseResponseV2<FirmwareUpdateResponse>>
            ) {
                if (response.isSuccessful) {
                    val user = response.body()
                    LogUtils.i("ddd", "errorCode = " + user?.errorCode)
                    LogUtils.i("ddd", "error_msg = " + user?.error_msg)
                    LogUtils.i("ddd", "bodyInfo = " + Gson().toJson(user?.bodyInfo))
                }

            }

            override fun onFailure(
                call: Call<BaseResponseV2<FirmwareUpdateResponse>>,
                t: Throwable
            ) {
                // 处理失败情况
                LogUtils.e("ddd", "error = " + t.message)
            }
        })
    }
}