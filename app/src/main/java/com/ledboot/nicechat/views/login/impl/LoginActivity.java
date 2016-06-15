package com.ledboot.nicechat.views.login.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ledboot.nicechat.R;
import com.ledboot.nicechat.presenters.login.impl.LoginPresenterImpl;
import com.ledboot.nicechat.views.BaseActivity;
import com.ledboot.nicechat.views.login.ILoginView;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView{

    @Inject
    LoginPresenterImpl mLoginPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindColor(R.color.transparent)
    int transparent;

    @BindView(R.id.user_email)
    EditText etEmail;

    @BindView(R.id.user_pwd)
    EditText etPwd;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        mPresenter = mLoginPresenter;
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        mLoginPresenter.attachView(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    public View setCustomContentView() {
        return null;
    }

    @OnClick(R.id.login)
    public void login(){
        mLoginPresenter.login(etEmail.getText().toString(),etPwd.getText().toString());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
