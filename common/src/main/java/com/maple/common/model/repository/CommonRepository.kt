package com.maple.common.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.maple.baselib.base.BaseRepository
import com.maple.common.model.RetrofitClient
import com.maple.common.model.handler.HttpParamsUtils
import java.util.*

class CommonRepository : BaseRepository() {


    private val retrofitClient = RetrofitClient.service

    override fun getPublicParams(addToken: Boolean): WeakHashMap<String, Any> {
        return HttpParamsUtils.addPublicRequestParams(addToken)
    }

    suspend fun loginPhone(params: WeakHashMap<String, Any>) = withContext(Dispatchers.IO) {
        retrofitClient.loginPhone(params2Body(params))
    }

    suspend fun getNoticeList(params: WeakHashMap<String, Any>) = withContext(Dispatchers.IO) {
        retrofitClient.getNoticeList(params2Body(params))
    }

}