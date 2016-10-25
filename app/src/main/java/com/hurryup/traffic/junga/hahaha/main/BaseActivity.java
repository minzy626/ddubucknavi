package com.hurryup.traffic.junga.hahaha.main;

/**
 * Created by JUNGA on 2016-10-25.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.route.RouteActivity;
import com.mommoo.library.toolkit.MommooMarshmallowActivity;

public class BaseActivity extends MommooMarshmallowActivity {
    private static Typeface mTypeface;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        if (BaseActivity.mTypeface == null)
            BaseActivity.mTypeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicUltraLight.ttf");

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);
    }

    @Override
    public void permissionGranted(int permissionIndex) {

    }

    @Override
    public void permissionDenied(boolean isShowDialog, int permissionIndex) {
        if(isShowDialog){
            Toast.makeText(BaseActivity.this, "설정에서 이 앱의 위치 권한 부여가 필요합니다.", Toast.LENGTH_SHORT).show();
            //퍼미션 다이얼로그가 떳는데, 거기서 거절을 누른경우
        }else{
            Toast.makeText(BaseActivity.this, "설정에서 직접 이 앱의 위치 권한부여가 필요합니다.", Toast.LENGTH_SHORT).show();
            //전에 퍼미션 다이얼로그에서 다시보지 않기를 눌러서 , 퍼미션 다이얼로그가 안뜨고, 거절상태인경우.
        }
    }

    // 아까랑 같은 setGlobalFont
    static void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView)
                ((TextView)child).setTypeface(mTypeface);
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child);
        }
    }}