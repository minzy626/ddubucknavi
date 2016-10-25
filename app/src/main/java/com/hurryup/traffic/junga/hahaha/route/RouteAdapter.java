package com.hurryup.traffic.junga.hahaha.route;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.model.Code;
import com.hurryup.traffic.junga.hahaha.result.ResultActivity;
import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.RouteData;
import com.hurryup.traffic.junga.hahaha.route.data.Section;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Transport;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {


    ArrayList<RouteData> list;
    Context context;
    GetImageURL getImageURL;
    public RouteAdapter(Context context, ArrayList<RouteData> list){
        this.list = list;
        this.context = context;
        getImageURL = new GetImageURL(context);
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
        Log.d("total","onBindViewHolder");
        RouteData routeData = list.get(position);

        ArrayList<Section> sectionList = routeData.getSectionList();
        Log.d("total","-----------------------------------------");
        Log.d("total","Test"+routeData.getBusStationCount());
        Log.d("total","Test"+routeData.getSubwayStationCount());

        Log.d("total","Position"+" "+position);
        Log.d("total","Section Count "+sectionList.size());
        setView(sectionList,holder);
        int busCount = routeData.getBusStationCount();
        int subwayCount = routeData.getSubwayStationCount();

        Log.d("total","Distance"+routeData.getTotalDistance());
        Log.d("total","PayMent"+routeData.getPayment());
        Log.d("total","BusCount "+busCount);
        Log.d("total","SubCount "+subwayCount);
        Log.d("total","SubCount "+subwayCount);

        StringBuilder str = new StringBuilder();
        if(busCount>0){
            str.append(" 버스정류장"+busCount);
        }
        if(subwayCount>0){
            str.append(" 지하철 역"+subwayCount);
        }
        holder.tv_route_start.setText(str.toString());
        NumberFormat nt = NumberFormat.getInstance();
        nt.setMaximumFractionDigits(2);
        double distance = routeData.getTotalDistance()/1000.0;

        //distance
        holder.tv_route_end.setText("약 "+nt.format(distance)+"Km");

        //time
        holder.tv_line_time.setText("약 "+routeData.getTotalTime()+"분");
        //pay
        holder.tv_line_payment.setText(routeData.getPayment() + " 원");

        if (routeData.getOrginalTotalTime() - Long.parseLong(routeData.getTotalTime()) > 0) {
            holder.tv_line_savetime.setText((routeData.getOrginalTotalTime() - Long.parseLong(routeData.getTotalTime())) +"분 정체");
        } else if (routeData.getOrginalTotalTime() - Long.parseLong(routeData.getTotalTime()) < 0) {
            holder.tv_line_savetime.setText((Long.parseLong(routeData.getTotalTime())-routeData.getOrginalTotalTime()) +"분 절약");
        } else {
            holder.tv_line_savetime.setText("");
        }

        //button
        holder.route_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent route_intent = new Intent(context, ResultActivity.class);
                route_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                RouteData routeData = list.get(position);
                route_intent.putExtra(Code.ROUTE_DATA,routeData);
                context.startActivity(route_intent);
            }
        });




    }
    public void setView(ArrayList<Section> sectionList, RouteAdapter.MyViewHolder holder){
        Log.d("setView","setView");
        Log.d("setView","count "+sectionList.size());
        holder.ll_route_line.removeAllViews();
        int sum = 0;
        for(int i=0;i<sectionList.size();i++){
            String startName = sectionList.get(i).getStart_name();
            Transport transport = sectionList.get(i).getTransport();

            int imgURL = getImageURL.getImagetURL(transport);
            if(transport instanceof Walk){
                continue;
            }


            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView arrow = new ImageView(context);
            arrow.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
            arrow.setImageResource(R.drawable.arrow2);
            layout.addView(arrow);

            TextView textView = new TextView(context);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(startName);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            textView.setTextColor(Color.BLACK);
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(50,50));

            imageView.setImageResource(imgURL);

            layout.addView(imageView);
            layout.addView(textView);
            layout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            Log.d("setView","width   "+width);
            Log.d("setView","sum   "+sum);
            if(sum>width-50){
                holder.ll_route_line.setOrientation(LinearLayout.VERTICAL);
                sum=0;
            }
            int count = holder.ll_route_line.getChildCount();
            if(count>0){

                LinearLayout childView= (LinearLayout)holder.ll_route_line.getChildAt(count-1);
                childView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                if(layout.getMeasuredWidth()+childView.getMeasuredWidth()<width-100){
                    childView.addView(layout);
                }else{
                    holder.ll_route_line.addView(layout);
                }
            }else{
                holder.ll_route_line.addView(layout);
            }


        }



    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_route_start;
        public TextView tv_route_end;
        public TextView tv_route_time;
        public TextView tv_line_time;
        TextView tv_line_payment;
        TextView tv_line_savetime;
        public ViewGroup route_btn;
        public LinearLayout ll_route_line;
        public CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.view_card);
            ll_route_line= (LinearLayout)itemView.findViewById(R.id.ll_routeline);
            tv_route_start = (TextView)itemView.findViewById(R.id.tv_line2_2);
            tv_route_end = (TextView)itemView.findViewById(R.id.tv_line2_3);
            tv_route_time = (TextView)itemView.findViewById(R.id.tv_line_savetime);
            route_btn = (ViewGroup)itemView.findViewById(R.id.layout_route_recyclerview);
            tv_line_time = (TextView)itemView.findViewById(R.id.tv_line_time);
            tv_line_payment = (TextView)itemView.findViewById(R.id.tv_line_payment);
            tv_line_savetime = (TextView)itemView.findViewById(R.id.tv_line_savetime);
        }


    }
}
