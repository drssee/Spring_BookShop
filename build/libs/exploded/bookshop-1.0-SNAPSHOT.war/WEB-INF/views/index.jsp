<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%--<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">

<!--book(베스트셀러) 리스트-->
<div class="center" style="height: 450px; margin-top:50px; overflow: hidden;">
    <h2>베스트셀러</h2>
    <a class="right" href="<c:url value="/book/books_bs"/>"
       style="display: inline-block; width: 100px;height: 30px;">
        <span>더보기</span>
    </a>
    <div class="row row-cols-1 row-cols-md-5 g-4 card_custum">
        <c:forEach items="${book_bs}" var="book">
            <div class="col size">
                <a href="<c:url value="/book/search/${book.bno}"/>">
                    <div class="card custom1 custom_size">
                        <!--커버 이미지가 없으면 기본 이미지 사용-->
                        <c:choose>
                            <c:when test="${book.storeFileName!=null}">
                                <img src="${book.storeFileName}"
                                     class="card-img-top" alt="..."
                                     width="200px" height="324px">
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="/resources/images/common/no-img.gif"/>"
                                     class="card-img-top" alt="..."
                                     width="200px" height="324px">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<!--book(베스트셀러) 리스트-->



<!--book(새로나온책) 리스트-->
<div class="center" style="height: 450px; margin-top: 50px; overflow: hidden;">
    <h2>새로나온책</h2>
    <a class="right" href="<c:url value="/book/books_new"/>" style="margin-top:15px;">
        <span>더보기</span>
    </a>
    <div class="row row-cols-1 row-cols-md-5 g-4 card_custum">
        <c:forEach items="${book_new.pageList}" var="book">
            <div class="col size">
                <a href="<c:url value="/book/search/${book.bno}"/>">
                    <div class="card custom1 custom_size">
                        <!--커버 이미지가 없으면 기본 이미지 사용-->
                        <c:choose>
                            <c:when test="${book.storeFileName!=null}">
                                <img src="${book.storeFileName}"
                                     class="card-img-top" alt="..."
                                     width="200px" height="324px">
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="/resources/images/common/no-img.gif"/>"
                                     class="card-img-top" alt="..."
                                     width="200px" height="324px">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<!--book(새로나온책) 리스트-->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

