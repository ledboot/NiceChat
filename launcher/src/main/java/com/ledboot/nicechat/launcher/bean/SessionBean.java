package com.ledboot.nicechat.launcher.bean;

/**
 * Created by wengaowei728 on 16/6/12.
 */
public class SessionBean extends BaseBean {

    public SessionBean(String title, String icon, String content, String showTime) {
        this.title = title;
        this.icon = icon;
        this.content = content;
        this.showTime = showTime;
    }

    private String title;

    private String icon;

    private String content;

    private String showTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
