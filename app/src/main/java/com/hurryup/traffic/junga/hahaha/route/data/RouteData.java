package com.hurryup.traffic.junga.hahaha.route.data;

import java.io.InterruptedIOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jylee on 2016-07-13.
 */
public class RouteData implements Serializable {
    private ArrayList<Section> sectionList ;
    private String start;
    private String end;
    private String totalTime;
    private Long orginalTotalTime;
    private String totalTimeInfo;
    private String payment;
    private String totalStationCount;
    private String totalDistance;
    private String busStationCount;
    private String subwayStationCount;
    private String walkTotaldis;

    public int getBusStationCount() {
        if(busStationCount==null){
            return 0;
        }
        int result = Integer.parseInt(busStationCount);
        return result;


    }

    public Long getOrginalTotalTime() {
        return orginalTotalTime;
    }

    public void setOrginalTotalTime(Long orginalTotalTime) {
        this.orginalTotalTime = orginalTotalTime;
    }

    public void setBusStationCount(String busStationCount) {
        this.busStationCount = busStationCount;
    }

    public int getSubwayStationCount() {
        if(subwayStationCount==null){
            return 0;
        }
        int result = Integer.parseInt(subwayStationCount);
        return result;
    }

    public void setSubwayStationCount(String subwayStationCount) {
        this.subwayStationCount = subwayStationCount;
    }

    public String getWalkTotaldis() {
        return walkTotaldis;
    }

    public void setWalkTotaldis(String walkTotaldis) {
        this.walkTotaldis = walkTotaldis;
    }



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

    public long getTotalDistance() {
        long result = Long.parseLong(totalDistance);
        return result;
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

    public ArrayList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.sectionList = sectionList;
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
