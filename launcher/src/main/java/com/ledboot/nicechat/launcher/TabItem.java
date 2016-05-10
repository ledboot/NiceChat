package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.media.Image;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wengaowei728 on 16/5/10.
 */
public class TabItem extends LinearLayout {

    private Context mContext;

    private ImageView mIcon;
    private TextView mLabel;

    private int mDefaultIconResId;
    private String mDefaultLabel;

    public TabItem(Context context,int iconResId,String label) {
        super(context);
        mContext = context;
        mDefaultIconResId = iconResId;
        mDefaultLabel = label;
        initView();
    }

    private void initView(){
        setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        setLayoutParams(params);

        mIcon = new ImageView(mContext);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mIcon.setLayoutParams(params);
        mIcon.setBackgroundResource(mDefaultIconResId);
        addView(mIcon);

        mLabel = new TextView(mContext);
        mLabel.setLayoutParams(params);
        mLabel.setText(mDefaultLabel);
        addView(mLabel);

    }

    public void selectTab(int resId){
        mIcon.setBackgroundResource(resId);
    }

}
