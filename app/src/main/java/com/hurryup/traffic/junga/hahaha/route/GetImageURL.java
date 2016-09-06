package com.hurryup.traffic.junga.hahaha.route;

import android.content.Context;

import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

/**
 * Created by lim on 16. 9. 3.
 */
public class GetImageURL {
    Context context;
    public GetImageURL(Context context){
        this.context = context;
    }
    public int getImagetURL(Bus bus){
        String url = "img_bus_"+bus.getLine_number();
        int imageResource
                = context.getResources().getIdentifier(url, "drawable",context.getPackageName());
        return imageResource;
    }
    public int getImagetURL(Train train){
        String url = "img_train_"+train.getLine_number();
        int imageResource
                = context.getResources().getIdentifier(url, "drawable",context.getPackageName());
        return imageResource;
    }
    public int getImagetURL(Walk walk){
        String url = "img_walk_";
        int imageResource
                = context.getResources().getIdentifier(url, "drawable",context.getPackageName());
        return imageResource;
    }

}
