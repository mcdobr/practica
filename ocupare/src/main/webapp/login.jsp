<!DOCTYPE html>
<html lang="ro">
	<head>
		<meta charset="UTF-8">
		<title>AC</title>
		<link href="style.css" rel="stylesheet">
		<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script> 
		<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script
			src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<style>
			.row{
				margin-left: 0px;
				margin-right: 0px;
				}
		</style>
	</head>
	
	<body>
		
		<div class="navbar">
		  <a href="index.jsp" style="text-decoration: none;"><i class="fa fa-home" style="font-size:20px"></i>&nbsp; Acas&#259; </a>
		  <a href="login.jsp" style="text-decoration: none;"><i class="fa fa-sign-in" style="font-size:20px"></i>&nbsp; Autentificare </a>
		  <a href="logout.jsp" style="text-decoration: none;"><i class="fa fa-sign-out" style="font-size:20px"></i>&nbsp; Deconectare </a>
		</div>
		
		<br/>
	
		<div class="main">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="form-body">
							<ul class="nav nav-tabs final-login">
								<li class="active"><a data-toggle="tab" href="#sectionA">Autentificare</a></li>
								<li><a data-toggle="tab" href="#sectionB">&#206;nregistrare</a></li>
							</ul>
							<div class="tab-content">
								<div id="sectionA" class="tab-pane fade in active">
									<div class="innter-form">
										<form class="sa-innate-form" method="post">
											<label>Email</label>
											<input type="text" name="username">
											<label>Parola</label>
											<input type="password" name="password">
											<button type="submit">Conectare</button>
										</form>
									</div>
									<div class="clearfix"></div>
								</div>
							<div id="sectionB" class="tab-pane fade">
								<div class="innter-form">
									<form class="sa-innate-form" method="post">
										<label>Nume</label>
										<input type="text" name="username">
										<label>Email</label>
										<input type="text" name="email">
										<label>Parola</label>
										<input type="password" name="password">
										<button type="submit">Creare cont</button>
									</form>
								</div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			<div style="text-align: center;"> &copy; Practic&#259; 2018 </div>
		</footer>

	</body>
	
	
</html>