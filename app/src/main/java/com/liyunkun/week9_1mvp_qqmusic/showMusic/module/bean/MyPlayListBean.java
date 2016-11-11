package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean;

import android.graphics.Bitmap;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class MyPlayListBean {

    private Bitmap img;
    private String playListName;
    private int musicNumber;
    private int downMusicNumber;

    public MyPlayListBean() {
    }

    public MyPlayListBean(Bitmap img, String playListName, int musicNumber, int downMusicNumber) {
        this.img = img;
        this.playListName = playListName;
        this.musicNumber = musicNumber;
        this.downMusicNumber = downMusicNumber;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public int getMusicNumber() {
        return musicNumber;
    }

    public void setMusicNumber(int musicNumber) {
        this.musicNumber = musicNumber;
    }

    public int getDownMusicNumber() {
        return downMusicNumber;
    }

    public void setDownMusicNumber(int downMusicNumber) {
        this.downMusicNumber = downMusicNumber;
    }
}
