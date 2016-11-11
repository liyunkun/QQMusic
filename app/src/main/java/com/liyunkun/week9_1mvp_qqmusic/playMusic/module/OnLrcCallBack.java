package com.liyunkun.week9_1mvp_qqmusic.playMusic.module;

import com.liyunkun.lrcview.Bean.LrcBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public interface OnLrcCallBack {

    void onFailed(String msg);

    void onSuccessful(List<LrcBean> list);

}
