package com.ledboot.nicechat.presenters.home.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ledboot.nicechat.injector.ContextLife;
import com.ledboot.nicechat.presenters.home.IHomePresenter;
import com.ledboot.nicechat.views.IView;
import com.ledboot.nicechat.views.home.IHomeView;
import com.ledboot.nicechat.views.home.impl.MainActivity;

import javax.inject.Inject;

/**
 * Created by Eleven on 16/5/7.
 */
public class HomePresenterImpl implements IHomePresenter {

    private IHomeView mHomeView;

    private Context mContext;
    private Activity mActivity;

    @Inject
    public HomePresenterImpl(@ContextLife("Activity")Context context, Activity activity){
        mContext = context;
        mActivity = activity;
    }

    @Override
    public void attachView(IView view) {
        mHomeView = (IHomeView) view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void startLauncher() {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
}
