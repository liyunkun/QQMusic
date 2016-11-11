package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class UserBean {

    private String userName;
    private String userFace;
    private int userId;

    public UserBean() {
    }

    public UserBean(String userName, String userFace, int userId) {
        this.userName = userName;
        this.userFace = userFace;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
