package com.example.mykotlintest

/**
 * Created by li.liu  on 2024/1/5
 */
object Constant {
    /**
     * 是否显示日志
     */
    @JvmField
    var debugLog = true
    const val STS_CHANGE_CHANNEL_STREAMINFO = 99
    const val START_LISTEN_RET = 96 //音频开启成功
    const val START_CHANNEL_RET = 97 //对讲回调

    // 自研设备截图保存图片像素最大值 bitmapPixels
    const val IMG_BITMAP_PIXELS = 12000000L

    //录像时长，默认3分钟
    var recordingTime = (500 * 60 * 1000).toLong()
}