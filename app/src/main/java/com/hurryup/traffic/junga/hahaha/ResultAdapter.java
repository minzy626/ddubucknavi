package com.hurryup.traffic.junga.hahaha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{

    private ArrayList<Section> section_list;
    private Context context;
    private RouteData routeData_result;

    public ResultAdapter(Context context, RouteData routeData_result){
        this.routeData_result = routeData_result;
        this.context = context;
        section_list = routeData_result.section;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View routeData_result = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_result,parent,false);
        return new ResultViewHolder(routeData_result);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

       holder.tvStart.setText(section_list.get(position).tran_type);
       holder.tvEnd.setText(section_list.get(position).start_name);
       holder.tvTime.setText(section_list.get(position).end_name);
    }

    @Override
    public int getItemCount() {
        return section_list.size();
    }
}
