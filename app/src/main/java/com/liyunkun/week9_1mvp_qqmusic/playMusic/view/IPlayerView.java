package com.liyunkun.week9_1mvp_qqmusic.playMusic.view;

import com.liyunkun.lrcview.Bean.LrcBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public interface IPlayerView {

    void initLrcView(List<LrcBean> list);
    void getErrorMsg(String msg);
    void setMusicInfo();
    void setSeekBarInfo();
}
