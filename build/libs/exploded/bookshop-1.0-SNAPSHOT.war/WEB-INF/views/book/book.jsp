<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 아임포트 -->
<script src ="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/comment.css"/>">
<style>
    button {
        border:none;
        outline: none;
    }
</style>
<!--book section-->
<section class="py-5" style="height: 1200px;">
    <div class="container px-4 px-lg-5 my-5">
        <div class="container" style="width: 350px;height: 480px; float:left; margin-top:100px; ">
            <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner" style="cursor: pointer;">
                    <!--내부 이미지가 있는경우 내부 이미지리스트 출력-->
                    <!--내부 이미지가 없는경우 커버 이미지 출력-->
                    <c:choose>
                        <c:when test="${images.size()>0}">
                            <c:forEach items="${images}" var="image" varStatus="index">
                                <c:if test="${index.first}">
                                    <div class="carousel-item active">
                                        <img width="350" height="480" src="${pageContext.request.contextPath}/resources/upload/images${image.storeFileName}" class="d-block w-100 full-image-size" alt="...">
                                    </div>
                                </c:if>
                                <c:if test="${!index.first}">
                                    <div class="carousel-item">
                                        <img width="350" height="480" src="${pageContext.request.contextPath}/resources/upload/images${image.storeFileName}" class="d-block w-100 full-image-size" alt="...">
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <c:choose>
                                <c:when test="${book.storeFileName!=null}">
                                    <c:choose>
                                        <c:when test="${book.size==0}">
                                            <img width="350" height="480" src="${book.storeFileName}" class="d-block w-100 full-image-size" alt="...">
                                        </c:when>
                                        <c:otherwise>
                                            <img width="350" height="480" src="${pageContext.request.contextPath}/resources/upload/images${book.storeFileName}" class="d-block w-100 full-image-size" alt="...">
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <img width="259" height="195" src="data:image/png;base64,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" class="d-block w-100 full-image-size" alt="no-img">
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                    <!--내부 이미지가 있는경우 내부 이미지리스트 출력-->
                    <!--내부 이미지가 없는경우 커버 이미지 출력-->
                </div>
                <!--carousel-inner-->

                <!--캐러셀 prev,next btn-->
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
                <!--캐러셀 prev,next btn-->
            </div>
            <!--carouselExampleControls-->
        </div>
        <!--inner container-->
        <script>
            //이미지클릭->전체이미지
            $(".full-image-size").click(function(){
                window.location=$(this).attr("src");
            })
        </script>
            <!--상품 상세 정보-->
            <div class="col-md-6" style="float:right; margin-top:100px; margin-left:12px;">
                <h1 class="display-5 fw-bolder">${book.title}</h1>
                <div class="fs-5 mb-5" style="margin-top:20px;">
                    <span>${book.price}원</span>
                </div>
                <p class="lead" style="margin-top:30px;">${book.author}</p>
                <p class="lead" style="margin-top:20px;">${book.publisher}</p>
                <p class="lead" style="margin-top:20px; margin-bottom: 20px;">${book.description}</p>
                <div class="d-flex">
                    <input type='button' onclick='count("minus")' value='-'/>
                    <input id="quantity" name="quantity" class="form-control text-center me-3" type="text" value="1" style="max-width: 3rem" readonly/>
                    <input type='button' onclick='count("plus")' value='+' style="margin-right:10px; position:relative; right:15px;"/>
                    <input type="hidden" name="bno" value="${book.bno}">
                    <input type="hidden" name="page" value="${bnoDto.page}">
                    <input type="hidden" name="size" value="${bnoDto.size}">
                    <button id="btn-like" style="margin-right: 10px;" class="btn btn-outline-dark flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1">좋아요!</i>
                        <span id="like_cnt">${book.like_cnt}개</span>
                    </button>
                    <script>
                        /**
                         * 좋아요 카운트 js
                         */
                        //좋아요 카운트+1개
                        $("#btn-like").click(function(){
                            //로그인 확인
                            if(${sessionScope.user.id==null}){
                                alert("먼저 로그인을 해주세요");
                                return;
                            }
                            if(!confirm('<c:out value="${book.title}"/>(을)를 추천 하시겠습니까?')){
                                return;
                            }
                            let bno = <c:out value="${book.bno}"/>
                            userLike(bno);
                        });

                        let userLike = function(bno){
                            //좋아요 중복 클릭 확인 + 좋아요 카운트+1
                            $.ajax({
                                url: '/bookshop/book/like/'+bno,
                                type: 'GET',
                                headers: {"content-type":"application/json"},

                                success : function(result){
                                    alert('해당 도서를 추천했습니다.');
                                    $("#like_cnt").html(result+'개');
                                },
                                error: function(request, status, error) {
                                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                                    alert("이미 추천한 도서입니다.");
                                }
                            });//ajax
                        };//validUserLike
                    </script>
                    <button style="margin-right: 10px;" id="btn-cart" class="btn btn-outline-dark flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1">장바구니 담기</i>
                    </button>
                    <button id="btn-buy" class="btn btn-outline-dark flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1">구매</i>
                    </button>
                    <script>

                        let buyerName = '<c:out value="${sessionScope.user.id}"/>';
                        let buyerEmail = '<c:out value="${sessionScope.user.email}"/>';
                        let buyerTel = '<c:out value="${sessionScope.user.phone}"/>';
                        let bno = '<c:out value="${book.bno}"/>';
                        let stock = '<c:out value="${book.stock}"/>';
                        let bookName = '<c:out value="${book.title}"/>';
                        let price = '<c:out value="${book.price}"/>';


                        $(document).ready(function(){

                            let IMP = window.IMP; //import payment == imp
                            // let code = 'imp41427810'; //가맹점 식별코드
                            let code = '<spring:message code="imp"/>'; //가맹점 식별코드
                            //구매 버튼 클릭
                            $("#btn-buy").click(function (){
                                if(buyerName===''){
                                    alert('잘못된 접근입니다');
                                    return;
                                }
                                //구매버튼 클릭시의 value
                                let quantity = document.getElementById('quantity').value;
                                //재고 체크
                                if(quantity>stock){
                                    let outOfStock = quantity-stock;
                                    alert('재고가 '+outOfStock+'개 부족합니다');
                                    return;
                                }
                                //확인
                                if(!confirm(bookName+' '+quantity+'개(을)를 구매하시겠습니까?')) {
                                    return;
                                }

                                let pay_amount = quantity*Number(price);
                                IMP.init(code);
                                //결제 요청
                                IMP.request_pay({ //파라미터
                                    pg: 'html5_inicis',
                                    pay_method: 'card',
                                    merchant_uid : 'merchant_' + new Date().getTime(),
                                    name: '프로젝트결제', //결제창에서 보여질 이름
                                    amount: pay_amount, //실제 결제되는 가격
                                    buyer_name: buyerName,
                                    buyer_tel: buyerTel,
                                    buyer_email: buyerEmail,
                                    // buyer_addr: '',
                                    // buyer_postcode: '',
                                }, function(rsp) {
                                    console.log(rsp);
                                    // 결제검증
                                    $.ajax({
                                        type: "POST",
                                        url: "/bookshop/orders/verifyIamport/"+rsp.imp_uid
                                    }).done(function (data) {
                                        console.log(data);
                                        // 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
                                        if (rsp.paid_amount === data.response.amount) {
                                            //검증 성공시
                                            console.log("결제 및 결제검증완료");
                                            //result의 멤버 객체배열인 ordersBookForms 초기화
                                            let ordersBookForms = [];
                                            ordersBookForms.push({
                                                bno: '<c:out value="${book.bno}"/>',
                                                order_price: '<c:out value="${book.price}"/>',
                                                order_quantity: quantity
                                            });

                                            //서버에 보낼 결제 정보 객체 result 초기화
                                            let result = {
                                                id: buyerName,
                                                order_date : new Date(), //결제날짜
                                                total_price : rsp.paid_amount, //결제금액
                                                merchant_uid : rsp.merchant_uid, //주문번호
                                                imp_uid : rsp.imp_uid, //결제번호
                                                ordersBookForms : ordersBookForms,
                                                fromCart: false
                                            }
                                            console.log(result);

                                            $.ajax({
                                                url: '/bookshop/orders/payment',
                                                type: 'POST',
                                                headers: {"content-type":"application/json"},
                                                data: JSON.stringify(result),

                                                success : function(result) {
                                                    alert("결제에 성공 했습니다.");
                                                    location.href='/bookshop/orders/result'; //결제완료 페이지로 이동
                                                },
                                                error: function(request, status, error) {
                                                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                                                    alert("결제에 실패 했습니다. 진행중인 결제를 취소합니다.");
                                                    //결제 취소기능
                                                    cancelPayment(rsp.imp_uid);
                                                }
                                            });//ajax
                                        } //if outer

                                        else { //검증 실패시
                                            alert("결제에 실패하였습니다. "+rsp.error_msg);
                                        }
                                    });//done
                                });//function
                            });//btn-payment.click
                        });//document.ready

                        let cancelPayment = function(imp_uid){
                            $.ajax({
                                type: "POST",
                                url: "/bookshop/orders/deleteIamport/"+imp_uid
                            }).done(function (data) {
                                if(data){
                                    alert('결제 취소 성공');
                                    location.reload();
                                } else{
                                    alert('결제 취소 실패');
                                }
                            });//function
                        }
                    </script>
                    <script>
                        /**
                         * 장바구니 js
                         */
                        let id = '<c:out value="${sessionScope.user.id}"/>';

                        //카트담기 버튼 클릭
                        $("#btn-cart").click(function(){
                            //수량은 가변적, 클릭당시 값을 가져와야함
                            let quantity = document.getElementById('quantity').value;
                            //로그인 체크
                            if(!checkLogin(id)){
                                return;
                            }
                            //재고 체크
                            if(quantity>stock){
                                let outOfStock = quantity-stock;
                                alert('재고가 '+outOfStock+'개 부족합니다');
                                return;
                            }
                            //확인
                            if(!confirm(bookName+' '+quantity+'개(을)를 장바구니에 담으시겠습니까?')) {
                                return;
                            }

                            //cartForm 초기화
                            let cartForm = {
                                id: id,
                                bno: bno,
                                quantity: quantity
                            }
                            //cart 상품 중복체크 + cart 저장
                            saveCart(cartForm);
                        });

                        //장바구니에 같은 상품이 있는지 중복 체크
                        let saveCart = function(cartForm){
                            $.ajax({
                                url: '/bookshop/cart/check/'+cartForm.bno,
                                type: 'GET',
                                headers: {"content-type":"application/json"},

                                success : function(result){
                                    if(result){
                                        //중복이 없을경우, 유저의 카트아이템 갯수 체크
                                        checkCartSize(cartForm);
                                    } else{
                                        alert(bookName+'(은)는 이미 장바구니에 존재합니다.');
                                    }
                                },
                                error: function(request, status, error) {
                                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                                    alert('장바구니 추가에 실패했습니다.');
                                }
                            });//ajax
                        }//checkCart

                        //유저의 카트아이템 갯수 체크
                        let checkCartSize = function(cartForm){
                            $.ajax({
                                url: '/bookshop/cart/size/'+cartForm.id,
                                type: 'GET',
                                headers: {"content-type":"application/json"},

                                success : function(result){
                                    if(result>=10){
                                        //카트 아이템 갯수 체크
                                        alert('장바구니엔 최대 10개의 상품까지 담을 수 있습니다.');
                                    } else{
                                        //장바구니에 담기
                                        insertIntoCart(cartForm);
                                    }
                                },
                                error: function(request, status, error) {
                                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                                    alert('장바구니 추가에 실패했습니다.');
                                }
                            });//ajax
                        }//checkCart

                        //장바구니에 담기
                        let insertIntoCart = function(cartForm){
                            $.ajax({
                                url: '/bookshop/cart/save',
                                type: 'POST',
                                headers: {"content-type":"application/json"},
                                data: JSON.stringify(cartForm),

                                success : function(result){
                                    alert('장바구니 페이지로 이동합니다');
                                    location.href="/bookshop/cart/carts";
                                },
                                error: function(request, status, error) {
                                    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                                    alert("장바구니 추가에 실패했습니다.");
                                }
                            });//ajax
                        }//saveInCart

                        //로그인 체크
                        function checkLogin(id){
                            if(id===''){
                                alert('먼저 로그인 해주세요');
                                return false;
                            }
                            return true;
                        }

                        //장바구니 물품 수량 카운트 체크
                        function count(type)  {
                            // 결과를 표시할 element
                            const resultElement = document.getElementById('quantity');
                            let number = resultElement.value;
                            //장바구니에 1 ~ 10개만 담기게
                            if(type === 'plus') {
                                number = parseInt(number) + 1;
                                if(number > 10){
                                    number = 10; alert('최대 수량은 10개 입니다.');
                                }
                            }
                            else if(type === 'minus')  {
                                number = parseInt(number) - 1;
                                if(number < 1){
                                    number = 1;
                                }
                            }
                            // 결과 출력
                            resultElement.value = number;
                        }//count
                    </script>
                </div><!--div.d-flex-->
            </div><!--상품 상세 정보-->
    </div><!--outer container-->
