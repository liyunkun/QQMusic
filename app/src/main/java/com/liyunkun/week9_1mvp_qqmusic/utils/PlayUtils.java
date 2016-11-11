package com.liyunkun.week9_1mvp_qqmusic.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;

import com.liyunkun.week9_1mvp_qqmusic.service.MusicService;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import java.io.IOException;
import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class PlayUtils {

    public static MediaPlayer player=null;
    public static final int PLAY=0;
    public static final int PAUSE=1;
    public static final int STOP=2;
    public static final int STOPSERVICE=3;
    public static final String MUSICBEAN="musicBean";
    public static final String MUSICNAME="musicName";
    public static final String MUSICURL="musicUrl";
    public static final String SINGER_NAME="singer_name";
    public static final int  LOCALMUSICBEAN=4;
    public static final int PLAYLISTMUSICBEAN=5;
    public static final String START_RECEIVER="start_receiver";
    public static final String UPDATE_BOTTOM_MUSIC_MSG_ACTION="update_bottom_music_msg_action";

    public static int CURRENT_START=STOP;
    public static MusicBean current_music;
    public static List<MusicBean> list;
    public static int current_position=0;

    public static void play(Context context,String musicPath){
        if(player==null){
            init(context);
        }
        player.reset();
        try {
            player.setDataSource(musicPath);
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void pause(){
        if (player != null && player.isPlaying()){
            player.pause();
            CURRENT_START=PAUSE;
        }else if(player != null && !player.isPlaying()){
            player.start();
            CURRENT_START=PLAY;
        }
    }

    public static void stop(){
        if(player != null){
            player.stop();
            CURRENT_START=STOP;
            player.release();
            player=null;
        }
    }
    private static void init(final Context context) {
        player=new MediaPlayer();
       player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {
               mp.start();
               CURRENT_START=PLAY;
               context.sendBroadcast(new Intent(UPDATE_BOTTOM_MUSIC_MSG_ACTION));
           }
       });
    }
    public static void startService(Context context, MusicBean musicBean,int type){
        current_music=musicBean;
        Intent intent=new Intent(context, MusicService.class);
        intent.putExtra("type",type);
        intent.putExtra("musicPath",musicBean.getUrl());
        context.startService(intent);
    }

    public static Bitmap getThumbnail(String filePath) {
        if (filePath == null || "".equals(filePath) || filePath.startsWith("http")) {
            return null;
        }
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = null;
        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(filePath);
            byte[] bytes = retriever.getEmbeddedPicture();
            if (bytes != null && bytes.length > 0) {
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        } finally {
            if (retriever != null) {
                retriever.release();
            }
        }
        return bitmap;
    }
}
