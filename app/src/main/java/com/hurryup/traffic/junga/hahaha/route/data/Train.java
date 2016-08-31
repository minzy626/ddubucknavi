package com.hurryup.traffic.junga.hahaha.route.data;

/**
 * Created by lim on 16. 8. 21.
 */
public class Train extends Transport {
    private final String trans_type = "TRAIN";
    private String line_number;

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getTrans_type() {
        return trans_type;
    }
}
