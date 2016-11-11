package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.RankLeftBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IRankLeftData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class IRankLeftDataImpl implements IRankLeftData {
    /**
     * 3=欧美
     5=内地
     6=港台
     16=韩国
     17=日本
     18=民谣
     19=摇滚
     23=销量
     26=热歌
     * @return
     */
    @Override
    public List<RankLeftBean> getData() {
        List<RankLeftBean> list=new ArrayList<>();
        list.add(new RankLeftBean(3,"欧美"));
        list.add(new RankLeftBean(5,"内地"));
        list.add(new RankLeftBean(6,"港台"));
        list.add(new RankLeftBean(16,"韩国"));
        list.add(new RankLeftBean(17,"日本"));
        list.add(new RankLeftBean(18,"民谣"));
        list.add(new RankLeftBean(19,"摇滚"));
        list.add(new RankLeftBean(23,"销量"));
        list.add(new RankLeftBean(26,"热歌"));
        return list;
    }
}
