package com.ledboot.nicechat.presenters.home.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ledboot.nicechat.injector.ContextLife;
import com.ledboot.nicechat.presenters.home.IWelcomePresenter;
import com.ledboot.nicechat.views.IView;
import com.ledboot.nicechat.views.home.IWelcomeView;
import com.ledboot.nicechat.views.login.impl.LoginActivity;

import javax.inject.Inject;

/**
 * Created by wengaowei728 on 16/6/13.
 */
public class WelcomePresenterImpl implements IWelcomePresenter {

    private IWelcomeView welcomeView;

    private Activity mActivity;
    private Context mContext;

    @Inject
    public WelcomePresenterImpl(Activity activity, @ContextLife("Activity")Context context){
        this.mActivity = activity;
        this.mContext= context;
    }

    @Override
    public void goLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void goRegist() {

    }

    @Override
    public void attachView(IView view) {
        welcomeView = (IWelcomeView)view;
    }

    @Override
    public void detachView() {

    }
}
