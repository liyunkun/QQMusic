package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public interface IRankRightView {
    void getData(List<MusicBean> list);
    void getErrorMsg(String errorMsg);
}
