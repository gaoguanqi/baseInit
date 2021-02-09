package com.maple.baselib.utils

import android.annotation.SuppressLint
import com.blankj.utilcode.util.NetworkUtils as utils

/**
 * 网络工具类
 */
class NetworkUtils {
    companion object{
        @SuppressLint("MissingPermission")
        fun isConnected(): Boolean {
            return utils.isConnected()
        }
    }
}