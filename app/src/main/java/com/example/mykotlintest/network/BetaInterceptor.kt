package com.example.mykotlintest.network

import android.text.TextUtils
import com.example.mykotlintest.util.LogUtils
import okhttp3.*
import okio.Buffer
import java.nio.charset.Charset


/**
 * 接口请求拦截器，这里主要是用来
 *
 * @author Beta-Tan
 */
class BetaInterceptor @JvmOverloads constructor(private val showResponse: Boolean = true) :
    Interceptor {

    //缓存相关
    private var cacheKey = ""
    private val TAG = "BetaInterceptor"

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response? {
        val request = chain.request()
        cacheKey = request.url().toString()
        logForRequest(request)
        val newRequest = addParams(request)
        if (newRequest.method() == "GET") {
            val response = chain.proceed(newRequest)
            val contentType = response.body()?.contentType()
            val body = response.body()?.string()
//                LogUtils.i("hhh---,cacheBean = $cacheBean")
            val newResponse = response.newBuilder()
                .body(ResponseBody.create(contentType, body))
                .build()
//            LogUtils.e(String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %s", newRequest.method(), newRequest.url(), newRequest.headers()))
            return logForResponse(newResponse)
        } else {//POST
            val UTF8 = Charset.forName("UTF-8")
            val requestBody = newRequest.body()
            var reqBody: String? = null
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)

                var charset: Charset? = UTF8
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                reqBody = buffer.readString(charset!!)
            }
            LogUtils.e(TAG,String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %s\nbody：%s", newRequest.method(), newRequest.url(), newRequest.headers(), reqBody))
            return logForResponse(chain.proceed(newRequest))
        }
    }

    private fun addParams(oldRequest: Request): Request {


        var url = oldRequest.url().newBuilder()
//                .addQueryParameter("header", value)
                .build()
        val newRequest = oldRequest.newBuilder()
//                .header("Authorization", "Bearer "+Constant.ApiConfig.TOKEN)
//                .url(url)
                .build()
//        val hash = getHash(newRequest)
//        url = newRequest.url().newBuilder().addQueryParameter("hash", hash).build()
        return newRequest
    }

    private fun logForResponse(response: okhttp3.Response): Response {
        try {
            //===>response log
            val builder = response.newBuilder()
            val clone = builder.build()
            if (!TextUtils.isEmpty(clone.message())) {
                LogUtils.e(TAG,"message : " + clone.message())
            }

            if (showResponse) {
                var body = clone.body()
                if (body != null) {
                    val mediaType = body.contentType()
                    if (mediaType != null) {
                        if (isText(mediaType)) {
                            val resp = body.string()
                            body = ResponseBody.create(mediaType, resp)
                            LogUtils.e(TAG,"responseBody's content : $resp")
                            return response.newBuilder().body(body).build()
                        } else {
//                            LogUtils.e("responseBody's content : " + " maybe [file part] , too large too print , ignored!")
                        }
                    }
                }
            }
        } catch (ignored: Exception) {

        }

        return response
    }

    private fun logForRequest(request: Request) {
        try {
            val url = request.url().toString()
            val headers = request.headers()

//            LogUtils.e("========request'log=======")
//            LogUtils.e("url : $url")
            if (headers != null && headers.size() > 0) {
            }
            val requestBody = request.body()
            if (requestBody != null) {
                val mediaType = requestBody.contentType()
                if (mediaType != null) {
                    if (isText(mediaType)) {
                    } else {
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun isText(mediaType: MediaType): Boolean {
        if (mediaType.type() != null && mediaType.type() == "text") {
            return true
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype() == "json" ||
                    mediaType.subtype() == "xml" ||
                    mediaType.subtype() == "html" ||
                    mediaType.subtype() == "webviewhtml") {
                return true
            }
        }
        return false
    }

    private fun bodyToString(request: Request): String {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()!!.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: Exception) {
            return "something error when show requestBody."
        }
    }


    companion object {
        val TAG = "OkHttpUtils"
    }
}
