package com.maple.baselib.utils

import android.util.Log
import com.maple.baselib.app.config.Config

class LogUtils {
    companion object {
        @JvmStatic
        fun logGGQ(s: String?) {
            if (Config.CONFIG_DEBUG) {
                Log.i("GGQ", "${s}")
            }
        }
    }
}