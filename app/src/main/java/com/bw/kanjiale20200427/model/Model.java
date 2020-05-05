package com.bw.kanjiale20200427.model;


import com.bw.kanjiale20200427.contract.UserContract;
import com.bw.kanjiale20200427.entity.UserEntity;
import com.bw.kanjiale20200427.util.RetrofitUtil;
import com.bw.kanjiale20200427.api.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public class Model implements UserContract.IModel {

    @Override
    public void getLoginData(String ph, String pw, final UserCallBack userCallBack) {
        RetrofitUtil.getInstance().createService(ApiService.class)
                .getLoginData(ph,pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserEntity>() {
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        if (userCallBack != null) {
                            userCallBack.success(userEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (userCallBack != null){
                            userCallBack.failure(throwable);
                        }
                    }
                });
    }

    @Override
    public void getRegister(String ph, String pw, final UserCallBack userCallBack) {
        RetrofitUtil.getInstance().createService(ApiService.class)
                .getRegisterData(ph,pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserEntity>() {
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        if (userCallBack != null) {
                            userCallBack.success(userEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (userCallBack != null){
                            userCallBack.failure(throwable);
                        }
                    }
                });
    }
}
