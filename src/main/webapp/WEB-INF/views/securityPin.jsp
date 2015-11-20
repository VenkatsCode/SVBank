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

<style>
body {
	min-height: 700px;
}
</style>
</head>
<body class="palette-silver">
<%@include file="/WEB-INF/views/fragments/userHeader.jsp" %>

<div class="container">
<div class="jumbotron palette-turquoise">

		<div class="tile" style="height: 500px;">
				<!-- <h4>Security Pin</h4> -->
				<div class="form-group">

			<!-- view pin status -->
				<div class="form-group">
					<b>${securityPinMessage}</b>	
				</div>
				<div class="form-group">
					<c:choose>
					<c:when test="${securityPinStatus == true}">
					<a id="update" href="javascript:addNew();" style="color: palette-turquoise;">Click here</a> to reset pin.
					</c:when>
					<c:when test="${securityPinStatus == false}">
					<a id="add" href="javascript:addNew();" style="color: palette-turquoise;">Click here</a> to set pin for transaction purposes.
					</c:when>
					</c:choose>
				</div>

<%-- <form:form id="resetpin" method="post" action="resetpin" modelAttribute="setPin" role="form">				
<div id="ashow" style="display: none;" class="form-group">
<div class="form-group col-lg-12">
<label for="securityPin" class="col-lg-3 control-label">Your Security Pin:</label>
<div class="col-lg-9">
<label for="securityPin" class="col-lg-3 control-label"><c:out value="${securityPin}" /></label>
</div>
</div>
<div class="form-group col-lg-12">
<label for="securityAnswer1" class="col-lg-3 control-label"><c:out value="${securityQuestion1}" /></label>
<div class="col-lg-9">
<form:input type="text" path="securityAnswer1" class="form-control" value="" id="securityAnswer1"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="securityAnswer2" class="col-lg-3 control-label"><c:out value="${securityQuestion2}" /></label>
<div class="col-lg-9">
<form:input type="text" path="securityAnswer2" class="form-control" id="securityAnswer2"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="password" class="col-lg-3 control-label">Enter a new 4 digit security pin</label>
<div class="col-lg-9">
<form:input type="password" path="password" class="form-control" id="password"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="repassword" class="col-lg-3 control-label">Re-Enter security pin</label>
<div class="col-lg-9">
<input type="password" name="rePassword" class="form-control" data-match="#password"/>
</div>
</div>
<div  class="form-group col-lg-12">
<div class="col-lg-12">
<button type="submit" class="btn btn-warning btn-lg">Set Pin</button>
<button type="reset" class="btn btn-info btn-lg">Reset</button>
<form:input type="text" path="userId" style="display: none;" />
</div>
</div>
</div>
</form:form>			 --%>	

<form:form id="setpin" method="post" action="setnewpin" modelAttribute="setPin" role="form">				
<div id="addshow" style="display: none;" class="form-group">
<div class="form-group col-lg-12">
<label for="securityAnswer1" class="col-lg-3 control-label"><c:out value="${securityQuestion1}" /></label>
<div class="col-lg-9">
<form:input type="text" path="securityAnswer1" class="form-control" value="" id="securityAnswer1"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="securityAnswer2" class="col-lg-3 control-label"><c:out value="${securityQuestion2}" /></label>
<div class="col-lg-9">
<form:input type="text" path="securityAnswer2" class="form-control" id="securityAnswer2"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="password" class="col-lg-3 control-label">Enter a new 4 digit security pin</label>
<div class="col-lg-9">
<form:input type="password" path="password" class="form-control" id="password"/>
</div>
</div>
<div class="form-group col-lg-12">
<label for="repassword" class="col-lg-3 control-label">Re-Enter security pin</label>
<div class="col-lg-9">
<input type="password" name="rePassword" class="form-control" data-match="#password"/>
</div>
</div>
<div  class="form-group col-lg-12">
<div class="col-lg-12">
<button type="submit" class="btn btn-warning btn-lg">Set Pin</button>
<button type="reset" class="btn btn-info btn-lg">Reset</button>
<form:input type="text" path="userId" style="display: none;" />
</div>
</div>
</div>
</form:form>

	</div>
	</div>
	</div>
	</div>
	<!-- /container -->

	<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
	<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
	<script
		src="<c:url value="/resources/dist/assets/js/application.js" />"></script>
	<script>
function addNew() 
{
$("#addshow").show();
$("#updateshow").hide();
}
	function update() 
	{
		$("#addshow").hide();
		$("#updateshow").show();
	}
	</script>
	
	<!-- Form Validation -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
<script src="<c:url value="/resources/dist/js/vendor/jquery.validate.min.js" />"></script>
<script type="text/javascript">
$(document).ready(function(){ 
console.info("into ready state");
    $("#setpin").validate({ 
    	rules: {
            "securityAnswer1": {
                required: true
            },
            "securityAnswer2": {
                required: true
            },
            "password": {
                required: true,
                minlength: 4,
                maxlength: 4,
                number: true
            },
            "rePassword": {
                required: true,
                minlength: 4,
                maxlength: 4,
                equalTo : "#password"
            },
        },
        messages: {
            "securityAnswer1": {
                required: "securityAnswer1 is required!"
            },
            "securityAnswer2": {
                required: "securityAnswer2 is required!"
            },
            "password": {
                required: "Please enter a 4 digit pin",
                minlength: "Should be a 4 digit number",
                maxlength: "Should be a 4 digit number"
            },
            "repassword": {
                required: "Please re-enter the password",
                minlength: "Should be a 4 digit number",
                maxlength: "Should be a 4 digit number"
            }
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
            $(element).closest('.form-group').removeClass('has-success');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.form-group').addClass('has-success');
        }
    	
    });
    
    $("#resetpin").validate({ 
    	rules: {
            "securityAnswer1": {
                required: true
            },
            "securityAnswer2": {
                required: true
            },
            "password": {
                required: true,
                minlength: 4,
                maxlength: 4,
                number: true
            },
            "rePassword": {
                required: true,
                minlength: 4,
                maxlength: 4,
                equalTo : "#password"
            },
        },
        messages: {
            "securityAnswer1": {
                required: "securityAnswer1 is required!"
            },
            "securityAnswer2": {
                required: "securityAnswer2 is required!"
            },
            "password": {
                required: "Please enter a 4 digit pin",
                minlength: "Should be a 4 digit number",
                maxlength: "Should be a 4 digit number"
            },
            "repassword": {
                required: "Please re-enter the password",
                minlength: "Should be a 4 digit number",
                maxlength: "Should be a 4 digit number"
            }
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
            $(element).closest('.form-group').removeClass('has-success');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.form-group').addClass('has-success');
        }
    	
    });
});
</script>
</body>
</html>
