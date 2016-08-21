package com.hurryup.traffic.junga.hahaha.route;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.result.ResultActivity;
import com.hurryup.traffic.junga.hahaha.model.RouteData;

import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {


    ArrayList<RouteData> list;
    Context context;
    public static final String ROUTE_DATA= "RouteData";
    public RouteAdapter(Context context, ArrayList<RouteData> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public RouteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_route,parent,false);
        MyViewHolder holder;
        holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RouteAdapter.MyViewHolder holder, final int position) {

        holder.tv_route_start.setText(list.get(position).getStart());
        holder.tv_route_end.setText(list.get(position).getEnd());
        holder.tv_route_time.setText(list.get(position).getTime());

        holder.route_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent route_intent = new Intent(context, ResultActivity.class);
                RouteData routeData = list.get(position);
                route_intent.putExtra(ROUTE_DATA,routeData);
                context.startActivity(route_intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_route_start;
        public TextView tv_route_end;
        public TextView tv_route_time;
        public ViewGroup route_btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_route_start = (TextView)itemView.findViewById(R.id.tv_route_start);
            tv_route_end = (TextView)itemView.findViewById(R.id.tv_route_end);
            tv_route_time = (TextView)itemView.findViewById(R.id.tv_route_time);
            route_btn = (ViewGroup)itemView.findViewById(R.id.layout_route_recyclerview);
        }


    }
}
