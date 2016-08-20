package com.hurryup.traffic.junga.hahaha;

import java.io.Serializable;

/**
 * Created by JUNGA on 2016-08-19.
 */
public class Section implements Serializable {

    public Section(long end_gps, String end_name, String number, long start_gps, long start_long, String start_name, int time, String tran_type) {
        this.end_gps = end_gps;
        this.end_name = end_name;
        this.number = number;
        this.start_gps = start_gps;
        this.start_long = start_long;
        this.start_name = start_name;
        this.time = time;
        this.tran_type = tran_type;
    }

    public Section() {
    }

    String tran_type;
    String start_name;
    long start_long;
    String end_name;
    long start_gps;
    long end_gps;
    String number;
    int time;

}
