package me.eric.component.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eric.basic.base.utils.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.jetbrains.annotations.NotNull;

import me.eric.component.view.LoadingDialog;

/**
 * Author: Eric
 * Date: Created in 2019/2/15 2:20 AM
 * Description： 支持mvp的baseFragment
 */
public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IView {
    protected P mPresenter;
    // 布局成功已经加载的标志位
    private boolean isViewLoaded = false;
    // 数据已经加载的标志位
    private boolean isDataLoaded = false;
    // 页面初始化标志位
    private boolean isViewInit = false;
    // contentView
    private View contentView;
    // loading
    private LoadingDialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfiguration(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.contentView = inflater.inflate(getContentLayout(), container, false);
        this.isViewLoaded = true;
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint() && isViewLoaded){
            if (!isViewInit) {
                initView(getContentView());
                this.isViewInit = true;
            }
            if (!isDataLoaded){
                this.isDataLoaded = loadData();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isViewLoaded ){
            if (!isViewInit) {
                initView(getContentView());
                this.isViewInit = true;
            }
            if (!isDataLoaded){
                this.isDataLoaded = loadData();
            }
        }
    }

    @Override
    public void onDestroyView() {
        // 若View被销毁了，则需要重新创建View，此时应该把标志位复原
        this.isViewLoaded = false;
        this.isDataLoaded = false;
        this.isViewInit = false;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDestroy();
    }

    @NotNull
    @Override
    public Context attachContext() {
        return getContext();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this.attachContext());
        }
        if (!mLoadingDialog.isShowing()) mLoadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(@NotNull String msg) {
        if (TextUtils.isEmpty(msg)) return;
        ToastUtils.showToast(getContext(), msg);
    }

    @Override
    public void killSelf() {

    }

    /**
     * 用于子类得到contentView
     * @return contentView
     */
    public View getContentView(){
        return this.contentView;
    }

    /**
     * 该方法在Fragment的OnCreate方法被执行时调用
     * 主要用于初始化其他模块传递到fragment中的数据
     * 该方法可以选择性地去实现
     * @param savedInstanceState 数据包
     */
    protected void initConfiguration(Bundle savedInstanceState){}

    /**
     * 在加载布局前调用，用于获取布局文件资源
     * @return 返回一个布局文件的layout资源
     */
    protected abstract int getContentLayout();

    /**
     * 实现View初始化的懒加载
     * 该方法会在OnStart方法执行时调用，或者是在对用户可见时调用，但无论是哪个地方调用，都只会执行一次
     * 在该方法中可以实现findViewById、view的事件初始化等
     * 在OnStart时调用：若是在OnStart时被调用，则要满足两个条件，1：当前的fragment对用户是可见的；2、布局文件已经已经加载完毕
     * 在对用户可见时调用：若是OnStart是没有调用，而是在对用户可见时被调用，则说明在OnStart方法执行时，当前的fragment对用户不可见
     * @param contentView contentView
     */
    protected void initView(View contentView){}

    /**
     * 实现数据的懒加载（该方法执行在initView之后）
     * 该方法会在OnStart方法执行时或者是在对用户可见时调用，但无论是哪个地方调用，都只会执行一次
     * 在OnStart是调用：若是在OnStart时被调用，则要满足两个条件，1：当前的fragment对用户是可见的；2、布局文件已经加载完毕
     * 在对用户可见时调用：若是OnStart是没有调用，而是在对用户可见时被调用，则说明在OnStart方法执行时，当前的fragment对用户不可见
     * @return 返回Boolean类型的值
     * 若为true：表示下次对用户可见时不需要进行数据加载；若为false：表示下次对用户可见时需要进行数据加载
     */
    protected abstract boolean loadData();
}
