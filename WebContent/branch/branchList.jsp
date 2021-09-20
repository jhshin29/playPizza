<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String url = application.getContextPath() + "/";
	//http://ip:port/project/ 구조의 변수 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Branch</title>
<jsp:include page="../common/styleLink.jsp"></jsp:include>
</head>
<body>

	<%-- http://ip:port/project/common/navbar.jsp --%>
	
	<jsp:include page="../common/navbar.jsp"></jsp:include>
	
	<header class="w3-container"
		style="padding:128px 16px; margin: 0 20%">
	<h1>Branch</h1>

	<br>
	<h5>지점 검색</h5>
	<form action="pizza?command=branch" method="post">
		<input class="w3-input" type="text" id="name" name="name"
			placeholder="지점명을 입력해주세요." required
			style="width: 75%; display: inline; margin: 20px 0"> <input
			class="w3-input w3-red" type="submit" value="검색"
			style="width: 20%; display: inline;">
	</form>

	<br>
	<h5>전체 지점</h5>

	<table class="w3-table">
		<thead>
			<tr class="w3-red">
				<th style="text-align: center;">지점 리스트 Click</th>
			</tr>
		</thead>

		<c:forEach items="${requestScope.branchesAll}" var="data">
			<tr>
				<td
					onclick="location.href='${url}pizza?command=branch&name=${data.name}'"
					style="border-bottom: 1px solid #ddd; cursor: pointer;">${data.name}</td>
			</tr>
		</c:forEach>

	</table>

	</header>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>