package com.liyunkun.week9_1mvp_qqmusic.localMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.presenter.LocalMusicPresenter;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.ILocalMusicView;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.adapter.LocalMusicFgLvAdapter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class LocalMusicFragment extends BaseFragment implements ILocalMusicView{

    private ListView mLv;
    private LocalMusicPresenter presenter=new LocalMusicPresenter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_music_fg_layout, container, false);
        initView(view);
        presenter.start(getActivity());
        return view;
    }

    private void initView(View view) {
        mLv = ((ListView) view.findViewById(R.id.lv));
    }

    @Override
    public void initData2Lv(final List<MusicBean> listBean) {
        PlayUtils.list=listBean;
        LocalMusicFgLvAdapter adapter=new LocalMusicFgLvAdapter(listBean,getActivity());
        mLv.setAdapter(adapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayUtils.current_position=position;
                PlayUtils.startService(getContext(),listBean.get(position),PlayUtils.PLAY);
            }
        });
    }
}
