package com.hurryup.traffic.junga.hahaha.route;

import android.os.Handler;
import android.os.Message;

import com.hurryup.traffic.junga.hahaha.route.data.RouteData;
import com.hurryup.traffic.junga.hahaha.route.data.Bus;
import com.hurryup.traffic.junga.hahaha.route.data.Section;
import com.hurryup.traffic.junga.hahaha.route.data.Train;
import com.hurryup.traffic.junga.hahaha.route.data.Walk;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jylee on 2016-08-31.
 */
public class RouteThreadData extends Thread {
    private String start, end, startGpsX,startGpsY, endGpsX, endGpsY;
    private Handler handler;
    //    String gpsX, gpsY;

    RouteThreadData(String start, String end, String startGpsX, String startGpsY, String endGpsX, String endGpsY, Handler handler) {
        this.start = start;
        this.end = end;
        this.startGpsX = startGpsX;
        this.startGpsY = startGpsY;
        this.endGpsX = endGpsX;
        this.endGpsY = endGpsY;
        this.handler = handler;
//        this.handler = handler;
//        this.what = what;
    }

    @Override
    public void run() {
//        super.run();

        try {
            String startStNm = java.net.URLEncoder.encode(start, "UTF8");
            String endStNm = java.net.URLEncoder.encode(end, "UTF8");
            System.out.println("http://52.78.109.14/api/v1/path/getpath?startStNm="
                    + startStNm + "&startX=" + startGpsX + "&startY=" + startGpsY + "&endStNm=" + endStNm + "&endX=" + endGpsX + "&endY=" + endGpsY);
            boolean status = false;
            int count = 0;
            Document doc = null;
            while (status == false && count < 5 ) {
                try {
                    doc = Jsoup.connect("http://52.78.109.14/api/v1/path/getpath?startStNm="
                            + startStNm + "&startX=" + startGpsX + "&startY=" + startGpsY + "&endStNm=" + endStNm + "&endX=" + endGpsX + "&endY=" + endGpsY)
                            .ignoreContentType(true).get(); // 웹에서 내용을 가져온다.
                    status = true;
                } catch(HttpStatusException e) {
                    e.printStackTrace();
                    count++;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            // Document doc =
            // Jsoup.connect("http://192.168.0.4:8080/Test/Test.html").ignoreContentType(true).get();


            Element contents = doc.getAllElements().get(0);

            String text = contents.text(); // 원하는 부분은 Elements형태로 되어 있으므로 이를 String 형태로 바꾸어 준다.
//            System.out.println(text);
//            System.out.println();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(text);
            JSONObject jTransfer = (JSONObject) jsonObject.get("miniTransfer");
            JSONObject jInfo = (JSONObject) jTransfer.get("info");
            JSONArray jSubPath = (JSONArray) jTransfer.get("subPath");
            JSONArray jResultList = (JSONArray) jsonObject.get("resultList");

            ArrayList<RouteData> rdList = new ArrayList<>();

            RouteData rd = new RouteData();
            rd.setStart((String) jInfo.get("firstStartStation"));
            rd.setEnd((String) jInfo.get("lastEndStation"));
            rd.setPayment((String) jInfo.get("payment"));
            rd.setTotalDistance((String) jInfo.get("totalDistance"));
            rd.setTotalTime((String) jInfo.get("totalTime"));
            rd.setTotalStationCount((String) jInfo.get("totalStationCount"));
            rd.setTotalTimeInfo((String) jInfo.get("totalTimeInfo"));

            ArrayList<Section> s = new ArrayList<>();

            for (int i = 0; i < jSubPath.size(); i++) {
                Section section = new Section();
                JSONObject j = (JSONObject) jSubPath.get(i);
                section.setTrafficType((String) j.get("trafficType"));

                switch (section.getTrafficType()) {
                    case "1":
                        Train train = new Train();
                        JSONObject js = (JSONObject) j.get("lane");
                        train.setLine_number((String) js.get("subwayCode"));

                        section.setTransport(train);
                        s.add(section);
                        break;

                    case "2":
                        Bus bus = new Bus();
                        JSONArray jb = (JSONArray) j.get("lane");
                        for (int n = 0; n < jb.size(); n++) {
                            JSONObject jl = (JSONObject) jb.get(n);
                            bus.setBus_Number((String) jl.get("busNo"));
                            bus.setLine_number((String) jl.get("type"));

                            section.setTransport(bus);
                            s.add(section);
                        }
                        break;

                    case "3":
                        Walk walk = new Walk();
                        walk.setSectionTime((String) j.get("sectionTime"));

                        section.setTransport(walk);
                        s.add(section);
                        break;

                    default:
                        break;
                }
            }

            rd.setSection(s);
            rdList.add(rd);

            System.out.println("rdList.size() : " + rdList.size());


            /////////////////////////////////////json resultList
            JSONArray resultList = (JSONArray)  jsonObject.get("resultList");
//            System.out.println("resultList.size() : " + resultList.size());
            for(int i = 0; i<resultList.size(); i++){
                RouteData result_rd = new RouteData();
                JSONObject result = (JSONObject) resultList.get(i);
                JSONObject result_info = (JSONObject) result.get("info");
                JSONArray result_subPath = (JSONArray) result.get("subPath");

                result_rd.setTotalTime((String) result_info.get("totalTime"));
                result_rd.setStart((String) result_info.get("firstStartStation"));
                result_rd.setEnd((String) result_info.get("lastEndStation"));
                result_rd.setPayment((String) result_info.get("payment"));
                result_rd.setTotalDistance((String) result_info.get("totalDistance"));
                result_rd.setTotalStationCount((String) result_info.get("totalStationCount"));
                result_rd.setTotalTimeInfo((String) result_info.get("totalTimeInfo"));

                ArrayList<Section> result_s = new ArrayList<>();

                for (int k = 0; k < result_subPath.size(); k++) {
                    Section section = new Section();
                    JSONObject rj = (JSONObject) result_subPath.get(k);
                    section.setTrafficType((String) rj.get("trafficType"));

                    switch (section.getTrafficType()) {
                        case "1":
                            Train train = new Train();
                            JSONObject js = (JSONObject) rj.get("lane");
                            train.setLine_number((String) js.get("subwayCode"));

                            section.setTransport(train);
                            result_s.add(section);
                            break;

                        case "2":
                            Bus bus = new Bus();
                            JSONArray jb = (JSONArray) rj.get("lane");
                            for (int n = 0; n < jb.size(); n++) {
                                JSONObject jl = (JSONObject) jb.get(n);
                                bus.setBus_Number((String) jl.get("busNo"));
                                bus.setLine_number((String) jl.get("type"));

                                section.setTransport(bus);
                                result_s.add(section);
                            }
                            break;

                        case "3":
                            Walk walk = new Walk();
                            walk.setSectionTime((String) rj.get("sectionTime"));

                            section.setTransport(walk);
                            result_s.add(section);
                            break;

                        default:
                            break;
                    }
                }


                result_rd.setSection(result_s);
                rdList.add(result_rd);
            }

//            System.out.println("rdList.size() :"+rdList.size());

//            ///////////////////////////////////print
//            for(int k = 0; k<rdList.size();k++) {
//                RouteData rdr = rdList.get(k);
//                ArrayList<Section> rds;
//                rds=(rdr.getSection());
//                for (int i = 0; i < rds.size(); i++) {
//
////                    System.out.println((i+1)+"번째");
////                    System.out.println("trafficType : " + rds.get(i).getTrafficType());
//                    Section section = rds.get(i);
//
//                    switch (rds.get(i).getTrafficType()) {
//                        case "1":
//                            Train train = (Train) section.getTransport();
////                            System.out.println("subwayCode : " + train.getLine_number());
//                            break;
//                        case "2":
//                            Bus bus = (Bus) section.getTransport();
////                            System.out.println("busNo : " + bus.getBus_Number());
////                            System.out.println("type : " + bus.getLine_number());
//                            break;
//                        case "3":
//                            Walk walk = (Walk) section.getTransport();
////                            System.out.println("sectionTime : " + walk.getSectionTime());
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }


//            System.out.println("start : " + rd.getStart());
//            System.out.println("end : " + rd.getEnd());
//            System.out.println("payment : " + rd.getPayment());
//            System.out.println("totalDistance : " + rd.getTotalDistance());
//            System.out.println("time : " + rd.getTotalTime());
//            System.out.println("totalStationCount : " + rd.getTotalStationCount());

            for (int i=0 ; i<rdList.size() ; i++) {
                System.out.println(rdList.get(i).getStart());
                System.out.println(rdList.get(i).getEnd());
                System.out.println(rdList.get(i).getPayment());
                System.out.println(rdList.get(i).getTotalDistance());
                System.out.println(rdList.get(i).getTotalTime());
                System.out.println(rdList.get(i).getTotalStationCount());
                System.out.println("");
            }

            // 핸들러 정의
            Message message = handler.obtainMessage();
            message.obj = rdList;
            handler.sendMessage(message);


        } catch (HttpStatusException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
