<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/css/book.css"/>">
<!--book 리스트-->
<script>
    $(document).ready(function (){
        let currentMonth = Number(new Date().getMonth())+1;
        let count = '<c:out value="${pageResponse.total}"/>';
        $("#new_books").text(currentMonth+'월 새로나온 도서 목록');
    });
</script>
<h3 id="new_books" style="font-size: 28px; font-style:italic; margin-left:170px;"></h3>
<div style="height: 800px; margin-top:350px; padding-top:0;">
    <div class="row row-cols-4 row-cols-md-5 g-4 card_custum">
        <c:forEach items="${pageResponse.pageList}" var="book">
            <a class="new_books_image" href="<c:url value="/book/${book.bno}?page=${pageResponse.page}"/>">
                <div class="new_books_description" style="display: none; font-size: 20px;">
                    <span>출간일 : ${book.pubDate.toLocaleString().substring(0,12)}</span><br>
                    <span>가격 : ${book.price}원</span>
                </div>
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
                        <div class="card-body" data-pubDate="${book.pubDate.toLocaleString().substring(0,12)}" data-description="${book.description}">
                            <h5 class="card-title" style="font-size: 12px; font-weight: bold"><strong>제목</strong> : ${book.title}</h5>
                            <p class="card-text"><strong>저자</strong> : ${book.author}</p>
                            <p class="card-text"><strong>출판일</strong> : ${book.pubDate.toLocaleString().substring(0,12)}</p>
                            <p class="card-text"><strong>출판사</strong> : ${book.publisher}</p>
                        </div>
                    </div>
                </div>
            </a>
        </c:forEach>
        <script>
            $(document).ready(function (){
                let thisDescription = $(".card-body",this).html();
                $(".new_books_image").mouseenter(function (){
                    let pubDate =  $(".card-body",this).attr("data-pubDate");
                    let description = $(".card-body",this).attr("data-description");
                    $(".card-body",this).html('<h5 class="card-title"><strong style="color:purple;">출판일<strong> : '+pubDate+'</h5><p class="card-text">'+description+'</p>');
                }).mouseleave(function (){
                    $(".card-body",this).html(thisDescription);
                });
            });
        </script>
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

