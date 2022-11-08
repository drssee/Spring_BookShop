<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/11/05
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <!--
    입력폼에 오류가 있어 다시 입력폼으로 돌아올 경우
    1.bindingResult에 저장된 fieldValue를 다시 보여주고
        validation 어노테이션에 설정한 메시지를 보여준다
    2.price,stock의 값이 숫자가 아닐경우
        typeMismatch를 체크해 다른 메시지를 보여줌
    -->

    <form action="<c:url value="/book/add"/>" method="post" enctype="multipart/form-data">
        <!--타이틀-->
        <label for="title">
            <input type="text" id="title" name="title" value="${bindingResult.getFieldValue("title")}" placeholder="<spring:message code="placeholder.book.title"/>">
        </label>
        <!--바인딩에러가 있을경우-->
        <spring:hasBindErrors name="book">
            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
            <c:if test="${errors.hasFieldErrors('title')}">
                <div class="binding-error">${errors.getFieldError("title").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--타이틀-->

        <!--출판일-->
        <label for="pubDate">
            <input type="date" id="pubDate" name="pubDate" value="${bindingResult.getFieldValue("pubDate")}" placeholder="<spring:message code="placeholder.book.pubDate"/>">
        </label>
        <spring:hasBindErrors name="book">
            <c:if test="${errors.hasFieldErrors('pubDate')}">
                <div class="binding-error">${errors.getFieldError("pubDate").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--출판일-->

        <!--저자-->
        <label for="author">
            <input type="text" id="author" name="author" value="${bindingResult.getFieldValue("author")}" placeholder="<spring:message code="placeholder.book.author"/>">
        </label>
        <spring:hasBindErrors name="book">
            <c:if test="${errors.hasFieldErrors('author')}">
                <div class="binding-error">${errors.getFieldError("author").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--저자-->

        <!--상세설명-->
        <label for="description"><!--수정해야함, textarea로-->
            <input type="text" id="description" name="description" value="${bindingResult.getFieldValue("description")}" placeholder="<spring:message code="placeholder.book.description"/>">
        </label>
        <spring:hasBindErrors name="book">
            <c:if test="${errors.hasFieldErrors('description')}">
                <div class="binding-error">${errors.getFieldError("description").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--상세설명-->

        <!--가격-->
        <label for="price">
            <input type="text" id="price" name="price" value="${bindingResult.getFieldValue("price")}" placeholder="<spring:message code="placeholder.book.price"/>">
        </label>
        <spring:hasBindErrors name="book">
            <!--price에 바인딩 오류가 있을때-->
            <c:if test="${errors.hasFieldErrors('price')}">
                <div class="binding-error">
                    <!--타입미스매치(numberformatException) 발생시 다른 오류메시지 생성-->
                    <!--validation이 작동하면 기본 메시지로-->
                    <c:choose>
                        <c:when test="${errors.getFieldError('price').defaultMessage.contains('java.lang.NumberFormatException')}">
                            <spring:message code="NumberFormat"/>
                        </c:when>
                        <c:otherwise>
                            ${errors.getFieldError("price").defaultMessage}
                        </c:otherwise>
                    </c:choose>
                 </div>
            </c:if>
        </spring:hasBindErrors>
        <!--가격-->

        <!--재고-->
        <label for="stock">
            <input type="text" id="stock" name="stock" value="${bindingResult.getFieldValue("stock")}" placeholder="<spring:message code="placeholder.book.stock"/>">

        </label>
        <spring:hasBindErrors name="book">
            <!--stock에 바인딩 오류가 있을때-->
            <c:if test="${errors.hasFieldErrors('price')}">
                <div class="binding-error">
                    <!--타입미스매치(numberformatException) 발생시 다른 오류메시지 생성-->
                    <!--validation이 작동하면 기본 메시지로-->
                    <c:choose>
                        <c:when test="${errors.getFieldError('stock').defaultMessage.contains('java.lang.NumberFormatException')}">
                            <spring:message code="NumberFormat"/>
                        </c:when>
                        <c:otherwise>
                            ${errors.getFieldError("stock").defaultMessage}
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </spring:hasBindErrors>
        <!--재고-->

        <!--출판사-->
        <label for="publisher">
            <input type="text" id="publisher" name="publisher" value="${bindingResult.getFieldValue("publisher")}" placeholder="<spring:message code="placeholder.book.publisher"/>">

        </label>
        <spring:hasBindErrors name="book">
            <c:if test="${errors.hasFieldErrors('publisher')}">
                <div class="binding-error">${errors.getFieldError("publisher").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--출판사-->

        <!--카테고리-->
        <label for="categoryName"><!--수정해야함, 옵션으로 고를수있게-->
            <input type="text" id="categoryName" name="categoryName" value="${bindingResult.getFieldValue("categoryName")}" placeholder="<spring:message code="placeholder.book.categoryName"/>">
        </label>
        <spring:hasBindErrors name="book">
            <c:if test="${errors.hasFieldErrors('categoryName')}">
                <div class="binding-error">${errors.getFieldError("categoryName").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--카테고리-->

        <!--이미지 업로드-->
        <label for="uploadFile">
            <input type="file" id="uploadFile" name="uploadFile" value="${bindingResult.getFieldValue("uploadFile")}" placeholder="<spring:message code="placeholder.book.uploadFile"/>">
        </label>
        <label for="uploadFiles">
            <input type="file" multiple="multiple" id="uploadFiles" name="uploadFiles" value="${bindingResult.getFieldValue("uploadFiles")}" placeholder="<spring:message code="placeholder.book.uploadFiles"/>">
        </label>

        <input type="hidden" name="page" value="${pageRequest.page}">
        <input type="hidden" name="size" value="${pageRequest.size}">
        <button type="submit"><spring:message code="add.submit"/></button>
    </form>
</body>
</html>
