package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class RankLeftBean {
    private int id;
    private String name;

    public RankLeftBean() {
    }

    public RankLeftBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
