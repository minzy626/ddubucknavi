package com.hurryup.traffic.junga.hahaha.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class ListSearchActivity_end extends AppCompatActivity{
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    private EditText et_search_end=null;
    private Button bnt_search_end;


    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_listsearch_end);
        final Intent intent = getIntent();
        String end =intent.getExtras().getString("end");
        et_search_end=(EditText)findViewById(R.id.et_search_end);
        et_search_end.setText(end);

        mListView = (ListView) findViewById(R.id.search_end_listView);
        mAdapter = new ListViewAdapter(this);
        Searcher searcher = new Searcher();
        //String query = et_search_start.getText().toString();
        double latitude = 37.537229;
        double longitude = 127.005515;
        int radius = 10000; // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 사용. meter 단위 (0 ~ 10000)
        int page = 1;
        String apikey = "db03b9259cfb1b5d8942e4a7d0374139";

        searcher.searchKeyword(getApplicationContext(),end, latitude, longitude, radius, page, apikey, new OnFinishSearchListener() {
            @Override
            public void onSuccess(final List<Item> itemList) {
                for (int i = 0; i < itemList.size(); i++) {
                    Item item = itemList.get(i);
                    mAdapter.addItem(
                            item.title,
                            item.address);
                }
            }
            @Override
            public void onFail() {
                showToast("API_KEY의 제한 트래픽이 초과되었습니다.");
            }
        });

        mListView.setAdapter(mAdapter);

        bnt_search_end=(Button)findViewById(R.id.endbutton);
        bnt_search_end.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String end = et_search_end.getText().toString();
                ListSearchActivity_end.this.finish();
                Intent intent = new Intent(ListSearchActivity_end.this,ListSearchActivity.class);
                intent.putExtra("end",end);
                startActivity(intent);

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                ListData mData = mAdapter.mListData.get(position);
                Intent intent = new Intent(getApplication(),EndSearchActivity.class);
                intent.putExtra("end",mData.mTitle.toString());
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
