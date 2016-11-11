package com.liyunkun.week9_1mvp_qqmusic.localMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class LocalArtistFragment extends BaseFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv=new TextView(getActivity());
        tv.setText("LocalArtistFragment");
        return tv;
    }
}
