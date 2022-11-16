
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/views/common/header_nobanner_nobg.jsp" %>
    <!--
    입력폼에 오류가 있어 다시 입력폼으로 돌아올 경우
    1.bindingResult에 저장된 fieldValue를 다시 보여주고
        validation 어노테이션에 설정한 메시지를 보여준다
    2.price,stock의 값이 숫자가 아닐경우
        typeMismatch를 체크해 다른 메시지를 보여줌
    -->
<link rel="stylesheet" href="<c:url value="/resources/css/addForm.css"/>">
<div id="wrap">
    <div id="main">
        <div id="join_wrap">
            <div id="sub_banner">
                <p><strong>물품 등록</strong></p>
            </div>
            <form id="join_form" action="<c:url value="/book/add"/>" method="post" enctype="multipart/form-data">
                <script>
                    <spring:hasBindErrors name="book">
                        alert('입력값을 확인해주세요');
                    </spring:hasBindErrors>
                </script>
                <div class="personal_info">
                    <p>
                        <!--타이틀-->
                        타이틀
                        <label for="title">
                            <input type="text" id="title" name="title" value="${bindingResult.getFieldValue("title")}"
                                   placeholder="<spring:message code="placeholder.book.title"/>">
                        </label>
                        <!--바인딩에러가 있을경우-->
                        <spring:hasBindErrors name="book">
                        <!--해당 필드 오류가 있을때 오류 메시지 출력-->
                        <c:if test="${errors.hasFieldErrors('title')}">
                            <div class="binding-error">${errors.getFieldError("title").defaultMessage}</div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--타이틀-->
                    </p>
                    <p style="width:30%;">
                        <!--출판일-->
                        출판일
                        <label for="pubDate">
                            <input type="date" id="pubDate" name="pubDate" value="${bindingResult.getFieldValue("pubDate")}"
                                   placeholder="<spring:message code="placeholder.book.pubDate"/>">
                        </label>
                        <spring:hasBindErrors name="book">
                        <!--pubDate에 바인딩 오류가 있을때-->
                        <c:if test="${errors.hasFieldErrors('pubDate')}">
                            <div class="binding-error">
                                <!--타입미스매치(IllegalArgumentException) 발생시 다른 오류메시지 생성-->
                                <!--validation이 작동하면 기본 메시지로-->
                                <c:choose>
                                    <c:when test="${errors.getFieldError('pubDate').
                                            defaultMessage.contains('java.lang.IllegalArgumentException')}">
                                        <spring:message code="DateFormat"/>
                                    </c:when>
                                    <c:otherwise>
                                        ${errors.getFieldError("pubDate").defaultMessage}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--출판일-->
                    </p>
                    <p>
                        저자
                        <!--저자-->
                        <label for="author">
                            <input type="text" id="author" name="author" value="${bindingResult.getFieldValue("author")}"
                                   placeholder="<spring:message code="placeholder.book.author"/>">
                        </label>
                        <spring:hasBindErrors name="book">
                        <c:if test="${errors.hasFieldErrors('author')}">
                            <div class="binding-error">${errors.getFieldError("author").defaultMessage}</div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--저자-->
                    </p>
                    <p>
                        가격
                        <!--가격-->
                        <label for="price">
                            <input type="text" id="price" name="price" value="${bindingResult.getFieldValue("price")}"
                                   placeholder="<spring:message code="placeholder.book.price"/>">
                        </label>
                        <spring:hasBindErrors name="book">
                        <!--price에 바인딩 오류가 있을때-->
                        <c:if test="${errors.hasFieldErrors('price')}">
                            <div class="binding-error">
                            <!--타입미스매치(numberformatException) 발생시 다른 오류메시지 생성-->
                            <!--validation이 작동하면 기본 메시지로-->
                                <c:choose>
                                    <c:when test="${errors.getFieldError('price').
                                    defaultMessage.contains('java.lang.NumberFormatException')}">
                                        <spring:message code="NumberFormat"/>
                                    </c:when>
                                    <c:otherwise>
                                        ${errors.getFieldError("price").defaultMessage}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--가격-->
                    </p>
                    <p>
                        재고
                        <!--재고-->
                        <label for="stock">
                            <input type="text" id="stock" name="stock" value="${bindingResult.getFieldValue("stock")}"
                                   placeholder="<spring:message code="placeholder.book.stock"/>">

                        </label>
                        <spring:hasBindErrors name="book">
                        <!--stock에 바인딩 오류가 있을때-->
                        <c:if test="${errors.hasFieldErrors('stock')}">
                            <div class="binding-error">
                                <!--타입미스매치(numberformatException) 발생시 다른 오류메시지 생성-->
                                <!--validation이 작동하면 기본 메시지로-->
                                <c:choose>
                                    <c:when test="${errors.getFieldError('stock').
                                    defaultMessage.contains('java.lang.NumberFormatException')}">
                                        <spring:message code="NumberFormat"/>
                                    </c:when>
                                    <c:otherwise>
                                        ${errors.getFieldError("stock").defaultMessage}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--재고-->
                    </p>
                    <p>
                        출판사
                        <!--출판사-->
                        <label for="publisher">
                            <input type="text" id="publisher" name="publisher" value="${bindingResult.getFieldValue("publisher")}"
                                   placeholder="<spring:message code="placeholder.book.publisher"/>">

                        </label>
                        <spring:hasBindErrors name="book">
                        <c:if test="${errors.hasFieldErrors('publisher')}">
                            <div class="binding-error">${errors.getFieldError("publisher").defaultMessage}</div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--출판사-->
                    </p>
                    <p>
                        카테고리
                        <!--카테고리-->
                        <label for="categoryName"><!--수정해야함, 옵션으로 고를수있게-->
                            <input type="text" id="categoryName" name="categoryName" value="${bindingResult.getFieldValue("categoryName")}"
                                   placeholder="<spring:message code="placeholder.book.categoryName"/>">
                        </label>
                        <spring:hasBindErrors name="book">
                        <c:if test="${errors.hasFieldErrors('categoryName')}">
                            <div class="binding-error">${errors.getFieldError("categoryName").defaultMessage}</div>
                        </c:if>
                        </spring:hasBindErrors>
                        <!--카테고리-->
                    </p>

                        <!--이미지 업로드-->
                        <label for="uploadFile">
                            <input  style="cursor: pointer; border: none; outline: none;" type="file"
                                    id="uploadFile" name="uploadFile" value="${bindingResult.getFieldValue("uploadFile")}"
                                    placeholder="<spring:message code="placeholder.book.uploadFile"/>">
                            이미지업로드 커버
                        </label>
                        <spring:hasBindErrors name="book">
                            <c:if test="${errors.hasFieldErrors('uploadFile')}">
                                <div class="binding-error">${errors.getFieldError("uploadFile").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>

                        <label for="uploadFiles" style="margin-top:10px; cursor: pointer; border: none; outline: none;">
                            <input  style="margin-top:10px; cursor: pointer; border: none; outline: none;"
                                    type="file" multiple="multiple" id="uploadFiles" name="uploadFiles"
                                    value="${bindingResult.getFieldValue("uploadFiles")}"
                                    placeholder="<spring:message code="placeholder.book.uploadFiles"/>">
                            이미지업로드 상품상세
                        </label>
                        <spring:hasBindErrors name="book">
                            <c:if test="${errors.hasFieldErrors('uploadFiles')}">
                                <div class="binding-error">${errors.getFieldError("uploadFiles").defaultMessage}</div>
                            </c:if>
                        </spring:hasBindErrors>
                </div>

                <p style="margin-top:20px;">
                    <!--상세설명-->
                    <label for="description"><!--수정해야함, textarea로-->
                        <textarea rows="4" cols="50"
                                  id="description" name="description"
                                  style="resize: none;"
                                  placeholder="<spring:message code="placeholder.book.description"/>">${bindingResult.getFieldValue("description")}</textarea>
                    </label>
                    <spring:hasBindErrors name="book">
                    <c:if test="${errors.hasFieldErrors('description')}">
                    <div class="binding-error">${errors.getFieldError("description").defaultMessage}</div>
                    </c:if>
                    </spring:hasBindErrors>
                    <!--상세설명-->
                </p>
                <div id="login_btn">
                    <button type="submit">전송</button>
                </div>
            </form>
        </div>
    </main>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>