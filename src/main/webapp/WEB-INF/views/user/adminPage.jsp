<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<style>
    main {
        width: 100%;
        height: 600px;
        margin-left:600px;
    }
    h3 {
        margin-left:150px;
        margin-bottom: 30px;
    }
    button {
        background-color: gray;
        color:#ffffff;
        margin-right: 30px;
        width: 130px;
        height: 60px;
    }
    button:hover {
        background-color: #333333;
    }
</style>
<main>
    <h3>관리자 메뉴</h3>
    <button id="btn-addBook">물품 등록</button>
    <button id="btn-updateBook">물품 관리</button>
    <button id="btn-userManagement">고객 관리</button>

    <script>
        $(document).ready(function (){
            let id = '<c:out value="${sessionScope.user.id}"/>';

            $("#btn-addBook").click(function (){
               checkLogin(id);
               window.location="/bookshop/book/add";
            });

            $("#btn-updateBook").click(function (){
                checkLogin(id);
                let keyword = prompt('수정할 상품의 이름을 입력해주세요.');
                if(keyword===''||keyword===null){
                    alert('다시 입력해주세요');
                    return;
                }
                window.location="/bookshop/book/search?keyword="+keyword+"&option=T"; //검색 버튼 이동
            });

            $("#btn-userManagement").click(function (){
                checkLogin(id);
                let searchUser = prompt('조회할 유저아이디를 입력해주세요.');
                if(searchUser===''||searchUser===null){
                    alert('다시 입력해주세요');
                    return;
                }
                getUser(searchUser);
            });

        });//document.ready

        function checkLogin(id){
            if(id===''||'admin'!==id){
                alert('잘못된 접근입니다');
                window.location="/bookshop";
            }
        }

        let getUser = function(id) {
            $.ajax({
                url: '/bookshop/user/admin/search/'+id,
                type: 'GET',
                headers: {"content-type":"application/json"},

                success : function(result){
                    if(result) {
                        window.location="/bookshop/orders/list/"+id;
                    } else{
                        alert("유저 조회에 실패했습니다");
                    }
                },
                error: function(request, status, error) {
                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    alert("유저 조회에 실패했습니다");
                }
            });//ajax
        }//getUser
    </script>
    <div>
        <img src="<c:url value="/resources/images/main_page/main_background2.jpg"/>" alt="관리자메뉴 배경"
             style="position: relative; right:250px; bottom:-50px; z-index: -100;">
    </div>
</main>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>