package com.hurryup.traffic.junga.hahaha;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder{

    public TextView tvTrans;
    public TextView tvStart;
    public TextView tvEnd;
    public TextView tvTime;
    public ImageView ivTrans;

    public ResultViewHolder(View view){
        super(view);

        this.tvTrans=(TextView)view.findViewById(R.id.tv_transportation);
        this.tvStart=(TextView)view.findViewById(R.id.tv_Startname);
        this.tvEnd=(TextView)view.findViewById(R.id.tv_Endname);
        this.tvTime=(TextView)view.findViewById(R.id.tv_time);
        this.ivTrans=(ImageView)view.findViewById(R.id.iv_transportation);
    }
}
