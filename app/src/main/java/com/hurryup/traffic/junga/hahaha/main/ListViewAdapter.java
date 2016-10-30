package com.hurryup.traffic.junga.hahaha.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hurryup.traffic.junga.hahaha.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2016-08-20.
 */
public class ListViewAdapter extends BaseAdapter{

    private Context mContext = null;
    public ArrayList<ListData> mListData = new ArrayList<ListData>();
    private ListViewAdapter mAdapter = null;

    public ListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position 리스트뷰의 순서, convertView 한 아이템의 화면
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, null);

            holder.mText = (TextView) convertView.findViewById(R.id.mText);
            holder.mDate = (TextView) convertView.findViewById(R.id.mDate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ListData mData = mListData.get(position);


        holder.mText.setText(mData.mTitle);
        holder.mDate.setText(mData.mDate);
        holder.mDate.setTextColor(Color.GRAY);

        Typeface tf1 = Typeface.createFromAsset(this.mContext.getAssets() , "fonts/NanumBarunGothicBold.otf" );
        Typeface tf2 = Typeface.createFromAsset(this.mContext.getAssets() , "fonts/NanumBarunGothic.otf" );

        holder.mText.setTypeface(tf1);
        holder.mDate.setTypeface(tf2);


        return convertView;
    }

    public void addItem(String mTitle, String mDate){
        ListData addInfo = null;
        addInfo = new ListData();
        addInfo.mTitle = mTitle;
        addInfo.mDate= mDate;

        mListData.add(addInfo);
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void sort(){
        Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
        dataChange();
    }

    public void dataChange(){
        mAdapter.notifyDataSetChanged();
    }


}

