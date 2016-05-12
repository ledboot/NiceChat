package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;

import com.ledboot.nicechat.core.Debuger;

import java.util.List;

/**
 * Created by wengaowei728 on 16/5/10.
 */
public class TabContainer extends FrameLayout implements GestureDetector.OnGestureListener, SmoothScroller.OnScrollingListener{

    public  static final java.lang.String TAG = TabContainer.class.getSimpleName();
    private Context mContext;
    private MainPanel mMainPanel;

    private List<HomeTab> mTabs;

    private boolean mFirstlayout = true;

    private GestureDetector mGestureDetector;
    private SmoothScroller mSmoothScroller;
    private VelocityTracker mTracker;

    private int mScrollMaxWidth = 0;
    private int mScrollMinWidth = 0;

    public TabContainer(Context context, MainPanel mainPanel) {
        super(context);
        mContext = context;
        mMainPanel = mainPanel;
        mTabs = mMainPanel.getTabs();
        mGestureDetector = new GestureDetector(mContext,this);
        mSmoothScroller = new SmoothScroller(this);
        mSmoothScroller.setOnScrollingListener(this);
        mTracker = VelocityTracker.obtain();
        mTracker.computeCurrentVelocity(1,(float)0.1);
        initView();
    }

    private void initView() {
        if (mTabs == null) return;
        for (HomeTab page : mTabs) {
            FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            page.setLayoutParams(params);
            addView(page);
        }
        Debuger.logD(TAG,"TabContainer initView()");
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Debuger.logD(TAG,"TabContainer onlayout");
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (!(view instanceof HomeTab) || view.getVisibility() == View.GONE) {
                continue;
            }
            mScrollMaxWidth = getWidth() * 3;
            mScrollMinWidth = 0;
            HomeTab page = (HomeTab) view;
            if (MainPanel.TAG_SESSION.equals(page.getTabTag())) {
                view.layout(0, 0, this.getWidth(), this.getHeight());
            } else if (MainPanel.TAG_EXPLORE.equals(page.getTabTag())) {
                view.layout(this.getWidth(), 0, this.getWidth(), this.getHeight());
            } else if (MainPanel.TAG_FUN.equals(page.getTabTag())) {
                view.layout(this.getWidth() * 2, 0, this.getWidth() * 2, this.getHeight());
            } else if (MainPanel.TAG_CIRCLE.equals(page.getTabTag())) {
                view.layout(this.getWidth() * 3, 0, this.getWidth() * 3, this.getHeight());
            } else if (MainPanel.TAG_SETTING.equals(page.getTabTag())) {
                view.layout(this.getWidth() * 4, 0, this.getWidth() * 4, this.getHeight());
            }
        }

        if(mFirstlayout){
            post(new Runnable() {
                @Override
                public void run() {
                    showPage(MainPanel.TAG_SESSION);
                    mFirstlayout = false;
                    Debuger.logD(TAG,"TabContainer onlayout......");
                }
            });
        }
    }

    private float mLastInterceptDownX = -1;
    private float mLastInterceptDownY = -1;
    private int mTrackerAddCount = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(getScrollX() < mScrollMinWidth)
            return false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastInterceptDownX = ev.getX();
                mLastInterceptDownY = ev.getY();
                Debuger.logD(TAG,"onInterceptTouchEvent down x="+ev.getX()+",y="+getY());
                mTracker.clear();
                mTracker.addMovement(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG,"onInterceptTouchEvent move x="+ev.getX()+",y="+getY());
                mTracker.addMovement(ev);
//                mTrackerAddCount++;
                Debuger.logD(TAG,"xvelocity="+Math.abs(mTracker.getXVelocity())+",y="+Math.abs(mTracker.getYVelocity()));
                if(Math.abs(mTracker.getXVelocity())> Math.abs(mTracker.getYVelocity()*2)){
                    Debuger.logD(TAG,"x velocity > y velocity");
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG,"onInterceptTouchEvent up x="+ev.getX()+",y="+getY());
                mLastInterceptDownX = -1;
                mLastInterceptDownX = -1;
                mTracker.clear();
                break;
        }
        return false;
//        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Debuger.logD(TAG,"onTouchEvent down x="+event.getX()+",y="+event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG,"onTouchEvent move x="+event.getX()+",y="+event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG,"onTouchEvent up x="+event.getX()+",y="+event.getY());
                break;
        }
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Debuger.logD(TAG,"onTouchEvent down x="+ev.getX()+",y="+ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG,"onTouchEvent move x="+ev.getX()+",y="+ev.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG,"onTouchEvent up x="+ev.getX()+",y="+ev.getY());
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private HomeTab findTabByTag(String tag){
        for(HomeTab tab : mTabs){
            if(tab.getTabTag().equals(tag)){
                return tab;
            }
        }
        return  null;
    }

    private void showPage(String tag){
        HomeTab tab = findTabByTag(tag);
        if(tab != null){
            tab.prepareToShow();
        }
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
