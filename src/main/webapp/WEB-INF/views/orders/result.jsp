<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value="/resources/css/result.css"/>">

<main id="result">
    <!--     상세페이지 영역       -->
    <div class="container_wrap">
        <section class="reserve_ok">
            <h1>구매가 완료되었습니다</h1>
            <p>자세한 내용은 마이페이지에서 확인하실 수 있습니다.</p>
            <p class="btn_result"><input id="myPage" type="button" value="마이페이지"><input id="toHome" type="button" value="홈으로"></p>
        </section>
        <script>
            $(document).ready(function (){
                $("#myPage").click(function(){
                    window.self.location="/bookshop/user/myPage";
                });
                $("#toHome").click(function(){
                    window.self.location="/bookshop";
                });
            })
        </script>
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>