package com.ledboot.nicechat.views.login.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.SignUpCallback;
import com.ledboot.nicechat.R;
import com.ledboot.nicechat.core.Debuger;
import com.ledboot.nicechat.views.BaseActivity;
import com.ledboot.nicechat.views.login.IRegisterView;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wengaowei728 on 16/6/13.
 */
public class RegisterActivity extends BaseActivity implements IRegisterView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindColor(R.color.transparent)
    int transparent;

    @BindView(R.id.email_inputlayout)
    TextInputLayout emailInputLayout;

    @BindView(R.id.pwd_inputlayout)
    TextInputLayout pwdInputLayout;

    @BindView(R.id.user_email)
    EditText userEmail;

    @BindView(R.id.user_pwd)
    EditText userPwd;

    @BindView(R.id.submit)
    Button btSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mToolbar.setBackgroundColor(transparent);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        setSupportActionBar(mToolbar);
    }


    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if(menuId == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_regitster;
    }

    @Override
    public View setCustomContentView() {
        return null;
    }

    @OnClick(R.id.submit)
    public void submit(View v){
        AVUser user = new AVUser();
        user.setUsername(userEmail.getText().toString());
        user.setEmail(userEmail.getText().toString());
        user.setPassword(userPwd.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Debuger.logD("注册成功！");
                    Toast.makeText(mContext,"注册成功！",Toast.LENGTH_SHORT).show();
                }else{
                    String msg = AVUtils.getJSONValue(e.getMessage(),"error");
                    Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
//                    Debuger.logD("注册失败,e="+e.getMessage());
                }
            }
        });
    }
}
