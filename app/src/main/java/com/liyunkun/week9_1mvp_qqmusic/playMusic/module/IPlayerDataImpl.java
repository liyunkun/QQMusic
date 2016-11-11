package com.liyunkun.week9_1mvp_qqmusic.playMusic.module;

import com.liyunkun.week9_1mvp_qqmusic.utils.NetUtils;
import com.liyunkun.week9_1mvp_qqmusic.utils.PauseJson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public class IPlayerDataImpl implements IPlayerData{
    @Override
    public void getData(int id, final OnLrcCallBack onLrcCallBack) {
        Request request=new Request.Builder()
                .url(String.format(NetUtils.lrcHttpUrl,id))
                .build();
        NetUtils.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onLrcCallBack.onFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String json = response.body().string();
                    onLrcCallBack.onSuccessful(PauseJson.getData2(json));
                }
            }
        });
    }
}
