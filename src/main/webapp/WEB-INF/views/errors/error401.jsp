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
          <h1>401 ERROR</h1>
           <h2> UNAUTHORIZED.</h2>
           <strong> 로그인이 필요한 페이지 입니다. <br> 다시 한 번 확인해 주세요.</strong>
       </div>
<%--       <p>--%>
<%--           <a onclick="showTxt()">에러 오류 확인:</a>--%>
<%--           <span id="error_txt">에러 내용 삽입</span>--%>
<%--       </p>--%>
       
       
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>


