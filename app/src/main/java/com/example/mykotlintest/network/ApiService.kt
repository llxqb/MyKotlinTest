package com.example.mykotlintest.network

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    /**
     * 使用@Body注解作为参数
     */
    @POST("v2/dm/query_device_upgrade_task")
    fun queryNotice(@Body body: RequestBody): Call<BaseResponseV2<FirmwareUpdateResponse>>


    /**
     * 使用@Field注解作为参数
     */
    @FormUrlEncoded
    @POST("v2/dm/query_device_upgrade_task")
    fun queryNotice2(@Field("phone") phone: String): Call<BaseResponseV2<FirmwareUpdateResponse>>

    /**
     * 使用@FieldMap注解作为参数  Map
     */
    @POST("v2/dm/query_device_upgrade_task")
    fun queryNotice3(@FieldMap param: Map<*, *>?):  Call<BaseResponseV2<FirmwareUpdateResponse>>
}