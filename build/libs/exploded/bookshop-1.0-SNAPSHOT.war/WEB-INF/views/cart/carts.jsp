<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>

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

                            <!--foreach-->
                            <c:forEach items="${cartBooks}" var="cartBook" varStatus="status">
                                <!--상품div-->
                                <div class="product">
                                    <div class="row">
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
                                        <div class="col-md-8">
                                            <div class="info">
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
                                                               type="text" value="${cartBook.quantity}" style="max-width: 3rem"/>
                                                        <button data-price="${cartBook.price}" class="btn-minus" style="position: relative;left:30px;">-</button>
                                                        <button data-price="${cartBook.price}" class="btn-plus" style="position: relative;left:30px;">+</button>
                                                    </div>
                                                    <div class="col-md-3 price">
                                                        <span class="sub-total">${cartBook.price*cartBook.quantity}</span>원
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--상품div-->
                            </c:forEach>
                            <!--foreach-->

                            <script>
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
                            <div class="summary-item"><span class="text">주문상품가격</span><span id="sub-price" class="price">0</span></div>
                            <!--준비중인 기능입니다-->
                            <div class="summary-item"><span class="text">할인</span><span class="price">0</span></div>
                            <div class="summary-item"><span class="text">배송료</span><span class="price">0</span></div>
                            <!--준비중인 기능입니다-->
                            <div class="summary-item"><span class="text">총계</span><span id="total-price" class="price">0</span></div>
                            <button type="button" class="btn btn-primary btn-lg btn-block">Checkout</button>
<%--                            <script>--%>
<%--                                let sumPrice = function () {--%>
<%--                                    let sum = 0;--%>
<%--                                    for(let i=0;i<document.getElementsByClassName('sub-total').length;i++){--%>
<%--                                        sum += Number(document.getElementsByClassName('sub-total').item(i).textContent);--%>
<%--                                    }--%>
<%--                                    document.getElementById('sub-price').innerHTML=sum;--%>
<%--                                    document.getElementById('total-price').innerHTML=sum;--%>
<%--                                }--%>
<%--                                sumPrice();--%>
<%--                            </script>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>