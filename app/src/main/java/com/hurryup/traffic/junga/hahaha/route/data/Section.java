package com.hurryup.traffic.junga.hahaha.route.data;

import java.io.Serializable;

/**
 * Created by Yeom on 2016-08-20.
 */
public class Section implements Serializable {
    private Transport transport;
    private String start_name;
    private String start_gpsX;
    private String start_gpsY;
    private String end_gpsX;
    private String end_gpsY;
    private String end_name;
    private String time;
    private String trafficType;
    private String guide;
    private String distance;


    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getEnd_name() {
        return end_name;
    }

    public void setEnd_name(String end_name) {
        this.end_name = end_name;
    }

    public String getStart_gpsX() {
        return start_gpsX;
    }

    public void setStart_gpsX(String start_gpsX) {
        this.start_gpsX = start_gpsX;
    }

    public String getStart_name() {
        return start_name;
    }

    public void setStart_name(String start_name) {
        this.start_name = start_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public String getStart_gpsY() {
        return start_gpsY;
    }

    public void setStart_gpsY(String start_gpsY) {
        this.start_gpsY = start_gpsY;
    }

    public String getEnd_gpsX() {
        return end_gpsX;
    }

    public void setEnd_gpsX(String end_gpsX) {
        this.end_gpsX = end_gpsX;
    }

    public String getEnd_gpsY() {
        return end_gpsY;
    }

    public void setEnd_gpsY(String end_gpsY) {
        this.end_gpsY = end_gpsY;
    }
//    // ------- subPath ---
//    String trafficType;     // 도보, 버스, 지하철
//    String sectionTime; // 사이사이 시간
//
//    // ------- subPath -- lane --
//    String type;    // 버스 색깔
//    String busNo;
//
//    // ------- subPath -- lane --
//    String subwayCode;  //  지하철 호선
}
