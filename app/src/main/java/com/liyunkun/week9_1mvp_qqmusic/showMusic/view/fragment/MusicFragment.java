package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter.MusicFgVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class MusicFragment extends BaseFragment{

    private ViewPager mVp;
    private TabLayout mTabLayout;
    private String[] titles;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fg_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mVp = ((ViewPager) view.findViewById(R.id.viewpager));
        mTabLayout = ((TabLayout) view.findViewById(R.id.tablayout));
        initData2Vp();
        initAdapter2Vp();
    }

    private void initAdapter2Vp() {
        MusicFgVpAdapter adapter=new MusicFgVpAdapter(getChildFragmentManager(),list,titles);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initData2Vp() {
        titles = new String[]{"精选","排行","歌单","电台","MV"};
        list=new ArrayList<>();
        list.add(new SelectFragment());
        list.add(new RankFragment());
        list.add(new PlayListFragment());
        list.add(new RadioStationFragment());
        list.add(new MVFragment());
    }
}
