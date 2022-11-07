//package com.example.temp;
//
//import com.example.book.dao.BookDao;
//import com.example.book.vo.BookVO;
//import com.example.book.vo.CategoryVO;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class API {
//
//    @Autowired
//    DataSource dataSource;
//    @Autowired(required = false)
//    BookDao bookDao;
//    String result = "";
//    URL url;
//
//    //api리스트 가져오는 메서드
//    public List<BookVO> openApi(){
//        List<BookVO> list = new ArrayList<>();
//        try {
//            int page;
//            String size = "Big";
//            for(page=1;page<=20;page++){
//                url = new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbsarmos881638001&QueryType=ItemNewAll&MaxResults=100&start="+page+"&SearchTarget=Book&output=js&Version=20131101&cover="+size);
//                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
//                result = br.readLine();
//                br.close();
//                JsonObject resultByJson = (JsonObject) JsonParser.parseString(result);
//                JsonElement item = resultByJson.get("item");
//                JsonArray aladin_jsonArray = item.getAsJsonArray();
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                for (JsonElement aladin_jsonElement : aladin_jsonArray) {
//                    JsonObject aladin_json = (JsonObject) aladin_jsonElement;
//                    BookVO bookVO = BookVO
//                            .builder()
//                            .title(parseNonDQM(aladin_json.get("title")))
//                            .price(Integer.parseInt(String.valueOf(aladin_json.get("priceStandard"))))
//                            .stock((int)(Math.random()*1000))
//                            .storeFileName(parseNonDQM(aladin_json.get("cover")))
//                            .pubDate(df.parse(aladin_json.get("pubDate").getAsString()))
//                            .author(parseNonDQM(aladin_json.get("author")))
//                            .description(parseNonDQM(aladin_json.get("description")))
//                            .publisher(parseNonDQM(aladin_json.get("publisher")))
//                            .build();
//                    list.add(bookVO);
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//
//    //카테고리 리스트 가져오는 메서드
//    public List<CategoryVO> openApi_category(){
//        List<CategoryVO> list = new ArrayList<>();
//        try {
//            int page;
//            String size = "Big";
//            //전체 api 리스트 카운트
//            //해당 api url을 지정하고
//            for(page=1;page<=20;page++){
//                url = new URL("http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbsarmos881638001&QueryType=ItemNewAll&MaxResults=100&start="+page+"&SearchTarget=Book&output=js&Version=20131101&cover="+size);
//                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
//                result = br.readLine();
//                br.close();
//                JsonObject resultByJson = (JsonObject) JsonParser.parseString(result);
//                JsonElement item = resultByJson.get("item");
//                JsonArray aladin_jsonArray = item.getAsJsonArray();
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                for (JsonElement aladin_jsonElement : aladin_jsonArray) {
//                    JsonObject aladin_json = (JsonObject) aladin_jsonElement;
//                    CategoryVO categoryVO = new CategoryVO(
//                            parseCategory2(
//                                    parseCategory(aladin_json.get("categoryName").getAsString())
//                            )
//                    );
//                    list.add(categoryVO);
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//
//    //api db에 저장하는 메서드
//    public void saveAladins(List<BookVO> bookVOS){
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        List<Long> bnoList = new ArrayList<>();
//        String sql1 = "insert into book_db.book (title,price,stock)\n" +
//                "values (?,?,?);";
//        String sql2 = "insert into book_db.book_info (pubDate, author, description, publisher)\n" +
//                "values (?,?,?,?);";
//        String sql3 = "insert into book_db.book_images (bno,storeFileName,originalFileName,imgCategory,ext,size) " +
//                "values (?,?,?,?,?,?)";
//        try {
//            conn=dataSource.getConnection();
//            conn.setAutoCommit(false);
//            pstmt=conn.prepareStatement(sql1);
//            for(BookVO bookVO : bookVOS) {
//                pstmt.setString(1, bookVO.getTitle());
//                pstmt.setInt(2, bookVO.getPrice());
//                pstmt.setInt(3, bookVO.getStock());
//                pstmt.executeUpdate();
//                bnoList.add(bookVO.getBno());
//            }
//
//            pstmt=conn.prepareStatement(sql2);
//            for(BookVO bookVO : bookVOS) {
//                pstmt.setDate(1, new Date(bookVO.getPubDate().getTime()));
//                pstmt.setString(2, bookVO.getAuthor());
//                pstmt.setString(3, bookVO.getDescription());
//                pstmt.setString(4,bookVO.getPublisher());
//                pstmt.executeUpdate();
//            }
//
//            pstmt=conn.prepareStatement(sql3);
//            for(int i=0;i<bookVOS.size();i++){
//                BookVO bookVO = bookVOS.get(i);
//                pstmt.setLong(1,i+1);
//                pstmt.setString(2,bookVO.getStoreFileName());
//                pstmt.setString(3,bookVO.getOriginalFileName());
//                pstmt.setString(4,"c");
//                pstmt.setString(5,bookVO.getExt());
//                pstmt.setLong(6,bookVO.getSize());
//                pstmt.executeUpdate();
//            }
//
//            conn.commit();
//            conn.setAutoCommit(true);
//
//            List<CategoryVO> categoryVOS = openApi_category();
//            long a=1;
//            for (CategoryVO categoryVO : categoryVOS) {
//                bookDao.insertCategoryBook(a++,categoryVO.getCategory_name_id());
//            }
//        } catch (SQLException e) {
//            try {
//                conn.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                pstmt.close();
//                conn.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//    }
//
//    //""<-제거해주는 메서드
//    public static String parseNonDQM(JsonElement element){
//        if(element.isJsonNull()){
//            return "";
//        }
//        String str = String.valueOf(element);
//        if(str == null || str.length() < 2){
//            return "";
//        }
//        return str.substring(1,str.length()-1);
//    }
//
//
//
//
//
//
//    private static String parseCategory2(String parseCategory1){
//        if(!parseCategory1.contains("/")){
//            return parseCategory1;
//        }
//        return parseCategory1.substring(0,parseCategory1.indexOf("/"));
//    }
//
//    private static String parseCategory(String categoryName){
//        int startIndex = 0;
//        int endIndex = 0;
//        try {
//            startIndex = categoryName.indexOf(">");
//            endIndex = categoryName.indexOf(">",startIndex+1);
//        } catch (Exception e) {
//            System.out.println("categoryName = " + categoryName);
//            return parseCategory2(categoryName);
//        }
//        if(startIndex==-1){
//            return parseCategory2(categoryName);
//        }
//        if(endIndex==-1){
//            return parseCategory2(categoryName.substring(startIndex));
//        }
//        return parseCategory2(categoryName.substring(startIndex+1,endIndex));
//    }
//}
