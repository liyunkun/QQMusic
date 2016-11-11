package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IMainData;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;

/**
 * Created by liyunkun on 2016/10/20 0020.
 */
public class IMainDataImpl implements IMainData{
    @Override
    public void saveData(MusicBean musicBean, Context context) {
        SharedPreferences sp = context.getSharedPreferences(PlayUtils.MUSICBEAN, context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(PlayUtils.MUSICNAME,musicBean.getSongname());
        edit.putString(PlayUtils.MUSICURL,musicBean.getUrl());
        edit.putString(PlayUtils.SINGER_NAME,musicBean.getSingername());
        edit.commit();
    }

    @Override
    public MusicBean loadData(Context context) {
        MusicBean bean=new MusicBean();
        SharedPreferences sp = context.getSharedPreferences(PlayUtils.MUSICBEAN, context.MODE_PRIVATE);
        String musicName = sp.getString(PlayUtils.MUSICNAME, "");
        String musicUrl = sp.getString(PlayUtils.MUSICURL, "");
        String singer_name = sp.getString(PlayUtils.SINGER_NAME, "");
        bean.setSongname(musicName);
        bean.setUrl(musicUrl);
        bean.setSingername(singer_name);
        return bean;
    }
}
