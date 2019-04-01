package com.eric.basic.base.app

import android.util.SparseArray

class ModuleRegister private constructor(){
    private val modules = SparseArray<ICoreContext>()
    companion object {
        val instance: ModuleRegister by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ModuleRegister()
        }
    }

    fun register(iCoreContext: ICoreContext) {
        this.modules.append(modules.size(), iCoreContext)
    }

    fun getModules(): SparseArray<ICoreContext> {
        return this.modules
    }
}