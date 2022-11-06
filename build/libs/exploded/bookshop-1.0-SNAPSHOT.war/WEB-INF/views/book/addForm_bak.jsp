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
    <%--@elvariable id="book" type="com.example.book.controller.form.BookSaveForm"--%>
    <form:form commandName="book" action="/bookshop/book/add" method="post">
        <form:errors element="div"/>
        <label for="title">
            <input type="text" id="title" name="title" placeholder="<spring:message code="BookSaveForm.title"/>">
            <form:errors path="title"/>
            <spring:hasBindErrors name="book">
                <c:if test="${errors.hasFieldErrors('title')}">
                    <c:out value="${errors.getFieldValue('title')}는 잘못된 값입니다"/>
                    <c:out value="${errors.getFieldError('title').code}"/>
                </c:if>
            </spring:hasBindErrors>
        </label>
        <label for="pubDate">
            <input type="date" id="pubDate" name="pubDate" placeholder="<spring:message code="BookSaveForm.pubDate"/>">
            <form:errors path="pubDate"/>
        </label>
        <label for="author">
            <input type="text" id="author" name="author" value="${book.author}" placeholder="<spring:message code="BookSaveForm.author"/>">
            <form:errors path="author"/>
        </label>
        <label for="description"><!--수정해야함, textarea로-->
            <input type="text" id="description" name="description" placeholder="<spring:message code="BookSaveForm.description"/>">
            <form:errors path="description"/>
        </label>
        <label for="price">
            <input type="text" id="price" name="price" placeholder="<spring:message code="BookSaveForm.price"/>">
            <form:errors path="price"/>
        </label>
        <label for="stock">
            <input type="text" id="stock" name="stock" placeholder="<spring:message code="BookSaveForm.stock"/>">
            <form:errors path="stock"/>
        </label>
        <label for="cover"> <!--수정해야함, 이미지 업로드 기능 추가-->
            <input type="text" id="cover" name="stock" placeholder="<spring:message code="BookSaveForm.cover"/>">
            <form:errors path="stock"/>
        </label>
        <label for="publisher">
            <input type="text" id="publisher" name="publisher" placeholder="<spring:message code="BookSaveForm.publisher"/>">
            <form:errors path="publisher"/>
        </label>
        <label for="categoryName"><!--수정해야함, 옵션으로 고를수있게-->
            <input type="text" id="categoryName" name="categoryName" placeholder="<spring:message code="BookSaveForm.categoryName"/>">
            <form:errors path="categoryName"/>
        </label>
        <input type="hidden" name="page" value="${page}">
        <input type="hidden" name="size" value="${size}">
        <button type="submit"><spring:message code="submit"/></button>
    </form:form>
</body>
</html>
