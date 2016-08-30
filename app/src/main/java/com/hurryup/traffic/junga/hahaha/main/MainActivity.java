package com.hurryup.traffic.junga.hahaha.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.RouteActivity;

public class MainActivity extends AppCompatActivity {

    private EditText et_start ;
    private EditText et_end ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_start = (EditText)findViewById(R.id.et_start);
        et_end =(EditText)findViewById(R.id.et_end);
        Button bnt_startSearch=(Button)findViewById(R.id.bnt_startSearch);
        bnt_startSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String start =et_start.getText().toString();

                if(start.length()==0){
                    Toast.makeText(getApplicationContext(),"출발지를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),ListSearchActivity.class);
                    intent.putExtra("start",start);
                    startActivity(intent);
                }

            }
        });

        Button bnt_endSearch = (Button)findViewById(R.id.bnt_endSearch);
        bnt_endSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String end =et_end.getText().toString();

                if(end.length()==0){
                    Toast.makeText(getApplicationContext(),"도착지를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),ListSearchActivity_end.class);
                    intent.putExtra("end",end);
                    startActivity(intent);
                }
            }
        });

        Button bnt_change = (Button)findViewById(R.id.bnt_change);
        bnt_change.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String start = et_start.getText().toString();
                String end = et_end.getText().toString();
                et_start.setText(end);
                et_end.setText(start);
            }
        });
        Button bnt_routeSearch=(Button)findViewById(R.id.bnt_routeSearch);
        bnt_routeSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String start = et_start.getText().toString();
                String end = et_end.getText().toString();
                Intent intent = new Intent(getApplication(),RouteActivity.class);
                intent.putExtra("start",start);
                intent.putExtra("end",end);
                startActivity(intent);
            }
        });
    }


  /*  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //Intent intent=getIntent();
        //String start=intent.getExtras().getString("startplace");
        //String end=intent.getExtras().getString("end");
        et_start = (EditText)findViewById(R.id.et_start);
        et_end =(EditText)findViewById(R.id.et_end);
        showToast("되니??");
            switch (requestCode){
                case StartSearchActivity:
                    et_start.setText(data.getStringExtra("startplace"));
                    showToast(et_start.getText().toString());
                    break;
                case EndSearchActivity:
                    if(resultCode==RESULT_OK){
                        et_end.setText(data.getStringExtra("end"));
                    }else{}
                    break;

            }
        }*/

    protected void onResume(){
        super.onResume();
        /*Intent intent=getIntent();
        String start=intent.getExtras().getString("startplace");
        String end=intent.getExtras().getString("end");*/
        et_start = (EditText)findViewById(R.id.et_start);
        et_end =(EditText)findViewById(R.id.et_end);
        et_start.setText(com.hurryup.traffic.junga.hahaha.main.StartSearchActivity.departure);
        et_end.setText(com.hurryup.traffic.junga.hahaha.main.EndSearchActivity.arrival);
    }
    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
