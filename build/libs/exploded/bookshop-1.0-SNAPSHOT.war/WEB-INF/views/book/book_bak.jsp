<%--<%@ include file="/WEB-INF/views/common/header.jsp" %>--%>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
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
<!-- Comments section-->
<section class="mb-5">
    <div class="card bg-light">
        <div class="card-body">
            <!-- Comment form-->
            <form class="mb-4">
                <textarea class="form-control" rows="3" placeholder="리뷰를 작성해 주세요"></textarea>
            </form>
            <!-- Comment with nested comments-->

            <!--댓글1예제-->
            <div id="reviewList" class="d-flex mb-4" data-bno="${book.bno}" data-id="${sessionScope.user}">
                <!-- Parent comment-->
                <div class="flex-shrink-0">
                    <img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
                </div>
                <div class="ms-3">
                    <div class="fw-bold">userId</div>
                    댓글내용1
                    <!-- Child comment 1-->
                    <div class="d-flex mt-4">
                        <div class="flex-shrink-0">
                            <img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
                        </div>
                        <div class="ms-3">
                            <div class="fw-bold">userId</div>
                            대댓글
                        </div>
                    </div>
                </div>
            </div>
            <!--댓글1예제-->

<%--            <!--댓글2예제-->--%>
<%--            <!-- Single comment-->--%>
<%--            <div class="d-flex">--%>
<%--                <div class="flex-shrink-0">--%>
<%--                    <img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />--%>
<%--                </div>--%>
<%--                <div class="ms-3">--%>
<%--                    <div class="fw-bold">Commenter Name</div>--%>
<%--                    When I look at the universe and all the ways the universe wants to kill us, I find it hard to reconcile that with statements of beneficence.--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <!--댓글2예제-->--%>
        </div>
        <script>
            // function count(type)  {
            //     // 결과를 표시할 element
            //     const resultElement = document.getElementById('useNum');
            //     let number = resultElement.value;
            //     //인원이 0~10명 사이에서만 신청 가능하도록 조정
            //     if(type === 'plus') {
            //         number = parseInt(number) + 1;
            //         if(number > 10){number = 10; alert('10인 이상은 단체 문의 부탁드립니다.');}
            //     }
            //     else if(type === 'minus')  {
            //         number = parseInt(number) - 1;
            //         if(number < 0){number = 0;}
            //     }
            //     // 결과 출력
            //     resultElement.value = number;
            // }
            $(document).ready(function(){
                let data_bno = $("#reviewList").attr("data-bno");
                let data_id = $("#reviewList").attr("data-id");
                // 시작하면서 해당 bno의 모든 리뷰 리스트를 가져옴
                alert(data_bno);
                alert(data_id);
                getReviews(data_bno);
                리뷰쓰기버튼 클릭이벤트
                $("#writeBtn").click(function(){
                    let reviewVO = {id : data_id ,
                        cno : data_bno,
                        content : $("textarea[name=content]").val(),
                        grade : $("input[name=grade]").val()
                    }
                    if($("input[name=grade]").val()===""||
                        $("textarea[name=content]").val()==""){
                        alert("평점과 내용을 전부 입력해주세요");
                        return;
                    }
                    writeReview(reviewVO);
                });
                //리뷰수정버튼 클릭이벤트  //parent prev eq(0)
                $("#reviewList").on("click",".modBtn",function(){ //아래의 클래스 modBtn클릭
                    if($(this).attr("data-id2")!==$("#reviewList").attr("data-id")){
                        if($("#reviewList").attr("data-id")!=='admin'){
                            alert("자신의 리뷰만 수정할 수 있습니다");
                            return;
                        }
                    }
                    alert('상단의 입력창에서 수정해주세요')
                    //1. writeBtn숨기고 숨겨진 #modBtn 버튼 다시 보이게
                    $("#writeBtn").css("display","none");
                    $("#modBtn").css("display","block");
                    //2. 수정에 필요한 content,grade
                    let data_cno = $("#reviewList").attr("data-cno");
                    let data_content = $(this).parent().prev().eq(0).text();
                    let data_grade = $(this).parent().parent().attr("data-grade");
                    //3. 검증에 필요한 re_no
                    let data_re_no = $(this).parent().parent().attr("data-re_no");
                    //4. 현재 리뷰 내용 textarea에 표시 + 평점도 표시
                    $("textarea[name=content]").val(data_content).focus();
                    $("input[name=grade]").val(data_grade);
                    $("#modBtn").click(function(){
                        let reviewVO = {
                            re_no : data_re_no,
                            cno : data_cno,
                            content : $("textarea[name=content]").val(),
                            grade : $("input[name=grade]").val()
                        }
                        if($("input[name=grade]").val()===""||
                            $("textarea[name=content]").val()==""){
                            alert("평점과 내용을 전부 입력해주세요");
                            return;
                        }
                        updateReview(reviewVO);
                    })
                })//리뷰수정버튼 클릭이벤트
                //리뷰삭제버튼 클릭이벤트
                $("#reviewList").on("click",".delBtn",function(){ //아래의 클래스 modBtn클릭
                    if($(this).attr("data-id1")!==$("#reviewList").attr("data-id")){
                        if($("#reviewList").attr("data-id")!=='admin'){
                            alert("자신의 리뷰만 삭제할 수 있습니다");
                            return;
                        }
                    }
                    if(!confirm('정말 삭제하시겠습니까?')){
                        return;
                    }
                    //1. 삭제,검증에 필요한 re_no
                    let re_no = $(this).parent().parent().attr("data-re_no");
                    //2. 목록 불러오기에 필요한 cno
                    let cno = $("#reviewList").attr("data-cno");
                    deleteReview(re_no,cno);
                })//리뷰삭제버튼 클릭이벤트
                $("#reviewList").on("click",".reviewPage",function(){
                    //페이지를 cno와 같이 넘겨준다
                    let page = $(this).text();
                    getReviews2(data_bno,page);
                })//리뷰 페이징
                //별 클릭 이벤트
                $(".review_write_star span").click(function() {
                    $(".review_write_star span").removeClass("on");
                    $(this).addClass("on");
                    $(this).prevAll("span").addClass("on");
                    $("#grade").val($(this).index()+1);
                });
                //prev
                $("#reviewList").on("click", ".prev", function() {
                    let pageStr1 = $(this).attr("data-page");
                    let page1 = Number(pageStr1);
                    getReviews2(data_bno,page1-1);
                })
                //next
                $("#reviewList").on("click", ".next", function() {
                    let pageStr2 = $(this).attr("data-page");
                    let page2 = Number(pageStr2);
                    getReviews2(data_bno,page2+1);
                })
            }); //document.ready
            //////////////////////////////////////////////

            let getReviews = function(bno) {
                $.ajax({
                    url: '/bookshop/review/bookReviews/'+bno,
                    type: 'GET',
                    headers: {"content-type":"application/json"},

                    success : function(result){
                        alert(result);
                        $("#reviewList").html(toReviewList(result));
                    }
                    ,
                    error: function(request, status, error) {
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
                });//ajax
            }//getReviews

            let writeReview = function(reviewVO) {
                isAjaxRun2=true;
                $.ajax({
                    url: '/project/review',
                    type: 'POST',
                    headers: {"content-type":"application/json"},
                    data: JSON.stringify(reviewVO),
                    success : function(result) {
                        alert("리뷰 등록 성공");
                        getReviews(reviewVO.cno);
                    },
                    error: function() {
                        alert("error");
                    }
                })//ajax
            }//writeReivew
            let updateReview = function(reviewVO) {
                $.ajax({
                    url: '/project/review/'+reviewVO.re_no,
                    type: 'PUT',
                    headers: {"content-type":"application/json"},
                    data: JSON.stringify(reviewVO),
                    success : function(result) {
                        alert("리뷰 수정 성공");
                        $("textarea[name=content]").val('');
                        $("input[name=grade]").val('');
                        $("#writeBtn").css("display","block");
                        $("#modBtn").css("display","none");
                        getReviews(reviewVO.cno);
                    },
                    error: function() {
                        alert("error");
                    }
                })//ajax
            }//updateReview
            let deleteReview = function(re_no,cno) {
                $.ajax({
                    url: '/project/review/'+re_no,
                    type: 'DELETE',
                    headers: {"content-type":"application/json"},
                    success : function(result) {
                        alert("삭제 성공");
                        getReviews(cno);
                    },
                    error: function() {
                        alert("error");
                    }
                })//ajax
            }//deleteReview

            //배열로 들어온 (js 객체를 html 문자로) 바꿔주는 함수
            let toReviewList = function(pageResponse) {
                let reviews = pageResponse.pageList;
                let prev = pageResponse.showPrev;
                let next = pageResponse.showNext;
                let tmp = "";
                reviews.forEach(function(review) {
                    tmp += '<div class="d-flex mb-4" data-rno='+review.rno+' data-prno='+review.prno+'>'
                    tmp += '<div class="flex-shrink-0">'
                    tmp += '<img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />'
                    tmp += '</div>'
                    tmp += '<div class="ms-3">'
                    tmp += '<div class="fw-bold">${sessionScope.user.id}</div>'+review.content+''
                    tmp += '</div>'
                    tmp += '</div>'
                })//foreach
                if (prev) {
                    tmp += '<span style="cursor: pointer" class="prev" data-page='+pageResponse.page+'>[PREV]</span>';
                }
                for(var i = pageResponse.start; i<=pageResponse.end ; i++){
                    tmp += '<div class="reviewPage" style="display:inline-block; cursor: pointer; margin:3px;">'+i+'</div>';
                }
                if (next) {
                    tmp += '<span style="cursor: pointer" class="next" data-page='+pageResponse.page+'>[NEXT]</span>';
                }
                return tmp;
            }
        </script>
    </div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>