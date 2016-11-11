package com.liyunkun.week9_1mvp_qqmusic.showMusic.module.impl;

import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.CallBack;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.intf.IRankRightData;
import com.liyunkun.week9_1mvp_qqmusic.utils.NetUtils;
import com.liyunkun.week9_1mvp_qqmusic.utils.PauseJson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class IRankRightDataImpl implements IRankRightData {
    @Override
    public void getData(int id, final CallBack callBack) {
        Request request=new Request.Builder()
                .url(String.format(NetUtils.httpUrl,id))
                .build();
        NetUtils.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccessful(PauseJson.getData(response.body().string()));
            }
        });
    }
}
