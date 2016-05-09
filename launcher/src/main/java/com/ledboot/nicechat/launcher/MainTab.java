package com.ledboot.nicechat.launcher;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class MainTab extends FrameLayout {

    private Context mContext;
    private Activity mAttached;
    private List<HomeTab> mTabs = null;

    public static final int S_WIDTH = 1080;
    public static final int S_HEIGHT = 1920;
    public static final int S_TITLE_HEIGHT = 150;



    public MainTab(Activity attached) {
        super(attached);
        mContext = attached;
        mAttached = attached;

        mTabs = genTabs();
    }

    private List<HomeTab> genTabs(){
        ArrayList<HomeTab> tabs = new ArrayList<>();
        tabs.add(new SessionTab(mContext));
        tabs.add(new ExploreTab(mContext));
        tabs.add(new FunTab(mContext));
        tabs.add(new CircleTab(mContext));
        tabs.add(new SettingTab(mContext));
        return tabs;
    }
}
