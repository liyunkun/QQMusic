package com.liyunkun.week9_1mvp_qqmusic.localMusic.presenter;

import android.content.Context;

import com.liyunkun.week9_1mvp_qqmusic.BasePresenter;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.module.ILocalMusicModule;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.module.ILocalMusicModuleImpl;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.ILocalMusicView;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class LocalMusicPresenter implements BasePresenter{

    private ILocalMusicView iLocalMusicView;
    private ILocalMusicModule iLocalMusicModule;

    public LocalMusicPresenter(ILocalMusicView iLocalMusicView) {
        this.iLocalMusicView = iLocalMusicView;
        iLocalMusicModule=new ILocalMusicModuleImpl();
    }

    public void start(Context context){
        iLocalMusicView.initData2Lv(iLocalMusicModule.getDataFromMedia(context));
    }
    @Override
    public void start() {
    }
}
