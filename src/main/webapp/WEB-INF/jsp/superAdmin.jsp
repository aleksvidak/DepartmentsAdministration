<%@page import="model.Katedra"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/MasterPageStyle.css' />
<title><spring:message code="head.text" /></title>
</head>
<body>
<div id='wrapper'>
<div id='header'>
<table>
	<tbody>
		<tr>
			<td><div class="logo-container">
<div class="logo"></div>
</div></td>
<td><p id='headertekst'><spring:message code="title1.text" /></p></td>
<td style="text-align: center;	vertical-align: bottom;">
<p id="trenutniKorisnik"><spring:message code="welcome.text" /> ${nastavnik}</p>
<a href="izmeniNalog.html"><spring:message code="edit_account.text" /></a></td>
<td><button class="btnOdjava" onclick="location.href = '<c:url value="/logout" />'"><spring:message code="logout.text" /></button></td>
		</tr>		
	</tbody>
</table>
</div>
<div id='maincontent'>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='superAdmin.html'><span><spring:message code="menu_main.text" /></span></a></li>
   <li class='has-sub'><a href='#'><span><spring:message code="menu_departments.text" /></span></a>
      <ul>
      <%List<Katedra> listaKatedri=(List<Katedra>)request.getAttribute("listaKatedri");%>
      <%for(int i=0;i<listaKatedri.size();i++){
    	  %>
    	  <li class='has-sub'><a href='katedra.html?id=<%= listaKatedri.get(i).getID_katedre() %>'><span><spring:message code='<%= listaKatedri.get(i).getNaziv_katedre().replaceAll(" " , "_").toLowerCase().concat(".text") %>'></spring:message></span></a> </li>
    	  <%
      }
      %>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span><spring:message code="menu_data.text" /></span></a>
      <ul>
         <li><a href='katedraSifarnik.html'><span><spring:message code="submenu_department.text" /></span></a></li>
         <li><a href='laboratorija.html'><span><spring:message code="submenu_laboratory.text" /></span></a></li>
         <li><a href='predmet.html'><span><spring:message code="submenu_subject.text" /></span></a></li>
         <li><a href='vrstaNastavnika.html'><span><spring:message code="submenu_teacher_type.text" /></span></a></li>
         <li><a href='kabinet.html'><span><spring:message code="submenu_classroom.text" /></span></a></li>
         <li><a href='zvanje.html'><span><spring:message code="submenu_title.text" /></span></a></li>
         <li class='last'><a href='nastavnik.html'><span><spring:message code="submenu_teacher.text" /></span></a></li>
      </ul>
   </li>
   <li><a href='oFakultetu.html'><span><spring:message code="menu_about.text" /></span></a></li>
   <li class='last'><a href='#'><span><spring:message code="menu_contact.text" /></span></a></li>
</ul>
</div>
<div id='content'> 
<h1><spring:message code="welcome_content.text" /></h1>
</div>
</div>

<div id='footer'>
<h1 style="padding-top:10px;"><spring:message code="footer_content.text" /></h1>
</div>

</div>
</body>
</html>