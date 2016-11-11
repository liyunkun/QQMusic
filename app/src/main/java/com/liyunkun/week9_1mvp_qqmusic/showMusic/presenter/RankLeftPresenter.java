package com.liyunkun.week9_1mvp_qqmusic.showMusic.presenter;

import com.liyunkun.week9_1mvp_qqmusic.BasePresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl.IRankLeftDataImpl;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IRankLeftData;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IRankLeftView;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class RankLeftPresenter implements BasePresenter {

    private IRankLeftData iRankLeftData;
    private IRankLeftView iRankLeftView;

    public RankLeftPresenter(IRankLeftView iRankLeftView) {
        this.iRankLeftView = iRankLeftView;
        iRankLeftData=new IRankLeftDataImpl();
    }

    @Override
    public void start() {
        iRankLeftView.getData(iRankLeftData.getData());
    }
}
