package com.liyunkun.week9_1mvp_qqmusic.showMusic.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.week9_1mvp_qqmusic.R;
import com.liyunkun.week9_1mvp_qqmusic.showMusic.module.bean.MusicBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class RvAdapter extends RecyclerView.Adapter{
    private List<MusicBean> list;
    private Context context;
    private LayoutInflater inflater;
    private int layoutId;

    public RvAdapter(List<MusicBean> list, Context context, int layoutId) {
        this.list = list;
        this.context = context;
        this.layoutId = layoutId;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MusicBean musicBean = list.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Picasso.with(context).load(musicBean.getAlbumpic_small()).into(myViewHolder.iv);
        myViewHolder.tv.setText(musicBean.getSongname());
        myViewHolder.tv2.setText(musicBean.getSingername());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;
        TextView tv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            tv= (TextView) itemView.findViewById(R.id.tv);
            tv2= (TextView) itemView.findViewById(R.id.tv2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClick != null){
                        onItemClick.onItemClick(v,getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClick{
        void onItemClick(View v,int position);
    }
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
