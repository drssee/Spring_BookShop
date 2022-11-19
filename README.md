<h1 align='center'> <img src='https://user-images.githubusercontent.com/113401870/202498788-74498caa-d4a2-4131-806a-a2c3da64db8f.png' style='width: 300px; height: 100px;'>&nbsp;</h1>
<h1  align='center'>📚스프링 책 쇼핑몰 프로젝트</h1>
<div align='center'>
  <h3>
    🔗 <a href="http://13.124.243.50:8080/bookshop/">http://13.124.243.50:8080/bookshop/</a> 사이트 바로가기
  </h3>
</div>
<br/><br/>

## 목차
- [개요](https://github.com/drssee/Spring_bookShop#-개요)
- [기술 스택](https://github.com/drssee/Spring_bookShop#-기술-스택)
- [시스템 구조도](https://github.com/drssee/Spring_bookShop#-시스템-구조도)
- [프로젝트 설계](https://github.com/drssee/Spring_bookShop#-프로젝트-설계)
- [핵심 기능](https://github.com/drssee/Spring_bookShop#-핵심-기능)
- [주요기능 실행화면](https://github.com/drssee/Spring_bookShop#-주요기능-실행화면)
- [개선사항](https://github.com/drssee/Spring_bookShop#-개선사항)

## 🚩 개요
- 프로젝트 목표 : 다양한 `API`를 활용한 `스프링` , `마이바티스` 책 쇼핑몰 프로젝트
- 개발기간 : 22/11/01 ~ 22/11/18



## 🔧 기술 스택
- Language : `Java(11)` `JavaScript(1.5)`
- Library & Framework : `Spring(5.3.23)` `Junit(5.8.2)` `Servlet(4.0.1)`
- Database : `MariaDB(10.8.3)`
- Target : `Web Browser`
- Tool : `Intellij`
- Infra : `Linux(Ubuntu)` `EC2`
- Etc : `Git`

## 📐 시스템 구조도

<img width="412" alt="스크린샷 2022-11-18 오전 2 30 12" src="https://user-images.githubusercontent.com/113401870/202516487-ee7d255b-f89a-4553-af0e-80ac618e87ec.png">


## 👾 프로젝트 설계
### ERD
-ERD(논리)
  <img width="684" alt="erd(논리)" src="https://user-images.githubusercontent.com/113401870/202856670-e87b5311-442e-4b1b-b1b3-1fa09d9c1a7c.png">
-ERD(물리)
  <img width="824" alt="erd(물리)" src="https://user-images.githubusercontent.com/113401870/202856671-76627768-8a72-437f-aff5-7ed70ba36c32.png">
### 클래스 다이어그램
![book](https://user-images.githubusercontent.com/113401870/202609068-4d67068c-8d63-4391-8d2b-5330476a39c5.png)

## 💻 핵심 기능

#### 상품
- 알라딘API를 이용한 도서정보 DB 저장
- 상품 정렬(베스트셀러, 이달의신간, 등록순)
- 상품 검색(제목, 카테고리, 제목+내용)
- 좋아요
- 상품 정보 제공

#### 유저
- 쿠키,UUID,인터셉터를 이용한 로그인기억 및 아이디기억
- 아이디 중복 처리
- 비밀번호 처리
- 다음 우편주소 API
- 마이페이지(장바구니, 결제정보, 나의1:1문의) 
- 유저 정보 수정

#### 장바구니
- 상품 장바구니에 담기 및 제거
- 실시간 수량 수정후 결제

#### 주문
- 장바구니 상품 주문
- 주문 정보 확인
- IamportAPI 이용한 결제

#### 리뷰(한줄평)
- 리뷰(작성, 수정, 삭제)
- 리뷰의 리뷰

#### 관리자
- 상품 등록
- 상품 재고 및 이미지 수정
- 고객 결제 관리 및 배송 (배송 부분은 구현중입니다.)
- 고객의 1:1문의 관리

#### 고객센터
- 비밀글 (작성자, 관리자만 조회 가능)
- 1:1 문의 작성 및 문의 조회
- 관리자의 계층형 답글
 
## 🎇 주요기능 실행화면

<details>
<summary>주요기능 실행화면</summary>

* **메인 화면**
  * `카테고리` 메뉴를 사용해 카테고리 별로 상품을 확인할 수 있습니다.
  ![category](https://user-images.githubusercontent.com/113401870/202537990-ca444583-bde8-422a-847a-ff26b679c0bd.gif)
* **회원가입 및 로그인**
  * 회원가입시 프론트+서버 검증으로 `잘못 입력된 부분과 그 값`을 다시 보여줍니다. 
    ![join](https://user-images.githubusercontent.com/113401870/202538555-8caeccbc-70da-4294-a753-0c54bfb3e3f2.gif)
  
  * `다음 우편주소API`를 이용해 배송을 위한 정확한 주소를 가져올 수 있습니다.
   ![join2](https://user-images.githubusercontent.com/113401870/202538839-10142c8f-26e5-4b2f-b6b2-13ee29ff6386.gif)

* **상품 상세 조회 및 좋아요**
  * 상품 목록에서 상품의 사진을 클릭하면 `상품 상세 정보` 를 확인할 수 있습니다.
  * `상품 상세` 페이지에서 좋아요(추천)을 할 수 있습니다.
  ![like](https://user-images.githubusercontent.com/113401870/202544810-8cf38a72-3d59-4b1e-815d-e3920a45fc6e.gif)
* **리뷰(한줄평) 작성**
  * `상품 상세` 페이지에서 리뷰를 등록할 수 있습니다.
  * 등록한 리뷰는 수정 및 삭제가 가능합니다.
  * 리뷰의 리뷰를 작성할 수 있습니다.
  ![review](https://user-images.githubusercontent.com/113401870/202545394-96ae900a-edf2-4c6f-aeb3-6739c8064ac3.gif)
* **장바구니**
  * `상품 상세보기`에서 `장바구니 상품 추가`가 가능합니다.
  * `장바구니` 메뉴에서 추가한 상품의 확인 및 수량변경이 가능합니다. 장바구니의 `결제하기` 를 누르면 결제페이지로 이동합니다.
  ![cart](https://user-images.githubusercontent.com/113401870/202547242-59206077-3a63-458c-a900-1c30e6084b61.gif)
  
* **주문하기**
  * `결제하기` 를 누르면 IamportAPI와 연동된 kg이니시스 결제페이지로 이동합니다.
  * 결제가 완료되면 `결제 내역` 메뉴에서 결제 정보를 확인할 수 있습니다.
  ![pay](https://user-images.githubusercontent.com/113401870/202548370-ebc47a3d-f5ee-46e7-b277-d4bc5818dd51.gif)
  * 주문상태가 배송이전이면 결제를 취소 할 수 있습니다.  
    ![pay2](https://user-images.githubusercontent.com/113401870/202548606-46d00f5c-6817-4128-850b-1bb6cb34bc75.gif)
* **관리자 페이지**

  * `물품등록` 메뉴에서 상품을 등록 할 수 있습니다.
  ![admin2](https://user-images.githubusercontent.com/113401870/202549811-eddff451-16ea-474f-8448-8be485c3d1fc.gif)

  * `물품수정` 메뉴에서 상품들의 재고 및 이미지등을 수정 할 수 있습니다.
  ![admin3](https://user-images.githubusercontent.com/113401870/202550032-ac7dd6a8-7f8d-4dd9-ad06-ddd9e0650506.gif)

  * `고객관리` 메뉴에서 고객의 환불요청을 진행할 수 있습니다.
  ![admin4](https://user-images.githubusercontent.com/113401870/202550341-2bc73d90-5891-49fe-aa8d-a8848b59e0c1.gif)

* **고객센터**
  * 고객센터에서 유저가 1:1문의를 작성하면 관리자가 답변을 해줄 수 있습니다.
  ![고객센터1](https://user-images.githubusercontent.com/113401870/202552961-c82c3b11-273c-45a7-8cc8-1a44edc838c0.gif)
  ![고객센터2](https://user-images.githubusercontent.com/113401870/202552971-c5818ec6-24c3-414c-a611-af7b9a39082f.gif)
  ![고객센터3](https://user-images.githubusercontent.com/113401870/202552977-d01d5362-e653-49cf-991e-6d8fadb49562.gif)
</details>

## 🌄 개선사항
- 소셜로그인과 택배사를 통한 배송기능 추가
- 관리자를 위한 통계페이지
