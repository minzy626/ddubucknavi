package com.hurryup.traffic.junga.hahaha.route;

import android.content.Context;

import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Transport;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

/**
 * Created by lim on 16. 9. 3.
 */
public class GetImageURL {
    Context context;
    public GetImageURL(Context context){
        this.context = context;
    }
    public int getImagetURL(Transport transport) {
        String url="";
        if (transport instanceof Bus) {
            switch (((Bus) transport).getLine_number()) {
                case "12":
                case "3":
                    url = "img_bus_" + "green";
                    break;
                case "5":
                    url = "img_bus_" + "airport";
                    break;
                case "11":
                    url = "img_bus_" + "blue";
                    break;
                case "13":
                    url = "img_bus_" + "yellow";
                    break;
                case "14":
                    url = "img_bus_" + "red";
                    break;
            }
        }else if(transport instanceof Train){
            url = "img_train_"+((Train)transport).getLine_number();
        }else{
            url = "image_walk";
        }
        int imageResource
                = context.getResources().getIdentifier(url, "drawable", context.getPackageName());

        return imageResource;
    }
//    public int getImagetURL(Bus bus){
//        String url = "img_bus_"+bus.getLine_number();
//        int imageResource
//                = context.getResources().getIdentifier(url, "drawable",context.getPackageName());
//        return imageResource;
//    }
//    public int getImagetURL(Train train){
//        String url = "img_train_"+train.getLine_number();
//        int imageResource
//                = context.getResources().getIdentifier(url, "drawable",context.getPackageName());
//        return imageResource;
//    }
//    public int getImagetURL(Walk walk){
//        String url = "img_walk_";
//        int imageResource
//                = context.getResources().getIdentifier(url, "drawable",context.getPackageName());
//        return imageResource;
//    }

}
