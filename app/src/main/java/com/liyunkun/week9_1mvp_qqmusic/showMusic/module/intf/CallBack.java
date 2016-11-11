package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public interface CallBack {
    void onSuccessful(List<MusicBean> list);
    void onFailed(String errorMsg);
}
