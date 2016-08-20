package com.hurryup.traffic.junga.hahaha;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class RouteData implements Serializable{

    public ArrayList<Section> section ;
    public String start;
    public String end;
    public String time;

    public RouteData(String start, String end, String time, ArrayList<Section> section){
        this.start =start;
        this.end =end;
        this.time = time;
        this.section  = section;
    }

}
