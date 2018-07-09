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
<script src="script.js"></script>
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
<style>
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

<body>

	<div class="navbar">
		<a href="index.jsp" style="text-decoration: none;"><i
			class="fa fa-home" style="font-size: 20px"></i>&nbsp; Acasă </a> <a
			href="login.jsp" style="text-decoration: none;"><i
			class="fa fa-sign-in" style="font-size: 20px"></i>&nbsp;
			Autentificare </a> <a href="logout.jsp" style="text-decoration: none;"><i
			class="fa fa-sign-out" style="font-size: 20px"></i>&nbsp; Deconectare
		</a>
		<p>Welcome!</p>
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
	
								<label class="align1" for="SOraI">Oră începere</label>
								<div>
									<input type="time" placeholder="Introdu ora de început" class="form-control"
										name="SOraI" id="SOraI" required>
								</div>
								
								<label class="align1" for="SOraS">Oră terminare</label>
								<div>
									<input type="time" placeholder="Introdu ora de terminare" class="form-control"
										name="SOraS" id="SOraS" required>
								</div>
	
								<label class="align1" for="STipSala">Tip sală</label>
								<div>
									<% String[] lista = Arrays.toString(TipSala.values()).replaceAll("^.|.$","").split(",");%>
									<select id="STipSala">
										<% for(int i=0;i<lista.length;++i) {%>
										<option value="<%= lista[i]%>"><%= lista[i] %></option>
										<% } %>
									</select>
								</div>
								
								<label class="align1" for="SNumarLocuri">Număr locuri</label>
								<div>
									<input type="text" placeholder="Introdu numărul de locuri" class="form-control"
										name="SNumarLocuri" id="SNumarLocuri" required>
								</div>
								
								<label class="align1" for="SProiector">Proiector</label>
								<div>
									<select id='SProiector'>
										<option value="Da">Da</option>
										<option value="Nu">Nu</option>
									</select>
								</div>
								
								<label class="align1" for="SParticipanti">Participanți</label>
								<div>
									<input type="text" placeholder="Introdu numele grupului de participanți" class="form-control" name="SParticipanti"
										id="SParticipanti" required>
								</div>
								
								<label class="align1" for="SNumePlan">Nume plan</label>
								<div>
									<input type="text" placeholder="Introdu numele planului" class="form-control" name="SNumePlan"
										id="SNumePlan" required>
								</div>
								
								<br>
							</fieldset>
					</div>

					<div class="align1 alignRight12">
						<fieldset>
							<label class="align1" for="STag">Tag</label>
							<div>
								<input type="text" placeholder="Introdu numele tag-ului" class="form-control"
									name="STag" id="STag" required>
							</div>
	
							<label class="align1" for="SDescriereTag">Descriere tag</label>
							<div>
								<input type="text" placeholder="Introdu descrierea tag-ului" class="form-control" name="SDescriereTag"
									id="SDescriereTag">
							</div>
							
							<label class="align1" for="SDataInceput">Data de început</label>
							<div>
								<input type="date" placeholder="Introdu data de început" class="form-control" name="SDataInceput"
									id="SDataInceput">
							</div>
							
							<label class="align1" for="SDataSfarsit">Data de sfârşit</label>
							<div>
								<input type="date" placeholder="Introdu data de sfârşit" class="form-control" name="SDataSfarsit"
									id="SDataSfarsit">
							</div>
							
							<label class="align1" for="LMMJVSD">Zilele saptamanii</label>
						    <br/>
							<div class="form-control">
								 <input type="checkbox" name="L" value="L"> L &nbsp;
								 <input type="checkbox" name="M" value="M"> M &nbsp;
								 <input type="checkbox" name="Mi" value="Mi"> Mi &nbsp;
								 <input type="checkbox" name="J" value="J"> J &nbsp;
								 <input type="checkbox" name="V" value="V"> V &nbsp;
								 <input type="checkbox" name="S" value="S"> S &nbsp;
								 <input type="checkbox" name="D" value="D"> D &nbsp;
							</div> 
							
							<label for="Perioada">Perioadă</label>
							<div>
								<% String[] lista2 = Arrays.toString(TipPeriodicitate.values()).replaceAll("^.|.$","").split(",");%>
								<select id="Perioada">
										<% for(int i=0;i<lista2.length;++i) {%>
										<option value="<%= lista2[i]%>"><%= lista2[i] %></option>
										<% } %>
								</select>
							</div>
							
							<label class="align1" for="SDescrierePlan">Descriere plan</label>
							<div>
								<input type="text" placeholder="Introdu o descriere a evenimentului" class="form-control" name="SDescrierePlan"
									id="SDescrierePlan" required>
							</div>
							
							<br>
						</fieldset>
					</div>
					
					
	
					<div class="buttonAlign">
						<button type="submit" class="btn btn-success" onclick="send()"
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

