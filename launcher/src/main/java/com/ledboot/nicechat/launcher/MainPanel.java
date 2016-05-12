package com.ledboot.nicechat.launcher;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ledboot.nicechat.core.Debuger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class MainPanel extends FrameLayout implements GestureDetector.OnGestureListener, SmoothScroller.OnScrollingListener {

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
    public static final int S_BOTTOM_PANEL_HEIGHT = 300;

    public static final String TAG_SESSION = "_session";
    public static final String TAG_EXPLORE = "_explore";
    public static final String TAG_FUN = "_fun";
    public static final String TAG_CIRCLE = "_circle";
    public static final String TAG_SETTING = "_setting";


    private TabContainer mTabContainer;
    private LinearLayout mBottomPanel;

    private List<HomeTab> mTabs = null;
    private List<TabItem> mBottomTabs = null;
    public static float S_SCALE;

    private SmoothScroller mSmoothScroller;
    private GestureDetector mGestureDetector;

    public MainPanel(Activity attached) {
        super(attached);
        mContext = attached;
        mAttached = attached;

//        mTabs = genTabs();


        init();
        initView();
        Debuger.logD(TAG, " finish Mainpanel~~~~");
    }

    private void initView() {
        LinearLayout root = new LinearLayout(mContext);
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(rootParams);
        root.setOrientation(LinearLayout.VERTICAL);

        mTabs = genTabs();

        mTabContainer = new TabContainer(mContext, this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        layoutParams.weight = 1.0f;
        mTabContainer.setLayoutParams(layoutParams);
        root.addView(mTabContainer);

        mBottomPanel = new LinearLayout(mContext);
        mBottomPanel.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bottomPanelParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomPanel.setLayoutParams(bottomPanelParams);

        mBottomTabs = new ArrayList<>();

        for (int i = 0; i < mTabs.size(); i++) {
            HomeTab tab = mTabs.get(i);
            TabItem item = new TabItem(mContext, tab.getNorResId(), tab.getLabel());
            FrameLayout.LayoutParams bottomTabParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//            bottomTabParams.weight = 1.0f;
            tab.setLayoutParams(bottomTabParams);
            mBottomTabs.add(item);
            mBottomPanel.addView(item);
        }

        root.addView(mBottomPanel);

        mGestureDetector = new GestureDetector(mContext, this);
        mSmoothScroller = new SmoothScroller(mTabContainer);
        mSmoothScroller.setOnScrollingListener(this);
        addView(root);
        Debuger.logD(TAG, "MainPanel initView()");
    }

    private void init() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int mSreenHeight = metrics.heightPixels;
        int mSreenWidth = metrics.widthPixels;
        float mScaleHeight = ((float) mSreenHeight) / ((float) S_HEIGHT);
        float mScaleWidth = ((float) mSreenWidth) / ((float) S_WIDTH);
        S_SCALE = mScaleHeight < mScaleWidth ? mScaleWidth : mScaleHeight;
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Debuger.logD(TAG,"dispatchTouchEvent x="+ev.getX()+",y="+ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG,"dispatchTouchEvent x="+ev.getX()+",y="+ev.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG,"dispatchTouchEvent x="+ev.getX()+",y="+ev.getY());
                break;
        }
        boolean rlt = super.dispatchTouchEvent(ev);
        Debuger.logD(TAG,"dispatchTouchEvent result = "+rlt);
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
                Debuger.logD(TAG,"onTouchEvent down x="+event.getX()+",y="+event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG,"onTouchEvent move x="+event.getX()+",y="+event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG,"onTouchEvent up x="+event.getX()+",y="+event.getY());
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
                Debuger.logD(TAG,"onInterceptTouchEvent down x="+ev.getX()+",y="+ev.getY());
                mLastInterceptDownX = ev.getX();
                mLastInterceptDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG,"onInterceptTouchEvent move x="+ev.getX()+",y="+ev.getY());
                if(mLastInterceptMoveY < 0){
                    mLastInterceptMoveY = mLastInterceptDownY;
                }
                if(mLastInterceptMoveX < 0){
                    mLastInterceptMoveX = mLastInterceptDownX;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG,"onInterceptTouchEvent up x="+ev.getX()+",y="+ev.getY());
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

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onScrollStart(int x, int y) {

    }

    @Override
    public void smoothScrollTo(int x, int y) {

    }

    @Override
    public void onScrollFinish(int x, int y) {

    }
}
