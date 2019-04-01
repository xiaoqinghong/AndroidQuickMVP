package ${ativityPackageName}

import android.os.Bundle

import ${packageName}.R
import me.eric.component.mvp.BaseActivity
import ${contractPackageName}.${pageName}Contract
import ${presenterPackageName}.${pageName}PresenterImpl

class ${pageName}Activity : BaseActivity<${pageName}PresenterImpl>() , ${pageName}Contract.View {

    override fun getLayout(): Int {
       return R.layout.${activityLayoutName}
    }

    override fun init(savedInstanceState: Bundle?) {
        mPresenter = ${pageName}PresenterImpl(this)
    }

    override fun killSelf() {
		finish()
    }
}
