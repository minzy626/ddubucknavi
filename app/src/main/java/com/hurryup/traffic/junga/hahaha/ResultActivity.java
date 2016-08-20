package com.hurryup.traffic.junga.hahaha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class ResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        RouteData data = (RouteData)intent.getSerializableExtra("RouteData");
        System.out.println(data);
        this.recyclerView_result = (RecyclerView)findViewById(R.id.recyclerview_result);
        this.recyclerView_result.setLayoutManager(new LinearLayoutManager(this));

        ResultAdapter adapter = new ResultAdapter(this,data);
        this.recyclerView_result.setAdapter(adapter);

    }
}
