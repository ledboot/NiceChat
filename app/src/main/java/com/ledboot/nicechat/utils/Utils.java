package com.ledboot.nicechat.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by wengaowei728 on 16/6/15.
 */
public  class Utils {
    private static Context mContext;

    public static  void initialize(Application application){
        mContext = application.getApplicationContext();
    }

    public static void Toast(String text) {
        android.widget.Toast.makeText(mContext, text, android.widget.Toast.LENGTH_SHORT).show();
    }

    public static void ToastL(String text) {
        android.widget.Toast.makeText(mContext, text, android.widget.Toast.LENGTH_LONG).show();
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text
     */
    public static void copyToClipboard(String text) {
        ClipboardManager cbm = (ClipboardManager) mContext.getSystemService(Activity.CLIPBOARD_SERVICE);
        cbm.setPrimaryClip(ClipData.newPlainText(mContext.getPackageName(), text));
    }
}
