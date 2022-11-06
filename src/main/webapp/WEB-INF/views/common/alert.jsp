<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/11/06
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>alert</title>
</head>
<body>
    <script>
        //redirectAttributes를 받는 변수 선언,초기화
        let msg = "<c:out value='${msg}'/>";
        let url = "<c:out value='${url}'/>";

        //msg,url 디코딩+replace
        msg=decodeURIComponent(msg);
        msg=msg.replace('+',' ');
        url=url.replace(/amp;/gi, '');

        //alert()에 msg 출력후 url로 리다이렉트
        if(msg.length>0) {
            alert(msg);
            alert(url);
        }
        location.href = url;
    </script>
</body>
</html>
