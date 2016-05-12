package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledboot.nicechat.core.Debuger;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class SessionTab extends HomeTab {


    public static final String TAG = MainPanel.TAG_SESSION;

    public SessionTab(Context context) {
        super(context);
    }

    @Override
    protected void setFacade() {
        mLabel = "聊天";
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
        View view = inflater.inflate(R.layout.session_tab,null);
        Debuger.logD(TAG,"Session onCreateContentView()");
        return view;
    }

    @Override
    protected void recycle() {

    }
}
