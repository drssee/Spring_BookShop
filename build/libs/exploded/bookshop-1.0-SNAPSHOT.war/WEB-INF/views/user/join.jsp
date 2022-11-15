<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%--<link rel="stylesheet" href="<c:out value="/resources/css/join.css"/>">--%>
<link rel="stylesheet" href="<c:url value="/resources/css/join.css"/>">

<div id="wrap">
    <main id="main">
        <div id="join_wrap">
            <div id="sub_banner">
                <p><strong>회원 가입</strong></p>
            </div>
            <form action="<c:url value="/user/join" />" method="post" id="join_form">
                <script>
                    $(document).ready(function(){
                        <spring:hasBindErrors name="user">
                            alert('입력값을 확인해주세요');
                        </spring:hasBindErrors>
                    });
                </script>
                <div class="personal_info">
                    <p>
                        <label for="u_id">아이디<span> (영문소문자/숫자, 4~16자)</span></label>
                        <input type="text" id="u_id" name="id" value="${bindingResult.getFieldValue("id")}">
                        <input type="button" id="u_id_check" name="id_check" value="중복 확인" onclick="checkID()">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="user">
                        <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('id')}">
                                <div class="binding-error">${errors.getFieldError("id").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </p>
                    <p>
                        <label for="u_pw1">비밀번호<span>(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8~25자)</span></label>
                        <input type="password" id="u_pw1" name="pwd" value="${bindingResult.getFieldValue("pwd")}">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="user">
                        <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('pwd')}">
                                <div class="binding-error">${errors.getFieldError("pwd").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </p>
                    <p>
                        <label for="u_pw2">비밀번호 확인<span id="pwCheckLabel"></span></label>
                        <input type="password" id="u_pw2">
                    </p>
                    <p>
                        <label for="u_name">이름</label>
                        <input type="text" id="u_name" name="name" value="${bindingResult.getFieldValue("name")}">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="user">
                        <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('name')}">
                                <div class="binding-error">${errors.getFieldError("name").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </p>
                    <p class="tel_wrap">
                        <label for="u_tel1">전화번호</label>
                        <input type="tel" id="u_tel1" name="phone1" value="${bindingResult.getFieldValue("phone1")}"> -
                        <input type="tel" id="u_tel2" name="phone2" value="${bindingResult.getFieldValue("phone2")}"> -
                        <input type="tel" id="u_tel3" name="phone3" value="${bindingResult.getFieldValue("phone3")}">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="user">
                        <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('phone')}">
                                <div class="binding-error">${errors.getFieldError("phone").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </p>
                    <p>
                        <label for="email_l">이메일</label>
                        <input type="email" id="email_l" name="email" value="${bindingResult.getFieldValue("email")}">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="user">
                        <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('email')}">
                                <div class="binding-error">${errors.getFieldError("email").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </p>
                    <div class="addr">
                        <input type="text" name="zipcode" id="sample6_postcode" placeholder="우편번호" value="${bindingResult.getFieldValue("zipcode")}">
                        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                        <input type="text" name="addr1" id="sample6_address" placeholder="주소" value="${bindingResult.getFieldValue("addr1")}"><br>
                        <input type="text" name="addr2" id="sample6_extraAddress" placeholder="참고항목" value="${bindingResult.getFieldValue("addr2")}">
                        <input type="text" name="addr3" id="sample6_detailAddress" placeholder="상세주소" value="${bindingResult.getFieldValue("addr3")}">
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="user">
                            <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                            <c:if test="${errors.hasFieldErrors('addr')}">
                                <div class="binding-error">${errors.getFieldError("addr").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                    </div>
                    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                    <script>
                        function sample6_execDaumPostcode() {
                            new daum.Postcode({
                                oncomplete: function(data) {
                                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                                    var addr = ''; // 주소 변수
                                    var extraAddr = ''; // 참고항목 변수

                                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                        addr = data.roadAddress;
                                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                        addr = data.jibunAddress;
                                    }

                                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                                    if(data.userSelectedType === 'R'){
                                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                            extraAddr += data.bname;
                                        }
                                        // 건물명이 있고, 공동주택일 경우 추가한다.
                                        if(data.buildingName !== '' && data.apartment === 'Y'){
                                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                        }
                                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                        if(extraAddr !== ''){
                                            extraAddr = ' (' + extraAddr + ')';
                                        }
                                        // 조합된 참고항목을 해당 필드에 넣는다.
                                        document.getElementById("sample6_extraAddress").value = extraAddr;

                                    } else {
                                        document.getElementById("sample6_extraAddress").value = '';
                                    }

                                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                    document.getElementById('sample6_postcode').value = data.zonecode;
                                    document.getElementById("sample6_address").value = addr;
                                    // 커서를 상세주소 필드로 이동한다.
                                    document.getElementById("sample6_detailAddress").focus();
                                }
                            }).open();
                        }
                    </script>

                </div>
                <div id="login_btn">
                    <button type="submit" id="btn-join" class="login_submit">회원가입</button>
                    <button type="reset" class="cancel">취소</button>
                </div>
            </form>
        </div>
    </main>
