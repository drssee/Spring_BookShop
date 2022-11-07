<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kimnamhyun
  Date: 2022/11/07
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<c:url value="/book/upload"/>" method="post" enctype="multipart/form-data">
        파일이름:<input type="text" name="originalFileName">
        파일선택:<input type="file" name="uploadFile">
        <input type="submit" value="전송">
    </form>

    <form action="<c:url value="/book/upload2"/>" method="post" enctype="multipart/form-data">
        파일이름:<input type="text" name="originalFileName">
        파일선택:<input type="file" multiple="multiple" name="uploadFiles">
        <input type="submit" value="전송">
    </form>
</body>
</html>
