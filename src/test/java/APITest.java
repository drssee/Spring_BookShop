import com.example.book.service.BookService;
import com.example.demo.API;
import com.example.book.vo.BookVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class APITest {
    @Autowired
    API api;
    @Autowired
    BookService bookService;

    @Test
    void test() throws SQLException {
        List<BookVO> bookVOS = api.openApi();
        api.saveAladins(bookVOS);
    }

    @Test
    @Rollback(value = false)
    void test3(){
        List<BookVO> bookVOS = api.openApi();
        for (BookVO bookVO : bookVOS) {
            bookService.registBook(bookVO);
        }
    }

    //    @Test
//    @DisplayName("insertCategory 테스트")
////    @Rollback(value = false)
//    void insertCategoryTest(){
//        //입력할 카테고리 리스트
//        List<String> categoryNames = new ArrayList<>();
//        categoryNames.add("건강");
//        categoryNames.add("경제경영");
//        categoryNames.add("고등학교참고서");
//        categoryNames.add("과학");
//        categoryNames.add("대학교재");
//        categoryNames.add("만화");
//        categoryNames.add("사회과학");
//        categoryNames.add("소설");
//        categoryNames.add("수험서");
//        categoryNames.add("어린이");
//        categoryNames.add("에세이");
//        categoryNames.add("여행");
//        categoryNames.add("역사");
//        categoryNames.add("예술");
//        categoryNames.add("외국어");
//        categoryNames.add("요리");
//        categoryNames.add("유아");
//        categoryNames.add("인문학");
//        categoryNames.add("자기계발");
//        categoryNames.add("잡지");
//        categoryNames.add("종교");
//        categoryNames.add("좋은부모");
//        categoryNames.add("중학교참고서");
//        categoryNames.add("청소년");
//        categoryNames.add("초등학교참고서");
//        categoryNames.add("컴퓨터");
//        //카테고리 이름 하나씩 vo 생성해 insert
//        for (String categoryName : categoryNames) {
//            CategoryVO categoryVO = new CategoryVO(categoryName);
//            assertEquals(1,bookMapper.insertCategory(categoryVO));
//        }
//    }
}