</section><!--book section-->

<!--js를 이용해 commentlist를 출력하는 div부분-->
<div id="commentList" data-bno="${book.bno}" data-id="${sessionScope.user.id}">
</div>

<!--답글 작성 box-->
<div id="reply-writebox">
    <div class="commenter commenter-writebox">${sessionScope.user.id==null?'비로그인 유저':sessionScope.user.id}</div>
    <div class="reply-writebox-content">
        <textarea name="write-comment" class="write-comment" id="write-comment" cols="30" rows="3" placeholder="한줄평을 남겨보세요"></textarea>
    </div>
    <div id="reply-writebox-bottom">
        <div class="register-box">
            <button class="btn btn-write-reply" id="btn-write-reply">등록</button>
            <button class="btn btn-cancel-reply" style="position:relative;bottom:5px;left:10px;">취소</button>
        </div>
    </div>
</div>
<!--답글 작성 box-->

<!--수정 modal box-->
<div id="modalWin" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p>
        <h2> | 댓글 수정</h2>
        <div id="modify-writebox">
            <div class="commenter commenter-writebox" id="commenter-writebox"></div>
            <div class="modify-writebox-content">
                <textarea name="modify-comment" id="modify-comment" cols="30" rows="5" placeholder="댓글을 남겨보세요"></textarea>
            </div>
            <div id="modify-writebox-bottom">
                <div class="register-box">
                    <button class="btn" id="btn-write-modify">등록</button>
                </div>
            </div>
        </div>
        </p>
    </div>
