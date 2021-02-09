package com.maple.common.model

import com.maple.baselib.app.config.Config
import com.maple.baselib.http.BaseRetrofitClient
import com.maple.common.model.api.ApiService
import com.maple.common.model.handler.BaseURLInterceptor
import com.maple.common.model.handler.HttpParamsUtils
import com.maple.common.model.handler.PublicInterceptor
import okhttp3.OkHttpClient

object RetrofitClient: BaseRetrofitClient() {

    val service by lazy { getService(ApiService::class.java, Config.BASE_URL) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        //添加多base url
        builder.addInterceptor(BaseURLInterceptor())
        //添加公共请求头参数
        val headers = HttpParamsUtils.addPublicRequestParams(true)
        builder.addInterceptor(PublicInterceptor(headers))
    }
}