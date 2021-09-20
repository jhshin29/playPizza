<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String url = application.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Menu</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="layout.css">
<style>
div img {width:100%; height: 300px; cursor: pointer;}
img:hover {
	opacity:0.8;
    transform:scale(1.05);
    transition: transform.2s;
}
</style>
</head>
<body>
<jsp:include page="../common/navbar.jsp"></jsp:include>
<header class="w3-container" style="padding:128px 16px; margin: 0 20%">
	<h1>Menu</h1>

	<br>
	<h5>메뉴 검색</h5>
	<form action="pizza?command=menu" method="post">
		<input class="w3-input" type="text" id="name" name="name" placeholder="메뉴명을 입력해주세요." required style="width: 75%; display: inline; margin: 20px 0">
		<input class="w3-input w3-red" type="submit" value="검색" style="width: 20%; display: inline;">
	</form>

	<br>
	<h5>메뉴별 정보</h5>
	
	<!-- Photo grid -->
	<div class="w3-row">
		<div class="w3-third">
			<img src="images/pepperoni.jpg" onclick="location.href='${url}pizza?command=menu&name=페퍼로니 피자'" alt="페퍼로니 피자">
			<img src="images/chicago.jpg" onclick="location.href='${url}pizza?command=menu&name=시카고 피자'" alt="시카고 피자">
			<img src="images/cola.jpg" onclick="location.href='${url}pizza?command=menu&name=콜라'" alt="콜라">
		</div>

		<div class="w3-third">
			<img src="images/cheese.jpg" onclick="location.href='${url}pizza?command=menu&name=치즈 피자'" alt="치즈 피자">
			<img src="images/berry.jpg" onclick="location.href='${url}pizza?command=menu&name=베리 피자'" alt="베리 피자">
			<img src="images/cider.jpg" onclick="location.href='${url}pizza?command=menu&name=사이다'" alt="사이다">
		</div>
    
		<div class="w3-third">
			<img src="images/vege.jpg" onclick="location.href='${url}pizza?command=menu&name=야채 피자'" alt="야채 피자">
			<img src="images/pasta.jpg" onclick="location.href='${url}pizza?command=menu&name=토마토 파스타'" alt="토마토 파스타">
			<img src="images/beer.jpg" onclick="location.href='${url}pizza?command=menu&name=맥주'" alt="맥주">
		</div>
	</div>
	
</header>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>