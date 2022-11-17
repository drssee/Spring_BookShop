<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>


<style>
        .error_wrap{text-align: center; 
            padding: 100px 0; margin: 0 auto; max-width: 1200px;
        }
        .error_wrap h1{
            font-size: 4em;
            margin-bottom: 50px;
            color: #793dea;
        }
        
        .error_wrap h2{font-size: 1.7em; margin-bottom: 50px;}
        .error_wrap strong{margin-bottom: 50px; display: block; color: #999;}
        .error_wrap>p{background-color: #ece8ef; padding: 20px 0; margin-bottom: 5px;}
        .error_wrap a{display: block;  font-weight: bold; cursor: pointer; color: #793dea;}
        .error_wrap a:hover{text-decoration: underline;}
        #error_txt{display: none; margin-top: 30px; color: #999;}
    </style>
<main>
    <div class="error_wrap">
       <div>
          <h1>500 ERROR</h1>
           <h2> HTTP 500 - 내부 서버 오류.</h2>
           <strong>페이지가 작동하지 않습니다. <br> 연결하려는 페이지에 문제가 있어 표시할 수 없습니다.</strong>
       </div>
<%--       <p>--%>
<%--           <a onclick="showTxt()">에러 오류 확인:</a>--%>
<%--           <span id="error_txt">에러 내용 삽입</span>--%>
<%--       </p>--%>
       
       
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>


