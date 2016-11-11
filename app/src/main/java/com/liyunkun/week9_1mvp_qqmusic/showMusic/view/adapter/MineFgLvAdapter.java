package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MyPlayListBean;

import java.util.List;


/**
 * Created by liyunkun on 2016/10/17 0017.
 */
public class MineFgLvAdapter extends BaseAdapter{
    private List<MyPlayListBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MineFgLvAdapter(List<MyPlayListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
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
        if(convertView == null){
            convertView=inflater.inflate(R.layout.mine_fg_lv_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(R.drawable.defualt);
        MyPlayListBean playListBean = list.get(position);
        holder.tv.setText(playListBean.getPlayListName());
        if(playListBean.getDownMusicNumber()>0){
            holder.tv2.setText(playListBean.getMusicNumber()+"首,"+ playListBean.getDownMusicNumber()+"首已下载");
        }else{
            holder.tv2.setText(playListBean.getMusicNumber()+"首");
        }
        return convertView;
    }
    private class ViewHolder{
        ImageView iv;
        TextView tv;
        TextView tv2;

        public ViewHolder(View itemView) {
            iv= (ImageView) itemView.findViewById(R.id.iv);
            tv= (TextView) itemView.findViewById(R.id.tv);
            tv2= (TextView) itemView.findViewById(R.id.tv2);
        }

    }
}
