package com.hurryup.traffic.junga.hahaha.model;

/**
 * Created by lim on 16. 8. 21.
 */
public class Bus extends Transport{
    private String bus_Number;
    private final String trans_type = "BUS";
    public Bus(String line_Number, String bus_Number){
        this.line_Number = line_Number;
        this.bus_Number = bus_Number;
    }

    public String getBus_Number() {
        return bus_Number;
    }

    public void setBus_Number(String bus_Number) {
        this.bus_Number = bus_Number;
    }

    public String getTrans_type() {
        return trans_type;
    }
}
