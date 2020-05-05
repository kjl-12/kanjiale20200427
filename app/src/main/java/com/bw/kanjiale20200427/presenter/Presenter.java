package com.bw.kanjiale20200427.presenter;

import com.bw.kanjiale20200427.base.BasePresenter;
import com.bw.kanjiale20200427.contract.UserContract;
import com.bw.kanjiale20200427.entity.UserEntity;
import com.bw.kanjiale20200427.model.Model;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public class Presenter extends BasePresenter<Model, UserContract.IView> implements UserContract.IPresenter{

    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void getLoginData(String ph, String pw) {
        model.getLoginData(ph, pw, new UserContract.IModel.UserCallBack() {
            @Override
            public void success(UserEntity userEntity) {
                getV().success(userEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getV().failure(throwable);
            }
        });
    }

    @Override
    public void getRegister(String ph, String pw) {
        model.getRegister(ph, pw, new UserContract.IModel.UserCallBack() {
            @Override
            public void success(UserEntity userEntity) {
                getV().success(userEntity);
            }

            @Override
            public void failure(Throwable throwable) {
                getV().failure(throwable);
            }
        });
    }
}
