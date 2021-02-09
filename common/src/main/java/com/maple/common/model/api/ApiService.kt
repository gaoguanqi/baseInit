package com.maple.common.model.api

import com.maple.common.base.BaseApi
import com.maple.common.model.entity.NoticeListEntity
import com.maple.common.model.entity.UserEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService: BaseApi {

    @Headers("urlname:hyntech")
    @POST(ApiURL.URL_USER_LOGIN)
    suspend fun loginPhone(@Body requestBody: RequestBody): UserEntity

    @Headers("urlname:hyntech")
    @POST(ApiURL.URL_ALARM_LIST)
    suspend fun getNoticeList(@Body requestBody: RequestBody): NoticeListEntity

}