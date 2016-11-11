package com.liyunkun.week9_1mvp_qqmusic.localMusic.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.adapter.LocalVpAdapter;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.fragment.LocalArtistFragment;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.fragment.LocalFolderFragment;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.fragment.LocalMusicFragment;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.fragment.LocalSpecialFragment;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp;
    private TabLayout mTabLayout;
    private ImageView mIvBack;
    private String[] titles;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();
        initData();
        setAdapter2Vp();
        mIvBack.setOnClickListener(this);
    }

    private void setAdapter2Vp() {
        LocalVpAdapter adapter=new LocalVpAdapter(getSupportFragmentManager(),list,titles);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initData() {
        titles = new String[]{"歌曲","歌手","专辑","文件夹"};
        list = new ArrayList<>();
        list.add(new LocalMusicFragment());
        list.add(new LocalArtistFragment());
        list.add(new LocalSpecialFragment());
        list.add(new LocalFolderFragment());
    }

    private void initView() {
        mVp = ((ViewPager) findViewById(R.id.viewpager));
        mTabLayout = ((TabLayout) findViewById(R.id.tablayout));
        mIvBack = ((ImageView) findViewById(R.id.goback));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goback:
                LocalMusicActivity.this.finish();
                break;
        }
    }
}
