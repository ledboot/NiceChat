package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.ledboot.nicechat.core.Debuger;

import java.util.List;

/**
 * Created by wengaowei728 on 16/5/10.
 */
public class TabContainer extends FrameLayout {

    public static final java.lang.String TAG = TabContainer.class.getSimpleName();
    private Context mContext;
    private MainPanel mMainPanel;

    private List<HomeTab> mTabs;

    private boolean mFirstlayout = true;

    private GestureDetector mGestureDetector;
    private VelocityTracker mTracker;

    private int mScrollMaxWidth = 0;
    private int mScrollMinWidth = 0;

    private int mMaximumVelocity = 0;
    private int mMinimunVelocity = 0;

    private String mCurrentTab = MainPanel.TAG_SESSION;

    public TabContainer(Context context, MainPanel mainPanel) {
        super(context);
        mContext = context;
        mMainPanel = mainPanel;
        mTabs = mMainPanel.getTabs();
        mTracker = VelocityTracker.obtain();
        initView();
        mMaximumVelocity = ViewConfiguration.get(mContext).getScaledMaximumFlingVelocity();
        mMinimunVelocity = ViewConfiguration.get(mContext).getScaledMinimumFlingVelocity();
    }

    private void initView() {
        if (mTabs == null) return;
        for (HomeTab page : mTabs) {
            FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            page.setLayoutParams(params);
            addView(page);
        }
        Debuger.logD(TAG, "TabContainer initView()");
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Debuger.logD(TAG, "TabContainer onlayout");
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (!(view instanceof HomeTab) || view.getVisibility() == View.GONE) {
                continue;
            }
            mScrollMaxWidth = getWidth() * 4;
            mScrollMinWidth = 0;
            HomeTab page = (HomeTab) view;
            if (MainPanel.TAG_SESSION.equals(page.getTabTag())) {
                view.layout(0, 0, this.getWidth(), this.getHeight());
            } else if (MainPanel.TAG_EXPLORE.equals(page.getTabTag())) {
                view.layout(this.getWidth(), 0, this.getWidth() * 2, this.getHeight());
            } else if (MainPanel.TAG_FUN.equals(page.getTabTag())) {
                view.layout(this.getWidth() * 2, 0, this.getWidth() * 3, this.getHeight());
            } else if (MainPanel.TAG_CIRCLE.equals(page.getTabTag())) {
                view.layout(this.getWidth() * 3, 0, this.getWidth() * 4, this.getHeight());
            } else if (MainPanel.TAG_SETTING.equals(page.getTabTag())) {
                view.layout(this.getWidth() * 4, 0, this.getWidth() * 5, this.getHeight());
            }
        }

