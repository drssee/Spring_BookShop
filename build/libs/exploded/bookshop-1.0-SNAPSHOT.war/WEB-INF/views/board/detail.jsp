<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    <% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Resources -->
<%@ include file="head.jsp" %>

<!-- body -->
<div class="row">
	<div class="col-md-9">
		<div id="postlist">        
        <div class="panel">
                <div class="panel-heading">
                    <div class="text-center">
                        <div class="row">
                            <div class="col-sm-9">
                                <h3 class="pull-left">제목</h3>
                            </div>
                            <div class="col-sm-3">
                                <h4 class="pull-right">
                                <small><em>2014-07-30 18:30:00</em></small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>                
            <div class="panel-body">
                <a href="#" class="thumbnail" style="width: fit-content;height: fit-content;">
                    <img alt="Image" src="http://i.imgur.com/tAHVmXi.jpg">
                </a>
                <span>별 하나에 추억과 별 하나에 사랑과 별 하나에 쓸쓸함과 별 하나에 동경과 별 하나에 시와 별 하나에 어머니, 어머니,</span>                
            </div>            
            <div class="panel-footer">
                <span class="label label-default">Image</span> <span class="label label-default">Updates</span> <span class="label label-default">July</span>
            </div>
        </div>
    </div>
</div>
	<div class="col-md-1"></div>
	<div class="col-md-3">
	</div>
	<div class="col-md-1">
	</div>
</div>

<!-- comments -->
<%@ include file="comment.jsp" %>