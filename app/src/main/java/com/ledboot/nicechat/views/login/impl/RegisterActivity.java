package com.ledboot.nicechat.views.login.impl;

import com.ledboot.nicechat.R;
import com.ledboot.nicechat.views.BaseActivity;
import com.ledboot.nicechat.views.login.IRegisterView;

import butterknife.ButterKnife;

/**
 * Created by wengaowei728 on 16/6/13.
 */
public class RegisterActivity extends BaseActivity implements IRegisterView {



    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_regitster;
    }
}
