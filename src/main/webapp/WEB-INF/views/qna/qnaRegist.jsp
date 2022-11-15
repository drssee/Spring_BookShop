<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>

<script>
    window.onpageshow = function(event) {
        //back 이벤트 일 경우
        if (event.persisted) {
            location.reload(true);
        }
    }
    <spring:hasBindErrors name="qna">
        alert('입력값을 다시 확인해주세요');
    </spring:hasBindErrors>
</script>
<main id="board_register">
    <div class="board_wrap">
        <div id="sub_banner">
            <p><strong>문의하기</strong>QnA센터 답변 가능 시간: 월~금 9:00 - 18:00 </p>
        </div>
        <form action="<c:url value="/qna/add"/>" method="post">
            <table class="board_write_table">
                <tr>
                    <td>제목</td>
                    <td align="left">
                        <input type="text" name="title" id="title" placeholder="제목" value="${bindingResult.getFieldValue("title")}">
                    </td>
                </tr>
                <tr>
                    <td>작성자</td>
                    <td align="left">
                        <input type="text" name="id" id="writer" value="<c:out value="${sessionScope.user.id}"/>" readonly>
                    </td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td align="left">
                        <textarea name="content" id="content" class="txt_area"
                                  placeholder="내용을 입력하세요.">${bindingResult.getFieldValue("content")}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit" class="board_edit">문의 등록하기</button>
                    </td>
                </tr>
            </table>
        </form>
        <p class="board_btns">
            <a href="<c:url value="/qnaList?page="/>${page}">목록으로 돌아가기</a>
        </p>
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>