package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class FindFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv=new TextView(getActivity());
        tv.setText("FindFragment");
        return tv;
    }
}
