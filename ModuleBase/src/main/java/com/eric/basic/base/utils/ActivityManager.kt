package com.eric.basic.base.utils

import android.app.Activity
import android.util.SparseArray

class ActivityManager {
    private val activities = SparseArray<Activity>()
    private var mCurrentIndex = -1
    private var mStepCount = 0
    companion object {
        val instance = lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityManager()
        }
    }

    fun put(activity: Activity) {
        if (!isExist(activity)) {
            activities.put(mStepCount, activity)
            mStepCount++
        }
    }

    fun remove(activity: Activity) {
        if (isExist(activity)) {
            val a = activities.valueAt(mCurrentIndex)
            a?.finish()
            activities.removeAt(mCurrentIndex)
        }
    }

    fun removeAll() {
        for (i in 0 until activities.size()) {
            val a = activities.valueAt(i)
            a?.finish()
        }
        activities.clear()
        mStepCount = 0
    }

    private fun isExist(activity: Activity): Boolean {
        var res = false
        for (i in 0 until activities.size()) {
            val a = activities.valueAt(i)
            if (a == activity) {
                mCurrentIndex = i
                res= true
            }
        }
        return res
    }
}