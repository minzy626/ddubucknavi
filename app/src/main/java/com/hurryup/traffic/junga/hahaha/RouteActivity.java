package com.hurryup.traffic.junga.hahaha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {

    RecyclerView rv_route_result;
    TextView tv_route_result;

    public void init(){
        rv_route_result = (RecyclerView)findViewById(R.id.rv_route_list);
        tv_route_result = (TextView)findViewById(R.id.tv_route_result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        //Initialize
        init();

        //Test Data
        ArrayList<RouteData> routeData_list = new ArrayList<RouteData>();
        ArrayList<Section> section_List = new ArrayList<>();
        Section s = new Section();
        s.tran_type="BUS";
        s.start_name="RUACH";
        s.end_name="asdif;jaskdlf";
        section_List.add(s);

        RouteData data = new RouteData("AAAA","BBBB","30Min",section_List);
        routeData_list.add(data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        rv_route_result.setLayoutManager(linearLayoutManager);
        rv_route_result.setAdapter(new RouteAdapter(this,routeData_list));

    }
}
