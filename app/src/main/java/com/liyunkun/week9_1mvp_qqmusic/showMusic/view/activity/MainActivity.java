package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.BaseActivity;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.playMusic.view.PlayActivity;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.presenter.MainPresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.receiver.MyReceiver;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter.MineVpAdapter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment.FindFragment;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment.MineFragment;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment.MusicFragment;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IMainView;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener {

    private ViewPager mVp;
    private List<Fragment> fragmentList;
    private RadioGroup mRg;
    private int currentPosition = 0;
    private RelativeLayout mPlayLayout;
    private TextView mMusicName;
    private ImageView mMusicFace;
    private ImageView mPlayer;
    private MainPresenter presenter = new MainPresenter(this);
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PlayUtils.START_RECEIVER);
        filter.addAction(PlayUtils.UPDATE_BOTTOM_MUSIC_MSG_ACTION);
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
        initFragment2Vp();
        setViewPager();
        presenter.loadData(PlayUtils.LOCALMUSICBEAN);
        mPlayLayout.setOnClickListener(this);
        mPlayer.setOnClickListener(this);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_mine:
                        mVp.setCurrentItem(0);
                        break;
                    case R.id.rb_music:
                        mVp.setCurrentItem(1);
                        break;
                    case R.id.rb_find:
                        mVp.setCurrentItem(2);
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
                ((RadioButton) mRg.getChildAt(currentPosition)).setTextColor(getResources().getColor(R.color.MainRbTextColor));
                ((RadioButton) mRg.getChildAt(position)).setTextColor(getResources().getColor(R.color.baise));
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData(PlayUtils.PLAYLISTMUSICBEAN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        presenter.saveData(PlayUtils.current_music);
    }

    private void initFragment2Vp() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MineFragment());
        fragmentList.add(new MusicFragment());
        fragmentList.add(new FindFragment());
    }

    private void setViewPager() {
        MineVpAdapter adapter = new MineVpAdapter(getSupportFragmentManager(), fragmentList);
        mVp.setAdapter(adapter);
    }

    private void initView() {
        mVp = ((ViewPager) findViewById(R.id.viewpager));
        mRg = ((RadioGroup) findViewById(R.id.rg));
        mPlayLayout = ((RelativeLayout) findViewById(R.id.play_layout));
        mMusicName = ((TextView) findViewById(R.id.music_name));
        mMusicFace = ((ImageView) findViewById(R.id.iv_music_face));
        mPlayer = ((ImageView) findViewById(R.id.player_btn));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_layout:
                startActivity(new Intent(this, PlayActivity.class));
                break;
            case R.id.player_btn:
                if (PlayUtils.CURRENT_START == PlayUtils.PLAY) {
                    mPlayer.setImageResource(R.drawable.landscape_player_btn_play_normal);
                    PlayUtils.startService(this, PlayUtils.current_music, PlayUtils.PAUSE);
                } else if (PlayUtils.CURRENT_START == PlayUtils.STOP) {
                    mPlayer.setImageResource(R.drawable.search_stop_btn);
                    PlayUtils.startService(this, PlayUtils.current_music, PlayUtils.PLAY);
                } else if (PlayUtils.CURRENT_START == PlayUtils.PAUSE) {
                    mPlayer.setImageResource(R.drawable.search_stop_btn);
                    PlayUtils.startService(this, PlayUtils.current_music, PlayUtils.PAUSE);
                }
                break;
        }
    }

    @Override
    public void updateMainBottom(MusicBean musicBean) {
        PlayUtils.current_music = musicBean;
        mMusicName.setText(musicBean.getSongname());
        String url = musicBean.getUrl();
        if (url.startsWith("http")) {
            Picasso.with(this).load(musicBean.getAlbumpic_small()).into(mMusicFace);
        }else{
            Bitmap bitmap = PlayUtils.getThumbnail(url);
            if (bitmap == null) {
                mMusicFace.setImageDrawable(getResources().getDrawable(R.drawable.defualt));
            } else {
                mMusicFace.setImageBitmap(bitmap);
            }
        }
        updatePlayerBtn();
    }

    @Override
    public void updatePlayerBtn2Main() {
        updatePlayerBtn();
    }

    private void updatePlayerBtn() {
        if (PlayUtils.CURRENT_START == PlayUtils.PLAY) {
            mPlayer.setImageResource(R.drawable.search_stop_btn);
        } else {
            mPlayer.setImageResource(R.drawable.landscape_player_btn_play_normal);
        }
    }
}
