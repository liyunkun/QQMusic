package com.liyunkun.week9_1mvp_qqmusic.playMusic.presenter;

import android.os.Handler;

import com.liyunkun.lrcview.Bean.LrcBean;
import com.liyunkun.week9_1mvp_qqmusic.BasePresenter;
import com.liyunkun.week9_1mvp_qqmusic.playMusic.module.IPlayerData;
import com.liyunkun.week9_1mvp_qqmusic.playMusic.module.IPlayerDataImpl;
import com.liyunkun.week9_1mvp_qqmusic.playMusic.module.OnLrcCallBack;
import com.liyunkun.week9_1mvp_qqmusic.playMusic.view.IPlayerView;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public class PlayerPresenter implements BasePresenter{

    private IPlayerData iPlayerData;
    private IPlayerView iPlayerView;
    private Handler handler=new Handler();

    public PlayerPresenter(IPlayerView iPlayerView) {
        this.iPlayerView = iPlayerView;
        iPlayerData=new IPlayerDataImpl();
    }

    public void start(int id){
        iPlayerData.getData(id, new OnLrcCallBack() {
            @Override
            public void onFailed(final String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iPlayerView.getErrorMsg(msg);
                    }
                });
            }

            @Override
            public void onSuccessful(final List<LrcBean> list) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iPlayerView.initLrcView(list);
                    }
                });
            }
        });
    }
    @Override
    public void start() {
        iPlayerView.setMusicInfo();
        iPlayerView.setSeekBarInfo();
    }
}
