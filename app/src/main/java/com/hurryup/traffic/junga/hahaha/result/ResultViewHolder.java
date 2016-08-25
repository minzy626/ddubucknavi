package com.hurryup.traffic.junga.hahaha.result;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.R;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder{

  //  public TextView tv_Trans;
    public TextView tv_Start;
    public TextView tv_End;
    public TextView tv_Time;
    public ImageView iv_Trans;


    public ResultViewHolder(View view){
        super(view);

        //tv_Trans=(TextView)view.findViewById(R.id.tv_transportation);
        tv_Start=(TextView)view.findViewById(R.id.tv_Startname);
        tv_End=(TextView)view.findViewById(R.id.tv_Endname);
        tv_Time=(TextView)view.findViewById(R.id.tv_time);
        iv_Trans=(ImageView)view.findViewById(R.id.iv_transportation);
    }
}
