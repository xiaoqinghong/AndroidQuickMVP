package me.eric.component.mvp

import android.content.Context

interface IView {
    fun showLoading()
    fun dismissLoading()
    fun attachContext(): Context
    fun showToast(msg: String)
    fun killSelf()
}