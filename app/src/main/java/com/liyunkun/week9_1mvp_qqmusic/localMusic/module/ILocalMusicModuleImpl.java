package com.liyunkun.week9_1mvp_qqmusic.localMusic.module;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class ILocalMusicModuleImpl implements ILocalMusicModule {


    @Override
    public List<MusicBean> getDataFromMedia(Context context) {
        List<MusicBean> list = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String musicPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            if (musicName != null && !"".equals(musicName)) {
                musicName=musicName.substring(musicName.indexOf("-") + 1,musicName.lastIndexOf("."));
                list.add(new MusicBean(musicName, (int) (duration / 1000), null, 0, 0, null, null, null, musicPath, artist, 0));
            }
        }
        return list;
    }
}
