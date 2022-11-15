<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!-- comment -->
<div class="row">
<div class="col-sm-12">
<h3>댓글</h3>
</div><!-- /col-sm-12 -->
</div><!-- /row -->
<div class="row">
<div class="comment">
<div class="col-sm-1">
<div class="thumbnail">
<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
</div><!-- /thumbnail -->
</div><!-- /col-sm-1 -->

<div class="col-sm-6">
<div class="panel panel-default">
<div class="panel-heading">
<strong>myusername</strong> <span class="text-muted">commented 5 days ago</span>
</div>
<div class="panel-body">
댓글 위치입니다
</div><!-- /panel-body -->
</div><!-- /panel panel-default -->
</div><!-- /col-sm-5 -->
</div><!-- /comment -->
</div><!-- /row -->


<!-- 댓글 등록 -->
<div class="row">
	<div class="col-md-3">
		<h3>댓글 쓰기</h3>	
	</div>
</div>   
<div class="row">   
	<div class="col-md-6">
		<form>
		<div class="panel panel-default">
				<div class="panel-heading">
					<strong>myusername</strong> <span class="text-muted">commented 5 days ago</span>
				</div>
				<div class="panel-body" style="padding: inherit;">
					<textarea placeholder="글쓴이는 댓글이 필요해요!" style="width: -webkit-fill-available;border: 0px;" ></textarea>				
				</div>
				<div class="panel-footer" style="text-align: -webkit-right;">				
					<button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Share</button>
				</div>
		</div><!-- Status Upload  -->
		</form>
	</div>       
</div>