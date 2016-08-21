package com.hurryup.traffic.junga.hahaha.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class RouteData implements Serializable{

    private ArrayList<Section> section ;
    private String start;
    private String end;
    private String time;
    public RouteData(String start, String end, String time, ArrayList<Section> section){
        this.start =start;
        this.end =end;
        this.time = time;
        this.section  = section;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ArrayList<Section> getSection() {
        return section;
    }

    public void setSection(ArrayList<Section> section) {
        this.section = section;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
