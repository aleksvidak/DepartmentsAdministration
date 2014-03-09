<%@page import="model.Predmeti"%>
<%@page import="model.Katedra"%>
<%@page import="java.util.List"%>
<%@page import="model.Laboratorija"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<script type="text/javascript" src="resources/script/predmet.js"></script>
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
         <li><a href='#'><span>Katedra</span></a></li>
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
<div style="width: 100%">
	<table id="tbPredmet" style="width: 100%">
			<thead>
				<tr>
					<td>ID_predmeta</td>
					<td>Predmet</td>
					<td>Broj bodova</td>
					<td>Semestar</td>
					<td>Broj casova predavanja</td>
					<td>Broj casova vezbi</td>
					<td>Broj casova ostalo</td>
				</tr>
			</thead>
				
			<tbody>		
			
				<%List<Predmeti> listPred=(List<Predmeti>)request.getAttribute("listaPredmeta"); %>				
				<%for(int i=0;i<listPred.size();i++){
						%>
					<tr>
					<td><%= listPred.get(i).getID_predmeta() %></td>
					<td><%= listPred.get(i).getNaziv_predmeta() %></td>
					<td><%= listPred.get(i).getBr_bodova() %></td>
					<td><%= listPred.get(i).getSemestar() %></td>
					<td><%= listPred.get(i).getBr_casova_predvanja() %></td>
					<td><%= listPred.get(i).getBr_casova_vezbi() %></td>
					<td><%= listPred.get(i).getBr_casova_ostalo() %></td>
					</tr>						
					<%					
					}%>
			</tbody>
		</table>		
</div>
  		<div>
                 <input type='button' value='Novi' id='btnNew'  style="font-family: Verdana,Arial,sans-serif; font-size: 1em; width: 90px;"/> 
                 <input type='button' value='Izmeni' id='btnChange'  disabled="disabled" style="font-family: Verdana,Arial,sans-serif; font-size: 1em; width: 90px;"/> 
                  <input type='button' value='Obrisi' id='btnDel' disabled="disabled" style="font-family: Verdana,Arial,sans-serif; font-size: 1em; width: 90px;"/> 
        </div>
       <div id="divPredmetPOPUP" style="width:1000px; display: none" >
        <form  id="frmPredmet"  >
        	<table>
        		<tbody>
        			<tr>
        				<td>Predmet:</td>
        				<td><input type="text" id="nazivPred" name="nazivPred"></td>
        			</tr>
        			<tr>
        				<td>Broj bodova:</td>
        				<td><input type="text" id="brBodova" name="brBodova"></td></tr>
        			<tr>
        				<td>Semestar:</td>
        				<td><input type="text" id="semestar" name="semestar"></td>
        			</tr> 
        				<tr>
        				<td>Broj casova predavanja:</td>
        				<td><input type="text" id="brCasovaPredavanja" name="brCasovaPredavanja"></td>
        			</tr>
        				<tr>
        				<td>Broj casova vezbi:</td>
        				<td><input type="text" id="brCasovaVezbi" name="brCasovaVezbi"></td>
        			</tr>
        				<tr>
        				<td>Broj casova ostalo:</td>
        				<td><input type="text" id="brCasovaOstalo" name="brCasovaOstalo"></td>
        			</tr>       			
        		</tbody>
        	</table>
        	
       	<input type="button" value='Sacuvaj' id='btnSacuvaj'/>
        <input type='button' value='Ponisti' id='btnPonisti'/>
        <input type='button' value='Odustani' id='btnOdustani'/>
         </form>
        </div> 
</div>
</div>

<div id='footer'>
<h1>Sva prava zadrzana, bla bla bla!</h1>
</div>

</div>
</body>
</html>