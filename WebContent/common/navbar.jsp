<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Navbar -->
<c:if test="${empty sessionScope.id}">
<div class="w3-top">
  <div class="w3-bar w3-red w3-card w3-left-align w3-large">
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-padding-large w3-white">Home</a>
    <a href="${pageContext.request.contextPath}/pizza?command=menuAll" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Menu</a>
    <a href="${pageContext.request.contextPath}/pizza?command=branchesAll" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Branch</a>
    <a href="${pageContext.request.contextPath}/customer/login.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Login</a>
    <a href="${pageContext.request.contextPath}/customer/signIn.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Sign In</a>
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="${pageContext.request.contextPath}/pizza?command=menuAll" class="w3-bar-item w3-button w3-padding-large">Menu</a>
    <a href="${pageContext.request.contextPath}/pizza?command=branchesAll" class="w3-bar-item w3-button w3-padding-large">Branch</a>
    <a href="${pageContext.request.contextPath}/customer/login.jsp" class="w3-bar-item w3-button w3-padding-large">Login</a>
    <a href="${pageContext.request.contextPath}/customer/signIn.jsp" class="w3-bar-item w3-button w3-padding-large">Sign In</a>
  </div>
</div>
</c:if>
<c:if test="${not empty sessionScope.id}">
<div class="w3-top">
  <div class="w3-bar w3-red w3-card w3-left-align w3-large">
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-padding-large w3-white">Home</a>
    <a href="${pageContext.request.contextPath}/pizza?command=menuAll" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Menu</a>
    <a href="${pageContext.request.contextPath}/pizza?command=branchesAll" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Branch</a>
    <a href="${pageContext.request.contextPath}/pizza?command=customer&sId=${sessionScope.id}" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">My Page</a>
    <a href="${pageContext.request.contextPath}/pizza?command=logout" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Logout</a>
    <span style="float: right; margin: 10px 20px;">Hello, ${sessionScope.id}</span>
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="${pageContext.request.contextPath}/pizza?command=menuAll" class="w3-bar-item w3-button w3-padding-large">Menu</a>
    <a href="${pageContext.request.contextPath}/pizza?command=branchesAll" class="w3-bar-item w3-button w3-padding-large">Branch</a>
    <a href="${pageContext.request.contextPath}/pizza?command=customer&sId=${sessionScope.id}" class="w3-bar-item w3-button w3-padding-large">My Page</a>
    <a href="${pageContext.request.contextPath}/pizza?command=logout" class="w3-bar-item w3-button w3-padding-large">Logout</a>
  	<span style="float: right; margin: 10px 20px;">Hello, ${sessionScope.id}</span>
  </div>
</div>
</c:if>
	
