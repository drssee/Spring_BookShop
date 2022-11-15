<%@ page language="java" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/11/08
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<div id="bottom_wrap">
    <footer id="bottom">
        <address>
            <ul>
                <li>(주)하나투어</li>
                <li>대표:송미선,육경건</li>
                <li>주소:(03161)서울특별시 종로구 인사동 5길 41</li>
                <li>사업자등록번호:102-81-399440</li>
                <li><a href="#">사업자정보확인</a></li>
                <li>통신판매업신고번호:종로01-1806호</li>
                <li>관광사업자 등록번호:제1993-000006호</li>
                <li>개인정보 보호책임자:채철훈</li>
                <li>영업보증보험:22억 1천만원 가입</li>
                <li>팩스:02-734-0392</li>
                <li>이메일:<a href="#">15771233@hanatour.com</a></li>
                <li>고객센터 1577-1233</li>
                <li>해외항공권문의 1899-1833</li>
            </ul>
        </address>
        <p>
            COPYRIGHT©BEGOJA SPRING PROJECT
        </p>
    </footer>
    <!--bottom-->
</div>
<!--bottom_wrap-->
<div id="btn_top">
    <figure class="to_service_center">
        <p><img src="<c:url value="/resources/images/common/ico-chatting.png"/>" alt="#" width="45" height="45"></p>
        <figcaption>
            고객센터
        </figcaption>
    </figure>
    <div class="to_top">
        <p><span class="triangle"></span>top</p>
    </div>
</div>
<script>
    //우측탑메뉴
    $(window).scroll(function(){
        if($(this).scrollTop()>700){
            $("#btn_top").fadeIn(500);
        }
        else {
            $("#btn_top").fadeOut(300);
        }
        $(".to_service_center").click(function (){
           window.location="/bookshop";
        });
        $(".to_top").click(function (){
            $('html,body').stop().animate({scrollTop:0},300);
        });
    });
</script>
</body>
</html>