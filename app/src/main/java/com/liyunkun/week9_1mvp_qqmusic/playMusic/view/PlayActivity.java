package com.liyunkun.week9_1mvp_qqmusic.playMusic.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.liyunkun.lrcview.Bean.LrcBean;
import com.liyunkun.lrcview.LrcView;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.playMusic.presenter.PlayerPresenter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.liyunkun.week9_1mvp_qqmusic.utils.PlayUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class PlayActivity extends AppCompatActivity implements IPlayerView, View.OnClickListener {

    private LinearLayout mLeftLayout;
    private LinearLayout mRightLayout;
    private LrcView mLrcView;
    private PlayerPresenter presenter = new PlayerPresenter(this);
    private TextView mDrawerMusicName;
    private TextView mSingerName;
    private TextView mMusicName;
    private ImageView mMusicFace;
    private ImageView mPlayerBtn;
    private TextView current_time;
    private Handler mHandler;
    private TextView totalTime;
    private SeekBar seekBar;
    private SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    private ImageView mGoBack;
    private int current_type = PlayUtils.STOP;
    private ImageView mLastBtn;
    private ImageView mNextBtn;
    private ImageView mShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
        setWidth();
        presenter.start();
        mGoBack.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mLastBtn.setOnClickListener(this);
        mShare.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (PlayUtils.player != null) {
                        PlayUtils.player.seekTo(progress);
                    }
                }
                if (PlayUtils.current_music != null) {
                    int duration = PlayUtils.current_music.getSeconds() * 1000;
                    if (progress != 0) {
                        if (progress == duration) {
                            int current_position = PlayUtils.current_position;
                            List<MusicBean> list = PlayUtils.list;
                            if (current_position < list.size() - 1) {
                                int position = current_position + 1;
                                PlayUtils.current_position = position;
                                MusicBean musicBean = list.get(position);
                                PlayUtils.current_music = musicBean;
                                PlayUtils.startService(PlayActivity.this, musicBean, PlayUtils.PLAY);
                                presenter.start();
                            } else {
                                PlayUtils.current_position = 0;
                                MusicBean musicBean = list.get(0);
                                PlayUtils.current_music = musicBean;
                                PlayUtils.startService(PlayActivity.this, musicBean, PlayUtils.PLAY);
                                presenter.start();
                            }
                        }
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setSeekBarInfo() {
        if (PlayUtils.current_music != null) {
            int duration = PlayUtils.current_music.getSeconds() * 1000;
            seekBar.setMax(duration);
            totalTime.setText(format.format(new Date(duration)) + "");
            mHandler.sendEmptyMessage(0);
        }
    }

    @Override
    public void setMusicInfo() {
        MusicBean current_music = PlayUtils.current_music;
        mDrawerMusicName.setText(current_music.getSongname() + "-" + current_music.getSingername());
        mSingerName.setText(current_music.getSingername());
        mMusicName.setText(current_music.getSongname());
        presenter.start(current_music.getSongid());
        String albumpic_big = current_music.getAlbumpic_big();
        if (albumpic_big != null) {
            Picasso.with(this).load(albumpic_big).into(mMusicFace);
        }
        Bitmap bitmap = PlayUtils.getThumbnail(PlayUtils.current_music.getUrl());
        if (bitmap != null) {
            mMusicFace.setImageBitmap(bitmap);
        }
        mPlayerBtn.setOnClickListener(this);
        if (PlayUtils.CURRENT_START == PlayUtils.PLAY) {
            mPlayerBtn.setImageResource(R.drawable.live_song_list_pause_normal);
        } else if (PlayUtils.CURRENT_START == PlayUtils.STOP) {
            mPlayerBtn.setImageResource(R.drawable.live_song_list_play_normal);
        } else if (PlayUtils.CURRENT_START == PlayUtils.PAUSE) {
            mPlayerBtn.setImageResource(R.drawable.live_song_list_play_normal);
        }
    }

    private void setWidth() {
        ViewGroup.LayoutParams leftLp = mLeftLayout.getLayoutParams();
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        leftLp.width = widthPixels;
        mLeftLayout.requestLayout();
        ViewGroup.LayoutParams rightLp = mRightLayout.getLayoutParams();
        rightLp.width = widthPixels;
        mRightLayout.requestLayout();
    }

    private void initView() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0: {
                        if (PlayUtils.player != null) {
                            int position = PlayUtils.player.getCurrentPosition();
                            seekBar.setProgress(position);
                            current_time.setText(format.format(new Date(position)) + "");
                            sendEmptyMessageDelayed(0, 100);
                        }
                    }
                    break;
                }
            }
        };
        mLeftLayout = ((LinearLayout) findViewById(R.id.left_layout));
        mRightLayout = ((LinearLayout) findViewById(R.id.right_layout));
        mLrcView = ((LrcView) findViewById(R.id.lrc_view));
        mDrawerMusicName = ((TextView) findViewById(R.id.drawer_music_name));
        mSingerName = ((TextView) findViewById(R.id.singer_name));
        mMusicName = ((TextView) findViewById(R.id.music_name));
        mMusicFace = ((ImageView) findViewById(R.id.music_face));
        mPlayerBtn = ((ImageView) findViewById(R.id.player_btn));
        current_time = ((TextView) findViewById(R.id.current_time));
        totalTime = ((TextView) findViewById(R.id.total_time));
        seekBar = ((SeekBar) findViewById(R.id.seekBar));
        mGoBack = ((ImageView) findViewById(R.id.back));
        mLastBtn = ((ImageView) findViewById(R.id.last_btn));
        mNextBtn = ((ImageView) findViewById(R.id.next_btn));
        mShare = ((ImageView) findViewById(R.id.share));
    }

    @Override
    public void initLrcView(List<LrcBean> list) {
        mLrcView.setList(list);
        mLrcView.setPlayer(PlayUtils.player);
        mLrcView.update();
    }

    @Override
    public void getErrorMsg(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.player_btn: {
                initPlayerBtn();
            }
            break;
            case R.id.back: {
                PlayActivity.this.finish();
            }
            break;
            case R.id.last_btn: {
                int current_position = PlayUtils.current_position;
                List<MusicBean> list = PlayUtils.list;
                if (list != null) {
                    if (current_position > 0) {
                        int position = current_position - 1;
                        PlayUtils.current_position = position;
                        MusicBean musicBean = list.get(position);
                        PlayUtils.current_music = musicBean;
                        PlayUtils.startService(this, musicBean, PlayUtils.PLAY);
                        presenter.start();
                    } else {
                        PlayUtils.current_position = list.size() - 1;
                        MusicBean musicBean = list.get(list.size() - 1);
                        PlayUtils.current_music = musicBean;
                        PlayUtils.startService(this, musicBean, PlayUtils.PLAY);
                        presenter.start();
                    }
                }
            }
            break;
            case R.id.next_btn:
                int current_position = PlayUtils.current_position;
                List<MusicBean> list = PlayUtils.list;
                if (list != null) {
                    if (current_position < list.size() - 1) {
                        int position = current_position + 1;
                        PlayUtils.current_position = position;
                        MusicBean musicBean = list.get(position);
                        PlayUtils.current_music = musicBean;
                        PlayUtils.startService(this, musicBean, PlayUtils.PLAY);
                        presenter.start();
                    } else {
                        PlayUtils.current_position = 0;
                        MusicBean musicBean = list.get(0);
                        PlayUtils.current_music = musicBean;
                        PlayUtils.startService(this, musicBean, PlayUtils.PLAY);
                        presenter.start();
                    }
                }
                break;
            case R.id.share: {
                ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle(PlayUtils.current_music.getSongname());
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl(PlayUtils.current_music.getUrl());
                // text是分享文本，所有平台都需要这个字段
                oks.setText(PlayUtils.current_music.getSingername());
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl(PlayUtils.current_music.getAlbumpic_big());
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl(PlayUtils.current_music.getUrl());
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment(PlayUtils.current_music.getSongname());
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(PlayUtils.current_music.getSongname());
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl(PlayUtils.current_music.getUrl());

// 启动分享GUI
                oks.show(this);
            }
            break;
        }
    }

    private void initPlayerBtn() {
        if (PlayUtils.CURRENT_START == PlayUtils.PLAY) {
            mPlayerBtn.setImageResource(R.drawable.live_song_list_play_normal);
            PlayUtils.startService(this, PlayUtils.current_music, PlayUtils.PAUSE);
        } else if (PlayUtils.CURRENT_START == PlayUtils.STOP) {
            mPlayerBtn.setImageResource(R.drawable.live_song_list_pause_normal);
            PlayUtils.startService(this, PlayUtils.current_music, PlayUtils.PLAY);
        } else if (PlayUtils.CURRENT_START == PlayUtils.PAUSE) {
            mPlayerBtn.setImageResource(R.drawable.live_song_list_pause_normal);
            PlayUtils.startService(this, PlayUtils.current_music, PlayUtils.PAUSE);
        }
    }
}
