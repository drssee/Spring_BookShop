<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/11/08
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>김남현</title>
    <script src="<c:url value="/resources/js/common/jquery-3.6.0.min.js"/>"></script>
    <script src="<c:url value="/resources/js/common/jquery.bxslider.min.js"/>"></script>
    <script src="<c:url value="/resources/js/common/jquery-ui-1.10.4.custom.min.js"/>"></script>
    <script src="<c:url value="/resources/js/header/header.js"/>"></script>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/common/jquery.bxslider.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/common/reset.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/header/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/footer/footer.css"/>">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/common/faviconV2.ico"/>"/>
</head>

<body>
<div id="top_wrap">
    <div class="s_banner_wrap">
        <ul class="bxslider">
            <li class="banner_0"></li>
            <li class="banner_1"></li>
            <li class="banner_2"></li>
            <li class="banner_3"></li>
        </ul>
    </div>
    <header id="top">
        <div class="quick_wrap1">
            <div class="quick_wrap2">
                <ul class="quick">
                    <li>
                        <a href="login.html">로그인</a>
                    </li>
                    <li>
                        <a href="register.html">회원가입</a>
                    </li>
                </ul>
                <!--quick-->
            </div>
            <!--quick wrap2-->
        </div>
        <!--quick wrap1-->
        <div id="main_navi_wrap">
            <h1>
                <a href="index.html">
                    <img src="<c:url value="/resources/images/common/main_logo.png"/>" alt="하나투어메인로고" width="165" height="52">
                </a>
            </h1>
            <p class="search">
                <input type="text" name="u_search" placeholder="제목을 입력해주세요" style="font-size: 12px">
                <img src="<c:url value="/resources/images/common/ico-search.png"/>" alt="돋보기" width="18" height="18" class="search_btn">
                <script>
                    $(".search_btn").click(function(){
                        let search_condition = $("input[name=u_search]").val();
                        window.location="#?=u_search="+search_condition; //검색 버튼 이동
                    });
                </script>
            </p>
            <!--search-->
            <nav id="main_navi">
                <ul>
                    <li><!--전체메뉴1-->
                        <a href="#">
                            <img src="<c:url value="/resources/images/common/three-bars.svg"/>" alt="카테고리">
                            카테고리
                        </a>
                    </li><!--전체메뉴1-->
                    <li class="sel"><a href="#">베스트셀러</a>
                    </li>
                    <li class="sel"><a href="#">새로나온책</a>
                    </li>
                    <li><a href="#">추천도서</a></li>
                    <li><a href="#">음반/영화</a>
                    </li>
                    <li><a href="#">고객센터</a>
                    </li>
                </ul>
            </nav>
            <!--main_navi-->
        </div>
    </header>
    <!--header top-->
</div>
<!--top_wrap-->
<div id="menu1_wrap">
    <ul id="menu1"><!--js메뉴1-->
        <li>
            <ul>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
            </ul>
        </li>
    </ul><!--main1-->
</div><!--main1_wrap-->