<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<meta charset="utf-8">
<title>SV Bank</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Loading Bootstrap -->
<link
	href="<c:url value="/resources/dist/css/vendor/bootstrap.min.css" />"
	rel="stylesheet" type="text/css">
<!-- Loading Flat UI -->
<link href="<c:url value="/resources/dist/css/flat-ui.min.css" />"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<c:url value="/resources/dist/img/favicon.ico" />">

<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="/resources/dist/js/vendor/video.js" />"></script>
<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>

<!-- Form Validation -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.validate.min.js" />"></script>
<script src="<c:url value="/resources/validation/forgotPassword.js" />"></script>
</head>
<body class="palette-silver">
<style>
body {
	min-height: 500px;
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
	
            <h4><small>Forgot Password ?? ... reset</small></h4><%-- <c:out value="${enterLoginId}" /> --%>
          <c:choose>
			<c:when test="${enterLoginId == true}">
			<div class="jumbotron palette-concrete">
			<form method="post" action="resetpassword" data-toggle="validator" id="userId">
			<div class="form-group">
<input type="text" name="userId" class="form-control" placeholder="Enter User Id" id="userId" data-minlength="12" data-maxlength="12" required/>
</div>
<div  class="form-group" align="center">
<button type="submit" class="btn btn-warning btn-lg">Change Password</button>
<button type="button" onclick="history.back()" class="btn btn-info btn-lg">Cancel</button>
</div>
			</form>
			</div>
			</c:when>
			<c:when test="${enterLoginId == false}">
<form:form id="setnewpassword" method="post" action="setnewpassword" modelAttribute="forgotPwd" role="form">
<div class="form-group col-lg-12">
<label for="securityAnswer1" class="col-lg-3 control-label"><c:out value="${securityQuestion1}" /></label>
<div class="col-lg-9">
<form:input type="text" path="securityAnswer1" class="form-control" value="" id="securityAnswer1"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="securityAnswer2" class="col-lg-3 control-label"><c:out value="${securityQuestion2}" /></label>
<div class="col-lg-9">
<form:input type="text" path="securityAnswer2" class="form-control" value="" id="securityAnswer2"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="password" class="col-lg-3 control-label">Enter new Password</label>
<div class="col-lg-9">
<form:input type="password" path="password" class="form-control" value="" id="password" placeholder="Enter a strong password" />
</div>
</div>
<div class="form-group col-lg-12">
<label for="repassword" class="col-lg-3 control-label">Re-enter new Password</label>
<div class="col-lg-9">
<input type="password" name="rePassword" class="form-control" placeholder="Re-Enter Password" data-match="#password"/>
</div>
</div>
<div  class="form-group col-lg-12" align="center">
<div class="col-lg-12" align="center">
<button type="submit" class="btn btn-warning btn-lg">Change Password</button>
<button type="button" onclick="history.back()" class="btn btn-info btn-lg">Cancel</button>
<form:input type="text" path="userId" style="display: none;" />
</div>
</div>
</form:form>	
</c:when>
</c:choose>
</div>		

	 <script>
      $(function () {
        $('[data-toggle=tooltip]').tooltip();
      });
    </script>
</body>
</html>