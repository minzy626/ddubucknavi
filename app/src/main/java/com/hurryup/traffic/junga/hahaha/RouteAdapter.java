package com.hurryup.traffic.junga.hahaha;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {


    ArrayList<RouteData> list;
    Context context;

    public RouteAdapter(Context context, ArrayList<RouteData> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public RouteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_route,parent,false);
        MyViewHolder holder;
        holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RouteAdapter.MyViewHolder holder, final int position) {

        holder.tv_route_start.setText(list.get(position).start);
        holder.tv_route_end.setText(list.get(position).end);
        holder.tv_route_time.setText(list.get(position).time);

        holder.route_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent route_intent = new Intent(context, ResultActivity.class);
                RouteData routeData = list.get(position);
                route_intent.putExtra("RouteData",routeData);
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
