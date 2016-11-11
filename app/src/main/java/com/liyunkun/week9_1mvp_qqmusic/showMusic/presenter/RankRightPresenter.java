package com.liyunkun.week9_1mvp_qqmusic.showMusic.presenter;

import android.os.Handler;

import com.liyunkun.week9_1mvp_qqmusic.BasePresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl.IRankRightDataImpl;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.CallBack;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IRankRightData;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IRankRightView;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class RankRightPresenter implements BasePresenter{

   private IRankRightData iRankRightData;
    private IRankRightView iRankRightView;
    private Handler handler=new Handler();

    public RankRightPresenter(IRankRightView iRankRightView) {
        this.iRankRightView = iRankRightView;
        iRankRightData=new IRankRightDataImpl();
    }

    public void start(int id){
        iRankRightData.getData(id, new CallBack() {
            @Override
            public void onSuccessful(final List<MusicBean> list) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iRankRightView.getData(list);
                    }
                });
            }

            @Override
            public void onFailed(final String errorMsg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iRankRightView.getErrorMsg(errorMsg);
                    }
                });
            }
        });
    }
    @Override
    public void start() {

    }
}
