<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>SV Bank</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="<c:url value="/resources/dist/css/vendor/bootstrap.min.css" />"
	rel="stylesheet" type="text/css">
<!-- Loading Flat UI -->
<link href="<c:url value="/resources/dist/css/flat-ui.min.css" />"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<c:url value="/resources/dist/img/favicon.ico" />">

</head>
<body class="palette-silver">
<style>
body {
	min-height: 800px;
	padding-top: 70px;
}
</style>

	<!-- Static navbar -->
	<div class="navbar navbar-default navbar-fixed-top palette-emerald"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
				</button>
				<a class="navbar-brand" href="login">SV Bank</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a>With You .... Forever !!!</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">
		<form:form id="registerUser" class="form-horizontal" action="register/registerUser" method="POST"
			modelAttribute="userDetails">
			<!-- Main component for a primary marketing message or call to action -->
			<div class="jumbotron palette-concrete" style="background-color: #eff0f2;">
				<h4>Personal Details</h4>

				<div class="form-group">
					<label for="firstName" class="col-lg-3 control-label">First
						Name</label>
					<div class="col-lg-9">
						<form:input type="text" path="firstName" class="form-control"
							id="firstName" placeholder="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="lastName" class="col-lg-3 control-label">Last
						Name</label>
					<div class="col-lg-9">
						<form:input type="text" class="form-control" path="lastName"
							id="lastName" placeholder="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="gender" class="col-lg-3 control-label">Gender</label>
					<div class="col-lg-9">
						<form:select data-toggle="select" path="gender"
							class="form-control select select-default">
							<option value="0" disabled>Gender</option>
							<option value="1">Male</option>
							<option value="2">Female</option>
							<option value="3">Others</option>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="dob" class="col-lg-3 control-label">Date of
						Birth</label>
					<div class="col-lg-9">
						<form:input type="date" class="form-control" path="dob" id="dob" />
					</div>
				</div>
				<div class="form-group">
					<label for="dob" class="col-lg-3 control-label">Address</label>
					<div class="col-lg-9">
						<form:textarea class="form-control" path="address" rows="3" />
					</div>
				</div>
				<div class="form-group">
					<label for="phone_number" class="col-lg-3 control-label">Phone
						Number</label>
					<div class="col-lg-9">
						<form:input type="text" class="form-control" path="phoneNumber"
							id="phone_number" placeholder="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-lg-3 control-label">Email ID</label>
					<div class="col-lg-9 input-group input-group-sm">
						<span class="input-group-addon">@</span>
						<form:input type="email" class="form-control" path="email"
							id="email" placeholder="" />
					</div>
				</div>
			</div>

			<div class="form-group" align="center">
				<button type="submit" class="btn btn-primary btn-lg btn-block">Register</button>
			</div>
		</form:form>
	</div>
	<!-- /container -->

	<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
	<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
<!-- Form Validation -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.validate.min.js" />"></script>
<script src="<c:url value="/resources/validation/transfers.js" />"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
	<script
		src="<c:url value="/resources/dist/assets/js/application.js" />"></script>
</body>
</html>
