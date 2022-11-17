<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/css/book.css"/>">
<!--book 리스트-->
<h3 style="font-size: 30px; font-style: italic; margin-left:170px;">베스트셀러 TOP10</h3>
<div style="height: 800px; margin-top:350px; padding-top:0;">
    <div>
    </div>
    <div class="row row-cols-4 row-cols-md-5 g-4 card_custum">
        <c:forEach items="${books}" var="book" varStatus="index">
            <a class="new_books_image" href="<c:url value="/book/${book.bno}"/>">

                <div class="col size">
                    <div style="font-size: 20px; position: relative; bottom:10px;">
                        <c:out value="${index.index+1}"/>
                    </div>
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
                        <div class="card-body" data-description="${book.description}">
                            <h5 class="card-title" style="font-size: 12px; font-weight: bold"><strong>제목</strong> : ${book.title}</h5>
                            <p class="card-text"><strong>저자</strong> : ${book.author}</p>
                            <p class="card-text"><strong>출간일</strong> : ${book.pubDate.toLocaleString().substring(0,12)}</p>
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
                    let description = $(".card-body",this).attr("data-description");
                    if(description!==''){
                        $(".card-body",this).stop().html('<p class="card-text">'+description+'</p>');
                    }
                }).mouseleave(function (){
                    $(".card-body",this).stop().html(thisDescription);
                });
            });
        </script>
    </div>
</div>
<!--book 리스트-->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

