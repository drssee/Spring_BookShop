<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/login_cookie.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
    <div id="wrap">
        <main id="main">
            <div id="login_bg">

            </div>
            <div id="login">
                <div id="top_wrap_login">
                    <h1><a href="<c:url value="/"/>">Login</a></h1>
                </div>

                <div id="login_con">
                    <div id="login1" class="login_box">
                        <form action="<c:url value="/user/login"/>" method="post">
                            <script>
                                <spring:hasBindErrors name="user">
                                    alert('아이디 또는 비밀번호를 확인해주세요.');
                                </spring:hasBindErrors>
                            </script>

                            <!--글로벌에러가 있을경우-->
                            <spring:hasBindErrors name="user">
                                <!--해당 객체에 오류가 있을때 오류 메시지 출력-->
                                <c:if test="${errors.hasGlobalErrors()}">
                                    <div class="binding-error"><spring:message code="loginError"/></div>
                                </c:if>
                            </spring:hasBindErrors>
                            <!--글로벌에러가 있을경우-->

                            <!--바인딩에러가 있을경우-->
                            <spring:hasBindErrors name="user">
                                <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                                <c:if test="${errors.hasFieldErrors('id')}">
                                    <div class="binding-error">${errors.getFieldError("id").defaultMessage}</div>
                                </c:if>
                            </spring:hasBindErrors>
                            <!--id-->
                            <p class="u_txt"><label for="u_id" class="u_id_txt"></label>
                                <input type="text" id="u_id" autocomplete="off" name="id" value="${bindingResult.getFieldValue("id")}" placeholder="<spring:message code="placeholder.user.id"/>">
                            </p>

                            <!--바인딩에러가 있을경우-->
                            <spring:hasBindErrors name="user">
                                <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                                <c:if test="${errors.hasFieldErrors('pwd')}">
                                    <div class="binding-error">${errors.getFieldError("pwd").defaultMessage}</div>
                                </c:if>
                            </spring:hasBindErrors>
                            <p class="u_txt"><label for="u_id"></label>
                                <label for="u_pwd"></label><input type="password" id="u_pwd" autocomplete="off" name="pwd" value="${bindingResult.getFieldValue("pwd")}" placeholder="<spring:message code="placeholder.user.pwd"/>">
                            </p>

                            <!--    아이디저장/로그인상태유지     -->
                            <p id="chk_wrap1">
                                <input type="checkbox" name="chk1" id="chk1" value="1">
                                <label for="chk1" class="type01 type02">아이디 저장</label>
                                <input type="checkbox" name="chk2" id="chk2" value="2">
                                <label for="chk2" class="type01 type03">로그인 상태 유지</label>
                            </p>
                            <p id="btn_wrap1">
                                <input type="submit" id="s_btn1" class="s_btn" value="로그인">
                            </p><!-- btn_wrap1 -->

                            <p class="login_account">
                                <a href="#" class="search1" onclick="alert('준비중입니다.'); return false;">아이디/비밀번호 찾기</a>
                                <a href="<c:url value="/user/join"/>" class="join1">회원가입</a>
                            </p>
                        </form>
                    </div><!-- 회원로그인, login1 -->
                </div><!-- login_con -->
            </div>
        </main>
    </div>


    <script>
        //체크박스 css클래스 추가,제거
        $("#chk1,#chk2").change(function() {
            var chk1 = $("#chk1").prop("checked");
            var chk2 = $("#chk2").prop("checked");
            if (chk1) {
                $(".type02").addClass("checked");
            } else {
                $(".type02").removeClass("checked");
            }
            if (chk2) {
                $(".type03").addClass("checked");
            } else {
                $(".type03").removeClass("checked");
            }
        })
    </script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>