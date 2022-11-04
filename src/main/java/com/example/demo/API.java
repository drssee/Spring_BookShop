package com.example.demo;

import com.example.book.vo.BookVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class API {

    @Autowired
    DataSource dataSource;


    String key = "";
    String result = "";
    URL url;

    public void saveAladins(List<BookVO> bookVOS) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into book_db.book (isbn, title, pubDate,  author, description, price, mileage, stock, cover, publisher)\n" +
                "values (?,?,?,?,?,?,?,?,?,?);";
        conn=dataSource.getConnection();
        pstmt=conn.prepareStatement(sql);
        for(BookVO bookVO : bookVOS) {
                pstmt.setString(1, bookVO.getIsbn());
                pstmt.setString(2, bookVO.getTitle());
                pstmt.setDate(3,new Date(bookVO.getPubDate().getTime()));
                pstmt.setString(4, bookVO.getAuthor());
                pstmt.setString(5, bookVO.getDescription());
                pstmt.setInt(6, bookVO.getPrice());
                pstmt.setInt(7, bookVO.getMileage());
                pstmt.setInt(8, bookVO.getStock());
                pstmt.setString(9, bookVO.getCover());
                pstmt.setString(10, bookVO.getPublisher());
                pstmt.executeUpdate();
        }
        pstmt.close();
        conn.close();
    }
    //""<-제거해주는 메서드
    public static String parseNonDQM(JsonElement element){
        if(element.isJsonNull()){
            return "";
        }
        String str = String.valueOf(element);
        if(str == null || str.length() < 2){
            return "";
        }
        return str.substring(1,str.length()-1);
    }

    public List<BookVO> openApi(){
        List<BookVO> list = new ArrayList<>();
            try {
                int page;
                String size = "Big";
                //전체 api 리스트 카운트
                //해당 api url을 지정하고
                for(page=1;page<=200;page++){
                    url = new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbsarmos881638001&QueryType=ItemNewAll&MaxResults=100&start="+page+"&SearchTarget=Book&output=js&Version=20131101&cover="+size);
                    //url에서 시스템으로 가져오는 스트림을 열어주고 result에 읽어온다
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                    result = br.readLine();
//                System.out.println("result = " + result);
                    br.close();
//                //얻어온 JSON 정리
//                JsonParser jsonParser = new JsonParser();
                    //jsonObject 타입으로 파싱
                    JsonObject resultByJson = (JsonObject) JsonParser.parseString(result);
                    //파싱된 result에서 필요한 정보의 value만 추출
//                System.out.println("resultByJson = " + resultByJson);
                    JsonElement item = resultByJson.get("item");
                    JsonArray aladin_jsonArray = item.getAsJsonArray();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    for (JsonElement aladin_jsonElement : aladin_jsonArray) {
                        JsonObject aladin_json = (JsonObject) aladin_jsonElement;
                        BookVO bookVO = BookVO
                                .builder()
                                .isbn(parseNonDQM(aladin_json.get("isbn")))
                                .title(parseNonDQM(aladin_json.get("title")))
                                .pubDate(df.parse(aladin_json.get("pubDate").getAsString()))
                                .author(parseNonDQM(aladin_json.get("author")))
                                .description(parseNonDQM(aladin_json.get("description")))
                                .price(Integer.parseInt(String.valueOf(aladin_json.get("priceStandard"))))
                                .stock((int)(Math.random()*1000))
                                .cover(parseNonDQM(aladin_json.get("cover")))
                                .publisher(parseNonDQM(aladin_json.get("publisher")))
                                .build();
                        list.add(bookVO);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return list;
    }

    private static String parseCategory2(String parseCategory1){
        if(!parseCategory1.contains("/")){
            return parseCategory1;
        }
        return parseCategory1.substring(0,parseCategory1.indexOf("/"));
    }

    private static String parseCategory(String categoryName){
        int startIndex = 0;
        int endIndex = 0;
        try {
            startIndex = categoryName.indexOf(">");
            endIndex = categoryName.indexOf(">",startIndex+1);
        } catch (Exception e) {
            System.out.println("categoryName = " + categoryName);
            return parseCategory2(categoryName);
        }
        if(startIndex==-1){
            return parseCategory2(categoryName);
        }
        if(endIndex==-1){
            return parseCategory2(categoryName.substring(startIndex));
        }
        return parseCategory2(categoryName.substring(startIndex+1,endIndex));
    }

//    private static String parseCategory(String categoryName){
//        int startIndex = 0;
//        int endIndex = 0;
//        try {
//            startIndex = categoryName.indexOf(">");
//            endIndex = categoryName.indexOf(">",startIndex+1);
//        } catch (Exception e) {
//            System.out.println("categoryName = " + categoryName);
//        }
//        if(startIndex==-1){
//            return categoryName;
//        }
//        if(endIndex==-1){
//            return categoryName.substring(startIndex);
//        }
//        return categoryName.substring(startIndex+1,endIndex);
//    }

    public static void main(String[] args) {
        new API().openApi_test();
    }
    public void openApi_test(){
        try {
            int page;
            String size = "Big";
            //전체 api 리스트 카운트
            //해당 api url을 지정하고
            for(page=1;page<=20;page++){
                url = new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbsarmos881638001&QueryType=ItemNewAll&MaxResults=100&start="+page+"&SearchTarget=Book&output=js&Version=20131101&cover="+size);
                //url에서 시스템으로 가져오는 스트림을 열어주고 result에 읽어온다
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                result = br.readLine();
//                System.out.println("result = " + result);
                br.close();
//                //얻어온 JSON 정리
//                JsonParser jsonParser = new JsonParser();
                //jsonObject 타입으로 파싱
                JsonObject resultByJson = (JsonObject) JsonParser.parseString(result);
                //파싱된 result에서 필요한 정보의 value만 추출
//                System.out.println("resultByJson = " + resultByJson);
                JsonElement item = resultByJson.get("item");
                JsonArray aladin_jsonArray = item.getAsJsonArray();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                for (JsonElement aladin_jsonElement : aladin_jsonArray) {
                    JsonObject aladin_json = (JsonObject) aladin_jsonElement;
                    BookVO bookVO = BookVO
                            .builder()
                            .isbn(asString(aladin_json.get("isbn")))
                            .title(asString(aladin_json.get("title")))
                            .pubDate(df.parse(aladin_json.get("pubDate").getAsString()))
                            .author(asString(aladin_json.get("author")))
                            .description(asString(aladin_json.get("description")))
                            .price(Integer.parseInt(String.valueOf(aladin_json.get("priceStandard"))))
                            .cover(asString(aladin_json.get("cover")))
                            .publisher(asString(aladin_json.get("publisher")))
                            .build();
                    System.out.println("aladin = " + bookVO);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String asString(Object obj){
        return String.valueOf(obj);
    }
}
