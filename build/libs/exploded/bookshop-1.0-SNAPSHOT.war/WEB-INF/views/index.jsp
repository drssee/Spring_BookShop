<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
<div class="row row-cols-1 row-cols-md-3 g-4">
    <div class="col">
        <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a short card.</p>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
            </div>
        </div>
    </div>
</div>
<%--<main id="list_wrap">--%>
<%--    <ul id="list">--%>
<%--        <h2>책 목록</h2>--%>
<%--        <c:forEach items="${pageResponse.pageList}" var="book">--%>
<%--            <li>--%>
<%--                <a href="<c:url value="/book/${book.bno}&page=${pageResponse.page}"/>">--%>
<%--                    <p id="list_img">--%>
<%--                        <img src="${book.storeFileName!=null?book.storeFileName:"../../resources/images/common/noimg.gif"}" alt="">--%>
<%--                    </p>--%>
<%--                    <div class="list_box">--%>
<%--                        <p id="list_pro_name">${book.title}</p>--%>
<%--                        <ul id="list_pro_info">--%>
<%--                            <li><strong>저자</strong><span>${book.author}</span></li>--%>
<%--                            <li><strong>출판일</strong><span>${book.pubDate.toString().substring(0,10)}</span></li>--%>
<%--                            <li><strong>출판사</strong><span>${book.publisher}</span></li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </c:forEach>--%>
<%--        <div class="nav">--%>
<%--            <ul>--%>
<%--                <c:if test="${pageResponse.showPrev}">--%>
<%--                    <li class="nav_prev">--%>
<%--                        <a href="<c:url value="/?page=${pageResponse.page-1}&size=${pageResponse.size}"/>">--%>
<%--                            [PREV]--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </c:if>--%>
<%--                <c:forEach begin="${pageResponse.start}" end="${pageResponse.end}" var="num">--%>
<%--                    <li>--%>
<%--                        <a href="<c:url value="/?page=${num}&size=${pageResponse.size}"/>"--%>
<%--                           class="${num==pageResponse.page?'sel':''}">${num} </a>--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--                <c:if test="${pageResponse.showNext}">--%>
<%--                    <li class="nav_next">--%>
<%--                        <a href="<c:url value="/?page=${pageResponse.page+1}&size=${pageResponse.size}"/>">--%>
<%--                            [NEXT]--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </c:if>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </ul>--%>
<%--</main>--%>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

