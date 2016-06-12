package com.ledboot.nicechat.launcher;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ledboot.nicechat.core.Debuger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class MainPanel extends FrameLayout {

    public static final String TAG = MainPanel.class.getSimpleName();

    private Context mContext;
    private Activity mAttached;

    public static final int S_WIDTH = 1080;
    public static final int S_HEIGHT = 1920;


    /**
     * 头部高度
     */
    public static final int S_TITLE_HEIGHT = 150;
    /**
     * 底部高度
     */
    public static final int S_BOTTOM_PANEL_HEIGHT = 150;

    /**
     * 底部tab宽度
     */
    public static final int S_TABITEM_LAYOUT_WIDTH = 60;
    /**
     * 底部tab高度
     */
    public static final int S_TABITEM_LAYOUT_HEIGHT = 60;


    public static final String TAG_SESSION = "_session";
    public static final String TAG_EXPLORE = "_explore";
    public static final String TAG_FUN = "_fun";
    public static final String TAG_CIRCLE = "_circle";
    public static final String TAG_SETTING = "_setting";


    private TabContainer mTabContainer;
    private TabPannel mTabPannel;

    private List<HomeTab> mTabs = null;
    private List<TabItem> mTabItems = null;



    public static float S_SCALE_HEIGHT;
    public static float S_SCALE_WIDTH;
    public static float S_SCALE;
    public static int S_SREEN_HEIGHT;
    public static int S_SREEN_WIDTH;


    public MainPanel(Activity attached) {
        super(attached);
        mContext = attached;
        mAttached = attached;
        init();
        initView();
    }

    private void initView() {
        LinearLayout root = new LinearLayout(mContext);
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(rootParams);
        root.setOrientation(LinearLayout.VERTICAL);

        mTabs = genTabs();

        mTabContainer = new TabContainer(mContext, this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1.0f;
        mTabContainer.setLayoutParams(layoutParams);
        root.addView(mTabContainer);

        mTabItems = genTabItems();
        mTabPannel = new TabPannel(mContext, mTabItems);
        LinearLayout.LayoutParams bottomPanelParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (MainPanel.S_SCALE * MainPanel.S_BOTTOM_PANEL_HEIGHT));
        mTabPannel.setLayoutParams(bottomPanelParams);
        root.addView(mTabPannel);

        addView(root);
        Debuger.logD(TAG, "MainPanel initView()");
    }

    private void init() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        S_SREEN_HEIGHT = metrics.heightPixels;
        S_SREEN_WIDTH = metrics.widthPixels;
        S_SCALE_HEIGHT = ((float) S_SREEN_HEIGHT) / ((float) S_HEIGHT);
        S_SCALE_WIDTH = ((float) S_SREEN_WIDTH) / ((float) S_WIDTH);
        S_SCALE = S_SCALE_HEIGHT < S_SCALE_WIDTH ? S_SCALE_WIDTH : S_SCALE_HEIGHT;
    }

    private List<HomeTab> genTabs() {
        ArrayList<HomeTab> tabs = new ArrayList<>();
        tabs.add(new SessionTab(mContext));
        tabs.add(new ExploreTab(mContext));
        tabs.add(new FunTab(mContext));
        tabs.add(new CircleTab(mContext));
        tabs.add(new SettingTab(mContext));
        return tabs;
    }

    private List<TabItem> genTabItems() {
        List<TabItem> tabItems = new ArrayList<>();

        TabItem sessionTabItem = new TabItem();
        sessionTabItem.mDesc = MainPanel.TAG_SESSION;
        sessionTabItem.mTextId = R.string.tab_item_session;
        sessionTabItem.mIconNormal = R.mipmap.ic_session;
        sessionTabItem.mIconPressed = R.mipmap.ic_session;
        tabItems.add(sessionTabItem);

        TabItem exploreTabItem = new TabItem();
        exploreTabItem.mDesc = MainPanel.TAG_EXPLORE;
        exploreTabItem.mTextId = R.string.tab_item_explore;
        exploreTabItem.mIconNormal = R.mipmap.ic_session;
        exploreTabItem.mIconPressed = R.mipmap.ic_session;
        tabItems.add(exploreTabItem);

        TabItem funTabItem = new TabItem();
        funTabItem.mDesc = MainPanel.TAG_FUN;
        funTabItem.mTextId = R.string.tab_item_fun;
        funTabItem.mIconNormal = R.mipmap.ic_session;
        funTabItem.mIconPressed = R.mipmap.ic_session;
        tabItems.add(funTabItem);


        TabItem circleTabItem = new TabItem();
        circleTabItem.mDesc = MainPanel.TAG_CIRCLE;
        circleTabItem.mTextId = R.string.tab_item_circle;
        circleTabItem.mIconNormal = R.mipmap.ic_session;
        circleTabItem.mIconPressed = R.mipmap.ic_session;
        tabItems.add(circleTabItem);

        TabItem settingTabItem = new TabItem();
        settingTabItem.mDesc = MainPanel.TAG_SETTING;
        settingTabItem.mTextId = R.string.tab_item_setting;
        settingTabItem.mIconNormal = R.mipmap.ic_session;
        settingTabItem.mIconPressed = R.mipmap.ic_session;
        tabItems.add(settingTabItem);


        return tabItems;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean rlt = super.dispatchTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Debuger.logD(TAG,"dispatchTouchEvent down x="+ev.getX()+",y="+ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
//                Debuger.logD(TAG,"dispatchTouchEvent move x="+ev.getX()+",y="+ev.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                Debuger.logD(TAG,"dispatchTouchEvent up x="+ev.getX()+",y="+ev.getY());
                break;
        }
        return rlt;
    }

    private float mLastTouchDownX = -1;
    private float mLastTouchDownY = -1;
    private float mLastTouchMoveX = -1;
    private float mLastTouchMoveY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Debuger.logD(TAG,"onTouchEvent down x="+event.getX()+",y="+event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
//                Debuger.logD(TAG,"onTouchEvent move x="+event.getX()+",y="+event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                Debuger.logD(TAG,"onTouchEvent up x="+event.getX()+",y="+event.getY());
                mLastTouchDownX = -1;
                mLastTouchDownY = -1;
                mLastTouchMoveX = -1;
                mLastTouchMoveY = -1;
                break;
        }
        return false;
    }


    private float mLastInterceptDownX = -1;
    private float mLastInterceptDownY = -1;
    private float mLastInterceptMoveX = -1;
    private float mLastInterceptMoveY = -1;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Debuger.logD(TAG,"onInterceptTouchEvent down x="+ev.getX()+",y="+ev.getY());
                mLastInterceptDownX = ev.getX();
                mLastInterceptDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                Debuger.logD(TAG,"onInterceptTouchEvent move x="+ev.getX()+",y="+ev.getY());
                if (mLastInterceptMoveY < 0) {
                    mLastInterceptMoveY = mLastInterceptDownY;
                }
                if (mLastInterceptMoveX < 0) {
                    mLastInterceptMoveX = mLastInterceptDownX;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                Debuger.logD(TAG,"onInterceptTouchEvent up x="+ev.getX()+",y="+ev.getY());
                mLastInterceptDownX = -1;
                mLastInterceptDownY = -1;
                mLastInterceptMoveX = -1;
                mLastInterceptMoveY = -1;
                break;
        }
        return false;
    }


    public List<HomeTab> getTabs() {
        return mTabs;
    }

    public List<TabItem> getTabItems() {
        return mTabItems;
    }

    class TabItem {
        public String mDesc = "";
        public int mTextId = 0;
        public int mIconNormal = 0;
        public int mIconPressed = 0;
        public TextView mText = null;
        public ImageView mIcon = null;
    }

    class TabPannel extends FrameLayout implements OnClickListener{

        private List<TabItem> mTabItems = null;
        private Context mContext;

        public TabPannel(Context context, List<TabItem> items) {
            super(context);
            this.mContext = context;
            this.mTabItems = items;
            setView();
        }

        private void setView() {
            if (mTabItems == null) return;
            int per = (S_SREEN_WIDTH / mTabItems.size());
            int layoutX;
            for (int i = 0; i < mTabItems.size(); i++) {
                layoutX = per * i;
                TabItem item = mTabItems.get(i);

                ImageView icon = new ImageView(mContext);
                int layoutW = (int) (S_TABITEM_LAYOUT_WIDTH * S_SCALE_WIDTH);
                int layoutH = (int) (S_TABITEM_LAYOUT_HEIGHT * S_SCALE_HEIGHT);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(layoutW, layoutH);
                params.topMargin = (int) (30 * S_SCALE_HEIGHT);
                params.leftMargin = layoutX + ((per - layoutW) / 2);
                icon.setLayoutParams(params);
                icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                icon.setImageResource(item.mIconNormal);
                icon.setTag(item);
                item.mIcon = icon;
                icon.setOnClickListener(this);
                this.addView(icon);


                TextView text = new TextView(mContext);
                params = new FrameLayout.LayoutParams(per, LayoutParams.WRAP_CONTENT);
                params.topMargin = (int) (100 * S_SCALE_HEIGHT);
                params.leftMargin = layoutX;
                text.setLayoutParams(params);
                text.setText(item.mTextId);
                text.setTextSize(12);
                text.setGravity(Gravity.CENTER);
                item.mText = text;
                text.setTag(item);
                this.addView(text);
            }
        }

        @Override
        public void onClick(View v) {
            Object tag = v.getTag();
            if(tag != null){
                if(tag instanceof TabItem){
                    String tagDesc = ((TabItem) tag).mDesc;
                    if(mTabContainer.getCurrentTab().equals(tagDesc)){
                        return;
                    }
                    mTabContainer.showPage(tagDesc);
                }
            }
        }
    }
}
