package com.bw.kanjiale20200427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.bw.kanjiale20200427.adapter.BookAdapter;
import com.bw.kanjiale20200427.api.ApiService;
import com.bw.kanjiale20200427.base.BaseActivity;
import com.bw.kanjiale20200427.base.BasePresenter;
import com.bw.kanjiale20200427.entity.BookEntity;

public class Main2Activity extends BaseActivity {

    private RecyclerView mRv;
    private Retrofit mMRetrofit;

    @Override
    protected void initProgress() {

    }

    @Override
    protected void initData() {
        mMRetrofit = new Retrofit.Builder()
                //对OKHttp进行二次封装                                  //d)使用拦截器打印网络请求数据日志信息
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .baseUrl("http://blog.zhaoliang5156.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mMRetrofit.create(ApiService.class)
                .getBookData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookEntity>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(BookEntity bookEntity) throws Exception {
                        if (bookEntity != null) {
                            Toast.makeText(Main2Activity.this, "123123132", Toast.LENGTH_SHORT).show();
                            BookAdapter bookAdapter = new BookAdapter(Main2Activity.this, bookEntity.getData().getContent());
                            mRv.setAdapter(bookAdapter);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    @Override
    protected void initView() {
        mRv = findViewById(R.id.rv);

        mRv.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }
}
