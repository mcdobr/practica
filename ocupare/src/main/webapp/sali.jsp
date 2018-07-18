<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.practica.ocupare.entitati.*"%>
<%@ page import="org.practica.ocupare.entitati.Sala.TipSala"%>
<%@ page
	import="org.practica.ocupare.entitati.Plan.Periodicitate.TipPeriodicitate"%>
<%@ page import="org.glassfish.jersey.client.*"%>
<%@ page import="javax.ws.rs.client.*"%>
<%@ page import="javax.ws.rs.core.*"%>
<%@ page import="java.net.URI"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.List"%>

<%@ page import="org.glassfish.jersey.client.HttpUrlConnectorProvider"%>
<%@ page import="com.fasterxml.jackson.core.JsonParseException"%>
<%@ page import="com.fasterxml.jackson.databind.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>AC</title>
<link href="css/style.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="js/script2.js"></script>
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
.row {
	margin-left: 0px;
	margin-right: 0px;
}

label {
	margin-bottom: 1px;
}

input[type="button"] {
	display: inline-block;
	border: none;
	color: white;
	padding: 15px 25px;
	text-align: center;
	text-decoration: none;
	font-size: 14px;
	weight: 20px;
}

th {
	text-align: center;
}

input[type="button"]:hover {
	color: white;
	background-color: #d14d42;
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
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
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
			style="font-family: Montsserrat; font-style: italic; font-size: 2.3em;">ALEGE
			SALA</h2>
		<hr>

		<div class="main">
			<div class="container">
				<div class="row">

					<%!private static URI getBaseURI() {
		//TODO change the port to whatever is the server running on
		return UriBuilder.fromUri("http://localhost:8080/ocupare/").build();
	}%>

					<%
						ClientConfig config2 = new ClientConfig();
						Client client = ClientBuilder.newClient(config2);
						client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
						WebTarget service = client.target(getBaseURI());

						Response response2;
						response2 = service.path("webapi").path("sali").request().accept(MediaType.APPLICATION_JSON)
								.get(Response.class);
						String data = response2.readEntity(String.class);
						int status = response.getStatus();
						ObjectMapper objectmapper = new ObjectMapper();
						List<Sala> sali = new ArrayList<>();
						try {
							sali = objectmapper.readValue(data,
									objectmapper.getTypeFactory().constructCollectionType(List.class, Sala.class));
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
						if (status == 200) {
						} else {

						}
					%>
					<%
						List<String> lista = new ArrayList<String>();
						lista.add(0, "0000");
						lista.add(1, "00");
						for (int i = 0; i < 10; ++i) {
					%>
					<br /> <input type="button" id="<%=("btn" + i)%>"
						onclick="functie(this)"
						style="background-color: <%=("#" + "000F" + i + "0")%>"
						value="<%=sali.get(i).getNume()%>" /> &nbsp; &nbsp;

					<%
						}
					%>
					<input type="button" id="btn10" onclick="functie(this)"
						style="background-color: <%=("#0000FF")%>"
						value="<%=sali.get(10).getNume()%>" /> &nbsp; &nbsp;
				</div>
				&nbsp; &nbsp; &nbsp; &nbsp;

				<div class="row">
					<%
						for (int i = 12; i < 24; ++i) {
							System.out.println("#" + lista.get(1) + i + "00");
					%>
					<input type="button" id="<%=("btn" + i)%>"
						onclick="functie(this)"
						style="background-color: <%=("#" + lista.get(1) + i + "0F")%>"
						value="<%=sali.get(i).getNume()%>" /> &nbsp; &nbsp;
					<%
						}
					%>
				</div>
				&nbsp; &nbsp;

				<div class="row">
					<%
						for (int i = 24; i < sali.size(); ++i) {
							System.out.println("#" + i + "00" + (i - 5));
					%>
					<input type="button" id="<%="btn" + i%>" onclick="functie(this)"
						style="background-color: <%=("#" + "FF0" + i + "F")%>"
						value="<%=sali.get(i).getNume()%>" /> &nbsp; &nbsp;
					<%
						}
					%>
				</div>


			</div>
			<br />
			<h2
				style="font-family: Montsserrat; font-style: italic; font-size: 2.3em;">TABELUL
				SĂLILOR</h2>
			<hr>

			<div id="tabel">
				
			</div>

		</div>
	</div>
</body>
</html>