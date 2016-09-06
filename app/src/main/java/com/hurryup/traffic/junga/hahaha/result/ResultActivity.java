package com.hurryup.traffic.junga.hahaha.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.hurryup.traffic.junga.hahaha.map.MapActivity;
import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.model.Code;
import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.RouteData;
import com.hurryup.traffic.junga.hahaha.route.data.Section;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class ResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView_result;
    private Button btn_result_map;
    private RouteData data;

    void init(){

        recyclerView_result = (RecyclerView)findViewById(R.id.recyclerview_result);
        btn_result_map = (Button)findViewById(R.id.btn_result_map);
        btn_result_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra(Code.ROUTE_DATA,data);
//                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        //data = (RouteData)intent.getSerializableExtra(Code.ROUTE_DATA);
        RouteData routeData = new RouteData();
        routeData.setStart("정아네집");
        routeData.setEnd("덕현오빠네집");
        routeData.setTotalTime("40");

        ArrayList<Section> list = new ArrayList();
        Section s1 = new Section();
        s1.setStart_name("정아네집");
        s1.setEnd_name("선유중학교");
        Walk walk1 = new Walk();
        s1.setTransport(walk1);
        s1.setTime("3");

        Section s = new Section();
        s.setStart_name("선유중학교");
        s.setEnd_name("경인교대입구");
        s.setTrafficType("bus");
        Bus bus = new Bus();
        bus.setBus_Number("1500");
        bus.setLine_number("3");
        s.setTransport(bus);
        s.setTime("37");

        Section s2 = new Section();
        s2.setStart_name("경인교대입구");
        s2.setEnd_name("덕현오빠네집");
        Walk walk = new Walk();
        s2.setTransport(walk);
        s2.setTime("1");

        list.add(s1);
        list.add(s);
        list.add(s2);

        routeData.setSectionList(list);

        data = routeData;

        init();

        recyclerView_result.setLayoutManager(new LinearLayoutManager(this));
        ResultAdapter adapter = new ResultAdapter(this,data);
        recyclerView_result.setAdapter(adapter);
        Button bnt_map=(Button)findViewById(R.id.btn_result_map);
//        bnt_map.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intent= new Intent(getApplicationContext(),MapActivity.class);
//                startActivity(intent);
//            }
//        });
    }


}
