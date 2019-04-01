package ${presenterPackageName}

import ${modelPackageName}.${pageName}Model
import me.eric.component.mvp.SimplePresenter
import ${contractPackageName}.${pageName}Contract

class ${pageName}PresenterImpl(view: ${pageName}Contract.View): SimplePresenter<${pageName}Contract.View>(view),${pageName}Contract.Presenter {
    // 用于调用mModel中的函数，进行一系列的数据操作
    private val mModel = ${pageName}Model()
    
}