package com.hurryup.traffic.junga.hahaha.route;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.model.Bus;
import com.hurryup.traffic.junga.hahaha.model.RouteData;
import com.hurryup.traffic.junga.hahaha.model.Section;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {

    RecyclerView rv_route_result;
    TextView tv_route_result;

    public void init(){
        rv_route_result = (RecyclerView)findViewById(R.id.rv_route_list);
        tv_route_result = (TextView)findViewById(R.id.tv_route_startname);
    }
    public ArrayList<RouteData> getTestData(){
        ArrayList<RouteData> routeData_list = new ArrayList<RouteData>();
        ArrayList<Section> section_List = new ArrayList<>();

        Section s = new Section();
        Bus bus = new Bus("1","1234");
        s.setTransport(bus);
        s.setStart_name("ewha university");
        s.setEnd_name("junga middle school");
        s.setTime("10");
        section_List.add(s);

        RouteData data = new RouteData("AAAA","BBBB","30Min",section_List);
        routeData_list.add(data);

        return routeData_list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        //Initialize
        init();

        //Test Data
        ArrayList<RouteData> routeData_list = getTestData();

        rv_route_result.setLayoutManager(new LinearLayoutManager(this));
        rv_route_result.setAdapter(new RouteAdapter(this,routeData_list));

    }
}
