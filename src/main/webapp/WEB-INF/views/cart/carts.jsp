<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 아임포트 -->
<script src ="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/resources/css/carts.css"/>">
<style>
    button {
        border: none;
        outline: none;
    }
</style>
<main class="page">
    <section class="shopping-cart dark">
        <div class="container">
            <div class="block-heading">
                <h2>${sessionScope.user.id} 장바구니</h2>
                <p></p>
            </div>
            <div class="content">
                <div class="row">
                    <div class="col-md-12 col-lg-8">
                        <div class="items">
                            <script>
                                <c:if test="${cartBooks.size()==0}">
                                    alert('장바구니가 비어있습니다.');
                                    window.location="/bookshop";
                                </c:if>
                            </script>
                            <!--foreach-->
                            <c:forEach items="${cartBooks}" var="cartBook" varStatus="status">
                                <!--전체상품div-->
                                <div class="product">
                                    <!--상품div-->
                                    <div class="row">
                                        <!--이미지 div-->
                                        <div class="col-md-3">
                                            <!--커버 이미지가 없으면 기본 이미지 사용-->
                                            <c:choose>
                                                <c:when test="${cartBook.storeFileName!=null}">
                                                    <c:choose>
                                                        <c:when test="${cartBook.size==0}">
                                                            <img src="${cartBook.storeFileName}"
                                                                 class="img-fluid mx-auto d-block image" alt="..."
                                                                 width="200px" height="324px">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${pageContext.request.contextPath}/resources/upload/images${cartBook.storeFileName}"
                                                                 class="img-fluid mx-auto d-block image" alt="no-image"
                                                                 width="200px" height="324px">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="<c:url value="/resources/images/common/no-img.gif"/>"
                                                         class="img-fluid mx-auto d-block image" alt="..."
                                                         width="200px" height="324px">
                                                </c:otherwise>
                                            </c:choose>
                                            <!--커버 이미지가 없으면 기본 이미지 사용 끝-->
                                        </div>
                                        <!--이미지 div-->

                                        <!--상세내용div wrap-->
                                        <div class="col-md-8">
                                            <!--상세내용div-->
                                            <div class="info">
                                                <!--상세내용row-->
                                                <div class="row">
                                                    <div class="col-md-5 product-name">
                                                        <div class="product-name">
                                                            <a href="<c:url value="/book/"/>${cartBook.bno}">${cartBook.title}</a>
                                                            <div class="product-info">
                                                                <div>저자: <span class="value">${cartBook.author}</span></div>
                                                                <div>출판사: <span class="value">${cartBook.publisher}</span></div>
                                                                <div>출간일: <span class="value">${cartBook.pubDate.toLocaleString()}</span></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4 quantity">
                                                        <label for="cart_quantity<c:out value="${status.index}"/>">수량:</label>
                                                        <input id="cart_quantity<c:out value="${status.index}"/>"
                                                               name="quantity" class="form-control quantity-input text-center me-3"
                                                               type="text" value="${cartBook.quantity}" style="max-width: 3rem" readonly/>
                                                        <button data-price="${cartBook.price}" class="btn-minus" style="position: relative;left:40px;">-</button>
                                                        <button data-price="${cartBook.price}" class="btn-plus" style="position: relative;left:40px;">+</button>
                                                        <!--삭제버튼-->
                                                        <button class="btn-delCart" data-cno="${cartBook.cno}"
                                                                style="font-size: 14px; position: relative;left:190px;bottom:30px;">x</button>
                                                    </div>
                                                    <div class="col-md-3 price">
                                                        <span id="sub-total${status.index}" class="sub-total">${cartBook.price*cartBook.quantity}</span>원
                                                    </div>
                                                </div><!--상세내용row-->
                                            </div><!--상세내용div-->
                                        </div><!--상세내용div wrap-->
                                    </div><!--상품div-->
                                </div><!--전체상품div-->
                            </c:forEach>
                            <!--foreach-->
                            <script>
                                let id = '<c:out value="${sessionScope.user.id}"/>';
                                //장바구니의 합계를 구하는 메서드
                                let sumPrice = function () {
                                    let sum = 0;
                                    //각 장바구니 물품의 가격을 더해서
                                    for(let i=0;i<document.getElementsByClassName('sub-total').length;i++){
                                        sum += Number(document.getElementsByClassName('sub-total').item(i).textContent);
                                    }
                                    //Summary탭에 출력
                                    document.getElementById('sub-price').innerHTML=sum;
                                    document.getElementById('total-price').innerHTML=sum;
                                }

                                $(document).ready(function (){
                                    sumPrice();
                                    //minus,plus 버튼 클릭시 수량,가격(총합) 변경
                                    $(".btn-minus").click(function (){
                                        let price = $(this).attr("data-price");
                                        let type = 'minus';
                                        let resultElement = $(this).prev();
                                        //수량 변경
                                        count(type,resultElement);
                                        //수량*가격 변경
                                        $(this).parent().next().children(0).html(resultElement.val()*price);
                                        //summary 변경
                                        sumPrice();
                                    });
                                    $(".btn-plus").click(function (){
                                        let price = $(this).attr("data-price");
                                        let type = 'plus';
                                        let resultElement = $(this).prev().prev();
                                        //수량 변경
                                        count(type,resultElement);
                                        //수량*가격 변경
                                        $(this).parent().next().children(0).html(resultElement.val()*price);
                                        //summary 변경
                                        sumPrice();
                                    });

                                    //삭제버튼 클릭
                                    $(".btn-delCart").click(function(){
                                        if(id===''){
                                            alert('잘못된 접근입니다');
                                            return;
                                        }
                                        if(!confirm('정말 삭제 하시겠습니까?')){
                                            return;
                                        }
                                        let cno = $(this).attr("data-cno");
                                        deleteCartItem(cno);
                                    });

                                    //deleteCartItem
                                    let deleteCartItem = function(cno) {
                                        $.ajax({
                                            url: '/bookshop/cart/'+cno,
                                            type: 'DELETE',
                                            headers: {"content-type":"application/json"},

                                            success : function(result) {
                                                if(result){
                                                    alert("삭제 성공");
                                                    location.reload();
                                                } else {
                                                    alert("장바구니 목록 삭제에 실패했습니다");
                                                }
                                            },
                                            error: function(request, status, error) {
                                                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                                                alert("장바구니 목록 삭제에 실패했습니다");
                                            }
                                        })//ajax
                                    }//deleteCartItem

                                    //장바구니 물품 수량 카운트 체크
                                    function count(type,resultElement)  {
                                        // 결과를 표시할 element
                                        let number = resultElement.val();
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
                                        resultElement.val(number);
                                    }//count
                                });
                            </script>
                        </div>
                        <!--items-->
                    </div>
                    <!--col-->

                    <!--요약+결제-->
                    <div class="col-md-12 col-lg-4">
                        <div class="summary">
                            <h3>Summary</h3>
                            <div class="summary-item"><span class="text">주문상품 가격</span><span id="sub-price" class="price">0</span>원</div>
                            <!--준비중인 기능입니다-->
                            <div class="summary-item"><span class="text">할인</span><span class="price">0</span></div>
                            <div class="summary-item"><span class="text">배송료</span><span class="price">0</span></div>
                            <!--준비중인 기능입니다-->
                            <div class="summary-item"><span class="text">총계</span><span id="total-price" class="price">0</span></div>
                            <button type="button" id="btn-payment" class="btn btn-primary btn-lg btn-block">구매</button>
                        </div>
                        <script>
                            <%--let buyerName = '<c:out value="${sessionScope.user.id}"/>';--%>

                            //테스트용 코드
                            let buyerName = 'user1';
                            let buyerEmail = 'user1@user1.com';
                            let buyerTel = '010-111-2222';


                            $(document).ready(function(){

                                let IMP = window.IMP; //import payment == imp
                                let code = '<spring:message code="imp"/>'; //가맹점 식별코드
                                //구매 버튼 클릭
                                $("#btn-payment").click(function (){
                                    if(buyerName===''){
                                        alert('잘못된 접근입니다');
                                        return;
                                    }

                                    //구매버튼 클릭당시의 장바구니 수량 가격을 저장

                                    // let pay_amount = Number($("#total-price").text());

                                    //테스트용 코드
                                    let pay_amount = Number(100);

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
                                                <c:forEach items="${cartBooks}" var="cart">
                                                ordersBookForms.push({
                                                    bno: '<c:out value="${cart.bno}"/>',
                                                    order_price: '<c:out value="${cart.price}"/>',
                                                });
                                                </c:forEach>
                                                for(let i=0;i<ordersBookForms.length;i++){
                                                    ordersBookForms[i].order_quantity =
                                                        Number(document.getElementsByClassName('sub-total')
                                                            .item(i).textContent)/ordersBookForms[i].order_price;
                                                }

                                                //서버에 보낼 결제 정보 객체 result 초기화
                                                let result = {
                                                    id: buyerName,
                                                    order_date : new Date(), //결제날짜
                                                    total_price : rsp.paid_amount, //결제금액
                                                    merchant_uid : rsp.merchant_uid, //주문번호
                                                    imp_uid : rsp.imp_uid, //결제번호
                                                    ordersBookForms : ordersBookForms,
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
                                                        alert("결제에 실패 했습니다.")
                                                        //결제 취소시키는 기능 넣어야함
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
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>