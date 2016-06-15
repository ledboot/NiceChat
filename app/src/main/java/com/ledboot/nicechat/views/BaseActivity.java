package com.ledboot.nicechat.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ledboot.nicechat.NiceChatApplication;
import com.ledboot.nicechat.injector.component.ActivityComponent;
import com.ledboot.nicechat.injector.component.DaggerActivityComponent;
import com.ledboot.nicechat.injector.module.ActivityModule;
import com.ledboot.nicechat.presenters.IPresenter;

import java.lang.reflect.Field;

/**
 * Created by Eleven on 16/4/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent mActivityComponent;
    protected IPresenter mPresenter;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((NiceChatApplication) getApplication()).getApplicationComponent())
                .build();
        super.onCreate(savedInstanceState);
        mContext = this;
        int layout = setContentView();
        if(layout == 0){
            View view = setCustomContentView();
            setContentView(view);
        }else{
            setContentView(layout);
        }
        initInjector();
        initUiAndListener();
    }

    /**
     * 注入Injector
     */
    public abstract void initInjector();


    public abstract void initUiAndListener();

    public abstract int setContentView();

    public abstract View setCustomContentView();

    /**
     * 得到stausBar高度
     *
     * @return
     */
    public int getStatusBarSize() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 38;//默认为38，貌似大部分是这样的
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}
