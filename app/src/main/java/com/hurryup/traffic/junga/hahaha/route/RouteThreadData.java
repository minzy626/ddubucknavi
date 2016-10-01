package com.hurryup.traffic.junga.hahaha.route;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.hurryup.traffic.junga.hahaha.model.Code;
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
        Log.d("RouteThread","RouteThread");
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
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(text);
            JSONObject jTransfer = (JSONObject) jsonObject.get(Code.MINI_TRANSFER);
            JSONObject jInfo = (JSONObject) jTransfer.get(Code.MINI_INFO);
            JSONArray jSubPath = (JSONArray) jTransfer.get(Code.MINI_SUBPATH);

            ArrayList<RouteData> routeDataList = new ArrayList<>();

            RouteData routeData = new RouteData();
            routeData.setStart((String) jInfo.get("firstStartStation"));
            routeData.setEnd((String) jInfo.get("lastEndStation"));
            routeData.setPayment((String) jInfo.get("payment"));
            routeData.setTotalDistance((String) jInfo.get("totalDistance"));
            routeData.setTotalTime((String) jInfo.get("totalTime"));
            routeData.setTotalStationCount((String) jInfo.get("totalStationCount"));
            routeData.setBusStationCount((String)jInfo.get(Code.BUS_STATION_COUNT));
            routeData.setSubwayStationCount((String)jInfo.get(Code.SUBWAY_STATION_COUNT));
            routeData.setWalkTotaldis((String)jInfo.get(Code.WALK_DIS));
//            routeData.setTotalTimeInfo((String) jInfo.get("totalTimeInfo"));

            ArrayList<Section> sectionList = new ArrayList<>();

            for (int i = 0; i < jSubPath.size(); i++) {
                Section section = new Section();
                JSONObject jsubPath = (JSONObject) jSubPath.get(i);
                section.setTrafficType((String) jsubPath.get("trafficType"));
                section.setTime((String)jsubPath.get(Code.SECTION_TIME));
                section.setDistance((String)jsubPath.get(Code.DISTANCE));
                section.setGuide((String)jsubPath.get(Code.GUIDE));
                section.setStart_name((String)jsubPath.get(Code.START_NAME));
                Log.d("RouteThread",i+" "+section.getStart_name());
                section.setStart_gpsX((String)jsubPath.get(Code.START_X));
                section.setStart_gpsY((String)jsubPath.get(Code.START_Y));
                section.setEnd_name((String)jsubPath.get(Code.END_NAME));
                section.setEnd_gpsY((String)jsubPath.get(Code.END_X));
                section.setEnd_gpsY((String)jsubPath.get(Code.END_Y));
                switch (section.getTrafficType()) {
                    case "1":
                        Train train = new Train();
                        JSONObject jLane = (JSONObject) jsubPath.get(Code.LANE);
                        String str = (String)jLane.get(Code.LANE_SUBWAYCODE);
                        train.setLine_number(str);
                        section.setTransport(train);
                        String startName = section.getStart_name()+"역";
                        String endName = section.getEnd_name()+"역";
                        section.setStart_name(startName);
                        section.setEnd_name(endName);
                        sectionList.add(section);
                        break;

                    case "2":
                        Bus bus = new Bus();
                        JSONArray jb = (JSONArray) jsubPath.get("lane");
                        for (int n = 0; n < jb.size(); n++) {
                            JSONObject jl = (JSONObject) jb.get(n);
                            bus.setBus_Number((String) jl.get(Code.LANE_BUSNO));
                            bus.setLine_number((String) jl.get(Code.LANE_BUSTYPE));
                            section.setTransport(bus);
                        }
                        sectionList.add(section);
                        break;

                    case "3":
                        Walk walk = new Walk();
                        section.setTransport(walk);
                        sectionList.add(section);
                        break;

                    default:
                        break;
                }
            }

            routeData.setSectionList(sectionList);
            routeDataList.add(routeData);

            System.out.println("rdList.size() : " + routeDataList.size());


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
                result_rd.setBusStationCount((String)result_info.get(Code.BUS_STATION_COUNT));
                result_rd.setSubwayStationCount((String)result_info.get(Code.SUBWAY_STATION_COUNT));
                Log.d("routeData","Sub"+result_rd.getSubwayStationCount()+"@@@@@@@@@@@@@");
                Log.d("routeData","Bus"+result_rd.getBusStationCount()+"@@@@@@@@@@@@@@@@@@@@");
                result_rd.setWalkTotaldis((String)result_info.get(Code.WALK_DIS));
                ArrayList<Section> result_s = new ArrayList<>();

                for (int k = 0; k < result_subPath.size(); k++) {
                    Section section = new Section();
                    JSONObject rj = (JSONObject) result_subPath.get(k);
                    section.setTrafficType((String) rj.get("trafficType"));
                    section.setTime((String)rj.get(Code.SECTION_TIME));
                    section.setDistance((String)rj.get(Code.DISTANCE));
                    section.setGuide((String)rj.get(Code.GUIDE));
                    section.setStart_name((String)rj.get(Code.START_NAME));
                    section.setStart_gpsX((String)rj.get(Code.START_X));
                    section.setStart_gpsY((String)rj.get(Code.START_Y));
                    section.setEnd_name((String)rj.get(Code.END_NAME));
                    section.setEnd_gpsY((String)rj.get(Code.END_X));
                    section.setEnd_gpsY((String)rj.get(Code.END_Y));
                    switch (section.getTrafficType()) {
                        case "1":
                            Train train = new Train();
                            JSONObject js = (JSONObject) rj.get("lane");
                            train.setLine_number((String) js.get("subwayCode"));

                            section.setTransport(train);
                            if(section==null)Log.d("RouteThread","11111111111111111");
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
                            }
                            if(section==null)Log.d("RouteThread","22222222222222222");
                            result_s.add(section);
                            break;

                        case "3":
                            Walk walk = new Walk();
                            section.setTransport(walk);
                            if(section==null)Log.d("RouteThread","33333333333333333");
                            result_s.add(section);
                            break;

                        default:
                            break;
                    }
                }


                result_rd.setSectionList(result_s);
                routeDataList.add(result_rd);
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

            for (int i=0 ; i<routeDataList.size() ; i++) {
                Log.d("routeData","BusCount"+routeDataList.get(i).getBusStationCount());
                Log.d("routeData","SubCount"+routeDataList.get(i).getSubwayStationCount());
                System.out.println(routeDataList.get(i).getStart());
                System.out.println(routeDataList.get(i).getEnd());
                System.out.println(routeDataList.get(i).getPayment());
                System.out.println(routeDataList.get(i).getTotalDistance());
                System.out.println(routeDataList.get(i).getTotalTime());
                System.out.println(routeDataList.get(i).getTotalStationCount());
                System.out.println("");
            }

            // 핸들러 정의
            Message message = handler.obtainMessage();
            message.obj = routeDataList;
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
