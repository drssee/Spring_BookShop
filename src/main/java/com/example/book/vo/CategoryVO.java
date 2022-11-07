package com.example.book.vo;

import lombok.Data;

import static com.example.common.data.CategoryData.categoryNames;

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
    public CategoryVO(int category_name_id, String category_name){
        this.category_name_id=category_name_id;
        this.category_name=category_name;
    }

    //입력된 카테고리문자열에 해당하는 categorynames인덱스 번호를 리턴
    private int getCategoryIndex(String categoryName) {
        int index = categoryNames.indexOf(categoryName);
        return index + 1;
    }
}
