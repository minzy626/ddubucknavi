package com.hurryup.traffic.junga.hahaha.route;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.route.RouteThreadData;

import com.hurryup.traffic.junga.hahaha.R;

public class RouteActivity extends AppCompatActivity {

    RecyclerView rv_route_result;
    TextView tv_route_startname;
    TextView tv_route_endname;
    String start, end, startGpsX, startGpsY, endGpsX, endGpsY;

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

    }
//    public ArrayList<RouteData> getTestData(){
//        ArrayList<RouteData> routeData_list = new ArrayList<RouteData>();
//        ArrayList<Section> section_List = new ArrayList<>();
//
//        Section s = new Section();
//        Bus bus = new Bus("1","1234");
//        s.setTransport(bus);
//        s.setStart_name("ewha university");
//        s.setEnd_name("junga middle school");
//        s.setTime("10");
//        section_List.add(s);
//
//        RouteData data = new RouteData("AAAA","BBBB","30Min",section_List);
//        routeData_list.add(data);
//
//        return routeData_list;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        //Initialize
        init();

        start = start.replaceAll(" ", "+");
        end = end.replaceAll(" ", "+");

        RouteThreadData td1 = new RouteThreadData(start, end, startGpsX, startGpsY, endGpsX, endGpsY);
        td1.setDaemon(true);
        td1.start();

        //Test Data
//        ArrayList<RouteData> routeData_list = getTestData();

        rv_route_result.setLayoutManager(new LinearLayoutManager(this));
//        rv_route_result.setAdapter(new RouteAdapter(this,routeData_list));

    }
}
