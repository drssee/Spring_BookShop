<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<style>
    main {
        width: 100%;
        height: 600px;
        margin-left:600px;
    }
    h3 {
        margin-left:150px;
        margin-bottom: 30px;
    }
    button {
        background-color: gray;
        color:#ffffff;
        margin-right: 30px;
        width: 130px;
        height: 60px;
    }
    button:hover {
        background-color: #333333;
    }
</style>
<main>
    <h3>${sessionScope.user.id}님의 마이페이지</h3>
    <button id="btn-update">유저정보 수정</button>
    <button id="btn-qna">나의 고객센터 문의</button>
    <button id="btn-result">결제목록 조회</button>
    <button id="btn-cart">장바구니 조회</button>
    <script>
        $(document).ready(function (){
            let id = '<c:out value="${sessionScope.user.id}"/>';

            $("#btn-update").click(function (){
               checkLogin(id);
               window.location="/bookshop/user/update/"+id;
            });

            $("#btn-qna").click(function (){
                checkLogin(id);
                window.location="/bookshop/qna/search/"+id;
            });

            $("#btn-result").click(function (){
                checkLogin(id);
                window.location="/bookshop/orders/list";
            });

            $("#btn-cart").click(function (){
                checkLogin(id);
                window.location="/bookshop/cart/carts";
            });
        });

        function checkLogin(id){
            if(id===''){
                alert('잘못된 접근입니다');
                window.location="/bookshop";
            }
        }
    </script>
    <div>
        <img src="<c:url value="/resources/images/main_page/main_background1.jpg"/>" alt="마이페이지 배경"
             style="position: relative; right:250px; bottom:-50px; z-index: -100;">
    </div>
</main>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>