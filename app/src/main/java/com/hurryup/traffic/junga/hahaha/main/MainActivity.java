package com.hurryup.traffic.junga.hahaha.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.RouteActivity;

public class MainActivity extends BaseActivity {

    private EditText et_start ;
    private EditText et_end ;

    private String start;
    private String end;
    private String startGpsX, startGpsY;
    private String endGpsX, endGpsY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("123","MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMAIN");
        et_start = (EditText)findViewById(R.id.et_start);
        et_end =(EditText)findViewById(R.id.et_end);
        Button bnt_startSearch=(Button)findViewById(R.id.bnt_startSearch);
        bnt_startSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                start = et_start.getText().toString();

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
                end =et_end.getText().toString();

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

                start = et_start.getText().toString();
                end = et_end.getText().toString();
                et_start.setText(end);
                et_end.setText(start);
            }
        });

//-------------------------------------------- 데이터 받아오는 핸들러 --------------------------------------------
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case 0 :
                        LocationData result1 = (LocationData)msg.obj;
                        startGpsX = result1.gpsX;
                        startGpsY = result1.gpsY;

//                        System.out.println("startGpsX1 : " + startGpsX + " startGpsY1 : " + startGpsY);
                        break;
                    case 1 :
                        LocationData result2 = (LocationData)msg.obj;
                        endGpsX = result2.gpsX;
                        endGpsY = result2.gpsY;

//                        System.out.println("startGpsX2 : " + startGpsX + " startGpsY2 : " + startGpsY);
//                        System.out.println("endGpsX : " + endGpsX + " endGpsY : " + endGpsY);

                        Intent intent = new Intent(MainActivity.this, RouteActivity.class);
                        intent.putExtra("start", start);
                        intent.putExtra("end", end);
                        intent.putExtra("startGpsX", startGpsX);
                        intent.putExtra("startGpsY", startGpsY);
                        intent.putExtra("endGpsX", endGpsX);
                        intent.putExtra("endGpsY", endGpsY);
//                        System.out.println("start : " + start);
//                        System.out.println("end : " + end);
//                        System.out.println("startGpsX : " + startGpsX);
//                        System.out.println("startGpsY : " + startGpsY);
//                        System.out.println("endGpsX : " + endGpsX);
//                        System.out.println("endGpsY : " + endGpsY);
                        startActivity(intent);
                        break;

                }
            }
        };
//-------------------------------------------- 데이터 받아오는 핸들러 --------------------------------------------

        Button bnt_routeSearch=(Button)findViewById(R.id.bnt_routeSearch);
        bnt_routeSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                start = et_start.getText().toString();
                end = et_end.getText().toString();
//                Intent intent = new Intent(getApplication(),RouteActivity.class);
//                intent.putExtra("start",start);
//                intent.putExtra("end",end);
//                startActivity(intent);

                start = "선유도역 9호선";
                LocationThreadData td1 = new LocationThreadData(start, handler, 0);
                td1.setDaemon(true);
                td1.start();
                try {
                    td1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                end = "혜화역 4호선";
                LocationThreadData td2 = new LocationThreadData(end, handler, 1);
                td2.setDaemon(true);
                td2.start();
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
