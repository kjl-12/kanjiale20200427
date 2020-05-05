package com.bw.kanjiale20200427.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bw.kanjiale20200427.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private final Retrofit retrofit;

    private RetrofitUtil(){
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance(){
        if (retrofitUtil == null){
            synchronized (RetrofitUtil.class){
                if (retrofitUtil == null){
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }

    public <T> T createService(Class<T> tClass){
        return retrofit.create(tClass);
    }
    //网络判断
    public boolean hashNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
            return true;
        }else {
            return false;
        }
    }
}
