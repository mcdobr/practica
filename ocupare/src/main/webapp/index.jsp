<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="org.practica.ocupare.entitati.*" %>
<%@ page import="org.practica.ocupare.entitati.Sala.TipSala" %>
<%@ page import="org.practica.ocupare.entitati.Plan.Periodicitate.TipPeriodicitate" %>
<%@ page import="org.glassfish.jersey.client.*" %>
<%@ page import="javax.ws.rs.client.*" %>
<%@ page import="javax.ws.rs.core.*" %>
<%@ page import="java.net.URI" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%@ page import="org.glassfish.jersey.client.HttpUrlConnectorProvider" %>
<%@ page import="com.fasterxml.jackson.core.JsonParseException" %>
<%@ page import="com.fasterxml.jackson.databind.*" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>AC</title>
<link href="css/style.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="js/script.js"></script>
<link
	href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>

<style>
.list > li > p {
	font-size: 14px; 
}

.row {
	margin-left: 0px;
	margin-right: 0px;
}

label {
    
    margin-bottom: 1px;
}


select {
	display: block;
	width: 100%;
	height: 34px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	padding-left: 11px;
	padding-right: 11px;
}

.form-control {
	height: 35px;
}

</style>
</head>

<body onload="calendar()">

	<div class="navbar">
		<a href="index.jsp" style="text-decoration: none;"><i
			class="fa fa-home" style="font-size: 20px"></i>&nbsp; Acasă </a> <a
			href="login.jsp" id="aut" style="text-decoration: none;"><i
			class="fa fa-sign-in" style="font-size: 20px"></i>&nbsp;
			Autentificare </a> <a href="login.jsp" id="dec" style="text-decoration: none;"><i
			class="fa fa-sign-out" style="font-size: 20px"></i>&nbsp; Deconectare
		</a> <a href="sali.jsp" style="text-decoration: none;"><i
			class="fa fa-calendar-o" style="font-size: 20px;"></i> &nbsp; Săli</a>
		<p id="msg"></p>
	</div>

	<br />

	<div class="container text-center">
		<h2
			style="font-family: Montsserrat; font-style: italic; font-size: 2.3em;">CALENDARUL
			REZERVĂRILOR</h2>
		<hr>

		<div class="main">
			<div class="container">
				<div class="row">

					<div class="wrapper">
						<div id="calendarContainer"></div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div id="organizerContainer"></div>
					</div>

				</div>
			</div>
		</div>

		<br />

		<h2
			style="font-family: Montsserrat; font-style: italic; font-size: 2.3em;">REZERVĂ
			O SALĂ</h2>
		<hr>

		<div class="main">
			<div id="autentification" class="container-fluid">
				<form method="post" action="">
					<div class="align1 alignLeft12">
							<fieldset>
								
								<label class="align1" for="SNumePlan">Nume plan</label>
								<div>
									<input type="text" placeholder="Introdu numele planului" class="form-control" name="SNumePlan"
										id="SNumePlan" required>
								</div>
								
								<label class="align1" for="SDescrierePlan">Descriere plan</label>
								<div>
									<input type="text" placeholder="Introdu o descriere a evenimentului" class="form-control" name="SDescrierePlan"
										id="SDescrierePlan" required>
								</div>
							
							
								<label class="align1" for="SSala">Sală</label>
								<div>
									<select id='SSala'>
									<%!
									
									private static URI getBaseURI() {
										//TODO change the port to whatever is the server running on
										return UriBuilder.fromUri("http://localhost:8080/ocupare/").build();
									} %>
									
									<%
									
									ClientConfig config2 = new ClientConfig();
									Client client = ClientBuilder.newClient(config2);
									client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
									WebTarget service = client.target(getBaseURI());
									
									Response response2;
									response2 = service.path("webapi").path("sali").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
									String data = response2.readEntity(String.class);
									int status = response.getStatus();
									ObjectMapper objectmapper = new ObjectMapper();
									List<Sala> sali = new ArrayList<>();
									try {
										sali = objectmapper.readValue(
											    data,
											    objectmapper.getTypeFactory().constructCollectionType(List.class, Sala.class)
											);
									} catch (JsonParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (JsonMappingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									System.out.println(data);
									if (status==200){
									}else {
										
									}
									for(int i=0;i<sali.size();++i) {%>
									<option value="<%= sali.get(i).getNume() %>"><%= sali.get(i).getNume() %></option>
									<% }
									%>
									</select>
								</div>
								
								<label class="align1" for="SParticipanti">Participanți</label>
								<div>
									<input type="text" placeholder="Introdu numele grupului de participanți" class="form-control" name="SParticipanti"
										id="SParticipanti" required>
								</div>
								
								<label class="align1" for="SDataInceput">Data de început</label>
								<div>
									<input type="date" placeholder="Introdu data de început" class="form-control" name="SDataInceput"
										id="SDataInceput">
								</div>
	
								<br>
							</fieldset>
					</div>

					<div class="align1 alignRight12">
						<fieldset>
							
							<label class="align1" for="SOraI">Oră începere</label>
							<div>
								<input type="time" placeholder="Introdu ora de început" class="form-control"
									name="SOraI" id="SOraI" required>
							</div>
							
							<label class="align1" for="SDataSfarsit">Data de sfârşit</label>
							<div>
								<input type="date" placeholder="Introdu data de sfârşit" class="form-control" name="SDataSfarsit"
									id="SDataSfarsit">
							</div>
							
							<label class="align1" for="SOraS">Oră terminare</label>
							<div>
								<input type="time" placeholder="Introdu ora de terminare" class="form-control"
									name="SOraS" id="SOraS" required>
							</div>
							
							<label for="Perioada">Perioadă</label>
							<div>
						 		<% String[] lista2 = Arrays.toString(TipPeriodicitate.values()).replaceAll("^.|.$","").split(","); %>
								<select id="Perioada">
										<% for(int i=0;i<lista2.length;++i) { %>
										<option value="<%= lista2[i]%>"><%= lista2[i] %></option>
										<% } %>
								</select>
							</div>
							
							<label class="align1" for="LMMJVSD">Zilele saptamanii</label>
						    <br/>
							<div class="form-control">
								 <input class="checkBoxZile" type="checkbox" name="L" value="L"> L &nbsp;
								 <input class="checkBoxZile" type="checkbox" name="M" value="M"> M &nbsp;
								 <input class="checkBoxZile" type="checkbox" name="Mi" value="Mi"> Mi &nbsp;
								 <input class="checkBoxZile" type="checkbox" name="J" value="J"> J &nbsp;
								 <input class="checkBoxZile" type="checkbox" name="V" value="V"> V &nbsp;
								 <input class="checkBoxZile" type="checkbox" name="S" value="S"> S &nbsp;
								 <input class="checkBoxZile" type="checkbox" name="D" value="D"> D &nbsp;
							</div> 
							
							<br>
						</fieldset>
					</div>
					
					
	
					<div class="buttonAlign">
						<button type="button" class="btn btn-success" onclick="creareEveniment()"
							style="background-color: rgb(194, 24, 91); border: none;">Rezervă</button>
						<button type="reset" class="btn btn-primary"
							style="background-color: rgb(194, 24, 91); border: none">Clear</button>
					</div>
				</form>

			</div>
		</div>

	</div>

	<br />
	<br />

	<footer>
		<div style="text-align: center;">&copy; Practică 2018</div>
	</footer>

	<br />
	<br />

</body>
</html>