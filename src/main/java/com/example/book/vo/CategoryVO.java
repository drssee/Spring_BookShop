package com.example.book.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryVO {
    private int category_name_id;

    private String category_name;

    private CategoryVO(){}

    //categoryNames에 해당하는 categoryNameId와 categoryName 초기화
    public CategoryVO(String category_name){
        this.category_name = category_name;
        this.category_name_id = getCategoryIndex(category_name);
    }

    //카테고리 추가를 위한 생성자
    public CategoryVO(int category_name_id,String category_name){
        this.category_name_id=category_name_id;
        this.category_name=category_name;
    }

    //입력된 카테고리문자열에 해당하는 categorynames인덱스 번호를 리턴
    private static int getCategoryIndex(String categoryName) {
        int index = categoryNames.indexOf(categoryName);
        return index + 1;
    }

    //26개 기본 카테고리
    private static final List<String> categoryNames = new ArrayList<>();
    static{
        categoryNames.add("건강");
        categoryNames.add("경제경영");
        categoryNames.add("고등학교참고서");
        categoryNames.add("과학");
        categoryNames.add("대학교재");
        categoryNames.add("만화");
        categoryNames.add("사회과학");
        categoryNames.add("소설");
        categoryNames.add("수험서");
        categoryNames.add("어린이");
        categoryNames.add("에세이");
        categoryNames.add("여행");
        categoryNames.add("역사");
        categoryNames.add("예술");
        categoryNames.add("외국어");
        categoryNames.add("요리");
        categoryNames.add("유아");
        categoryNames.add("인문학");
        categoryNames.add("자기계발");
        categoryNames.add("잡지");
        categoryNames.add("종교");
        categoryNames.add("좋은부모");
        categoryNames.add("중학교참고서");
        categoryNames.add("청소년");
        categoryNames.add("초등학교참고서");
        categoryNames.add("컴퓨터");
    }
}