<script>
	
		  function openNav() {
		  document.getElementById("mySidenav").style.width = "250px";
			}

		   function closeNav() {
		   document.getElementById("mySidenav").style.width = "0";
			}
			
		  function Calendar(id, size, labelSettings, colors) {
		  this.id = id;
		  this.size = size;
		  this.labelSettings = labelSettings;
		  this.colors = colors;

		  months = [ "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie" ]
		  label = [ "Duminica", "Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata" ];

		  this.months = months;

		  this.label = [];
		  this.labels = []; 
		  for (var i = 0; i < 7; i++)
			this.label.push(label[(label.indexOf(labelSettings[0]) + this.label.length >= label.length) ? Math.abs(label.length - (label.indexOf(labelSettings[0]) + this.label.length)) : (label.indexOf(labelSettings[0]) + this.label.length)]);
		  for (var i = 0; i < 7; i++)
			this.labels.push(this.label[i].substring(0, (labelSettings[1] > 3) ? 3 : labelSettings[1]));

		  this.date = new Date();

		  this.draw();
		  this.update();
		}

		Calendar.prototype.constructor = Calendar;

		Calendar.prototype.draw = function () {
		  backSvg = '<svg style="width: 24px; height: 24px;" viewBox="0 0 24 24"><path fill="' + this.colors[3] + '" d="M15.41,16.58L10.83,12L15.41,7.41L14,6L8,12L14,18L15.41,16.58Z"></path></svg>';
		  nextSvg = '<svg style="width: 24px; height: 24px;" viewBox="0 0 24 24"><path fill="' + this.colors[3] + '" d="M8.59,16.58L13.17,12L8.59,7.41L10,6L16,12L10,18L8.59,16.58Z"></path></svg>';

		  theCalendar = document.createElement( "DIV");
		  theCalendar.className = "calendar " + this.size;

		  document.getElementById(this.id).appendChild(theCalendar);

		  theContainers = [], theNames = [ 'year', 'month', 'labels', 'days' ];
		  for (var i = 0; i < theNames.length; i++) {
			theContainers[i] = document.createElement( "DIV");
			theContainers[i].className = theNames[i];

			if (theNames[i] != "days") {
			  if (theNames[i] != "month") {
				theContainers[i].style.backgroundColor = this.colors[1];
				theContainers[i].style.color = this.colors[3];

				if (theNames[i] != "labels") {
				  backSlider = document.createElement("DIV");
				  backSlider.id = this.id + "-year-back";
				  backSlider.insertAdjacentHTML('beforeend', backSvg);
				  theContainers[i].appendChild(backSlider);
				  
				  theText = document.createElement("SPAN");
				  theText.id = this.id + "-" + theNames[i];
				  theContainers[i].appendChild(theText);
				  
				  nextSlider = document.createElement("DIV");
				  nextSlider.id = this.id + "-year-next";
				  nextSlider.insertAdjacentHTML('beforeend', nextSvg);
				  theContainers[i].appendChild(nextSlider);
				}
			  } else {
				theContainers[i].style.backgroundColor = this.colors[0];
				theContainers[i].style.color = this.colors[2];

				backSlider = document.createElement("DIV");
				backSlider.id = this.id + "-month-back";
				backSlider.insertAdjacentHTML('beforeend', backSvg);
				theContainers[i].appendChild(backSlider);
				
				theText = document.createElement("SPAN");
				theText.id = this.id + "-" + theNames[i];
				theContainers[i].appendChild(theText);
				
				nextSlider = document.createElement("DIV");
				nextSlider.id = this.id + "-month-next";
				nextSlider.insertAdjacentHTML('beforeend', nextSvg);
				theContainers[i].appendChild(nextSlider);
			  }
			}
		  }

		  for (var i = 0; i < this.labels.length; i++) {
			theLabel = document.createElement("SPAN");
			theLabel.id = this.id + "-label-" + (i + 1);
			theLabel.appendChild(document.createTextNode(this.labels[i]));

			theContainers[2].appendChild(theLabel);
		  }

		  theRows = [], theDays = [], theRadios = [];
		  for (var i = 0; i < 6; i++) {
			theRows[i] = document.createElement("DIV");
			theRows[i].className = "row";
		  }
		  
		  for (var i = 0, j = 0; i < 42; i++) {
			theRadios[i] = document.createElement("INPUT");
			theRadios[i].className = "day-radios";
			theRadios[i].type = "radio";
			theRadios[i].name = this.id + "-day-radios";
			theRadios[i].id = this.id + "-day-radio-" + (i + 1);

			theDays[i] = document.createElement("LABEL");
			theDays[i].className = "day";
			theDays[i].htmlFor = this.id + "-day-radio-" + (i + 1);
			theDays[i].id = this.id + "-day-" + (i + 1);

			theText = document.createElement("SPAN");
			theText.id = this.id + "-day-num-" + (i + 1);

			theDays[i].appendChild(theText);
		  
			theRows[j].appendChild(theRadios[i]);
			theRows[j].appendChild(theDays[i]);

			if ((i + 1) % 7 == 0) {
			  j++;
			}
		  }

		  for (var i = 0; i < 6; i++) {
			theContainers[3].appendChild(theRows[i]);
		  }
		  
		  for (var i = 0; i < theContainers.length; i++) {
			theCalendar.appendChild(theContainers[i]);
		  }

		  document.getElementById(this.id).appendChild(theCalendar);
		}

		Calendar.prototype.update = function () {
		  document.getElementById(this.id + '-year').innerHTML = this.date.getFullYear();
		  document.getElementById(this.id + '-month').innerHTML = months[this.date.getMonth()];

		  for (i = 1; i <= 42; i++) {
			document.getElementById(this.id + '-day-num-' + i).innerHTML = "";
			document.getElementById(this.id + '-day-' + i).className = this.id + " day";
		  }

		  firstDay = new Date(this.date.getFullYear(), this.date.getMonth(), 1).getDay();
		  lastDay = new Date((this.date.getMonth() + 1 > 11) ? this.date.getFullYear() + 1 : this.date.getFullYear(), (this.date.getMonth() + 1 > 12) ? 0 : this.date.getMonth() + 1, 0).getDate();

		  previousLastDay = new Date((this.date.getMonth() < 0) ? this.date.getFullYear() - 1 : this.date.getFullYear(), (this.date.getMonth() < 0) ? 11 : this.date.getMonth(), 0).getDate();

		  if (firstDay != 0)
			for (i = 0, j = previousLastDay; i < this.label.indexOf(label[firstDay]); i++, j--) {
			  document.getElementById(this.id + '-day-num-' + (1 + i)).innerHTML = j;
			  document.getElementById(this.id + '-day-' + (1 + i)).className = this.id + " day diluted";
			}

		  for (i = 1; i <= lastDay; i++) {
			document.getElementById(this.id + '-day-num-' + (this.label.indexOf(label[firstDay]) + i)).innerHTML = i;
			if (i == this.date.getDate())
			  document.getElementById(this.id + '-day-radio-' + (this.label.indexOf(label[firstDay]) + i)).checked = true;
		  }

		  for (i = lastDay + 1, j = 1; (this.label.indexOf(label[firstDay]) + i) <= 42; i++, j++) {
			document.getElementById(this.id + '-day-num-' + (this.label.indexOf(label[firstDay]) + i)).innerHTML = j;
			document.getElementById(this.id + '-day-' + (this.label.indexOf(label[firstDay]) + i)).className = this.id + " day diluted";
		  }
		}

		function Organizer(id, calendar) {
		  this.id = id;
		  this.calendar = calendar;

		  this.draw();
		  this.update();
		}

		Organizer.prototype = {
		  constructor: Organizer,
		  back: function (func) {
			date = this.calendar.date;
			lastDay = new Date((date.getMonth() + 1 > 11) ? date.getFullYear() + 1 : date.getFullYear(), (date.getMonth() + 1 > 12) ? 0 : date.getMonth() + 1, 0).getDate();
			previousLastDay = new Date((date.getMonth() < 0) ? date.getFullYear() - 1 : date.getFullYear(), (date.getMonth() < 0) ? 11 : date.getMonth(), 0).getDate();

			if (func == "day") {
			  if (date.getDate() != 1) {
				this.changeDateTo(date.getDate() - 1);
			  } else {
				this.back('month');
				this.changeDateTo(lastDay);
			  }
			} else {
			  if (func == "month") {
				if (date.getDate() > previousLastDay) {
				  this.changeDateTo(previousLastDay);
				}
				if (date.getMonth() != 0)
				  date.setMonth(date.getMonth() - 1);
				else {
				  date.setMonth(11);
				  date.setFullYear(date.getFullYear() - 1);
				}
			  } else
				date.setFullYear(date.getFullYear() - 1);
			}
			
			this.calendar.update();  
			this.update();
		  },
		  next: function (func) {
			date = this.calendar.date;
			lastDay = new Date((date.getMonth() + 1 > 11) ? date.getFullYear() + 1 : date.getFullYear(), (date.getMonth() + 1 > 12) ? 0 : date.getMonth() + 1, 0).getDate();
			soonLastDay = new Date((date.getMonth() + 2 > 11) ? date.getFullYear() + 1 : date.getFullYear(), (date.getMonth() + 2 > 12) ? 0 : date.getMonth() + 2, 0).getDate();

			if (func == "day") {
			  if (date.getDate() != lastDay) {
				date.setDate(date.getDate() + 1);
			  } else {
				this.next('month');
				date.setDate(1);        
			  }
			} else {
			  if (func == "month") {
				if (date.getDate() > soonLastDay) {
				  this.changeDateTo(soonLastDay);
				}
				if (date.getMonth() != 11)
				  date.setMonth(date.getMonth() + 1);
				else {
				  date.setMonth(0);
				  date.setFullYear(date.getFullYear() + 1);
				}
			  } else
				date.setFullYear(date.getFullYear() + 1);
			}
			
			this.calendar.update();
			this.update();
		  },
		  changeDateTo: function (theDay, theBlock) {
			if ((theBlock >= 31 && theDay <= 11) || (theBlock <= 6 && theDay >= 8)) {
			  if (theBlock >= 31 && theDay <= 11)
				this.next('month');
			  else if (theBlock <= 6 && theDay >= 8)
				this.back('month');
			}
			this.calendar.date.setDate(theDay);
			this.calendar.update();
			this.update();
			calendar = this.calendar;
			setTimeout(function () { calendar.update(); }, 10);
		  }
		}

		Organizer.prototype.draw = function () {
		  backSvg = '<svg style="width: 24px; height: 24px;" viewBox="0 0 24 24"><path fill="' + this.calendar.colors[3] + '" d="M15.41,16.58L10.83,12L15.41,7.41L14,6L8,12L14,18L15.41,16.58Z"></path></svg>';
		  nextSvg = '<svg style="width: 24px; height: 24px;" viewBox="0 0 24 24"><path fill="' + this.calendar.colors[3] + '" d="M8.59,16.58L13.17,12L8.59,7.41L10,6L16,12L10,18L8.59,16.58Z"></path></svg>';
		  
		  theOrganizer = document.createElement( "DIV");
		  theOrganizer.className = "events " + this.calendar.size;

		  theDate = document.createElement( "DIV");
		  theDate.className = "date";
		  theDate.style.backgroundColor = this.calendar.colors[1];
		  theDate.style.color = this.calendar.colors[3];

		  backSlider = document.createElement("DIV");
		  backSlider.id = this.id + "-day-back";
		  backSlider.insertAdjacentHTML('beforeend', backSvg);
		  theDate.appendChild(backSlider);
		  
		  theText = document.createElement("SPAN");
		  theText.id = this.id + "-date";
		  theDate.appendChild(theText);
		  
		  nextSlider = document.createElement("DIV");
		  nextSlider.id = this.id + "-day-next";
		  nextSlider.insertAdjacentHTML('beforeend', nextSvg);
		  theDate.appendChild(nextSlider);

		  theRows = document.createElement( "DIV");
		  theRows.className = "rows";

		  theList = document.createElement("OL");
		  theList.className = "list";
		  theList.id = this.id + "-list";

		  theRows.appendChild(theList);
		  
		  theOrganizer.appendChild(theDate);
		  theOrganizer.appendChild(theRows);

		  document.getElementById(this.id).appendChild(theOrganizer);
		}

		Organizer.prototype.update = function () {
		  document.getElementById(this.id + "-date").innerHTML = this.calendar.months[this.calendar.date.getMonth()] + " " + this.calendar.date.getDate() + ", " + this.calendar.date.getFullYear();
		  document.getElementById(this.id + "-list").innerHTML = "";
		}

		Organizer.prototype.list = function (data) {
		  document.getElementById(this.id + "-list").innerHTML = "";

		  content = ""; 
		  for (var i = 0; i < data.length; i++) {
			content += '<li id="' + this.id + '-list-item-' + i + '"><div><span class="' + this.id + ' time" id="' + this.id + '-list-item-' + i + '-time">' + data[i].startTime + ' - ' + data[i].endTime + '</span><span class="' + this.id + ' m" id="' + this.id + '-list-item-' + i + '-m">' + data[i].mTime + '</span></div><p id="' + this.id + '-list-item-' + i + '-text">' + data[i].text + '</p></li>';
		  }

		  document.getElementById(this.id + "-list").innerHTML = content;
		}

		Organizer.prototype.setupBlock = function (blockId, theOrganizer, callback) {
		  document.getElementById(calendarId + "-day-" + blockId).addEventListener('click', function () {
			if (document.getElementById(calendarId + "-day-num-" + blockId).innerHTML.length > 0) {
			  theOrganizer.changeDateTo(document.getElementById(calendarId + "-day-num-" + blockId).innerHTML, blockId);
			  callback();
			}
		  });
		}

		Organizer.prototype.setOnClickListener = function (theCase, backCallback, nextCallback) {
		  calendarId = this.calendar.id;
		  organizerId = this.id;

		  theOrganizer = this;

		  switch (theCase) {
			case "days-blocks":
			  for (i = 1; i <= 42; i++) {
				theOrganizer.setupBlock(i, theOrganizer, backCallback);
			  }
			  break;
			case "day-slider":
			  document.getElementById(organizerId + "-day-back").addEventListener('click', function () {
				theOrganizer.back('day');
				backCallback();  
			  });
			  document.getElementById(organizerId + "-day-next").addEventListener('click', function () {
				theOrganizer.next('day');
				nextCallback();
			  });
			  break;
			case "month-slider":
			  document.getElementById(calendarId + "-month-back").addEventListener('click', function () {
				theOrganizer.back('month');
				backCallback();
			  });
			  document.getElementById(calendarId + "-month-next").addEventListener('click', function () {
				theOrganizer.next('month');
				nextCallback();
			  });
			  break;
			case "year-slider":
			  document.getElementById(calendarId + "-year-back").addEventListener('click', function () {
				theOrganizer.back('year');
				backCallback();
			  });
			  document.getElementById(calendarId + "-year-next").addEventListener('click', function () {
				theOrganizer.next('year');
				nextCallback();
			  });
			  break;
		  }
		};

	
		var calendar = new Calendar("calendarContainer", "small", [ "Luni", 1 ], [ "#e91e63", "#c2185b", "#ffffff", "#f8bbd0" ]);
		var organizer = new Organizer("organizerContainer", calendar);

		currentDay = calendar.date.getDate();

		data = {
		  years: [
			{
			  int: 1999,
			  months: [
				{
				  int: 4,
				  days: [
					{
					  int: 28,
					  events: [
						{
						  startTime: "6:00",
						  endTime: "6:30",
						  mTime: "pm",
						  text: "Weirdo was born"
						}
					  ]
					}
				  ]
				}
			  ]
			},
			{
			  int: (new Date().getFullYear()),
			  months: [
				{
				  int: (new Date().getMonth() + 1),
				  days: [
					{
					  int: (new Date().getDate()),
					  events: [
						{
						  startTime: "6:00",
						  endTime: "7:00",
						  mTime: "am",
						  text: "This is scheduled to show today, anyday."
						},
						{
						  startTime: "5:45",
						  endTime: "7:15",
						  mTime: "pm",
						  text: "WIP Library"
						},
						{
						  startTime: "10:00",
						  endTime: "11:00",
						  mTime: "pm",
						  text: "Probably won't fix that (time width)"
						},
						{
						  startTime: "8:00",
						  endTime: "9:00",
						  mTime: "pm",
						  text: "Next spam is for demonstration purposes only"
						},
						{
						  startTime: "5:45",
						  endTime: "7:15",
						  mTime: "pm",
						  text: "WIP Library"
						},
						{
						  startTime: "10:00",
						  endTime: "11:00",
						  mTime: "pm",
						  text: "Probably won't fix that (time width)"
						},
						{
						  startTime: "5:45",
						  endTime: "7:15",
						  mTime: "pm",
						  text: "WIP Library"
						},
						{
						  startTime: "10:00",
						  endTime: "11:00",
						  mTime: "pm",
						  text: "Probably won't fix that (time width)"
						},
						{
						  startTime: "5:45",
						  endTime: "7:15",
						  mTime: "pm",
						  text: "WIP Library"
						},
						{
						  startTime: "10:00",
						  endTime: "11:00",
						  mTime: "pm",
						  text: "Probably won't fix that (time width)"
						},
						{
						  startTime: "5:45",
						  endTime: "7:15",
						  mTime: "pm",
						  text: "WIP Library"
						},
						{
						  startTime: "10:00",
						  endTime: "11:00",
						  mTime: "pm",
						  text: "Probably won't fix that (time width)"
						},
						{
						  startTime: "5:45",
						  endTime: "7:15",
						  mTime: "pm",
						  text: "WIP Library"
						},
						{
						  startTime: "10:00",
						  endTime: "11:00",
						  mTime: "pm",
						  text: "Probably won't fix that (time width)"
						}
					  ]
					}
				  ]
				}
			  ]
			}
		  ]
		};

		function showEvents() {
		  theYear = -1, theMonth = -1, theDay = -1;
		  for (i = 0; i < data.years.length; i++) {
			if (calendar.date.getFullYear() == data.years[i].int) {
			  theYear = i;
			  break;
			}
		  }
		  if (theYear == -1) return;
		  for (i = 0; i < data.years[theYear].months.length; i++) {
			if ((calendar.date.getMonth() + 1) == data.years[theYear].months[i].int) {
			  theMonth = i;
			  break;
			}
		  }
		  if (theMonth == -1) return;
		  for (i = 0; i < data.years[theYear].months[theMonth].days.length; i++) {
			if (calendar.date.getDate() == data.years[theYear].months[theMonth].days[i].int) {
			  theDay = i;
			  break;
			}
		  }
		  if (theDay == -1) return;
		  theEvents = data.years[theYear].months[theMonth].days[theDay].events;  
		  organizer.list(theEvents);
		}

		showEvents();

		organizer.setOnClickListener('day-slider', function () { showEvents(); console.log("Day back slider clicked"); }, function () { showEvents(); console.log("Day next slider clicked"); });
		organizer.setOnClickListener('days-blocks', function () { showEvents(); console.log("Day block clicked"); }, null);
		organizer.setOnClickListener('month-slider', function () { showEvents(); console.log("Month back slider clicked"); }, function () { showEvents(); console.log("Month next slider clicked"); });
		organizer.setOnClickListener('year-slider', function () { showEvents(); console.log("Year back slider clicked"); }, function () { showEvents(); console.log("Year next slider clicked"); });
			
	</script>

</html>