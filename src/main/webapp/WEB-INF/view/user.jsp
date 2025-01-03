<%@ page import="model.dto.UserDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 配合插入在pom.xml的JSTL -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User 會員資料</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
</head>
<body>
	<%@ include file="/WEB-INF/view/menu.jspf" %>
	<div class="pure-form" style="padding: 15px">
		<fieldset>
			<legend>User 會員資料列表</legend>
			<table class="pure-table pure-table-bordered">
				<thead>
					<tr>
						<th>序號</th>
						<th>名稱</th>
						<th>權限</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userDtos}" var="userDto"> <!-- 這是 JSTL 中用於迴圈的標籤 -->
					<tr>
						<td>${userDto.userId}</td>
						<td>${userDto.username}</td>
						<td>${userDto.priority}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</div>
</body>
</html>