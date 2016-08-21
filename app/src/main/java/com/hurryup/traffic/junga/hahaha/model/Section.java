package com.hurryup.traffic.junga.hahaha.model;

import java.io.Serializable;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class Section implements Serializable {

    public Section(Transport transport, long end_gps, String end_name, long start_gps, String start_name, String time) {
        this.end_gps = end_gps;
        this.end_name = end_name;
        this.start_gps = start_gps;
        this.start_name = start_name;
        this.time = time;
        this.transport = transport;
    }

    public Section() {
    }

    public long getEnd_gps() {
        return end_gps;
    }

    public void setEnd_gps(long end_gps) {
        this.end_gps = end_gps;
    }

    public String getEnd_name() {
        return end_name;
    }

    public void setEnd_name(String end_name) {
        this.end_name = end_name;
    }

    public long getStart_gps() {
        return start_gps;
    }

    public void setStart_gps(long start_gps) {
        this.start_gps = start_gps;
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

    private Transport transport;
    private String start_name;
    private long start_gps;
    private String end_name;
    private long end_gps;
    private String time;

}
