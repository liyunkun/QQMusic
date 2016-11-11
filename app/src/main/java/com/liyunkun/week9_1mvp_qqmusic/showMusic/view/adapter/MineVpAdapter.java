package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class MineVpAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;

    public MineVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
