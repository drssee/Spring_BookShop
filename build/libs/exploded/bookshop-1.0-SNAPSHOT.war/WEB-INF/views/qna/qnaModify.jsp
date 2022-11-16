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
        alert('입력값을 확인해 주세요');
    </spring:hasBindErrors>
</script>
<link rel="stylesheet" href="<c:url value="/resources/css/board.css"/>">
<main id="board_register">
    <div class="board_wrap">
        <div id="sub_banner">
            <p><strong>문의하기</strong>QnA센터 답변 가능 시간: 월~금 9:00 - 18:00 </p>
        </div>
        <form action="<c:url value="/qna/modify/"/>${qna.qno}" method="post">
            <table class="board_write_table">
                <tr>
                    <td>제목</td>
                    <td align="left">
                        <c:if test="${bindingResult==null}">
                            <input type="text" name="title" id="title" placeholder="제목" value="${qna.title}">
                        </c:if>
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="qna">
                            <input type="text" name="title" id="title" placeholder="제목" value="${bindingResult.getFieldValue("title")}">
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
                        <input type="text" name="id" id="writer" value="<c:out value="${qna.id}"/>" readonly>
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
                        <c:if test="${bindingResult==null}">
                            <textarea name="content" id="content" class="txt_area"
                                      placeholder="내용을 입력하세요.">${qna.content}</textarea>
                        </c:if>
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="qna">
                            <textarea name="content" id="content" class="txt_area"
                                      placeholder="내용을 입력하세요.">${bindingResult.getFieldValue("content")}</textarea>
                            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('content')}">
                                <div class="binding-error">${errors.getFieldError("content").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="page" value="${page}">
            <div class="qna_btn_wrap">
                <a href="<c:url value="/qna/"/>${qna.qno}?page=${page}" class="btn_modify btn_default">뒤로가기</a>
                <button type="submit" id="qna_modify_save" class="btn_modify btn_active">전송</button>
                <a href="<c:url value="/qna/qnas?page="/>${page}" class="btn_modify btn_default">목록</a>
            </div>
        </form>

        <script>
            //수정,삭제 버튼 클릭시 작성자 검증
            $(document).ready(function(){
                let id = '<c:out value="${sessionScope.user.id}"/>';
                let writer = '<c:out value="${qna.id}"/>';

               //수정버튼 클릭
                $("#qna_modify_save").click(function (){
                    //작성자 또는 관리자만 수정 가능
                    if(id===''||(id!==writer&&id!=='admin')){
                        alert('작성자만 수정 가능합니다');
                        return false;
                    }
                });//modify
            });
        </script>
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>