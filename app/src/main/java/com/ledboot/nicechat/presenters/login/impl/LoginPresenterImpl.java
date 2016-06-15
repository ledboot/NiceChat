package com.ledboot.nicechat.presenters.login.impl;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.AVUtils;
import com.avos.avoscloud.LogInCallback;
import com.ledboot.nicechat.core.Debuger;
import com.ledboot.nicechat.injector.ContextLife;
import com.ledboot.nicechat.presenters.home.impl.HomePresenterImpl;
import com.ledboot.nicechat.presenters.login.ILoginPresenter;
import com.ledboot.nicechat.utils.Utils;
import com.ledboot.nicechat.views.IView;
import com.ledboot.nicechat.views.login.ILoginView;

import javax.inject.Inject;


/**
 * Created by wengaowei728 on 16/6/14.
 */
public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginView mLoginView;

    private Activity mActivity;
    private Context mContext;

    @Inject
    HomePresenterImpl homePresenter;

    @Inject
    public LoginPresenterImpl(Activity activity, @ContextLife("Activity") Context context) {
        this.mActivity = activity;
        this.mContext = context;
    }

    @Override
    public void login(String email, String pwd) {
        if(AVUtils.isBlankString(email) || AVUtils.isBlankString(pwd)){
            Utils.Toast("请输入用户名或密码");
            return;
        }
        AVUser.logInInBackground(email, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    Utils.Toast("登录成功！");
                    Toast.makeText(mContext, "登录成功！", Toast.LENGTH_SHORT).show();
                    startLauncher();
                } else {
                    Debuger.logD(e.getMessage());
                    Utils.Toast("登录失败！");
                }
            }
        });
    }


    @Override
    public void attachView(IView view) {
        mLoginView = (ILoginView) view;
    }

    @Override
    public void detachView() {

    }

    private void startLauncher(){
        homePresenter.startLauncher();
    }
}
