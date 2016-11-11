package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.presenter.RankRightPresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter.RvAdapter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IRankRightView;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class RankRightFragment extends BaseFragment implements IRankRightView{

    private RecyclerView mRv;
    private RankRightPresenter presenter=new RankRightPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank_right_fg_layout, container, false);
        initView(view);
        presenter.start(3);
        return view;
    }

    private void initView(View view) {
        mRv = ((RecyclerView) view.findViewById(R.id.rv));
    }

    @Override
    public void getData(final List<MusicBean> list) {
        PlayUtils.list=list;
        RvAdapter adapter=new RvAdapter(list,getActivity(),R.layout.rank_right_rv_item);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRv.setAdapter(adapter);
        adapter.setOnItemClick(new RvAdapter.OnItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                PlayUtils.current_position=position;
                PlayUtils.startService(getActivity(),list.get(position),PlayUtils.PLAY);
            }
        });
    }

    @Override
    public void getErrorMsg(String errorMsg) {

    }
    public void updateRv(int id){
        presenter.start(id);
    };
}
