<%@ page language="java" contentType="text/html; charset=utf-8" %>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Prijava</title>

<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
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
		<h1 class="title1">Fakultet organizacionih nauka</h1>
		<h2 class="title2">Dobrodošli</h2>
		<div class="logo-container" style="width:800px; margin:0 auto;">
		<div class="logo"></div>
		</div>
		
		<form:form commandName="loginForma" class="login" id="frm_Login">
		    <p>
		      <label for="login">Email:</label>
		      <form:input path="korisnickoIme" placeholder="name@examle.com" />		      
		   
		    </p>
		
		    <p>
		      <label for="password">Lozinka:</label>
		      <form:password path="lozinka" placeholder="vaša lozinka"/>
		 
		    </p>
		
		    <p class="login-submit">
		      <button type="submit" class="login-button">Prijava</button>
		    </p>
		
		    <p class="forgot-password"><a href="index.html">Zaboravili ste lozinku?</a></p>
		</form:form>
		
			
		
		<!-- 
		  <form method="post" class="login">
		    <p>
		      <label for="login">Email:</label>
		      <input type="text" name="login" id="login" placeholder="name@examle.com">
		    </p>
		
		    <p>
		      <label for="password">Lozinka:</label>
		      <input type="password" name="password" id="password" placeholder="vaša lozinka">
		    </p>
		
		    <p class="login-submit">
		      <button type="submit" class="login-button">Prijava</button>
		    </p>
		
		    <p class="forgot-password"><a href="index.html">Zaboravili ste lozinku?</a></p>
		  </form>
		   -->
</body>
</html>