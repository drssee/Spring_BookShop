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
<link rel="stylesheet" href="<c:url value="/resources/css/board.css"/>">
<main id="board_register">
    <div class="board_wrap">
        <div id="sub_banner">
            <p><strong>문의하기</strong>QnA센터 답변 가능 시간: 월~금 9:00 - 18:00 </p>
        </div>
        <form action="<c:url value="/qna/answer"/>" method="post">
            <table class="board_write_table">
                <tr>
                    <td>제목</td>
                    <td align="left">
                        <input type="text" name="title" id="title" placeholder="제목" value="${bindingResult.getFieldValue("title")}">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="qna">
                            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('title')}">
                                <div class="binding-error">${errors.getFieldError("title").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </td>
                </tr>
                <tr>
                    <td>작성자</td>
                    <td align="left">
                        <input type="text" name="id" id="writer" value="<c:out value="${sessionScope.user.id}"/>" readonly>
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="qna">
                            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('id')}">
                                <div class="binding-error">${errors.getFieldError("id").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td align="left">
                        <textarea name="content" id="content" class="txt_area"
                                  placeholder="내용을 입력하세요.">${bindingResult.getFieldValue("content")}</textarea>
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="qna">
                            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('content')}">
                                <div class="binding-error">${errors.getFieldError("content").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </td>
                </tr>
                <input type="hidden" name="qno" value="${qno}">
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit" class="board_edit">답변 등록하기</button>
                    </td>
                </tr>
            </table>
        </form>
        <p class="board_btns">
            <a href="<c:url value="/qna/qnas?page="/>${page}">목록으로 돌아가기</a>
        </p>
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>