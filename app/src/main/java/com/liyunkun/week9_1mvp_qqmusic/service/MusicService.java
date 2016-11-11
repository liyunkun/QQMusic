package com.liyunkun.week9_1mvp_qqmusic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class MusicService extends Service {
    private Notification.Builder builder;
    private RemoteViews remoteViews;
    private NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        builder = new Notification.Builder(this);
        remoteViews = new RemoteViews(getPackageName(), R.layout.nf_item);
        Intent intentService = new Intent(this, MusicService.class);
        intentService.putExtra("type", PlayUtils.STOPSERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intentService, PendingIntent.FLAG_ONE_SHOT);
        remoteViews.setOnClickPendingIntent(R.id.nf_close_btn, pendingIntent);
        builder.setContent(remoteViews).setSmallIcon(R.drawable.defualt);
        startForeground(1, builder.build());
        manager = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int type = intent.getIntExtra("type", PlayUtils.STOP);
        String musicPath = intent.getStringExtra("musicPath");
        remoteViews.setTextViewText(R.id.singerName, PlayUtils.current_music.getSingername());
        remoteViews.setTextViewText(R.id.nf_music_name, PlayUtils.current_music.getSongname());
        if (musicPath != null && !"".equals(musicPath)) {
            Bitmap bitmap = null;
            if (musicPath.startsWith("http")) {
//                try {
//                    bitmap = Picasso.with(this).load(musicPath).get();
//                } catch (IOException e) {
////                    e.printStackTrace();
//                    bitmap = null;
//                }
            } else {
                bitmap = PlayUtils.getThumbnail(musicPath);
                if (bitmap != null) {
                    remoteViews.setImageViewBitmap(R.id.music_face, bitmap);
                }
            }
        }
        manager.notify(1, builder.build());
        switch (type) {
            case PlayUtils.PLAY:
                PlayUtils.play(this, musicPath);
                break;
            case PlayUtils.PAUSE:
                PlayUtils.pause();
                break;
            case PlayUtils.STOP:
                PlayUtils.stop();
                break;
            case PlayUtils.STOPSERVICE:
                stopSelf();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PlayUtils.stop();
    }
}
