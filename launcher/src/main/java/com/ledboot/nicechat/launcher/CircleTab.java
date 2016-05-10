package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class CircleTab extends HomeTab {

    public static final String TAG = MainPanel.TAG_CIRCLE;

    public CircleTab(Context context) {
        super(context);
    }

    @Override
    protected void setFacade() {
        mLabel = "圈子";
    }

    @Override
    protected String getTabTag() {
        return TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    }

    @Override
    protected View onCreateTitleView(ViewGroup parent, LayoutInflater inflater) {
        return null;
    }

    @Override
    protected View onCreateContentView(ViewGroup parent, LayoutInflater inflater) {
        return null;
    }

    @Override
    protected void recycle() {

    }
}
