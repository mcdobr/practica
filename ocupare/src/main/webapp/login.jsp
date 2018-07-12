<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="js/script.js"></script>
<title>AC</title>

<link href="css/style.css" rel="stylesheet">
<link
	href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
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

	.formular input[type=text], input[type=password],input[type=email], input[type=tel]{
		padding:10px;
		border:1px solid #ccc;
		outline:none;
		width: 100%;
		height: 40px;
		margin: 8px 0;
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

	<div class="main">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="form-body">
						<ul class="nav nav-tabs final-login">
							<li class="active"><a data-toggle="tab" href="#sectionA">Autentificare</a></li>
							<li><a data-toggle="tab" href="#sectionB">Înregistrare</a></li>
						</ul>
						<div class="tab-content">
							<div id="sectionA" class="tab-pane fade in active">
								<div class="innter-form">
									<fieldset class="formular">
										<label>Nume</label> <input type="text"
											placeholder="Introdu adresa de email" name="username" id="LUsername">
										<label>Parolă</label> <input type="password"
											placeholder="Introdu parola" name="password" id="LPassword">
										<button type="submit" onclick="logare()">Conectare</button>
									</fieldset>
									<br><br>
									<span color="red" id="logMsg"></span>
								</div>
								<div class="clearfix"></div>
							</div>
							<div id="sectionB" class="tab-pane fade">
								<div class="innter-form">
									<form class="formular">
										<label>Nume</label> <input type="text"
											placeholder="Introdu numele" name="" id="ANume"> <label>Email</label>
										<input type="email" placeholder="Introdu adresa de email"
											name="" id="AEmail"> <label>Parolă</label> <input
											type="password" placeholder="Introdu o parolă" name="" id="AParola">
										<button type="button" onclick="autentificare()">Creare cont</button>
									</form>
									<br><br>
									<span color="red" id="AuthMsg"></span>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<div style="text-align: center;">&copy; Practică 2018</div>
	</footer>

</body>


</html>