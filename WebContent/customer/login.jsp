<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<jsp:include page="../common/styleLink.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../common/navbar.jsp"></jsp:include>

	<header class="w3-container" style="padding: 128px 16px; margin: 0 20%">
		<form action="../pizza?command=login" method="post">
			<h1>Login</h1>
			<h3>ID와 Password를 입력해주세요.</h3>
			<br>
			
			<label for="id">ID</label>
			<input class="w3-input" type="text" id="id" name="id" required style="margin-bottom:20px;">
			
			<label for="pwd">Password</label>
			<input class="w3-input" type="password" id="pwd" name="pwd" required>

			<input class="w3-input w3-red" type="submit" value="로그인" style="cursor:pointer; margin-top:20px;">
		</form>
	</header>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>