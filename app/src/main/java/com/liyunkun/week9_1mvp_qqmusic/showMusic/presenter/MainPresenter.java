package com.liyunkun.week9_1mvp_qqmusic.showMusic.presenter;

import android.content.Context;

import com.liyunkun.week9_1mvp_qqmusic.BasePresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl.IMainDataImpl;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IMainData;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IMainView;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;

/**
 * Created by liyunkun on 2016/10/20 0020.
 */
public class MainPresenter implements BasePresenter{
    private IMainData iMainData;
    private IMainView iMainView;
    private Context context;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        iMainData=new IMainDataImpl();
        context= (Context) iMainView;
    }

   public void saveData(MusicBean musicBean){
        iMainData.saveData(musicBean,context);
    }
    public void loadData(int type){
        if(type== PlayUtils.LOCALMUSICBEAN){
            iMainView.updateMainBottom(iMainData.loadData(context));
        }else if(type==PlayUtils.PLAYLISTMUSICBEAN){
            iMainView.updateMainBottom(PlayUtils.current_music);
        }
    }
    @Override
    public void start() {

    }
}
