package com.hurryup.traffic.junga.hahaha.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.hurryup.traffic.junga.hahaha.R;
import com.hurryup.traffic.junga.hahaha.main.MainActivity;

public class SplashActivity extends Activity{
    protected void onCreate(Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.activity_splash);
        ImageView img = (ImageView)findViewById(R.id.splash_image);

        TranslateAnimation ani = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 4,
                Animation.RELATIVE_TO_SELF, -4,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        ani.setFillAfter(true); // 애니메이션 후 이동한좌표에
        ani.setDuration(2000); //지속시간

        img.startAnimation(ani);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_intro=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent_intro);
                finish();
            }
        },2000);

    }
}
