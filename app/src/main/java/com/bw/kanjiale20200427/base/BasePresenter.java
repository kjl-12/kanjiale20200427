package com.bw.kanjiale20200427.base;

import java.lang.ref.WeakReference;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public abstract class BasePresenter <M extends IBaseModel,V extends IBaseView>{
    public M model;
    private WeakReference<V> weakReference;
    public BasePresenter(){
        model = initModel();
    }
    public void attach(V v){
        weakReference = new WeakReference<>(v);
    }

    public void detach(){
        if (weakReference != null){
            weakReference.clear();
            weakReference = null;
        }
    }

    protected abstract M initModel();

    public V getV(){
        return weakReference.get();
    }

}
