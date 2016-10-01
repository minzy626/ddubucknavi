package com.hurryup.traffic.junga.hahaha.route;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.RouteData;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.data.Section;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Transport;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {

    RecyclerView rv_route_result;
    TextView tv_route_startname;
    TextView tv_route_endname;
    TextView tv_route_distance;
    TextView tv_route_time;
    TextView tv_route_payment;
    String start, end, startGpsX, startGpsY, endGpsX, endGpsY;
    GetImageURL getImagetURL;
    LinearLayout ll_firstLine;
    ProgressBar pb_route;
    public void init(){
        System.out.println("RouteActivity");
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

        pb_route = (ProgressBar)findViewById(R.id.pb_route);
        ll_firstLine = (LinearLayout)findViewById(R.id.ll_firstline);

    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        getImagetURL = new GetImageURL(this);



        //Initialize
        init();

        start = start.replaceAll(" ", "+");
        end = end.replaceAll(" ", "+");





        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ArrayList<RouteData> result = (ArrayList)msg.obj;

                //first line
                RouteData routeData = result.get(0);
                ArrayList<Section> sList = routeData.getSectionList();

                //init
                tv_route_distance.setText(routeData.getTotalDistance()/1000+"km");
                tv_route_time.setText("약"+routeData.getTotalTime()+"분");
                tv_route_payment.setText(routeData.getPayment()+"원");
                for(int i =0; i<sList.size();i++){
                    Section section = sList.get(i);


                    TextView tv = new TextView(getApplicationContext());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    tv.setLayoutParams(lp);

                    ImageView iv = new ImageView(getApplicationContext());
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(20,20);
                    iv.setLayoutParams(lp2);

                    Transport transport = section.getTransport();
                    StringBuilder url = new StringBuilder();
                    url.append("img_");
                    if(transport instanceof Bus){
                        url.append("bus");
                        int resource = getImagetURL.getImagetURL((Bus)transport);
                        iv.setImageResource(R.drawable.img_bus_1);
                    }else if(transport instanceof Train){
                        url.append("train");
                        int resource = getImagetURL.getImagetURL((Train)transport);
                        iv.setImageResource(R.drawable.img_bus_1);
                    }else if(transport instanceof Walk){
                        url.append("walk");
                        int resource = getImagetURL.getImagetURL((Walk)transport);
                        iv.setImageResource(R.drawable.img_bus_1);
                    }else{
                        iv.setImageResource(R.drawable.img_bus_4);
                    }
                    tv.setText(section.getStart_name());
                    tv.setTextColor(Color.BLACK);
                    ll_firstLine.addView(iv);
                    ll_firstLine.addView(tv);
                }
                result.remove(0);
                rv_route_result.setAdapter(new RouteAdapter(getApplicationContext(), result));
                pb_route.setVisibility(View.INVISIBLE);
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
