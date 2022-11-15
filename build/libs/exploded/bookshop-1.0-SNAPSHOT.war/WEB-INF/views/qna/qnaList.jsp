<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>


  <title>Q&A 게시판</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/sub.css"/>">
<script>
  window.onpageshow = function(event) {
    //back 이벤트 일 경우
    if (event.persisted) {
      location.reload(true);
    }
  }
</script>
<body>
<main id="notice">
  <h2 class="sub_h2_tit">Q&A 게시판</h2>
  <!--        게시판 시작   -->
  <div class="container_wrap">
    <!--     게시판 검색 영역       -->
    <div id="search_wrap" style="position: relative; top:80px;">
      <button id="list_search_btn">자신의 문의 검색</button>
      <p class="qna_write" style="position:relative; bottom:40px;"><a href="<c:url value="/qna/add?page="/>">1:1 문의하기</a></p>
      <script>
        //아이디로 게시글 조회
        $(document).ready(function (){
          $("#list_search_btn").click(function (){
            window.self.location="/bookshop/search/"+${sessionScope.user.id};
          });
        });
      </script>
    </div>
    <!--     게시판 목록 영역       -->
    <div id="board_wrap">
      <table id="list_wrap">
        <tr>
          <th class="bo_num">번호</th>
          <th class="bo_tit" width="650">제목</th>
          <th class="bo_writer">글쓴이</th>
          <th class="bo_regDate">등록일</th>
        </tr>
        <c:forEach items="${requestScope.pageResponse.getPageList()}" var="qna">
          <tr>
            <td class="bo_num"><c:out value="${qna.qno}"/></td>
            <td class="bo_tit"><a href="<c:url value='/qna/${qna.qno}'/>"><c:out value="${qna.title}"/></a></td>
            <td class="bo_writer"><c:out value="${qna.id}"/></td>
            <td class="bo_comments"><c:out value="${qna.getCommentCnt()}"/></td>
            <td class="bo_regDate"><fmt:formatDate value="${qna.getRegDate()}" pattern="yyyy/MM/dd" var="regDate"/>${regDate}</td>
          </tr>
        </c:forEach>
      </table>
      <!--페이징버튼-->
      <div class="nav">
        <ul>
          <c:if test="${pageResponse.showPrev}">
            <li class="nav_prev">
              <a href="<c:url value="/qna/qnas?page=${pageResponse.page-1}&size=${pageResponse.size}"/>">
                [PREV]
              </a>
            </li>
          </c:if>
          <c:forEach begin="${pageResponse.start}" end="${pageResponse.end}" var="num">
            <c:if test="${num!=0}">
              <li>
                <a href="<c:url value="/qna/qnas?page=${num}&size=${pageResponse.size}"/>"
                   class="${num==pageResponse.page?'sel':''}">${num} </a>
              </li>
            </c:if>
          </c:forEach>
          <c:if test="${pageResponse.showNext}">
            <li class="nav_next">
              <a href="<c:url value="/qna/qnas?page=${pageResponse.page+1}&size=${pageResponse.size}"/>">
                [NEXT]
              </a>
            </li>
          </c:if>
        </ul>
      </div>
      <!--페이징버튼-->
    </div>

  </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>