package com.bw.kanjiale20200427.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity implements IBaseView{
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        getSupportActionBar().hide();
        presenter = initPresenter();
        if (presenter != null){
            presenter.attach(this);
        }
        initView();
        initData();
        initProgress();
    }

    protected abstract void initProgress();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detach();
        }
    }
}
