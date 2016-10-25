package com.hurryup.traffic.junga.hahaha.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.search.Searcher;
import com.hurryup.traffic.junga.hahaha.search.Item;
import com.hurryup.traffic.junga.hahaha.search.OnFinishSearchListener;
import com.mommoo.library.toolkit.MommooMarshmallowActivity;

import java.util.List;

public class ListSearchActivity extends BaseActivity {
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    private EditText et_search_start=null;
    private Button bnt_search_start;
    Handler handler;
    Searcher searcher;
    Context context;
    String dest;
    double latitude = 37.537229;
    double longitude = 127.005515;
    String apikey = "db03b9259cfb1b5d8942e4a7d0374139";
    int radius = 10000; // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 사용. meter 단위 (0 ~ 10000)
    int page = 1;
    public void search_Function(String dest){
        searcher = new Searcher();
        searcher.searchKeyword(context,dest, latitude, longitude, radius, page, apikey, new OnFinishSearchListener() {

            @Override
            public void onSuccess(final List<Item> itemList) {
                mAdapter = new ListViewAdapter(context);
                for (int i = 0; i < itemList.size(); i++) {
                    Item item = itemList.get(i);
                    Log.d("route",item.title);

                    mAdapter.addItem(
                            item.title,
                            item.address);
                }
                handler.sendEmptyMessage(1);
            }
            @Override
            public void onFail() {

            }
        });
    }

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        context = getApplicationContext();
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, 1);
        setContentView(R.layout.activity_listsearch);
        final Intent intent = getIntent();
        dest =intent.getExtras().getString("start");
        et_search_start=(EditText)findViewById(R.id.et_search_start);
        et_search_start.setText(dest);

        mListView = (ListView) findViewById(R.id.search_listView);
        mAdapter = new ListViewAdapter(this);



         handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mListView.setAdapter(mAdapter);
                Log.d("route","%%%%%%%%%%%%");
            }
        };
        search_Function(dest);
        bnt_search_start=(Button)findViewById(R.id.button);
        bnt_search_start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String dest = et_search_start.getText().toString();
                search_Function(dest);

                Toast.makeText(ListSearchActivity.this, "RRRRR", Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                ListData mData = mAdapter.mListData.get(position);
                Intent intent = new Intent(getApplication(),StartSearchActivity.class);
                intent.putExtra("start",mData.mTitle.toString());
                finish();
                startActivity(intent);
            }
        });
    }

    private void showToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void permissionGranted(int permissionIndex) {

    }

    @Override
    public void permissionDenied(boolean isShowDialog, int permissionIndex) {
        if(isShowDialog){
            Toast.makeText(ListSearchActivity.this, "앱의 위치 권한부여가 필요합니다.", Toast.LENGTH_SHORT).show();
            //퍼미션 다이얼로그가 떳는데, 거기서 거절을 누른경우
        }else{
            Toast.makeText(ListSearchActivity.this, "앱의 위치 권한부여가 필요합니다.", Toast.LENGTH_SHORT).show();
            //전에 퍼미션 다이얼로그에서 다시보지 않기를 눌러서 , 퍼미션 다이얼로그가 안뜨고, 거절상태인경우.
        }
    }

}
