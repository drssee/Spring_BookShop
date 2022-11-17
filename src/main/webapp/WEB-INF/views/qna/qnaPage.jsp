<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
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
    strong {
        font-weight: bold;
    }
</style>
<main>
    <div style="margin-left:200px;">
        <h4>BEGOJA 고객센터입니다.</h4>
    </div>
    <div style="margin-left:50px; margin-top:20px; margin-bottom:20px;">
        궁금한 점이 있다면 무엇이든 물어보세요.<br><br>
        자주묻는질문(FAQ)에 원하시는 답이 없으시면
        <a href="<c:url value="/qna/add"/>" style="font-size: 20px;" onclick="return checkSessionId()">1:1 문의 남기기</a> 또는 <a href="<c:url value="/qna/qnas"/>" style="font-size: 20px;">문의 게시판</a>을 이용해주세요.
    </div>
    <div class="accordion" id="accordionExample" style="width: 40%;">

        <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    자주묻는질문(FAQ)
                </button>
            </h2>
            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    <strong style="font-weight: bold;">Q.구매시 실제로 결제가 되나요?<br></strong>
                    <span>A. 아니요, 테스트 결제api와 연동되어 다음날 00시에 자동 결제 취소됩니다.<br><br></span>
                    <strong style="font-weight: bold;">Q.구매한 도서를 환불하고 싶어요<br></strong>
                    <span>A. 고객요청 환불은 결제완료 시점부터, 배송완료 전까지 가능합니다.<br>배송완료후 환불을 원하시면 <a href="<c:url value="/qna/add"/>" onclick="return checkSessionId()">1:1문의</a>를 요청해주세요.<br><br></span>
                    <strong style="font-weight: bold;">Q.구매목록을 확인하고 싶어요<br></strong>
                    <span>A. 로그인->마이페이지->결제목록 보기, 페이지로 이동하시면 됩니다.</span>
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    개인정보취급방침
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    BEGOJA(이하 '회사')는 개인정보보호법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률(이하 '정보통신망법')에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.<br>



                    회사는 개인정보처리방침을 개정하는 경우 웹사이트 공지사항(또는 개별공지)을 통하여 공지할 것입니다.<br><br>



                    ■ 수집하는 개인정보<br>
                    회사는 회원가입, 원활한 고객상담, 각종 서비스 등 기본적인 서비스 제공을 위한 필수정보와 선택정보로 구분하여 아래와 같은 개인정보를 수집하고 있습니다.<br><br>

                    필수항목 : 아이디, 이메일 주소, 이름, 성별, 생년월일, 휴대전화, 관심브랜드<br>
                    선택항목 : 일반전화, 주소, 직업, 관심분야, 이메일 및 SMS 수신 여부<br>
                    선택항목은 입력하지 않아도 서비스를 이용할 수 있습니다.<br><br>



                    서비스 이용과정에서 아래와 같은 정보들이 수집될 수 있습니다.<br><br>

                    공통 : IP 주소, 쿠키, 로그인 일시, 서비스 이용 기록<br>
                    유료 서비스 이용 시: 성명, 은행계좌 정보, 결제기록<br>
                    ■ 개인정보의 수집 및 이용목적<br>
                    회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.<br><br>

                    서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산<br>
                    콘텐츠 제공, 구매 및 요금 결제, 물품배송 또는 청구지 등 발송, 금융거래 본인 인증 및 금융 서비스<br>
                    회원 관리<br>
                    회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가 사용 방지, 가입 의사 확인, 연령확인, 불만처리 등 민원처리, 고지사항 전달<br>
                    마케팅 및 광고에 활용<br>
                    이벤트 등 광고성 정보 전달 , 접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계<br>
                    ■ 개인정보의 보유 및 이용기간<br>
                    원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다.<br><br>

                    보존 항목 : 결제기록<br>
                    보존 근거 : 계약 또는 청약철회 등에 관한 기록<br>
                    보존 기간 : 3년<br>
                    계약 또는 청약철회 등에 관한 기록 : 5년 (전자상거래등에서의 소비자보호에 관한 법률)<br>
                    대금결제 및 재화 등의 공급에 관한 기록 : 5년 (전자상거래등에서의 소비자보호에 관한 법률)<br>
                    소비자의 불만 또는 분쟁처리에 관한 기록 : 3년 (전자상거래등에서의 소비자보호에 관한 법률)<br><br>

                    • 시행일자 : 2021년 10월 14일<br>
                    • 공고일자 : 2021년 10월 22일<br>
                </div>
            </div>
        </div>

        <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    이용약관
                </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    다음 약관은 공정거래위원회 표준 약관을 기초로 합니다.<br><br>

                    제1조(목적)<br>
                    본 약관은 BEGOJA(이하 '회사')가 운영하는 BEGOJA 웹사이트에서 제공하는 인터넷 관련 서비스(이하 '서비스'라 한다)를 이용함에 있어 몰과 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.<br><br>



                    제2조(정의)<br>
                    ① "몰"이란 회사가 재화 또는 용역, 서비스를 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 또는 용역, 서비스를 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.<br>

                    ② "이용자"란 "몰"에 접속하여 본 약관에 따라 "몰"이 제공하는 "서비스"를 받는 회원 및 비회원을 말합니다.<br>

                    ③ '회원'이라 함은 "몰"에 개인정보를 제공하여 회원등록한 자로서, "몰"의 정보를 지속적으로 제공받으며, "몰"이 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.<br>

                    ④ 비회원'이라 함은 회원에 가입하지 않고 "몰"이 제공하는 서비스를 이용하는 자를 말합니다.<br>

                    ⑤ 'ID’라 함은 이용자가 회원가입 당시 "몰" 등록한 사용자 '개인이용문자’를 말합니다.<br><br>



                    제3조(약관의 명시와 개정)<br>
                    ① "몰"은 본 약관의 내용과 상호, 영업소 소재지, 대표자의 성명, 사업자등록번호, 연락처(전화, 팩스, 전자우편 주소 등) 등을 이용자가 알 수 있도록 사이버몰의 초기 서비스화면에 게시합니다.<br>

                    ② "몰"은 약관의 규제 등에 관한 법률, 전자거래기본법, 전자서명법, 정보통신망 이용 촉진 등에 관한 법률, 방문판매 등에 관한 법률, 소비자보호법 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.<br>

                    ③ "몰"이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행 약관과 함께 몰의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다.<br>

                    ④ "몰"이 약관을 개정할 경우에는 그 개정 약관은 그 적용 일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관 조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정 약관 조항의 적용을 받기를 원하는 뜻을 제③항에 의한 개정 약관의 공지 기간 내에 "몰"에 송신하여 "몰"의 동의를 받은 경우에는 개정 약관 조항이 적용됩니다.<br>

                    ⑤ 본 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 정부가 제정한 전자거래소비자보호지침 및 관계법령 또는 상관례에 따릅니다.<br><br>



                    제4조(서비스의 제공 및 변경)<br>
                    ① "몰"은 다음과 같은 업무를 수행합니다.<br>

                    1. 재화 또는 용역, 서비스에 대한 정보 제공 및 구매계약의 체결<br>

                    2. 구매계약이 체결된 재화 또는 용역, 서비스의 이용대금 수납<br>

                    3. 구매계약이 체결된 재화 또는 용역, 서비스의 배송<br>

                    4. 기타 "몰"이 정하는 업무<br>

                    ② "몰"은 재화의 품절 또는 기술적 사양의 변경 등의 경우에는 장차 체결되는 계약에 의해 제공할 재화,용역,서비스의 내용을 변경할 수 있습니다. 이 경우에는 변경된 재화,용역,서비스의 내용 및 제공일자를 명시하여 현재의 재화,용역,서비스의 내용을 게시한 곳에 그 제공일자 이전 7일부터 공지합니다.<br>

                    ③ "몰"이 제공하기로 이용자와 계약을 체결한 서비스의 내용을 재화의 품절 또는 기술적 사양의 변경 등의 사유로 변경할 경우에는 "몰"은 이로 인하여 이용자가 입은 손해를 배상합니다. 단, "몰"에 고의 또는 과실이 없는 경우에는 그러하지 아니합니다.<br><br>



                    제5조(서비스의 중단)<br>
                    ① "몰"은 컴퓨터 등 정보통신설비의 보수점검,교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다.<br>

                    ② 제①항에 의한 서비스 중단의 경우에는 "몰"은 제8조에 정한 방법으로 이용자에게 통지합니다.<br>

                    ③ "몰"은 제①항의 사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상합니다. 단 "몰"에 고의 또는 과실이 없는 경우에는 그러하지 아니합니다.<br><br>



                    제6조(회원가입)<br>
                    ① 이용자는 "몰"이 정한 가입 양식에 따라 회원정보를 기입한 후 이 약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다.<br>

                    ② "몰"은 제①항과 같이 회원으로 가입할 것을 신청한 이용자 중 다음 각호에 해당하지 않는 한 회원으로 등록합니다.<br>

                    1. 가입신청자가 본 약관 제7조 제③항에 의하여 이전에 회원자격을 상실한 적이 있는 경우, 다만 제7조 제③항에 의한 회원자격 상실 후 3년이 경과한 자로서 "몰"의 회원 재가입 승낙을 얻은 경우에는 예외로 한다.<br>

                    2. 등록 내용에 허위, 기재누락, 오기가 있는 경우<br>

                    3. 기타 회원으로 등록하는 것이 "몰"의 기술상 현저히 지장이 있다고 판단되는 경우<br>

                    ③ 회원가입계약의 성립시기는 "몰"의 승낙이 회원에게 도달한 시점으로 합니다.<br>

                    ④ 회원은 제15조 제①항에 의한 등록사항에 변경이 있는 경우, 즉시 전자우편 기타 방법으로 "몰"에 대하여 그 변경사항을 알려야 합니다.<br>

                    ⑤ 회원 정보는 향후 "몰"과 제휴한 사이트를 편리하게 이용하기 위해 사이트간에 공유됩니다. 이때 "몰"은 제휴 사이트명을 전자우편 등으로 회원에게 공지합니다.<br><br>
                    ...이하 생략...<br><br>

                    부칙<br>
                    이용약관 최초제정일자: 2000-08-10<br>

                    이용약관 최종변경일자: 2017-07-05<br>

                    이용약관 시행일자 : 2018-08-06<br>
                </div>
            </div>
        </div>
    </div>
    <script>
        let sessionId = '<c:out value="${sessionScope.user.id}"/>';
        function checkSessionId(){
            if(sessionId===''){
                alert('먼저 로그인을 해주세요.');
                return false;
            }
        }
    </script>

</main>
