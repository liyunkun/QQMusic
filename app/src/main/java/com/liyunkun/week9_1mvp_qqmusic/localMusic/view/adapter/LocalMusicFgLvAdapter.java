package com.liyunkun.week9_1mvp_qqmusic.localMusic.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class LocalMusicFgLvAdapter extends BaseAdapter {
    private List<MusicBean> list;
    private Context context;
    private LayoutInflater inflater;

    public LocalMusicFgLvAdapter(List<MusicBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.local_music_fg_lv_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MusicBean musicBean = list.get(position);
        holder.musicName.setText(musicBean.getSongname());
        holder.artist.setText(musicBean.getSingername());
        return convertView;
    }

    class ViewHolder {
        TextView musicName;
        TextView artist;

        public ViewHolder(View itemView) {
            musicName = (TextView) itemView.findViewById(R.id.music_name);
            artist = (TextView) itemView.findViewById(R.id.artist);
        }

    }
}
