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
	min-height: 2000px;
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
		<form:form class="form-horizontal" action="registerUser" method="POST"
			modelAttribute="userDetails">
			<!-- Main component for a primary marketing message or call to action -->
			<div class="jumbotron palette-concrete">
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
						<form:input type="text" class="form-control" path="dob" id="dob"
							placeholder="required" />
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
						<form:input type="text" class="form-control" path="phone_Number"
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

					<!--  <span class="input-group-addon">@</span>
                <input type="email" class="col-lg-7 form-control" id="email" placeholder="">
              </div> -->
				</div>
			</div>


			<div class="jumbotron palette-concrete">
				<h4>Account Details</h4>
				<div class="form-group">
					<label for="user_id" class="col-lg-3 control-label">Card
						Number</label>
					<div class="col-lg-9 input-group">
						<span class="input-group-addon">6260</span>
						<form:input type="text" class="form-control" path="userId"
							id="user_id" placeholder="required (remaining 12 digits)" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-lg-3 control-label">Password</label>
					<div class="col-lg-9">
						<form:input type="password" class="form-control" path="password"
							id="password" placeholder="required" />
					</div>
				</div>
				<div class="form-group">
					<label for="repassword" class="col-lg-3 control-label">Re-enter
						Password</label>
					<div class="col-lg-9">
						<input type="password" class="form-control" id="repassword"
							placeholder="required">
					</div>
				</div>
			</div>

			<div class="jumbotron palette-concrete">
				<h4>Security Questions</h4>
				<div class="form-group">
					<label for="securityQuestion1" class="col-lg-3 control-label">Security
						Question 1</label>
					<div class="col-lg-9">
						<form:select data-toggle="select" path="securityQuestion1"
							id="securityQuestion1"
							class="form-control select select-default mrs mbm">
							<option value="0" disabled>---</option>
							<option value="1">SQ1</option>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="securityAnswer1" class="col-lg-3 control-label">Answer</label>
					<div class="col-lg-9">
						<form:input type="text" class="form-control"
							path="securityAnswer1" id="securityAnswer1"
							placeholder="required" />
					</div>
				</div>

				<div class="form-group">
					<label for="securityQuestion2" class="col-lg-3 control-label">Security
						Question 2</label>
					<div class="col-lg-9">
						<form:select data-toggle="select" path="securityQuestion2"
							id="securityQuestion2"
							class="form-control select select-default mrs mbm">
							<option value="0" disabled>---</option>
							<option value="1">SQ2</option>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="securityAnswer2" class="col-lg-3 control-label">Answer</label>
					<div class="col-lg-9">
						<form:input type="text" class="form-control"
							path="securityAnswer2" id="securityAnswer2"
							placeholder="required" />
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
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
	<script
		src="<c:url value="/resources/dist/assets/js/application.js" />"></script>

	<script>
		$(document)
				.ready(
						function() {
							$(
									'select[name="inverse-dropdown"], select[name="inverse-dropdown-optgroup"], select[name="inverse-dropdown-disabled"]')
									.select2(
											{
												dropdownCssClass : 'select-inverse-dropdown'
											});

							$('select[name="searchfield"]').select2({
								dropdownCssClass : 'show-select-search'
							});
							$('select[name="inverse-dropdown-searchfield"]')
									.select2(
											{
												dropdownCssClass : 'select-inverse-dropdown show-select-search'
											});
						});
	</script>


</body>
</html>
