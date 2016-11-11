package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.UserBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IMineData;
import com.liyunkun.week9_1mvp_qqmusic.utils.LoginUtils;

/**
 * Created by liyunkun on 2016/10/24 0024.
 */
public class IMineDataImpl implements IMineData{
    @Override
    public UserBean getData(Context context) {
        UserBean userBean=new UserBean();
        SharedPreferences sp = context.getSharedPreferences(LoginUtils.USER_BEAN, context.MODE_PRIVATE);
        String userName = sp.getString(LoginUtils.USER_NAME, "");
        String userFace = sp.getString(LoginUtils.USER_FACE, "");
        userBean.setUserName(userName);
        userBean.setUserFace(userFace);
        return userBean;
    }
}
