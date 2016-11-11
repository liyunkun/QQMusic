package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.RankLeftBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.presenter.RankLeftPresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IRankLeftView;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class RankLeftFragment extends BaseFragment implements IRankLeftView{

    private LinearLayout mLayout;
    private RankLeftPresenter presenter=new RankLeftPresenter(this);
    private RankRightFragment rightFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank_left_fg_layout, container, false);
        initView(view);
        presenter.start();
        return view;
    }

    private void initView(View view) {
        mLayout = ((LinearLayout) view.findViewById(R.id.layout));
    }

    @Override
    public void getData(final List<RankLeftBean> leftBeen) {
        for (int i = 0; i < leftBeen.size(); i++) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.rank_left_layout_item, mLayout, false);
            TextView tv = ((TextView) view.findViewById(R.id.tv));
            tv.setText(leftBeen.get(i).getName());
            mLayout.addView(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateTextColor(finalI);
                    if (rightFragment!=null) {
                        rightFragment.updateRv(leftBeen.get(finalI).getId());
                    }
                }
            });
        }
        View view = mLayout.getChildAt(0);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        View viewById = view.findViewById(R.id.view);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary2));
        viewById.setVisibility(View.VISIBLE);
    }

    private void updateTextColor(int finalI) {
        int count = mLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i==finalI){
                View view = mLayout.getChildAt(i);
                TextView tv = (TextView) view.findViewById(R.id.tv);
                View viewById = view.findViewById(R.id.view);
                tv.setTextColor(getResources().getColor(R.color.colorPrimary2));
                viewById.setVisibility(View.VISIBLE);
            }else{
                View view = mLayout.getChildAt(i);
                TextView tv = (TextView) view.findViewById(R.id.tv);
                View viewById = view.findViewById(R.id.view);
                tv.setTextColor(getResources().getColor(R.color.tvColor));
                viewById.setVisibility(View.GONE);
            }
        }
    }

    public void setRightFragment(RankRightFragment rightFragment) {
        this.rightFragment = rightFragment;
    }
}
