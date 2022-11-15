<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="head.jsp" %>

<form>
<div class="col-md-9">
	<div class="form-group"> <!-- userId field -->
		<label class="control-label " for="userId">아이디</label>
		<input class="form-control" id="userId" name="userId" type="text"/>
	</div>
	
	<div class="form-group"> <!-- "title" field -->
		<label class="control-label " for="title">제목</label>
		<input class="form-control" id="title" name="title" type="text"/>
	</div>
	
	<div class="form-group"> <!-- content field -->
		<label class="control-label " for="content">내용</label>
		<textarea class="form-control" cols="40" id="content" name="content" rows="10"></textarea>
	</div>
	
	<div class="form-group"> <!-- File field -->
	   <input type="file" name="img[]" class="file" style="visibility: hidden;position: absolute;">
	   <div class="input-group col-xs-12">
	     <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
	     <input type="text" class="form-control input-lg" disabled placeholder="파일 업로드">
	     <span class="input-group-btn">
	       <button class="browse btn btn-primary input-lg" type="button"><i class="glyphicon glyphicon-search"></i> 파일</button>
	     </span>
	   </div>
	</div>
	
	<div class="form-group pull-right">
		<button class="btn btn-primary " name="submit" type="submit">글쓰기</button>
	</div>
	
</div>
	
	
</form>