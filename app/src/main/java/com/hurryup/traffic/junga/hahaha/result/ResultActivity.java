package com.hurryup.traffic.junga.hahaha.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.main.BaseActivity;
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
public class ResultActivity extends BaseActivity {

    private RecyclerView recyclerView_result;
    private Button btn_result_map;
    private RouteData data;
    private TextView tv_result_startname;



    void init(){

        recyclerView_result = (RecyclerView)findViewById(R.id.recyclerview_result);
        tv_result_startname = (TextView)findViewById(R.id.tv_result_Startname);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        data = (RouteData)intent.getSerializableExtra(Code.ROUTE_DATA);


        init();

        recyclerView_result.setLayoutManager(new LinearLayoutManager(this));
        ResultAdapter adapter = new ResultAdapter(this,data);
        recyclerView_result.setAdapter(adapter);

    }


}

