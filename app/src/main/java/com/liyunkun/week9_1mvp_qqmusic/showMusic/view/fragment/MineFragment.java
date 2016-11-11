package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liyunkun.week9_1mvp_qqmusic.BaseFragment;
import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.localMusic.view.LocalMusicActivity;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MyPlayListBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.UserBean;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.activity.LoginActivity;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.activity.MyMusicListActivity;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter.MineFgLvAdapter;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.view.intf.IMineView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener,IMineView {

    private ListView mLvPlayList;
    private TextView mMyPlayListCount;
    private List<MyPlayListBean> listBeen;
    private RelativeLayout mMyMusicListLayout;
    private LinearLayout mLocalMusicLayout;
    private RelativeLayout mLoginLayout;
    private TextView mUserName;
    private ImageView mUserFace;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fg_layout, container, false);
        initView(view);
        listBeen = new ArrayList<>();
        listBeen.add(new MyPlayListBean(null, "默认列表", 0, 0));
        MineFgLvAdapter adapter = new MineFgLvAdapter(listBeen, getActivity());
        mLvPlayList.setAdapter(adapter);
        setPlayListCount();
        mLvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), listBeen.get(position).getPlayListName(), Toast.LENGTH_SHORT).show();
            }
        });
        mMyMusicListLayout.setOnClickListener(this);
        mLocalMusicLayout.setOnClickListener(this);
        mLoginLayout.setOnClickListener(this);
        return view;
    }

    private void setPlayListCount() {
        mMyPlayListCount.setText(listBeen.size() + "");
    }


    private void initView(View view) {
        mLvPlayList = ((ListView) view.findViewById(R.id.lv_play_list));
        mMyPlayListCount = ((TextView) view.findViewById(R.id.my_play_list_count));
        mMyMusicListLayout = ((RelativeLayout) view.findViewById(R.id.my_music_list_layout));
        mLocalMusicLayout = ((LinearLayout) view.findViewById(R.id.local_music_layout));
        mLoginLayout = ((RelativeLayout) view.findViewById(R.id.login_layout));
        mUserName = ((TextView) view.findViewById(R.id.tv_user_name));
        mUserFace = ((ImageView) view.findViewById(R.id.iv_user_face));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.local_music_layout:
                startActivity(new Intent(getActivity(), LocalMusicActivity.class));
                break;
            case R.id.my_music_list_layout:
                startActivity(new Intent(getActivity(), MyMusicListActivity.class));
                break;
            case R.id.login_layout:
            {
                String userName = mUserName.getText().toString();
                if("请登录".equals(userName)){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else{

                }
            }
                break;
        }
    }

    @Override
    public void initUserInfo(UserBean userBean) {
        if (userBean.getUserName() != null && !"".equals(userBean.getUserName())) {
            mUserName.setText(userBean.getUserName());
            Picasso.with(getActivity()).load(userBean.getUserFace()).into(mUserFace);
        }else{
            mUserName.setText("请登录");
            mUserFace.setImageResource(R.drawable.defualt);
        }
    }
}
