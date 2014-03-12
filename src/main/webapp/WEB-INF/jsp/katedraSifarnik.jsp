<%@page import="model.Nastavnik"%>
<%@page import="model.Katedra"%>
<%@page import="java.util.List"%>
<%@page import="model.Laboratorija"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Sifarnik katedre</title>
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

<script type="text/javascript" src="resources/script/katedraSifarnik.js"></script>
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
         <li><a href='vrstaNastavnika.html'><span>Vrsta nastavnika</span></a></li>
         <li><a href='kabinet.html'><span>Kabinet</span></a></li>
         <li><a href='zvanje.html'><span>Zvanje</span></a></li>
         <li class='last'><a href='nastavnik.html'><span>Nastavnik</span></a></li>
      </ul>
   </li>
   <li><a href='#'><span>O fakultetu</span></a></li>
   <li class='last'><a href='#'><span>Kontakt</span></a></li>
</ul>
</div>
<div id='content'> 
<div style="width: 100%">
	<table id="tbKatSif" width="100%">
			<thead>
				<tr>
					<td>ID_Katedre</td>
					<td>Katedra</td>
					<td>ID_Rukovodilac</td>
					<td>Rukovodilac</td>
					<td>ID_Sekretar</td>
					<td>Sekretar</td>
				</tr>
			</thead>				
			<tbody>				
				<%for(int i=0;i<listaKatedri.size();i++){
						%>
					<tr>
					<td><%= listaKatedri.get(i).getID_katedre()%></td>
					<td><%= listaKatedri.get(i).getNaziv_katedre() %></td>
					<td><%= listaKatedri.get(i).getNastavnik1().getId_nastavnika() %></td>
					<td><%= listaKatedri.get(i).getNastavnik1().getIme()+" "+ listaKatedri.get(i).getNastavnik1().getPrezime() %></td>
					<td><%= listaKatedri.get(i).getNastavnik2().getId_nastavnika() %></td>
					<td><%= listaKatedri.get(i).getNastavnik2().getIme()+" "+ listaKatedri.get(i).getNastavnik2().getPrezime() %></td>
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
       <div id="divKatSifPOPUP" style="width:1000px; display: none" >
        <form  id="frmKatSif">
        	<table>
        		<tbody>
        			<tr>
        				<td>Katedra:</td>
        				<td><input type="text" id="nazivKat" name="nazivKat"></td>
        			</tr>
        			<tr>
        				<td>Rukovodilac:</td>
        				<td>
        				<select id="rukovodilac" name="rukovodilac">
        				<option value="">--Izaberite--</option>
        					<% List<Nastavnik>lNastavnik=(List<Nastavnik>)request.getAttribute("listaNastavnik");        						
        					for(int i=0;i<lNastavnik.size();i++){
        					%>
        					<option value=<%= lNastavnik.get(i).getId_nastavnika() %>>
        					<%= lNastavnik.get(i).getIme()+" "+lNastavnik.get(i).getPrezime()  %>
        					</option>>
        					<%		
        						}
        					%>
        				</select>
						</td>
        			</tr>
        			<tr>
        				<td>Sekretar:</td>
        				<td>
        				<select id="sekretar" name="sekretar">
        				<option value="">--Izaberite--</option>
        					<%       						
        					for(int i=0;i<lNastavnik.size();i++){
        					%>
        					<option value=<%= lNastavnik.get(i).getId_nastavnika() %>>
        					<%= lNastavnik.get(i).getIme()+" "+lNastavnik.get(i).getPrezime()  %>
        					</option>>
        					<%		
        						}
        					%>
        				</select>
        				</td>
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