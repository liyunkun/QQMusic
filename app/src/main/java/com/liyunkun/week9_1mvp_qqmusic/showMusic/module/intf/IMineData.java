package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf;

import android.content.Context;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.UserBean;

/**
 * Created by liyunkun on 2016/10/24 0024.
 */
public interface IMineData {

    UserBean getData(Context context);
}
