<%@page import="model.Zvanje"%>
<%@page import="model.Kabinet"%>
<%@page import="model.Vrsta_nastavnika"%>
<%@page import="com.fon.jpadatabase.JPADatabase"%>
<%@page import="model.Nastavnik"%>
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

<script type="text/javascript" src="resources/script/nastavnikSifarnik.js"></script>
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
          <li><a href='#'><span>Vrsta nastavnika</span></a></li>
         <li><a href='#'><span>Kabinet</span></a></li>
         <li><a href='#'><span>Zvanje</span></a></li>
         <li class='last'><a href='nastavnik.html'><span>Nastavnik</span></a></li>
      </ul>
   </li>
   <li><a href='#'><span>O fakultetu</span></a></li>
   <li class='last'><a href='#'><span>Kontakt</span></a></li>
</ul>
</div>
<div id='content'> 
<div style="width: 100%">
	<table id="tbNastavnik">
			<thead>
				<tr>
					<td>ID_Nastavnik</td>
					<td>Nastavnik</td>
					<td>E-mail</td>
					<td>ID_VrsteNastavnika</td>
					<td>Vrsta</td>
					<td>ID_Kabineta</td>
					<td>Kabinet</td>
					<td>ID_Zvanja</td>
					<td>Zvanje</td>
					<td>ID_Privilegije</td>
					<td>Privilegija</td>
					<td>Prezentacija</td>
					<td>Telefon</td>
				</tr>
			</thead>
				
			<tbody>		
			
				<%List<Nastavnik> listaNastavnika=(List<Nastavnik>)request.getAttribute("listaNastavnik"); %>				
				<%for(int i=0;i<listaNastavnika.size();i++){
						%>
					<tr>
					<td><%= listaNastavnika.get(i).getId_nastavnika() %></td>
					<td><%= listaNastavnika.get(i).getIme()+" "+listaNastavnika.get(i).getPrezime() %></td>
					<td><%= listaNastavnika.get(i).getEmail() %></td>
					<td><%= listaNastavnika.get(i).getVrstaNastavnika().getId_vrste_nastavnika() %></td>
					<td><%= listaNastavnika.get(i).getVrstaNastavnika().getNaziv_vrste_nastavnika() %></td>
					<td><%= listaNastavnika.get(i).getKabinet().getId_kabineta() %></td>
					<td><%= listaNastavnika.get(i).getKabinet().getBroj_kabineta() %></td>
					<td><%= listaNastavnika.get(i).getId_zvanja_trenutno() %></td>
					<td><%= JPADatabase.dajObjekat().dajTrenutnoZvanje(listaNastavnika.get(i).getId_zvanja_trenutno()) %></td>
					<%try{
						String a=JPADatabase.dajObjekat().dajPrivilegije(listaNastavnika.get(i).getId_nastavnika());
						String [] p=a.split(";");%>
					<td><%=p[0] %></td>
					<td><%= p[1] %></td>
					<%
					}
					catch(Exception e){
						
					}%>
					
					<td><%= listaNastavnika.get(i).getLicna_prezentacija() %></td>
					<td><%= listaNastavnika.get(i).getTelefon() %></td>
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
                  <input type='button' value='Administracija naloga' id='btnAdministracijaNaloga' disabled="disabled" style="font-family: Verdana,Arial,sans-serif; font-size: 1em; width: 200px;"/> 
        </div>
        <div id="listaKatedriZaNastavnika" style="width: 40%; margin-top: 100px;">
		</div>
       <div id="divNastavnikPOPUP" style="width:1000px; display: none" >
        <form  id="frmNastavnik"  >
        	<table>
        		<tbody>
        			<tr>
        				<td>Ime:</td>
        				<td><input type="text" id="ime" name="ime"></td>
        			</tr>
        			<tr>
        				<td>Prezime:</td>
        				<td><input type="text" id="prezime" name="prezime"></td>
        			</tr>
        			<tr>
        				<td>E-mail:</td>
        				<td><input type="text" id="email" name="email"></td>
        			</tr>
        			<tr>
        				<td>Telefon:</td>
        				<td><input type="text" id="telefon" name="telefon"></td>
        			</tr>
        			<tr>
        				<td>Vrsta:</td>
        				<td>
        				<select id="vrsta" name="vrsta">
        				<option value="">--Izaberite--</option>
        					<% 	
        					List<Vrsta_nastavnika> lVrsta=(List<Vrsta_nastavnika>)request.getAttribute("listaVrsta_nastavnika");
        					for(int i=0;i<lVrsta.size();i++){
        					%>
        					<option value=<%= lVrsta.get(i).getId_vrste_nastavnika() %>>
        					<%= lVrsta.get(i).getNaziv_vrste_nastavnika()  %>
        					</option>
        					<%		
        						}
        					%>
        				</select>
        				</td>
        			</tr>
        			<tr>
        				<td>Kabinet:</td>
        				<td><select id="kabinet" name="kabinet">
        				<option value="">--Izaberite--</option>
        				    <% 	
        					List<Kabinet> lKabinet=(List<Kabinet>)request.getAttribute("listaKabinet");
        					for(int i=0;i<lKabinet.size();i++){
        					%>
        					<option value=<%= lKabinet.get(i).getId_kabineta() %>>
        					<%= lKabinet.get(i).getBroj_kabineta()  %>
        					</option>
        					<%		
        						}
        					%>
        				</select>
        				</td>
        				</tr>
        				<tr>
        				<td>Zvanje:</td>
        				<td><select id="zvanje" name="zvanje">
        				<option value="">--Izaberite--</option>
        				    <% 	
        					List<Zvanje> lZvanje=(List<Zvanje>)request.getAttribute("listaZvanje");
        					for(int i=0;i<lKabinet.size();i++){
        					%>
        					<option value=<%= lZvanje.get(i).getID_zvanja() %>>
        					<%= lZvanje.get(i).getNaziv_zvanja() %>
        					</option>
        					<%		
        						}
        					%>
        				</select>
        				</td>
        				</tr>
        				<tr>
        				<td>Licna prezentacija:</td>
        				<td><textarea rows="4" cols="40" id="licPrez" name="licPrez"></textarea></td>
        			</tr>
        			       			
        		</tbody>
        	</table>
        	
       	<input type="button" value='Sacuvaj' id='btnSacuvaj'/>
        <input type='button' value='Ponisti' id='btnPonisti'/>
        <input type='button' value='Odustani' id='btnOdustani'/>
         </form>
        </div> 
       <div id="divKatedraZaNastavnikPOPUP" style="width:1000px; display: none" >
        <form  id="frmKatedraZaNastavnik"  >
        	<table>
        		<tbody>
        			<tr>
        				<td>Katedra:</td>
        				<td><select id="katZaNas" name="katZaNas">
        				<option value="">--Izaberite--</option>
        				    <% 	
        					
        					for(int i=0;i<listaKatedri.size();i++){
        					%>
        					<option value=<%= listaKatedri.get(i).getID_katedre() %>>
        					<%= listaKatedri.get(i).getNaziv_katedre() %>
        					</option>
        					<%		
        						}
        					%>
        				</select>
        				</td>
        				</tr>
        				
        			        			
        		</tbody>
        	</table>
        	
       	<input type="button" value='Sacuvaj' id='btnSacuvajKat'/>    
        <input type='button' value='Odustani' id='btnOdustaniKat'/>
         </form>
        </div>
        
        <div id="divAdministracijaNalogaPOPUP" style="width:1000px; display: none" >
        <form  id="frmdministracijaNaloga"  >
        	<table>
        		<tbody>
        			<tr>
        				<td>Korisnicko ime:</td>
        				<td><input type="text" id="kIme" name="kIme"></td>
        				</tr>
        				<tr>
        				<td>Lozinka:</td>
        				<td><input type="text" id="lozinka" name="lozinka"></td>
        				</tr>
        				 <tr>
        				<td>Privilegija:</td>
        				<td><select id="privilegija" name="privilegija">
        				<option value="">--Izaberite--</option>
        				<option value="superAdmin">superAdmin</option>
        				<option value="adminKatedre">adminKatedre</option>
        				<option value="adminLab">adminLab</option> 
        				<option value="obicanKorisnik">Obican korisnik</option>   
        				</select>
        				</td>
        				</tr>			
        		</tbody>
        	</table>
        	
       	<input type="button" value='Sacuvaj' id='btnSacuvajAdminNal'/>    
        <input type='button' value='Odustani' id='btnOdustaniAdminNal'/>
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