</div>
<script>
    //유효성 검사에 사용될 변수들
    let id = document.getElementById("u_id");
    let pw1 = document.getElementById("u_pw1");
    let pw2 = document.getElementById("u_pw2");
    let pwLabel = document.getElementById("pwCheckLabel");
    //비밀번호 영문+숫자+특수조합(8~25자리) 정규식
    let pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*_+=])(?=.*[0-9]).{8,25}$/;
    //아이디 중복 확인 ajax로 db 호출
    function checkID() {
        let id = $("#u_id").val();
        if(id===''){
            alert('아이디를 입력해주세요');
            return;
        }
        checkDuplicateId(id);
    }
    let checkDuplicateId = function(id){
        $.ajax({
            url: '/bookshop/user/check/'+id,
            type: 'GET',
            headers: {"content-type":"application/json"},
            success : function(result){
                if(result.id===id){
                    alert(id+"는 중복된 아이디입니다.");
                    //css오류
                    // id.style.outline = "2px solid #eb9494";
                    return;
                }
                alert(id+"는 사용 가능한 아이디입니다.");
                // id.style.outline = "2px solid #a3ea92";
            },
            error: function() {
                alert(id+"는 중복된 아이디입니다.");
                // id.style.outline = "2px solid #eb9494";
            }
        });//ajax
    }
    //아이디/비밀번호에 포커스하면 텍스트 효과
    $(".u_txt input").focus(function() {
        $(this).siblings("label").addClass("focus");
    }).blur(function() {
        if ($(this).val() === "") {
            $(this).siblings("label").removeClass("focus");
        } else {
            $(this).siblings("label").addClass("focus");
        }
    })
    //        $(pw1).blur(function() {
    //            //비밀번호 정규식 확인
    //            if (!pwdCheck.test(pw1.value)) {
    //                alert("비밀번호는 영문+숫자+특수문자 조합으로 8-25자리 사용해야 합니다.");
    //                pw1.focus();
    //            }
    //        })
    //비밀번호확인 유효성 검사
    function checkPw(inputVal) {
        $(inputVal).blur(function() {
            if (pw1.value === pw2.value) {
                if(pw1.value===''){
                    pwLabel.innerHTML = "비밀번호를 입력해주세요.";
                    pwLabel.style.color = "#e76060";
                } else{
                    pwLabel.innerHTML = "비밀번호 일치";
                    pwLabel.style.color = "#6edb54";
                }
            } else {
                pwLabel.innerHTML = "비밀번호가 일치하지 않습니다.";
                pwLabel.style.color = "#e76060";
            }
            console.log("pw1: " + pw1.value);
            console.log("pw2: " + pw2.value);
        })
    }
    checkPw(pw1);
    checkPw(pw2);
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>