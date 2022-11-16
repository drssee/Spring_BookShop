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
                <li style="font-style: italic">BEGOJA</li>
                <li style="font-style: italic">김남현</li>
                <li><a style="font-weight: bold; font-style: italic; text-decoration: underline;"
                       href="https://github.com/drssee/Spring_bookShop.git" target="_blank">Git: Spring_BookShop</a></li>
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
           window.location="/bookshop/qna/qnas";
        });
        $(".to_top").click(function (){
            $('html,body').stop().animate({scrollTop:0},300);
        });
    });
</script>
</body>
</html>