<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/css/book.css"/>">
<!--book 리스트-->
<h3 style="font-size: 24px; margin-left:170px;">검색된 도서 목록 : ${pageResponse.total}개</h3>
<div style="height: 800px; margin-top:350px; padding-top:0;">
    <div>
    </div>
    <div class="row row-cols-4 row-cols-md-5 g-4 card_custum">
        <c:forEach items="${pageResponse.pageList}" var="book">
            <a href="<c:url value="/book/${book.bno}?page=${pageResponse.page}"/>">
                <div class="col size">
                    <div class="card h-100 custom1">
                        <!--커버 이미지가 없으면 기본 이미지 사용-->
                        <c:choose>
                            <c:when test="${book.storeFileName!=null}">
                                <c:choose>
                                    <c:when test="${book.size==0}">
                                        <img src="${book.storeFileName}"
                                             class="card-img-top" alt="..."
                                             width="200px" height="324px">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/resources/upload/images${book.storeFileName}"
                                             class="card-img-top" alt="no-image"
                                             width="200px" height="324px">
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="/resources/images/common/no-img.gif"/>"
                                     class="card-img-top" alt="..."
                                     width="200px" height="324px">
                            </c:otherwise>
                        </c:choose>
                        <!--커버 이미지가 없으면 기본 이미지 사용 끝-->
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 12px; font-weight: bold"><strong>제목</strong> : ${book.title}</h5>
                            <p class="card-text"><strong>저자</strong> : ${book.author}</p>
                            <p class="card-text"><strong>출판일</strong> : ${book.pubDate.toString().substring(0,10)}</p>
                            <p class="card-text"><strong>출판사</strong> : ${book.publisher}</p>
                        </div>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div>
<!--book 리스트-->

<!--페이징버튼-->
<div class="nav">
    <ul>
        <c:if test="${pageResponse.showPrev}">
            <li class="nav_prev">
                <a href="<c:url value="/book/books_new?page=${pageResponse.page-1}&size=${pageResponse.size}"/>">
                    [PREV]
                </a>
            </li>
        </c:if>
        <c:forEach begin="${pageResponse.start}" end="${pageResponse.end}" var="num">
            <li>
                <a href="<c:url value="/book/books_new?page=${num}&size=${pageResponse.size}"/>"
                   class="${num==pageResponse.page?'sel':''}">${num} </a>
            </li>
        </c:forEach>
        <c:if test="${pageResponse.showNext}">
            <li class="nav_next">
                <a href="<c:url value="/book/books_new?page=${pageResponse.page+1}&size=${pageResponse.size}"/>">
                    [NEXT]
                </a>
            </li>
        </c:if>
    </ul>
</div>
<!--페이징버튼-->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

