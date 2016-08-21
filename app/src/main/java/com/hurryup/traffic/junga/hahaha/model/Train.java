package com.hurryup.traffic.junga.hahaha.model;

/**
 * Created by lim on 16. 8. 21.
 */
public class Train extends Transport{
    private final String trans_type = "TRAIN";
    public Train(String line_number){
        this.line_Number = line_number;
    }

    public String getTrans_type() {
        return trans_type;
    }
}
