package com.liyunkun.week9_1mvp_qqmusic.localMusic.module;

import android.content.Context;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public interface ILocalMusicModule {

    List<MusicBean> getDataFromMedia(Context context);
}
