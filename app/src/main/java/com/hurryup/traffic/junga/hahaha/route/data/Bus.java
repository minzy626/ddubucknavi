package com.hurryup.traffic.junga.hahaha.route.data;

/**
 * Created by lim on 16. 8. 21.
 */
public class Bus extends Transport{
    private final String trans_type = "BUS";
    private String bus_Number;
    private String line_number;

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
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
