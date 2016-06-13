package com.ledboot.nicechat.injector.component;

import android.app.Activity;
import android.content.Context;

import com.ledboot.nicechat.injector.ContextLife;
import com.ledboot.nicechat.injector.PerActivity;
import com.ledboot.nicechat.injector.module.ActivityModule;
import com.ledboot.nicechat.views.home.impl.HomeActivity;
import com.ledboot.nicechat.views.home.impl.WelcomeActivity;
import com.ledboot.nicechat.views.login.impl.LoginActivity;
import com.ledboot.nicechat.views.login.impl.RegisterActivity;

import dagger.Component;

/**
 * Created by Eleven on 16/4/22.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplication();

    Activity getActivity();

    void inject(LoginActivity loginActivity);

    void inject(HomeActivity homeActivity);

    void inject(WelcomeActivity welcomeActivity);
    void inject(RegisterActivity registerActivity);



}
