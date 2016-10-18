package com.hurryup.traffic.junga.hahaha.route.data;

/**
 * Created by jylee on 2016-08-24.
 */

//public class Bus extends Transport{
//    private final String trans_type = "BUS";
//    private String bus_Number;
//    private String line_number;
//
//    public Bus(){}




public class Walk extends Transport {
    private final String trans_type = "WALK";
//    private String sectionTime;

    public String getTrans_type() {

        return trans_type;
    }

//    public String getSectionTime() {
//        return sectionTime;
//    }

//    public void setSectionTime(String sectionTime) {
//        this.sectionTime = sectionTime;
//    }
}
