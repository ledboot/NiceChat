package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by wengaowei728 on 16/5/10.
 */
public class TabContainer extends FrameLayout {

    private Context mContext;
    private MainPanel mMainPanel;

    private List<HomeTab> mTabs;

    public TabContainer(Context context, MainPanel mainPanel) {
        super(context);
        mContext = context;
        mMainPanel = mainPanel;
        mTabs = mMainPanel.getTabs();
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
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof HomeTab || view.getVisibility() == View.GONE) {
                continue;
            }
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
    }
}
