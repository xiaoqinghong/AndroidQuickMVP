package com.eric.basic.base.rx

import android.util.Log
import com.eric.basic.base.BuildConfig
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: Eric
 * Date: Created in 2019/1/26 12:27 AM
 * Description： 不用再使用时处理每个函数
 */
abstract class BaseObserver<T> : Observer<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        onNextWith(t)
    }

    override fun onError(e: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e("onError", e.toString())
        }
    }

    open fun onNextWith(t: T) {

    }
}