</div>
<!--수정 modal box-->

<script>
    /**
     * 리뷰 js
     */
    let commentList = $("#commentList");
    let data_bno = commentList.attr("data-bno");
    //세션에 로그인된 유저 아이디
    let data_id = commentList.attr("data-id");

    $(document).ready(function(){
        getReviews(data_bno);

        //답글 쓰기 클릭시 답글 작성 폼 보이기 + //답글 작성
        commentList.on("click",".btn-write",function(){
            let bno = $(this).attr("data-bno");
            let rno = $(this).attr("data-rno");
            let prno = $(this).attr("data-prno");

            let repForm = $("#reply-writebox");
            repForm.appendTo($(this).parent());
            repForm.css("display", "block");
            // repForm.attr("data-bno", bno);
            repForm.attr("data-rno",  rno);
            repForm.attr("data-prno",  prno);
        });

        //답글창 닫기
        $(".btn-cancel-reply").click(function(e){
            $("#write-comment").text('');
            $("#reply-writebox").css("display", "none");
        });

        //답글 작성 버튼
        $(".btn-write-reply").click(function(){

            let repForm = $("#reply-writebox");

            if(data_id===''){
                alert('먼저 로그인을 하셔야 합니다');
                return;
            }
            let data_content = $(".write-comment").val();
            if(data_content.length<=0) {
                alert('내용을 작성해 주세요');
                return;
            }
            let reviewVO = {
                rno: repForm.attr("data-rno"),
                bno: data_bno,
                id: data_id,
                content: data_content,
                prno: repForm.attr("data-prno")
            }
            writeReview(reviewVO);
        });

        //메인 댓글창 등록
        commentList.on("click",".btn-write-comment",function() {
            if(data_id===''){
                alert('먼저 로그인을 하셔야 합니다');
                return;
            }
            let data_content = $(".comment-write").val();
            if(data_content.length<=0) {
                alert('내용을 작성해 주세요');
                return;
            }
            let reviewVO = {
                bno: data_bno,
                id: data_id,
                content: data_content,
            }
            writeReview(reviewVO);
        });

        //수정 모달창 열기
        /*
        val()=input,textarea에 사용
        text(),html()=그외
         */
        commentList.on("click",".btn-modify", function(){
            //수정은 로그인한 유저 + 작성자만 가능 해야함
            if(data_id===''){
                alert('먼저 로그인을 하셔야 합니다');
                return;
            }
            //부모중 태그가 li인 첫번째 부모 1개만 찾아서 반환
            let li = $(this).closest("li");
            let commenter = $(".commenter", li).text();
            let comment = $(".comment-content", li).text();
            if(data_id!==commenter) {
                alert('본인의 리뷰만 수정 가능합니다');
                return;
            }

            let bno = $(this).attr("data-bno");
            let rno = $(this).attr("data-rno");
            let prno = $(this).attr("data-prno");
            let btn_write_modify = $("#btn-write-modify");

            $("#modalWin .commenter").text(commenter);
            $("#modalWin textarea").val(comment);
            btn_write_modify.attr("data-rno" , rno);
            btn_write_modify.attr("data-prno" , prno);

            // 팝업창을 열고 내용을 보여준다.
            $("#modalWin").css("display","block");
        });

        //수정 모달창 닫기
        $(".close").click(function(){
            $("#commenter-writebox").text('');
            $("#modify-comment").val('');
            $("#modalWin").css("display","none");
        });

        //수정 버튼 클릭
        $("#btn-write-modify").click(function(){
            let data_content = $("#modify-comment").val();
            //내용이 비어있으면 안됨
            if(data_content.length<=0) {
                alert('내용을 작성해 주세요');
                return;
            }
            // 1. 변경된 내용을 서버로 전송
            let reviewVO = {
                rno: $(this).attr("data-rno"),
                bno: data_bno,
                //text->val
                id: $("#commenter-writebox").text(),
                content: data_content,
                prno: $(this).attr("data-prno")
            }
            updateReview(reviewVO);
            // 2. 모달 창을 닫는다.
            $(".close").trigger("click");
        });

        //삭제 버튼 클릭
        commentList.on("click",".btn-delete", function(){
            //수정은 로그인한 유저 + 작성자만 가능 해야함
            if(data_id===''){
                alert('먼저 로그인을 하셔야 합니다');
                return;
            }
            //부모중 태그가 li인 첫번째 부모 1개만 찾아서 반환
            let li = $(this).closest("li");
            let commenter = $(".commenter", li).text();
            if(data_id!==commenter) {
                alert('본인의 리뷰만 삭제 가능합니다');
                return;
            }
            if(!confirm('정말 삭제 하시겠습니까?')) {
                return;
            }

            let reviewVO = {
                rno: $(this).attr("data-rno"),
                bno: data_bno,
                id: commenter,
                prno: $(this).attr("data-prno")
            }
            deleteReview(reviewVO);
        });

        //페이징처리
        commentList.on("click",".pageBtn", function(){
            getReviews($(this).attr("data-bno"),$(this).attr("data-page"),$(this).attr("data-size"));
        });

    }); //document.ready

    let getReviews = function(bno,page,size) {
        $.ajax({
            url: '/bookshop/review/bookReviews/'+bno+'?page='+page+"&size="+size,
            type: 'GET',
            headers: {"content-type":"application/json"},

            success : function(result){
                $("#commentList").html(toReviewList(result));
            },
            error: function(request, status, error) {
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                alert("리뷰 조회에 실패했습니다");
            }
        });//ajax
    }//getReviews

    let writeReview = function(reviewVO) {
        $.ajax({
            url: '/bookshop/review/bookReview',
            type: 'POST',
            headers: {"content-type":"application/json"},
            data: JSON.stringify(reviewVO),

            success : function(result) {
                alert("리뷰 등록 성공");
                getReviews(reviewVO.bno);
                location.reload();
            },
            error: function(request, status, error) {
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                alert("리뷰 등록에 실패했습니다")
            }
        })//ajax
    }//writeReivew

    let updateReview = function(reviewVO) {
        $.ajax({
            url: '/bookshop/review/bookReview/'+reviewVO.rno,
            type: 'PUT',
            headers: {"content-type":"application/json"},
            data: JSON.stringify(reviewVO),

            success : function(result) {
                alert("리뷰 수정 성공");
                getReviews(reviewVO.bno);
            },
            error: function(request, status, error) {
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                alert("리뷰 수정에 실패했습니다")
            }
        })//ajax
    }//updateReview

    let deleteReview = function(reviewVO) {
        $.ajax({
            url: '/bookshop/review/bookReview/'+reviewVO.rno,
            type: 'DELETE',
            headers: {"content-type":"application/json"},
            data: JSON.stringify(reviewVO),

            success : function(result) {
                alert("삭제 성공");
                getReviews(reviewVO.bno);
            },
            error: function(request, status, error) {
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                alert("리뷰 삭제에 실패했습니다")
            }
        })//ajax
    }//deleteReview

    //배열로 들어온 (js 객체를 html 문자로) 바꿔주는 함수
    let toReviewList = function(pageResponse) {
        let reviews = pageResponse.pageList;
        let prev = pageResponse.showPrev;
        let next = pageResponse.showNext;
        let tmp = '<h4 style="margin-bottom:20px;">한줄평</h4>';
            tmp += '<ul>';
        //리뷰 리스트를 순회하며 각각 출력
        reviews.forEach(function(review) {
            let reviewDate;
            if(review.update_date!=null){
                reviewDate = review.update_date;
            } else if(review.update_date==null){
                reviewDate = review.reg_date;
            }

            //rno!=prno 자식 답글일 경우(내부 css 변경)
            if(review.rno!==review.prno) {
                tmp += '<li class="comment-item" style="text-indent:20px; background-color: #ffffff;" data-bno=${book.bno} data-id=${sessionScope.user.id}>'
                tmp += '<span style="position: relative; top:18px;">'
                tmp += '<i style="margin-left:25px;">'+'ㄴ'+'</i>'
                tmp += '</span>'
            }

            //rno==prno 부모 댓글일 경우(그대로 출력)
            else if (review.rno === review.prno) {
                tmp += '<li class="comment-item" data-bno=${book.bno} data-id=${sessionScope.user.id}>'
                tmp += '<span class="comment-img">'
                tmp += '<i class="fa fa-user-circle" aria-hidden="true"></i>'
                tmp += '</span>'
            }

            //리뷰 출력 부분
            tmp += '<div class="comment-area">'
            if(review.rno!==review.prno){
                tmp += '<div class="commenter"">'+review.id+'</div>'
            } else if (review.rno === review.prno) {
                tmp += '<div class="commenter">'+review.id+'</div>'
            }
            tmp += '<div class="comment-content">'+review.content+'</div>'
            tmp += '<div class="comment-bottom">'
            tmp += '<span class="up_date">'+dateToString(reviewDate)+'</span>'
            if(review.rno===review.prno){
                tmp += '<button class="btn-write" data-bno='+review.bno+' data-rno='+review.rno+' data-prno='+review.prno+'>답글쓰기</button>'
            }
            tmp += '<button class="btn-modify"  data-bno='+review.bno+' data-rno='+review.rno+' data-prno='+review.prno+'>수정</button>'
            tmp += '<button class="btn-delete"  data-bno='+review.bno+' data-rno='+review.rno+' data-prno='+review.prno+'>삭제</button>'
            tmp += '</div>'
            tmp += '</div>'
            tmp += '</li>'
        })//foreach
        tmp += '</ul>'

        //댓글 작성 box
        tmp += '<div id="comment-writebox">'
        tmp += '<div class="commenter commenter-writebox">${sessionScope.user.id==null?'비로그인 유저':sessionScope.user.id}</div>'
        tmp += '<div class="comment-writebox-content">'
        tmp += '<textarea name="" class="comment-write" id="comment-write" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>'
        tmp += '</div>'
        tmp += '<div id="comment-writebox-bottom">'
        tmp += '<div class="register-box">'
        tmp += '<button class="btn btn-write-comment" id="btn-write-comment">등록</button>'
        tmp += '</div>'
        tmp += '</div>'
        tmp += '</div>'

        //페이징 처리 부분
        tmp += '<div class="paging-container">'
        let prevPage = pageResponse.page-1;
        let nextPage = pageResponse.page+1;
        if (prev) {
            tmp += '<button class="page pageBtn" data-bno="${book.bno}" data-page='+prevPage+'>&lt;</button>';
        }
        for(var i = pageResponse.start; i<=pageResponse.end ; i++){
            if(i===pageResponse.page){
                tmp += '<button class="page paging-active pageBtn" data-bno="${book.bno}" data-page='+i+'>'+i+'</button>';
            } else {
                tmp += '<button class="page pageBtn" data-bno="${book.bno}" data-page='+i+'>'+i+'</button>';
            }
        }
        if (next) {
            tmp += '<button class="page pageBtn" data-bno="${book.bno}" data-page='+nextPage+'>&gt;</button>';
        }
        tmp += '</div>'

        return tmp;
    }


    //날짜 처리를 위한 함수들
    let addZero = function(value){
        return value > 9 ? value : "0"+value;
    }

    let dateToString = function(reviewDate) {
        let date = new Date(reviewDate);

        let yyyy = date.getFullYear();
        let mm = addZero(date.getMonth() + 1);
        let dd = addZero(date.getDate());

        let HH = addZero(date.getHours());
        let MM = addZero(date.getMinutes());
        let ss = addZero(date.getSeconds());

        return yyyy+"."+mm+"."+dd+ " " + HH + ":" + MM + ":" + ss;
    }
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>