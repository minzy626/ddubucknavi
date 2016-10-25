package com.hurryup.traffic.junga.hahaha.route;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.main.BaseActivity;
import com.hurryup.traffic.junga.hahaha.model.Code;
import com.hurryup.traffic.junga.hahaha.result.ResultActivity;
import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.RouteData;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.data.Section;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Transport;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;


import java.text.NumberFormat;
import java.util.ArrayList;

public class RouteActivity extends BaseActivity {
    Context context;
    RecyclerView rv_route_result;
    TextView tv_route_startname;
    TextView tv_route_endname;
    TextView tv_route_distance;
    TextView tv_route_time;
    TextView tv_route_payment;
    public static String start, end;
    String startGpsX, startGpsY, endGpsX, endGpsY;
    GetImageURL getImagetURL;
    LinearLayout ll_firstLine;
    LinearLayout ll_first;
    ProgressBar pb_route;
    ProgressBar pb_route_first;
    TextView tv_route_count;
    RouteData routeData;
    public void init(){
        Intent intent = getIntent();
        start =intent.getExtras().getString("start");
        end =intent.getExtras().getString("end");
        startGpsX = getIntent().getStringExtra("startGpsX");
        startGpsY = getIntent().getStringExtra("startGpsY");
        endGpsX = getIntent().getStringExtra("endGpsX");
        endGpsY = getIntent().getStringExtra("endGpsY");

        rv_route_result = (RecyclerView)findViewById(R.id.rv_route_list);
        tv_route_startname= (TextView)findViewById(R.id.tv_route_startname);
        tv_route_endname=(TextView)findViewById(R.id.tv_route_endname);
        tv_route_startname.setText(start);
        tv_route_endname.setText(end);
        tv_route_distance = (TextView)findViewById(R.id.tv_route_distance);
        tv_route_time = (TextView)findViewById(R.id.tv_route_time);
        tv_route_payment = (TextView)findViewById(R.id.tv_route_payment);
        tv_route_count = (TextView)findViewById(R.id.tv_route_count);
        pb_route = (ProgressBar)findViewById(R.id.pb_route);
        pb_route_first = (ProgressBar)findViewById(R.id.pb_route_first);
        ll_firstLine = (LinearLayout)findViewById(R.id.ll_firstline);
        ll_first = (LinearLayout)findViewById(R.id.ll_first);
        ll_first.setVisibility(View.INVISIBLE);

    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        getImagetURL = new GetImageURL(this);
        context = getApplicationContext();


        //Initialize
        init();

        start = start.replaceAll(" ", "+");
        end = end.replaceAll(" ", "+");

        ll_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent route_intent = new Intent(context, ResultActivity.class);
                route_intent.putExtra(Code.ROUTE_DATA,routeData);
                route_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(route_intent);
            }
        });
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ArrayList<RouteData> result = (ArrayList)msg.obj;

                //first line
                routeData = result.get(0);
                ArrayList<Section> sList = routeData.getSectionList();
                NumberFormat nt = NumberFormat.getInstance();
                nt.setMaximumFractionDigits(2);
                double distance = routeData.getTotalDistance()/1000.0;
                //init
                tv_route_distance.setText(nt.format(distance)+" Km");
                tv_route_time.setText("약 "+routeData.getTotalTime()+"분");
                tv_route_payment.setText(routeData.getPayment()+" 원");
                String bs_Count="";
                int subCount = routeData.getSubwayStationCount();
                if(subCount>0)bs_Count=bs_Count+"Sub "+subCount;
                int busCount = routeData.getBusStationCount();
                if(busCount>0)bs_Count=bs_Count+"Bus "+busCount;
                tv_route_count.setText(bs_Count);
                int sum = 0;
                for(int i =0; i<sList.size();i++){
                    Section section = sList.get(i);
                    Transport transport = section.getTransport();
                    if(transport instanceof Walk){
                        continue;
                    }

                    TextView tv = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    tv.setLayoutParams(lp);

                    ImageView iv = new ImageView(getApplicationContext());
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(50,50);
                    iv.setLayoutParams(lp2);

                    StringBuilder url = new StringBuilder();
                    url.append("img_");
                    if(transport instanceof Bus){
                        url.append("bus");
                        int resource = getImagetURL.getImagetURL(transport);
                        iv.setImageResource(resource);
                    }else if(transport instanceof Train){
                        url.append("train");
                        int resource = getImagetURL.getImagetURL(transport);
                        iv.setImageResource(resource);
                    }
                    tv.setText(section.getStart_name());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                    tv.setTextColor(Color.BLACK);

                    ImageView arrawImg = new ImageView(getApplicationContext());
                    arrawImg.setLayoutParams(lp2);
                    arrawImg.setImageResource(R.drawable.arrow2);
                    LinearLayout layout = new LinearLayout(context);
                    layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

                    layout.addView(arrawImg);
                    layout.addView(iv);
                    layout.addView(tv);

                    layout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    DisplayMetrics dm = context.getResources().getDisplayMetrics();
                    int width = dm.widthPixels;
                    Log.d("setView","width   "+width);
                    Log.d("setView","sum   "+sum);
                    if(sum>width-50){
                        ll_firstLine.setOrientation(LinearLayout.VERTICAL);
                        sum=0;
                    }
                    int count = ll_firstLine.getChildCount();
                    if(count>0){

                        LinearLayout childView= (LinearLayout)ll_firstLine.getChildAt(count-1);
                        childView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                        if(layout.getMeasuredWidth()+childView.getMeasuredWidth()<width-100){
                            childView.addView(layout);
                        }else{
                            ll_firstLine.addView(layout);
                        }
                    }else{
                        ll_firstLine.addView(layout);
                    }
                }










                result.remove(0);
                rv_route_result.setAdapter(new RouteAdapter(getApplicationContext(), result));
                pb_route.setVisibility(View.INVISIBLE);
                pb_route_first.setVisibility(View.GONE);
                ll_first.setVisibility(View.VISIBLE);
            }
        };

        RouteThreadData td1 = new RouteThreadData(start, end, startGpsX, startGpsY, endGpsX, endGpsY, handler);
        td1.setDaemon(true);
        td1.start();

        //Test Data
//        ArrayList<RouteData> routeData_list = getTestData();

        rv_route_result.setLayoutManager(new LinearLayoutManager(this));
    }
}
