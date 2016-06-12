package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class FunTab extends HomeTab {

    public static final String TAG = MainPanel.TAG_FUN;

    public FunTab(Context context) {
        super(context);
    }

    @Override
    protected void setFacade() {
        mLabel = "Funs";
    }

    @Override
    protected String getTabTag() {
        return TAG;
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected View onCreateTitleView(ViewGroup parent, LayoutInflater inflater) {
        return null;
    }

    @Override
    protected View onCreateContentView(ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fun_tab,null);
        return view;
    }

    @Override
    protected void recycle() {

    }
}
