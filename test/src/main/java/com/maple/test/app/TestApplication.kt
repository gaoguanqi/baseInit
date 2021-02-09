package com.maple.test.app

import android.app.Application
import androidx.lifecycle.ViewModelStore
import com.maple.common.app.CommonApp

class TestApplication : CommonApp() {

    companion object {
        @JvmStatic
        lateinit var instance: TestApplication
            private set
    }

    override fun initApp() {
        super.initApp()
        instance = this
    }

    override fun getAppPackage(): String = this.packageName


    override fun getViewModelStore(): ViewModelStore = ViewModelStore()




    override fun initSDK(app: Application) {

    }
}