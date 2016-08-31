package com.hurryup.traffic.junga.hahaha.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import android.os.Handler;

import java.io.IOException;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Message;

public class LocationThreadData extends Thread {
    String location;
    private Handler handler;
    private int what;
//    String gpsX, gpsY;

    LocationThreadData (String location, Handler handler, int what) {
        this.location = location;
        this.handler = handler;
        this.what = what;
        System.out.println("what : " + what);
    }

    @Override
    public void run() {
//        super.run();
        try {
            String name = location;
            String keyword = java.net.URLEncoder.encode(name, "UTF8");
            Document doc = Jsoup.connect("http://52.78.109.14/api/v1/info?keyword=" + keyword)
                    .ignoreContentType(true).get(); // 웹에서 내용을 가져온다.
            // Document doc =
            // Jsoup.connect("http://192.168.0.4:8080/Test/Test.html").ignoreContentType(true).get();

            Element contents = doc.getAllElements().get(0);

            String text = contents.text(); // 원하는 부분은 Elements형태로 되어 있으므로 이를 String 형태로 바꾸어 준다.
//            System.out.println(text);
//            System.out.println();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(text);
            JSONArray jArray = (JSONArray) jsonObject.get("resultList");
//            ArrayList<LocationData> resultList = new ArrayList<LocationData>();

            for (int i = 0; i < jArray.size(); i++) {
                JSONObject j = (JSONObject) jArray.get(i);
//                JSONObject jObject = (JSONObject) j.get(String.valueOf(i));
                LocationData r = new LocationData();
                r.title = (String) j.get("title");
                r.gpsX = (String) j.get("gpsX");
                r.gpsY = (String) j.get("gpsY");

                if (java.net.URLEncoder.encode(r.title, "UTF8").equals(keyword)) {          // 핸들러 정의
                    Message message = handler.obtainMessage();
                    message.what = what;
                    message.obj = r;
                    handler.sendMessage(message);
                }
//                r.address = (String) j.get("address");
//                r.newAddress = (String) j.get("newAddress");
//                r.category = (String) j.get("category");
//                resultList.add(r);

            }
//            System.out.println(resultList.get(0).toString());
//            System.out.println(resultList.get(1).toString());
//            System.out.println(resultList.get(2).toString());

        } catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
