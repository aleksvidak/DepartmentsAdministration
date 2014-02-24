<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Prijava</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
</head>
<body>
<h1 class="title1">Fakultet organizacionih nauka</h1>
<h2 class="title2">Dobrodošli</h2>
<div class="logo-container" style="width:800px; margin:0 auto;">
<div class="logo"></div>
</div>
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
</body>
</html>