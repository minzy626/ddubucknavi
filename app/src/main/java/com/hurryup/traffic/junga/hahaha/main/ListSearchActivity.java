package com.hurryup.traffic.junga.hahaha.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.List;

public class ListSearchActivity extends AppCompatActivity{
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    private EditText et_search_start=null;
    private Button bnt_search_start;


    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_listsearch);
        final Intent intent = getIntent();
        String start =intent.getExtras().getString("start");
        et_search_start=(EditText)findViewById(R.id.et_search_start);
        et_search_start.setText(start);

        mListView = (ListView) findViewById(R.id.search_listView);
        mAdapter = new ListViewAdapter(this);
        Searcher searcher = new Searcher();
        //String query = et_search_start.getText().toString();
        double latitude = 37.537229;
        double longitude = 127.005515;
        int radius = 10000; // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 사용. meter 단위 (0 ~ 10000)
        int page = 1;
        String apikey = "db03b9259cfb1b5d8942e4a7d0374139";

        searcher.searchKeyword(getApplicationContext(),start, latitude, longitude, radius, page, apikey, new OnFinishSearchListener() {
            @Override
            public void onSuccess(final List<Item> itemList) {
                for (int i = 0; i < itemList.size(); i++) {
                    Item item = itemList.get(i);
                    mAdapter.addItem(
                            item.title,
                            item.address);
                }
                // mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFail() {
                showToast("API_KEY의 제한 트래픽이 초과되었습니다.");
            }
        });
        mListView.setAdapter(mAdapter);

        bnt_search_start=(Button)findViewById(R.id.button);
        bnt_search_start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String start = et_search_start.getText().toString();
                ListSearchActivity.this.finish();
                Intent intent = new Intent(ListSearchActivity.this,ListSearchActivity.class);
                intent.putExtra("start",start);
                startActivity(intent);

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                ListData mData = mAdapter.mListData.get(position);
                Intent intent = new Intent(getApplication(),StartSearchActivity.class);
                intent.putExtra("start",mData.mTitle.toString());
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



}