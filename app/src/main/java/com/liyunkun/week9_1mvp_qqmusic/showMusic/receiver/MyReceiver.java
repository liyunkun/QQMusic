package com.liyunkun.week9_1mvp_qqmusic.showMusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IMainView;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public class MyReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(PlayUtils.START_RECEIVER.equals(intent.getAction())){
            ((IMainView) context).updatePlayerBtn2Main();
        }else if(PlayUtils.UPDATE_BOTTOM_MUSIC_MSG_ACTION.equals(intent.getAction())){
            ((IMainView) context).updateMainBottom(PlayUtils.current_music);
        }
    }
}
