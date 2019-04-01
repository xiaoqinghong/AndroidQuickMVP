package com.eric.basic.base.app

import android.app.Application
import android.content.Context

/**
 * 整个应用最核心的application
 */
abstract class CoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        registerModules(ModuleRegister.instance)
        init(applicationContext)
        val modules = ModuleRegister.instance.getModules()
        for (index in 0 until modules.size()) {
            modules.valueAt(index).init(applicationContext)
        }
    }

    /**
     * 注册module
     */
    abstract fun registerModules(moduleRegister: ModuleRegister)

    /**
     * 初始化主Application。在此之后才根据各个模块注册顺序依次初始化
     */
    abstract fun init(context: Context)
}