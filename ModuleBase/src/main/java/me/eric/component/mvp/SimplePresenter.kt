package me.eric.component.mvp

open class SimplePresenter<V: IView>(view : V) : IPresenter {
    var mView = view
    var mContext = view.attachContext()

    override fun onViewDestroy() {
    }
}