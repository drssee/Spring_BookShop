<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>

<script type="text/javascript" src='${pageContext.request.contextPath }/resources/js/write.js' ></script>
<link rel="stylesheet" href='${pageContext.request.contextPath }/resources/css/write.css' />

<!-- list-style -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/list-style.css" />

<!-- body -->
<div class="row">
<div class="col-sm-11">
<div class="table-responsive">          
  <table class="table table-hover table-bordered">
  	<colgroup>
  		<col class="col-sm-1" />
  		<col class="col-sm-8" />
  		<col class="col-sm-1" />
  		<col class="col-sm-1" />
  		<col class="col-sm-1" />
  	</colgroup>
    <thead>
      <tr class="active">
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>날짜</th>
        <th>조회</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td><a href="">안녕하세요 첫글입니다</a><i class="fa fa-comment"></i>10</td>
        <td>운영자</td>
        <td>2017-10-11</td>
        <td>0</td>
      </tr>
    </tbody>
  </table>
  </div>
</div>
</div>

<!-- 페이징 -->
<div class="row" style="text-align: center;">
   <ul class="pagination pagination-lg">
  <li class="disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
  <li class="disabled"><a href="#"><i class="fa fa-angle-left"></i></a></li>    
  <li><a href="#">1</a></li>
  <li class="active"><a href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
  <li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
</ul>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>