package com.bw.kanjiale20200427.contract;

import com.bw.kanjiale20200427.base.IBaseModel;
import com.bw.kanjiale20200427.base.IBaseView;
import com.bw.kanjiale20200427.entity.UserEntity;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public interface UserContract {
    interface IModel extends IBaseModel{
        void getLoginData(String ph,String pw,UserCallBack userCallBack);
        void getRegister(String ph,String pw,UserCallBack userCallBack);

       interface UserCallBack{
            void success(UserEntity userEntity);
            void failure(Throwable throwable);
       }
    }
    interface IView extends IBaseView{
        void success(UserEntity userEntity);
        void failure(Throwable throwable);
    }

    interface IPresenter{
        void getLoginData(String ph, String pw);
        void getRegister(String ph, String pw);
    }
}
