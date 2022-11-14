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
                <h2>${sessionScope.user.id} 주문목록</h2>
                <p></p>
            </div>
            <div class="content">
                <div class="row" style="background-color: #f7fbff;">
                    <div class="col-md-12 col-lg-8">
                        <div class="items">
                            <script>
                                <c:if test="${ordersBooks.size()==0}">
                                alert('주문목록이 존재하지 않습니다.');
                                window.location="/bookshop";
                                </c:if>
                            </script>
                            <!--foreach-->
                                <!--전체상품div-->
                                <div class="product">
                                    <!--상품div-->
                                    <div class="row">
                                        <!--상세내용div wrap-->
                                        <div class="col-md-8">
                                            <!--상세내용div-->
                                            <div class="info">
                                                <!--상세내용row-->
                                                <div class="row">
                                                    <c:forEach items="${ordersBooks}" var="orderBookDto" varStatus="status">
                                                            <div class="col-md-5 product-name" style="position: relative;top:20px;left:250px;margin-left:10px;">
                                                                <div class="product-name" style="width: 300px;">
                                                                    <div class="product-info">
                                                                        <c:forEach items="${orderBookDto.ordersBookVOList}" var="orderBook" varStatus="index">
                                                                            <c:if test="${index.first}">
                                                                                <div>결제상품: <span class="value">${orderBook.bookVO.title}</span></div>
                                                                            </c:if>
                                                                            <c:if test="${!index.first}">
                                                                                <div><span class="value"><i>외 ${orderBookDto.ordersBookVOList.size()-1}개</i></span></div>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        <div>주문상태: <span class="value">${orderBookDto.order_status}</span></div>
                                                                        <div>주문날짜: <span class="value">${orderBookDto.order_date.toLocaleString()}</span></div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        <div class="col-md-4 quantity" style="position:relative;left:400px;">
                                                            <div style="position:relative;right:20px;font-weight: bold;">
                                                                결제금액:
                                                            </div>
                                                            <div class="col-md-3 price">
                                                                <span id="sub-total${status.index}" class="sub-total">${orderBookDto.total_price}</span>
                                                            </div>
                                                            <div style="position:relative;left:15px;bottom:5px;font-weight: bold;">
                                                                원
                                                            </div>
                                                            <!--삭제버튼-->
                                                            <button class="btn-delOrder" data-order_id="${orderBookDto.order_id}" data-imp_uid="${orderBookDto.imp_uid}"
                                                                     style="font-size: 14px; position: relative;left:190px;bottom:30px;">x</button>
                                                        </div>
                                                    </c:forEach>
                                                    <!--foreach-->
                                                </div><!--상세내용row-->
                                            </div><!--상세내용div-->
                                        </div><!--상세내용div wrap-->
                                    </div><!--상품div-->
                                </div><!--전체상품div-->
                            <script>
                                let id = '<c:out value="${sessionScope.user.id}"/>';
                                <%--let buyerName = '<c:out value="${sessionScope.user.id}"/>';--%>

                                //테스트용 코드
                                let buyerName = 'user1';

                                $(document).ready(function (){
                                    //삭제 버튼 클릭
                                    $(".btn-delOrder").click(function (){
                                        if(buyerName===''){
                                            alert('잘못된 접근입니다');
                                            return;
                                        }
                                        if(!confirm('정말 취소 하실건가요?')){
                                            return;
                                        }
                                        //valid

                                        let imp_uid = $(this).attr("data-imp_uid");
                                        let order_id = $(this).attr("data-order_id");
                                        $.ajax({
                                            type: "POST",
                                            url: "/bookshop/orders/deleteIamport/"+imp_uid+"?order_id="+order_id
                                        }).done(function (data) {
                                            if(data){
                                                alert('결제 취소 성공');
                                                location.reload();
                                            } else{
                                                alert('결제 취소 실패');
                                            }
                                        });//function
                                    });//btn-payment.click
                                });//document.ready
                            </script>
                        </div>
                        <!--items-->
                    </div>
                    <!--col-->
                        <script>
                            <%--let buyerName = '<c:out value="${sessionScope.user.id}"/>';--%>

                            //테스트용 코드
                            let buyerName = 'user1';
                            let buyerEmail = 'user1@user1.com';
                            let buyerTel = '010-111-2222';


                            $(document).ready(function(){


                            });//document.ready
                        </script>
<%--                    </div>--%>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>