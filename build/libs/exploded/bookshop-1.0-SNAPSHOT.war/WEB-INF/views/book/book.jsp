<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/comment.css"/>">
<style>
    button {
        border:none;
        outline: none;
    }
</style>
<section class="py-5" style="height: 1200px;">
    <div class="container px-4 px-lg-5 my-5">
        <div class="container" style="width: 350px;height: 480px; float:left; margin-top:100px; ">
            <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner" style="cursor: pointer;">
                    <c:choose>
                        <c:when test="${images.size()>0}">
                            <c:forEach items="${images}" var="image" varStatus="index">
                                <c:if test="${index.first}">
                                    <div class="carousel-item active">
                                        <img width="350" height="480" src="${pageContext.request.contextPath}/resources/upload/images${image.storeFileName}" class="d-block w-100 iimg" alt="...">
                                    </div>
                                </c:if>
                                <c:if test="${!index.first}">
                                    <div class="carousel-item">
                                        <img width="350" height="480" src="${pageContext.request.contextPath}/resources/upload/images${image.storeFileName}" class="d-block w-100 iimg" alt="...">
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${book.storeFileName!=null}">
                                    <c:choose>
                                        <c:when test="${book.size==0}">
                                            <img width="350" height="480" src="${book.storeFileName}" class="d-block w-100 iimg" alt="...">
                                        </c:when>
                                        <c:otherwise>
                                            <img width="350" height="480" src="${pageContext.request.contextPath}/resources/upload/images${book.storeFileName}" class="d-block w-100 iimg" alt="...">
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <img width="259" height="195" src="data:image/png;base64,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" class="d-block w-100 iimg" alt="no-img">
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        <script>
            $(".iimg").click(function(){
                let img = $(this).attr("src");
                window.location=img;
            })
        </script>
            <div class="col-md-6" style="float:right; margin-top:100px; margin-left:12px;">
                <h1 class="display-5 fw-bolder">${book.title}</h1>
                <div class="fs-5 mb-5" style="margin-top:20px;">
<%--                    <span class="text-decoration-line-through">$45.00</span>--%>
                    <span>${book.price}원</span>
                </div>
                <p class="lead" style="margin-top:30px;">${book.author}</p>
                <p class="lead" style="margin-top:20px;">${book.publisher}</p>
                <p class="lead" style="margin-top:20px; margin-bottom: 20px;">${book.description}</p>
<%--                <p class="lead" style="margin-top:10px;">${book.pubDate}</p>--%>
                <div class="d-flex">
                    <input name="quantity" class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                    <input type="hidden" name="bno" value="${book.bno}">
                    <input type="hidden" name="page" value="${bnoDto.page}"> <!--page,size 문제있을 수 있음-->
                    <input type="hidden" name="size" value="${bnoDto.size}">
                    <button style="margin-right: 10px;" class="btn btn-outline-dark flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1"></i>
                        장바구니 담기
                    </button>
                    <button class="btn btn-outline-dark flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1"></i>
                        구매
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>

<h4>리뷰</h4>

<div id="commentList" data-bno="${book.bno}" data-id="${sessionScope.user.id}">
</div>

<div id="reply-writebox">
    <div class="commenter commenter-writebox">${sessionScope.user.id==null?'비로그인 유저':sessionScope.user.id}</div>
    <div class="reply-writebox-content">
        <textarea name="write-comment" class="write-comment" id="write-comment" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
    </div>
    <div id="reply-writebox-bottom">
        <div class="register-box">
            <button class="btn btn-write-reply" id="btn-write-reply">등록</button>
            <button class="btn btn-cancel-reply">취소</button>
        </div>
    </div>
</div>

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

<script>
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
        $("#commentList").on("click",".btn-delete", function(){
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
            if(!confirm('정말 삭제 하시겠습니까?'))
                return;
            let reviewVO = {
                rno: $(this).attr("data-rno"),
                bno: data_bno,
                id: commenter,
                prno: $(this).attr("data-prno")
            }
            deleteReview(reviewVO);
        });
    }); //document.ready

    let getReviews = function(bno) {
        $.ajax({
            url: '/bookshop/review/bookReviews/'+bno,
            type: 'GET',
            headers: {"content-type":"application/json"},

            success : function(result){
                $("#commentList").html(toReviewList(result));
            },
            error: function() {
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
        let tmp = '<ul>';
        reviews.forEach(function(review) {
            let reviewDate;
            if(review.update_date!=null){
                reviewDate = review.update_date;
            } else if(review.update_date==null){
                reviewDate = review.reg_date;
            }

            if(review.rno!==review.prno) {
                tmp += '<li class="comment-item" style="text-indent:20px; background-color: #ffffff;" data-bno=${book.bno} data-id=${sessionScope.user.id}>'
                tmp += '<span style="position: relative; top:18px;">'
                tmp += '<i style="margin-left:25px;">'+'ㄴ'+'</i>'
                tmp += '</span>'
            } else if (review.rno === review.prno) {
                tmp += '<li class="comment-item" data-bno=${book.bno} data-id=${sessionScope.user.id}>'
                tmp += '<span class="comment-img">'
                tmp += '<i class="fa fa-user-circle" aria-hidden="true"></i>'
                tmp += '</span>'
            }
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

        <%--tmp += '<div class="paging-container">'--%>
        <%--if (prev) {--%>

        <%--    tmp += "<a class='page' href='<c:url value="/review/bookReviews/"/>'+${book.bno}+'?page="+(pageResponse.page-1)+">&lt;</a>";--%>
        <%--}--%>
        <%--for(var i = pageResponse.start; i<=pageResponse.end ; i++){--%>
        <%--    if(i===pageResponse.page){--%>
        <%--        tmp += "<a class='page paging-active' href="<c:url value="/review/bookReviews/"/>${book.bno}>"+i+"</a>";--%>
        <%--    } else {--%>
        <%--        tmp += "<a class='page' href='<c:url value="/review/bookReviews/"/>'+${book.bno}+''>"+i+"</a>";--%>
        <%--    }--%>
        <%--}--%>
        <%--if (next) {--%>
        <%--    tmp += "<a class='page' href='<c:url value="/review/bookReviews/"/>'+${book.bno}+'?page="+(pageResponse.page+1)+">&gt;</a>";--%>
        <%--}--%>
        <%--tmp += '</div>'--%>

        return tmp;
    }

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