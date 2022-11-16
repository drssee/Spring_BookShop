<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>

<script>
    window.onpageshow = function(event) {
        //back 이벤트 일 경우
        if (event.persisted) {
            location.reload(true);
        }
    }
</script>
<link rel="stylesheet" href="<c:url value="/resources/css/board.css"/>">
<main id="board_register">
    <div class="board_wrap">
        <div id="sub_banner">
            <p><strong>문의하기</strong>QnA센터 답변 가능 시간: 월~금 9:00 - 18:00 </p>
        </div>
        <table class="board_write_table">
            <tr>
                <td>제목</td>
                <td align="left">
                    <c:out value="${qna.title}" />
                </td>
            </tr>
            <tr>
                <td>작성자</td>
                <td align="left">
                    <c:out value="${qna.id}" />
                </td>
            </tr>
            <tr>
                <td>내용</td>
                <td align="left">
                    <textarea name="content" id="content" class="txt_area" readonly style="cursor: auto"><c:out value="${qna.content}"/></textarea>
                </td>
            </tr>
        </table>

        <div class="qna_btn_wrap">
            <button id="qna_modify" class="btn_modify btn_active">수정</button>
            <button id="qna_delete" class="btn_modify btn_delete">삭제</button>
            <a href="<c:url value="/qna/qnas?page="/>${page}" class="btn_modify btn_default">목록</a>
            <c:if test="${sessionScope.user.id.equals('admin')}">
                <a class="btn_modify btn_default" href="<c:url value="/qna/answer/"/>${qna.qno}">답변(관리자)</a>
            </c:if>
        </div>
        <script>
            $(document).ready(function (){
                //수정,삭제 버튼 클릭시 작성자 검증
                let id = '<c:out value="${sessionScope.user.id}"/>';
                let writer = '<c:out value="${qna.id}"/>';
                let qno = '<c:out value="${qna.qno}"/>';
                let page = '<c:out value="${page==null?1:page}"/>';
                //수정버튼 클릭
                $("#qna_modify").click(function (){
                    //작성자 또는 관리자만 수정 가능
                    if(id===''||(id!==writer&&id!=='admin')){
                        alert('작성자만 수정 가능합니다');
                        return;
                    }
                    window.location="/bookshop/qna/modify/"+qno+"?page="+page;
                });//modify

                //삭제버튼 클릭
                $("#qna_delete").click(function (){
                    //작성자 또는 관리자만 삭제 가능
                    if(id===''||(id!==writer&&id!=='admin')){
                        alert('작성자만 삭제 가능합니다');
                        return;
                    }
                    if(!confirm('정말 삭제하시겠습니까?')){
                        return;
                    }
                    window.location="/bookshop/qna/delete/"+qno+"?page="+page;
                });//remove
            });
        </script>
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>