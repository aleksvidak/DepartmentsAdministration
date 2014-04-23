<%@ page language="java" contentType="text/html; charset=utf-8" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <% response.setHeader("pragma", "no-cache"); 
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");  
	response.setHeader("Expires", "-1"); %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="title.text" /></title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/loginStyle.css" rel="stylesheet" >
<style>
.error {
	color: #ff0000;
}
</style>
<script type="text/javascript" src="script/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="script/jquery.validate.js"></script>
<script type="text/javascript" src="script/login.js"></script>
</head>
<body>
		<h1 class="title1"><spring:message code="title1.text" /></h1>
		<h2 class="title2"><spring:message code="title2.text" /></h2>
		<div class="logo-container" style="width:800px; margin:0 auto;">
		<div class="logo"></div>
		</div>
		<div class="lang"><spring:message code="language.text" />&nbsp;&nbsp;&nbsp;<a href="?language=rs">Srpski</a> | <a href="?language=en">English</a></div>
		<c:url value='/j_spring_security_check' var="j_check"></c:url>
		<form:form commandName="loginForma" class="login" id="frm_Login" action="${j_check}" method="POST">
		    <p>
		      <label for="username"><spring:message code="email.text" /></label>
		      <form:input path="j_username" autocomplete="off" placeholder="name@examle.com" />	      	   
		    </p>
		    <p>
		      <label for="password"><spring:message code="password.text" /></label>
		      <form:password path="j_password" autocomplete="off" placeholder="vaša lozinka"/>
		    </p>
		    <p class="login-submit">
		      <button type="submit" class="login-button"><spring:message code="title.text" /></button>
		    </p>
		    <p class="forgot-password"><a href="index.html"><spring:message code="forgotPassword.text" /></a></p>
		</form:form>
</body>
</html>