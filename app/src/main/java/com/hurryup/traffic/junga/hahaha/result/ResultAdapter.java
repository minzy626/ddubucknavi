package com.hurryup.traffic.junga.hahaha.result;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.GetImageURL;
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
    GetImageURL getImagetURL;

    public ResultAdapter(Context context, RouteData routeData_result){
        this.routeData_result = routeData_result;
        this.context = context;
        section_list = routeData_result.getSectionList();
        getImagetURL = new GetImageURL(context);
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
        StringBuilder url = new StringBuilder();
        url.append("img_");

        if(trans instanceof Bus){
            Bus bus = (Bus)trans;
            url.append("bus");
            int resource = getImagetURL.getImagetURL(bus);
            holder.iv_Trans.setImageResource(R.drawable.img_bus_blue);
            holder.tv_Trans.setText(bus.getBus_Number() + "번 탑승");
            holder.tv_Start.setText(section.getStart_name() + " 정류소");
            holder.tv_End.setText(section.getEnd_name() + " 정류소 하차");
            //holder.tv_Trans.setText(bus.getBus_Number());
            //holder.iv_Trans.setImageResource(getImageURL(bus));
        }else if(trans instanceof Train){
            //String + "역" 분기처리 start

            Log.d("log : ", "log1");
            String start_name = section.getStart_name();
            String end_name = section.getStart_name();
            if(!start_name.endsWith("역")) start_name+="역 ";
            if(!end_name.endsWith("역")) end_name+="역 ";
            //String + "역" 분기처리 end

            Train train = (Train)trans;
            url.append("train");
            int resource = getImagetURL.getImagetURL(train);
            holder.iv_Trans.setImageResource(R.drawable.img_train_1);
            holder.tv_Trans.setText(train.getLine_number()+"호선 탑승");
            holder.tv_Start.setText(start_name);
            holder.tv_End.setText(end_name+" 하차");


            // holder.tv_Trans.setText(train.getLine_Number());
            // holder.iv_Trans.setImageResource(getImageURL(train));
        }else{
            Walk walk = (Walk)trans;
            url.append("walk");
            int resource = getImagetURL.getImagetURL(walk);
            holder.iv_Trans.setImageResource(R.drawable.image_walk);
        }

        //1지하철 2버스 3걷기

        if(section.getStart_name() != null && section.getStart_name() != "") {
            //holder.tv_Start.setText(section.getStart_name());
            //holder.tv_End.setText(section.getEnd_name());
            holder.tv_Time.setText("약 "+section.getTime()+"분");
        } else {
            holder.tv_Start.setText("걸어보아요");
            holder.tv_Time.setText("약 "+section.getTime()+"분");
            holder.tv_End.setText(section.getGuide());
            holder.iv_Trans.setImageResource(R.drawable.image_walk);
        }

        Transport transport = section.getTransport();
        int imgURL = getImagetURL.getImagetURL(transport);
        holder.iv_Trans.setImageResource(imgURL);
        //여기

        Typeface tf1 = Typeface.createFromAsset(this.context.getAssets() , "fonts/NanumBarunGothicBold.otf" );
        Typeface tf2 = Typeface.createFromAsset(this.context.getAssets() , "fonts/NanumBarunGothic.otf" );

        holder.tv_Start.setTypeface(tf1);
        holder.tv_Start.setTextColor(Color.parseColor("#5ec0c8"));
        holder.tv_Trans.setTypeface(tf2);
        holder.tv_Time.setTypeface(tf2);
        holder.tv_End.setTypeface(tf2);
    }

    @Override
    public int getItemCount() {
        return section_list.size();
    }


}