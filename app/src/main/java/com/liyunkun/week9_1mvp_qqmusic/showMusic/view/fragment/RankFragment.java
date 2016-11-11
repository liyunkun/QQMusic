package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;
import com.liyunkun.week9_1mvp_qqmusic.R;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class RankFragment extends BaseFragment{

    private RankLeftFragment leftFragment;
    private RankRightFragment rightFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank_fg_layout, container, false);
        FragmentManager manager = getChildFragmentManager();
        leftFragment = ((RankLeftFragment) manager.findFragmentById(R.id.rank_left_fg));
        rightFragment = ((RankRightFragment) manager.findFragmentById(R.id.rank_right_fg));
        leftFragment.setRightFragment(rightFragment);
        return view;
    }
}
