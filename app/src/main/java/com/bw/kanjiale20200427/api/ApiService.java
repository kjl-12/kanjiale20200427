package com.bw.kanjiale20200427.api;

import com.bw.kanjiale20200427.entity.BookEntity;
import com.bw.kanjiale20200427.entity.UserEntity;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public interface ApiService {
    @POST(Api.LOGIN_URL)
    @FormUrlEncoded
    Observable<UserEntity> getLoginData(@Field("phone")String phone, @Field("pwd")String pwd);

    @POST(Api.REGISTER_URL)
    @FormUrlEncoded
    Observable<UserEntity> getRegisterData(@Field("phone")String phone, @Field("pwd")String pwd);

    @GET(Api.BOOK_URL)
    Observable<BookEntity> getBookData();
}
