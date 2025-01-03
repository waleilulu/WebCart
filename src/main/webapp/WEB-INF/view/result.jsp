<%@page import="model.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>  <!-- 配合插入在pom.xml的JSTL -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>結果頁面</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" >
	</head>
	<body>
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px">
			<fieldset>
				<legend>結果</legend>
				${ result }<p />
<%-- 				<c:if test="${ userDto ne null }"> <!-- 有userDto顯示的話在印出來，沒有就不印 --> --%>
<%-- 				<c:if test="${ userDto != null }"> <!-- 也可以寫成這樣 --> --%>
<%-- 				session userDto 變數: ${userDto}<p /> <!-- EL，這樣寫也可以 --> --%>
<%-- 				session userDto 變數: ${sessionScope.userDto}<p /> <!-- 這樣寫比較標準 --> --%>
<%-- 				session userDto 變數: <%=session.getAttribute("userDto") %><p /> <!-- 傳統寫法 --> --%>
<%-- 				session userDto 變數: <%=pageContext.getAttribute("userDto", PageContext.SESSION_SCOPE) %><p /> --%>
<%-- 				</c:if> --%>
				<a href="${ redirectURL }">${ redirectName }</a>
			</fieldset>
		</div>
	</body>
</html>