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
    <script src="<c:url value="/resources/js/common/jquery-ui-1.10.4.custom.min.js"/>"></script>
    <script src="<c:url value="/resources/js/header/header.js"/>"></script>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/common/reset.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/header/header_nobanner.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/footer/footer.css"/>">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/common/faviconV2.ico"/>"/>
</head>

<body>
<div id="top_wrap" style="height: 200px;">
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
                <a href="<c:url value="/"/>">
                    <img src="<c:url value="/resources/images/common/main_logo.png"/>" alt="하나투어메인로고" width="165" height="52">
                </a>
            </h1>
            <p class="search">
                <input type="text" name="u_search" placeholder="제목을 입력해주세요" style="font-size: 12px">
                <img src="<c:url value="/resources/images/common/ico-search.png"/>" alt="돋보기" width="18" height="18" class="search_btn">
                <script>
                    $(".search_btn").click(function(){
                        let search_condition = $("input[name=u_search]").val();
                        window.location="/bookshop/book/search?keyword="+search_condition+"&option=T"; //검색 버튼 이동
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
                    <li class="sel"><a href="<c:url value="/book/books_bs"/>">베스트셀러</a>
                    </li>
                    <li class="sel"><a href="<c:url value="/book/books"/>">새로나온책</a>
                    </li>
                    <li><a href="<c:url value="/book/books_new"/>">출판예정도서</a></li>
                    <li><a href="#" onclick="alert('준비중인 메뉴입니다.')">음반/영화</a>
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
        </li>
    </ul><!--main1-->
</div><!--main1_wrap-->
<script>
    $(document).ready(function() {
        getCategorys();
        //카테고리
        $("#menu1_wrap").hide();
        $("#main_navi ul li").eq(0).click(function () {
            $("#menu1_wrap").toggle();
        });
    });

    let getCategorys = function(){
        $.ajax({
            url: '/bookshop/categorys',
            type: 'GET',
            headers: {"content-type": "application/json"},
            success: function (result) {
                $("#menu1 li").html(toHtml(result))
            },
            error: function () {
                alert("error");
            }
        });//ajax
    }

    //배열로 들어온 (js 객체를 html 문자로) 바꿔주는 함수
    let toHtml = function(categorys) {
        let tmp = '<ul>'
        for(let i = 0; i<categorys.length; i++){
            let category = categorys[i];
            if(category==null||category===''||category==='undefined'){
                category='기타';
            }
            tmp+='<li>'
            tmp += '<a href="/bookshop/book/search?keyword='+category+'&option=C">'+category+'</a>'
            tmp += '</li>';
        }
        tmp += '</ul>';
        return tmp;
    }
</script>