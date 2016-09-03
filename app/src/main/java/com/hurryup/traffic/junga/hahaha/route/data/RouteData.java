package com.hurryup.traffic.junga.hahaha.route.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jylee on 2016-07-13.
 */
public class RouteData implements Serializable {
    private ArrayList<Section> section ;
    private String start;
    private String end;
    private String totalTime;
    private String totalTimeInfo;
    private String payment;
    private String totalStationCount;
    private String totalDistance;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTotalStationCount() {
        return totalStationCount;
    }

    public void setTotalStationCount(String totalStationCount) {
        this.totalStationCount = totalStationCount;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
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

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getTotalTimeInfo() {
        return totalTimeInfo;
    }

    public void setTotalTimeInfo(String totalTimeInfo) {
        this.totalTimeInfo = totalTimeInfo;
    }
}
