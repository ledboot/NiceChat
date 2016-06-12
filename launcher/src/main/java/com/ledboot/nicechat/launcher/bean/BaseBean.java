package com.ledboot.nicechat.launcher.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wengaowei728 on 16/6/12.
 */
public class BaseBean implements Serializable {


    private int id;

    private Date createAt;

    private Date updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
