<%@page import="model.Katedra"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/MasterPageStyle.css' />
<title>Super administrator</title>
</head>
<body>
<div id='wrapper'>
<div id='header'>
<p id='headertekst'>Fakultet organizacionih nauka</p>
</div>
<div id='maincontent'>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='#'><span>Naslovna</span></a></li>
   <li class='has-sub'><a href='#'><span>Katedre</span></a>
      <ul>
      <%List<Katedra> listaKatedri=(List<Katedra>)request.getAttribute("listaKatedri");%>
      <%for(int i=0;i<listaKatedri.size();i++){
    	  %>
    	  <li class='has-sub'><a href='katedra.html?id=<%= listaKatedri.get(i).getID_katedre() %>'><span><%= listaKatedri.get(i).getNaziv_katedre() %></span></a> </li>
    	  <%
      }
      %>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>Sifarnici</span></a>
      <ul>
         <li><a href='katedraSifarnik.html'><span>Katedra</span></a></li>
         <li><a href='laboratorija.html'><span>Laboratorija</span></a></li>
         <li><a href='predmet.html'><span>Predmet</span></a></li>
         <li class='last'><a href='#'><span>Nastavnik</span></a></li>
      </ul>
   </li>
   <li><a href='#'><span>O fakultetu</span></a></li>
   <li class='last'><a href='#'><span>Kontakt</span></a></li>
</ul>
</div>
<div id='content'> 
<h1>DOBRODOSLI BLA BLA BLA</h1>
</div>
</div>

<div id='footer'>
<h1>Sva prava zadrzana, bla bla bla!</h1>
</div>

</div>
</body>
</html>