package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class MusicFgVpAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;
    private String[] titles;

    public MusicFgVpAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
