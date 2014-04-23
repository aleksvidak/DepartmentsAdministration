<%@page import="model.Pripadnost_Nastavnika_katedri"%>
<%@page import="model.Pripadnost_predmeta_katedri"%>
<%@page import="model.Nastavnik"%>
<%@page import="model.Laboratorija"%>
<%@page import="model.Katedra"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/MasterPageStyle.css' />
	
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/jquery-ui-1.9.2.custom.css' />
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/jquery.dataTables_themeroller.css' />
<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/resources/css/jquery_notification.css' />

<script type="text/javascript" src="resources/script/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="resources/script/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/script/jquery-ui-1.9.2.custom.min.js"></script>

<script type="text/javascript" src="resources/script/jquery.validate.js"></script>
<script type="text/javascript" src="resources/script/jquery_notification_v.1.js"></script>
	
	
	<script type="text/javascript" src="resources/script/katedraPrezentacion.js"></script>
	
<title>Super administrator</title>
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
<td><p id='headertekst'>Fakultet organizacionih nauka</p></td>
<td style="text-align: center;	vertical-align: bottom;">
<p id="trenutniKorisnik">Dobrodošli, ${nastavnik}</p>
<a href="izmeniNalog.html">Izmeni nalog</a></td>
<td><button class="btnOdjava" onclick="location.href = 'prijava.html';">Odjava</button></td>
		</tr>		
	</tbody>
</table>
</div>
<div id='maincontent'>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='superAdmin.html'><span>Naslovna</span></a></li>
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
         <li><a href='vrstaNastavnika.html'><span>Vrsta nastavnika</span></a></li>
         <li><a href='kabinet.html'><span>Kabinet</span></a></li>
         <li><a href='zvanje.html'><span>Zvanje</span></a></li>
         <li class='last'><a href='nastavnik.html'><span>Nastavnik</span></a></li>
      </ul>
   </li>
   <li><a href='oFakultetu.html'><span>O fakultetu</span></a></li>
   <li class='last'><a href='#'><span>Kontakt</span></a></li>
</ul>
</div>
<div id='content'>
<% Katedra k=(Katedra)request.getAttribute("Katedra"); 
List<Laboratorija> listLab=k.getLaboratorijas();
List<Pripadnost_predmeta_katedri> listPripPredmeta=k.getPripadnostPredmetaKatedris();
List<Pripadnost_Nastavnika_katedri> listPripNast=k.getPripadnostNastavnikaKatedris();
%> 
	<table>
		<tbody>
		  <tr>
    <th id="katedraInfo">Katedra</th>
    <th id="laboratorije">Laboratorije u sastavu katedre</th>
    <th id="predmeti">Predmeti</th>
    <th id="profesori">Profesori</th>
  </tr>
			<tr>
				<td headers="katedraInfo" style="width: 25%; text-align: center; vertical-align: top; padding-top: 10px;">
					Naziv katedre:<br/>
					<%=k.getNaziv_katedre() %><br/>
					Rukovodilac:<br/>
					<%=request.getAttribute("zvanjeRukovodioca") +" "+k.getNastavnik1().getPrezime()+" "+k.getNastavnik1().getIme() %><br/>
					Sekretar:<br/>
					<%=request.getAttribute("zvanjeSekretara") +" "+k.getNastavnik2().getPrezime()+" "+k.getNastavnik2().getIme() %><br/>
				</td>
				<td headers="laboratorije" style="width: 25%; text-align: left; vertical-align: top; padding-top: 10px;">
					<ul><%for(int i=0;i<listLab.size();i++){
						%>
						<li><%= listLab.get(i).getNaziv_laboratorije() %><br/>
							&nbsp;&nbsp;&nbsp;<a href=<%=listLab.get(i).getSajt() %>><%=listLab.get(i).getSajt() %></a></li>
						
					<%	
					} %></ul>
					
				</td>
				<td headers="predmeti" style="width: 25%; text-align: center; vertical-align: top; padding-top: 10px;">
				<div id="divA">
					<%for(int i=0;i<listPripPredmeta.size();i++){
						%>
						<a id="linkZaPredmet"  href="#" name="<%= listPripPredmeta.get(i).getPredmeti().getID_predmeta() %>"><%= listPripPredmeta.get(i).getPredmeti().getNaziv_predmeta() %></a><br/>
					<%	
					} %>
				</div>
				</td>
				<td headers="profesori" style="width: 25%; text-align: center; vertical-align: top; padding-top: 10px;">
					<%for(int i=0;i<listPripNast.size();i++){
						%>
						<a href="http://<%=listPripNast.get(i).getNastavnik().getLicna_prezentacija() %>"><%= listPripNast.get(i).getNastavnik().getIme()+" "+listPripNast.get(i).getNastavnik().getPrezime() %></a><br/>
					<%	
					} %>
					
				</td>
			</tr>
		</tbody>
	</table>

</div>
</div>
<div id="detaljiPredmetaPOPUP" style="display: none;">

	<table>
		<tbody>
			<tr>
				<td>Predavac: <label id="predavac"></label></td>
			</tr>
			<tr>
				<td>Semestar: <label id="semstar"></label></td>
			</tr>
			<tr>
				<td>Broj casova ostalo: <label id="ostalo"></label></td>
			</tr>
			<tr>
				<td>Broj casova vezbi: <label id="vezbe"></label></td>
			</tr>
			<tr>
				<td>Broj casova predavanja: <label id="predavanja"></label></td>
			</tr>
			<tr>
				<td>Broj bodova: <label id="bodova"></label></td>
			</tr>
		</tbody>
	</table>
	   <input type='button' value='Zatvori' id='btnZatvori'/>
</div>

<div id='footer'>
<h1>Sva prava zadrzana, bla bla bla!</h1>
</div>

</div>
</body>
</html>