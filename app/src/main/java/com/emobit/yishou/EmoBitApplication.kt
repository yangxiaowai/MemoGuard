package com.emobit.yishou

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * EmoBit·忆守 应用程序入口
 * 基于时空智能与数字孪生的认知障碍代偿系统
 */
@HiltAndroidApp
class EmoBitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: EmoBitApplication
            private set
    }
}
