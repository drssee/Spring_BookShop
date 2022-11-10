<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/11/10
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<c:url value="/user/login"/>" method="post">
        <!--글로벌에러가 있을경우-->
        <spring:hasBindErrors name="user">
            <!--해당 객체에 오류가 있을때 오류 메시지 출력-->
            <c:if test="${errors.hasGlobalErrors()}">
                <div class="binding-error"><spring:message code="loginError"/></div>
            </c:if>
        </spring:hasBindErrors>
        <!--글로벌에러가 있을경우-->

        <!--id-->
        <label for="id">
            <input type="text" id="id" name="id" value="${bindingResult.getFieldValue("id")}" placeholder="<spring:message code="placeholder.user.id"/>">
        </label>
        <!--바인딩에러가 있을경우-->
        <spring:hasBindErrors name="user">
            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
            <c:if test="${errors.hasFieldErrors('id')}">
                <div class="binding-error">${errors.getFieldError("id").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <!--id-->

        <!--pwd-->
        <label for="pwd">
            <input type="text" id="pwd" name="pwd" value="${bindingResult.getFieldValue("pwd")}" placeholder="<spring:message code="placeholder.user.pwd"/>">
        </label>
        <!--바인딩에러가 있을경우-->
        <spring:hasBindErrors name="user">
            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
            <c:if test="${errors.hasFieldErrors('pwd')}">
                <div class="binding-error">${errors.getFieldError("pwd").defaultMessage}</div>
            </c:if>
        </spring:hasBindErrors>
        <button type="submit"><spring:message code="submit"/></button>
        <!--pwd-->
    </form>
</body>
</html>
