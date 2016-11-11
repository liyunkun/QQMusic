package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter.MyMusicListVpAdapter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment.CollectionFragment;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment.CreateFragment;

import java.util.ArrayList;
import java.util.List;

public class MyMusicListActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp;
    private RadioGroup mRg;
    private ImageView mIvBack;
    private ImageView mIvRenew;
    private List<Fragment> list;
    private RadioButton mCreate;
    private RadioButton mCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music_list);
        initView();
        initData2Vp();
        setAdapter2Vp();
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_create:
                        mVp.setCurrentItem(0);
                        mCreate.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mCreate.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_music_list_rb_bg_left_check));
                        mCollection.setTextColor(getResources().getColor(R.color.baise));
                        mCollection.setBackgroundDrawable(null);
                        break;
                    case R.id.rb_collection:
                        mVp.setCurrentItem(1);
                        mCollection.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mCollection.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_music_list_rb_bg_right_check));
                        mCreate.setTextColor(getResources().getColor(R.color.baise));
                        mCreate.setBackgroundDrawable(null);
                        break;
                }
            }
        });
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mCreate.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mCreate.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_music_list_rb_bg_left_check));
                        mCollection.setTextColor(getResources().getColor(R.color.baise));
                        mCollection.setBackgroundDrawable(null);
                        break;
                    case 1:
                        mCollection.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mCollection.setBackgroundDrawable(getResources().getDrawable(R.drawable.my_music_list_rb_bg_right_check));
                        mCreate.setTextColor(getResources().getColor(R.color.baise));
                        mCreate.setBackgroundDrawable(null);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIvBack.setOnClickListener(this);
    }

    private void setAdapter2Vp() {
        MyMusicListVpAdapter adapter = new MyMusicListVpAdapter(getSupportFragmentManager(), list);
        mVp.setAdapter(adapter);
    }

    private void initData2Vp() {
        list = new ArrayList<>();
        list.add(new CreateFragment());
        list.add(new CollectionFragment());
    }

    private void initView() {
        mVp = ((ViewPager) findViewById(R.id.viewpager));
        mRg = ((RadioGroup) findViewById(R.id.rg));
        mIvBack = ((ImageView) findViewById(R.id.goback));
        mIvRenew = ((ImageView) findViewById(R.id.renew));
        mCreate = ((RadioButton) findViewById(R.id.rb_create));
        mCollection = ((RadioButton) findViewById(R.id.rb_collection));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goback:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
        }
    }
}
