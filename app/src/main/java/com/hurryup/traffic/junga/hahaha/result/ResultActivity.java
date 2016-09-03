package com.hurryup.traffic.junga.hahaha.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.main.MainActivity;
import com.hurryup.traffic.junga.hahaha.map.MapActivity;
import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.model.Code;
import com.hurryup.traffic.junga.hahaha.model.RouteData;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        data = (RouteData)intent.getSerializableExtra(Code.ROUTE_DATA);
        init();

        recyclerView_result.setLayoutManager(new LinearLayoutManager(this));
        ResultAdapter adapter = new ResultAdapter(this,data);
        recyclerView_result.setAdapter(adapter);
        Button bnt_map=(Button)findViewById(R.id.btn_result_map);
        bnt_map.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent= new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent);
            }
        });
    }


}
