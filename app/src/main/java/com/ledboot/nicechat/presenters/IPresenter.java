package com.ledboot.nicechat.presenters;

import com.ledboot.nicechat.views.IView;

/**
 * Created by Eleven on 16/4/28.
 */
public interface IPresenter {

    void attachView(IView view);

    void detachView();
}