        if (mFirstlayout) {
            post(new Runnable() {
                @Override
                public void run() {
                    showPage(MainPanel.TAG_SESSION);
                    mFirstlayout = false;
                    Debuger.logD(TAG, "TabContainer onlayout......");
                }
            });
        }
    }

    private float mLastInterceptDownX = -1;
    private float mLastInterceptDownY = -1;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*Debuger.logW(TAG, "onInterceptTouchEvent,");
        if (getScrollX() < mScrollMinWidth)
            return false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastInterceptDownX = ev.getX();
                mLastInterceptDownY = ev.getY();
                Debuger.logD(TAG, "onInterceptTouchEvent down x=" + ev.getX() + ",y=" + getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG, "onInterceptTouchEvent move x=" + ev.getX() + ",y=" + getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG, "onInterceptTouchEvent up x=" + ev.getX() + ",y=" + getY());
                mLastInterceptDownX = -1;
                mLastInterceptDownX = -1;
                break;
        }*/
        Debuger.logD("onInterceptTouchEvent");

        return false;
    }


    private float mLastTouchDownX = -1;
    private float mLastTouchDownY = -1;
    private float mLastTouchMoveX = -1;
    private float mLastTouchMoveY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Debuger.logD("onTouchEvent");
        /*switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Debuger.logD(TAG, "onTouchEvent down x=" + ev.getX() + ",y=" + ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG, "onTouchEvent move x=" + ev.getX() + ",y=" + ev.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG, "onTouchEvent up x=" + ev.getX() + ",y=" + ev.getY());
                break;
        }*/
        computeAction(ev);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean rlt = super.dispatchTouchEvent(ev);
        Debuger.logD("dispatchTouchEvent,rlt=" + rlt);
        /*switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Debuger.logD(TAG, "dispatchTouchEvent down x=" + ev.getX() + ",y=" + ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG, "dispatchTouchEvent move x=" + ev.getX() + ",y=" + ev.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Debuger.logD(TAG, "dispatchTouchEvent up x=" + ev.getX() + ",y=" + ev.getY());
                break;
        }*/
        computeAction(ev);
        return rlt;
    }

    private void computeAction(MotionEvent ev) {
        mTracker.addMovement(ev);
        int targetX;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mLastTouchDownX < 0 || mLastTouchDownY < 0) {
                    mLastTouchDownY = ev.getY();
                    mLastTouchDownX = ev.getX();
                    mLastTouchMoveX = ev.getX();
                    mLastTouchMoveY = ev.getY();
                }
                mLastTouchDownX = ev.getX();
                mLastTouchDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Debuger.logD(TAG, "onTouchEvent move x=" + ev.getX() + ",y=" + ev.getY());
                targetX = (int) (getScrollX() - (ev.getX() - mLastTouchMoveX));
                if (targetX <= mScrollMinWidth) {
                    targetX = mScrollMinWidth;
                } else if (targetX >= mScrollMaxWidth) {
                    targetX = mScrollMaxWidth;
                }
                scrollTo(targetX, 0);
                mLastTouchMoveX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTracker.computeCurrentVelocity(100, mMaximumVelocity);
                int velocityX = (int) mTracker.getXVelocity();
                targetX = (int) (getScrollX() - (ev.getX() - mLastTouchMoveX));
                if (targetX <= mScrollMinWidth) {
                    targetX = mScrollMinWidth;
                } else if (targetX >= mScrollMaxWidth) {
                    targetX = mScrollMaxWidth;
                }
                //负数向左滑，正数向右滑
                if (Math.abs(velocityX) > 500) {
                    if (velocityX < 0) {
                        //向左滑
                        scrollToNextPage();
                    } else {
                        //向右滑
                        scrollToPrePage();
                    }
                } else {
                    if (targetX < getWidth() * 0.5) {
                        showPage(MainPanel.TAG_SESSION);
                    } else if (targetX < getWidth() * 1.5) {
                        showPage(MainPanel.TAG_EXPLORE);
                    } else if (targetX < getWidth() * 2.5) {
                        showPage(MainPanel.TAG_FUN);
                    } else if (targetX < getWidth() * 3.5) {
                        showPage(MainPanel.TAG_CIRCLE);
                    } else {
                        showPage(MainPanel.TAG_SETTING);
                    }
                }
                Debuger.logD("velocityX=" + velocityX + ",Width=" + getWidth());

                Debuger.logD("targetX = " + targetX);


                mLastTouchDownX = -1;
                mLastTouchDownY = -1;
                mLastTouchMoveX = -1;
                mLastTouchMoveY = -1;
                break;
        }
    }

    private HomeTab findTabByTag(String tag) {
        for (HomeTab tab : mTabs) {
            if (tab.getTabTag().equals(tag)) {
                return tab;
            }
        }
        return null;
    }

    public void showPage(String tag) {
        HomeTab tab = findTabByTag(tag);
        if (tab != null) {
            tab.prepareToShow();
            scrollToPage(tag);
        }
    }


    private void scrollToPage(String tag) {
        if (findTabByTag(tag) == null) return;
        if (MainPanel.TAG_SESSION.equals(tag)) {
            mCurrentTab = MainPanel.TAG_SESSION;
            Debuger.logD(TAG, "scroll to session~~~");
            this.scrollTo(0, 0);
        } else if (MainPanel.TAG_EXPLORE.equals(tag)) {
            mCurrentTab = MainPanel.TAG_EXPLORE;
            Debuger.logD(TAG, "scroll to explore~~~");
            this.scrollTo(this.getWidth(), 0);
        } else if (MainPanel.TAG_FUN.equals(tag)) {
            mCurrentTab = MainPanel.TAG_FUN;
            Debuger.logD(TAG, "scroll to fun~~~");
            this.scrollTo(this.getWidth() * 2, 0);
        } else if (MainPanel.TAG_CIRCLE.equals(tag)) {
            mCurrentTab = MainPanel.TAG_CIRCLE;
            Debuger.logD(TAG, "scroll to circle~~~");
            this.scrollTo(this.getWidth() * 3, 0);
        } else if (MainPanel.TAG_SETTING.equals(tag)) {
            mCurrentTab = MainPanel.TAG_SETTING;
            Debuger.logD(TAG, "scroll to setting~~~");
            this.scrollTo(this.getWidth() * 4, 0);
        }
    }

    public String getCurrentTab() {
        return this.mCurrentTab;
    }


    public void scrollToNextPage() {
        String tempTab = getCurrentTab();
        if (tempTab.equals(MainPanel.TAG_SESSION)) {
            Debuger.logD("scrollToNextPage(),TAG_EXPLORE!!!");
            showPage(MainPanel.TAG_EXPLORE);
        } else if (tempTab.equals(MainPanel.TAG_EXPLORE)) {
            Debuger.logD("scrollToNextPage(),TAG_EXPLORE!!!");
            showPage(MainPanel.TAG_FUN);
        } else if (tempTab.equals(MainPanel.TAG_FUN)) {
            Debuger.logD("scrollToNextPage(),TAG_FUN!!!");
            showPage(MainPanel.TAG_CIRCLE);
        } else if (tempTab.equals(MainPanel.TAG_CIRCLE)) {
            Debuger.logD("scrollToNextPage(),TAG_CIRCLE!!!");
            showPage(MainPanel.TAG_SETTING);
        }
    }

    public void scrollToPrePage() {
        String tempTab = getCurrentTab();
        if (tempTab.equals(MainPanel.TAG_EXPLORE)) {
            showPage(MainPanel.TAG_SESSION);
        } else if (tempTab.equals(MainPanel.TAG_FUN)) {
            showPage(MainPanel.TAG_EXPLORE);
        } else if (tempTab.equals(MainPanel.TAG_CIRCLE)) {
            showPage(MainPanel.TAG_FUN);
        } else if (tempTab.equals(MainPanel.TAG_SETTING)) {
            showPage(MainPanel.TAG_CIRCLE);
        }
    }

}
