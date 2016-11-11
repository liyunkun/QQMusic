package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf;

import android.content.Context;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

/**
 * Created by liyunkun on 2016/10/20 0020.
 */
public interface IMainData {

    void saveData(MusicBean musicBean, Context context);

    MusicBean loadData(Context context);

}
