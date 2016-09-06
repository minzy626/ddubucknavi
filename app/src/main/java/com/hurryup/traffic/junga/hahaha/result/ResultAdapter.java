package com.hurryup.traffic.junga.hahaha.result;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.RouteData;
import com.hurryup.traffic.junga.hahaha.route.data.Section;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Transport;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

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
        section_list = routeData_result.getSectionList();
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View routeData_result = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_result,parent,false);
        return new ResultViewHolder(routeData_result);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        Section section = section_list.get(position);
        Transport trans = section.getTransport();

        if(trans instanceof Bus){
            Bus bus = (Bus)trans;
            //holder.tv_Trans.setText(bus.getBus_Number());
//            holder.iv_Trans.setImageResource(getImageURL(bus));
        }else if(trans instanceof Train){
            Train train = (Train)trans;
            //holder.tv_Trans.setText(train.getLine_Number());
//            holder.iv_Trans.setImageResource(getImageURL(train));
        }else{
            Walk walk = (Walk)trans;
        }

        holder.tv_Start.setText(section.getStart_name());
        holder.tv_End.setText(section.getEnd_name());
        holder.tv_Time.setText(section.getTime());
        holder.iv_Trans.setImageResource(R.drawable.image_walk);//여기
    }

    @Override
    public int getItemCount() {
        return section_list.size();
    }


}
