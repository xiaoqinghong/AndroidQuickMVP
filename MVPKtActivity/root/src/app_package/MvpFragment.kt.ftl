package ${fragmentPackageName}

import android.os.Bundle
import android.view.View
import me.eric.component.mvp.BaseFragment
import ${contractPackageName}.${pageName}Contract
import ${presenterPackageName}.${pageName}PresenterImpl

class ${pageName}Fragment : BaseFragment<${pageName}PresenterImpl>() , ${pageName}Contract.View {

    companion object {
        fun newInstance(): ${pageName}Fragment {
            val bundle = Bundle()
            val fragment = ${pageName}Fragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getContentLayout(): Int {
        // 绑定layout
        return 0
    }

    override fun initView(contentView: View?) {
        mPresenter = ${pageName}PresenterImpl(this)
        // 初始化view相关
    }

    override fun loadData(): Boolean {
        // 返回值表示是否再次可见时进行的数据加载
        return true
    }
}
