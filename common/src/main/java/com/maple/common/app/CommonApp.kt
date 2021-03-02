package com.maple.common.app

import android.app.Application
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.xuexiang.xupdate.XUpdate
import com.xuexiang.xupdate.entity.UpdateError
import com.xuexiang.xupdate.listener.OnUpdateFailureListener
import com.maple.baselib.app.BaseApp
import com.maple.baselib.app.config.Config
import com.maple.baselib.utils.LogUtils
import com.maple.baselib.utils.UIUtils
import com.maple.common.R
import com.maple.common.app.global.Global
import com.maple.common.widget.update.OKHttpUpdateHttpService

abstract class CommonApp:BaseApp() {

    companion object {
        @JvmStatic
        lateinit var instance: CommonApp
            private set
    }

    override fun initApp() {
        super.initApp()
        instance = this
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.common_white, R.color.common_color_refresh)
            ClassicsHeader(context).setDrawableArrowSize(14f).setDrawableProgressSize(14f).setTextSizeTitle(14f).setTextSizeTime(10f)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> ClassicsFooter(context).setDrawableSize(14f).setTextSizeTitle(14f).setAccentColor(
            UIUtils.getColor(R.color.common_color_refresh)) }
    }

    override fun initGlobalSDK(app: Application) {
        super.initGlobalSDK(app)

        initUpdate()
    }


    private fun initUpdate(){
        XUpdate.get()
            .debug(Config.CONFIG_DEBUG)
            .isWifiOnly(false)    //默认设置只在wifi下检查版本更新
            .isGet(true)              //默认设置使用get请求检查版本
            .isAutoMode(false)
            .setOnUpdateFailureListener { error ->
                error?.let {
                    if(it.code != UpdateError.ERROR.CHECK_NO_NEW_VERSION){
                        LogUtils.logGGQ(it.message)
                    }
                }
            }.supportSilentInstall(true)
            .setIUpdateHttpService(OKHttpUpdateHttpService())
            .init(this)
    }

}