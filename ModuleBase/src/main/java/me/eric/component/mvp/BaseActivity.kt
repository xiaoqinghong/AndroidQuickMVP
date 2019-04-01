package me.eric.component.mvp

import android.content.Context
import android.os.Bundle
import com.eric.basic.base.utils.ActivityManager
import com.eric.basic.base.utils.StatusBarUtils
import com.eric.basic.base.utils.ToastUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import me.eric.component.view.LoadingDialog

/**
 * Author: Eric
 * Date: Created in 2019/2/15 2:20 AM
 * Description： 支持mvp的baseActivity
 */
abstract class BaseActivity<P: IPresenter>: RxAppCompatActivity(), IView {
    lateinit var mPresenter: P
    private var mLoadingDialog: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.instance.value.put(this)
        setStatusBarMode(isDark = false, isFullScreen = false)
        setContentView(getLayout())
        init(savedInstanceState)
    }

    protected fun setStatusBarColor(color: Int) {
        StatusBarUtils.setColor(this, color, 0)
    }

    protected fun setStatusBarMode(isDark: Boolean, isFullScreen: Boolean) {
        if (isDark) {
            StatusBarUtils.setDarkMode(this, isFullScreen)
        } else {
            StatusBarUtils.setLightMode(this, isFullScreen)
        }
    }

    abstract fun getLayout(): Int

    abstract fun init(savedInstanceState: Bundle?)

    override fun showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this)
        }
        mLoadingDialog?.apply { if (!isShowing) show() }
    }

    override fun dismissLoading() {
        mLoadingDialog?.apply { if (isShowing) dismiss() }
    }

    override fun attachContext(): Context {
        return this
    }

    override fun showToast(msg: String) {
        ToastUtils.showToast(this, msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.instance.value.remove(this)
        mPresenter.onViewDestroy()
    }
}