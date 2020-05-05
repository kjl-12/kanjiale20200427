package com.bw.kanjiale20200427;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.kanjiale20200427.base.BaseActivity;
import com.bw.kanjiale20200427.contract.UserContract;
import com.bw.kanjiale20200427.entity.UserEntity;
import com.bw.kanjiale20200427.presenter.Presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends BaseActivity<Presenter> implements UserContract.IView {

    private EditText mEtPh;
    private EditText mEtPw;
    private CheckBox mCPh;
    private Button mBRegister;
    private Button mBLogin;

    private String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";

    private int tag = 0;
    @Override
    protected void initProgress() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mEtPh = findViewById(R.id.et_ph);
        mEtPw = findViewById(R.id.et_pw);
        mCPh = findViewById(R.id.c_ph);
        mBRegister = findViewById(R.id.b_register);
        mBLogin = findViewById(R.id.b_login);

        final Pattern compile = Pattern.compile(regExp);
        final SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);

        boolean jizhuzahnghao = user.getBoolean("jizhuzahnghao", false);
        if (jizhuzahnghao){
            mEtPh.setText(user.getString("ph",""));
            mEtPw.setText(user.getString("pw",""));
            mCPh.setChecked(user.getBoolean("jizhuzahnghao",false));
        }else {
            mEtPw.setText("");
            mEtPh.setText("");
        }


        mBLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("CommitPrefEdits")
            @Override
            public void onClick(View view) {
                String ph = mEtPh.getText().toString();
                if (TextUtils.isEmpty(ph)){
                    Toast.makeText(MainActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Matcher matcher = compile.matcher(ph);
                    if (matcher.matches()){
                        String pw = mEtPw.getText().toString();
                        if (TextUtils.isEmpty(pw)){
                            Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            presenter.getLoginData(ph,pw);
                            SharedPreferences.Editor editor = user.edit();
                            editor.putBoolean("jizhuzahnghao",mCPh.isChecked());
                            editor.putString("ph",ph);
                            editor.putString("pw",pw);
                            editor.commit();

                            tag = 1;
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mBRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph = mEtPh.getText().toString();
                if (TextUtils.isEmpty(ph)) {
                    Toast.makeText(MainActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Matcher matcher = compile.matcher(ph);
                    if (matcher.matches()) {
                        String pw = mEtPw.getText().toString();
                        if (TextUtils.isEmpty(pw)) {
                            Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            //调取数据
                            presenter.getRegister(ph, pw);
                            tag = 0;
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(UserEntity userEntity) {
        if (userEntity != null){
            if (tag == 1){
                Toast.makeText(this, userEntity.getMessage(), Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }else {
                Toast.makeText(this, userEntity.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failure(Throwable throwable) {

    }
}
