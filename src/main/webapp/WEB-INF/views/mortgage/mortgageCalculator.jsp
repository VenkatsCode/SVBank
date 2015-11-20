<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
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
<%@include file="/WEB-INF/views/fragments/userHeader.jsp" %>

<div class="container">

<form id="mortgageCalculator" class="form-horizontal">
<div class="jumbotron palette-concrete" style="background-color: #eff0f2;">
			
			<div class="form-group">
              <label for="amount" class="col-lg-3 control-label">Loan Amount</label>
              <div class="col-lg-9">
              <input type="text" class="form-control" name="loanAmount" id="loanAmount" placeholder="CAD" />
              </div>
            </div>
            
			<div class="form-group">
              <label for="fromAccount" class="col-lg-3 control-label">Payable Term</label>
              <div class="col-lg-9">
               <select data-toggle="select" id="term" name="term" class="form-control select select-default mrs mbm">
        		<option value="0">Select Term</option>
        		<option value="12">1 year</option>
        		<option value="24">2 years</option>
        		<option value="60">5 years</option>
        		<option value="120">10 years</option>
			   </select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="amount" class="col-lg-3 control-label">Interest Rate</label>
              <div class="col-lg-9">
              <input type="text" class="form-control" name="loanInterest" id="loanInterest" placeholder="% per annum" disabled="disabled" style="color: maroon;"/>
              </div>
            </div>
            
            <div class="form-group" align="center">
	            <div class="col-lg-12" align="center">
				<button type="button" id="calculate" class="btn btn-info btn-lg btn-block">Calculate Monthly Payment</button>
				</div>
	  		</div>
	  		
	  		<div class="form-group">
              <div class="col-lg-12">
              <div class="alert alert-success">
              <input type="text" class="form-control" name="montlyPayable" id="montlyPayable" placeholder="Monthly Payment (in CAD)" disabled="disabled" style="color: maroon;"/>
              </div>
              </div>
            </div>
	  		
<!-- 	  		<div class="form-group" align="center">
	            <div class="col-lg-12" align="center">
				<button type="button" id="info" onclick="#" class="btn btn-warning btn-lg btn-block"></button>
				</div>
	  		</div> -->
            
	  		</div>
</form>
	  		
</div>
    
    	<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
	<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
	<!-- Form Validation -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.validate.min.js" />"></script>
<script src="<c:url value="/resources/validation/mortgageCalculator.js" />"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
	<script
		src="<c:url value="/resources/dist/assets/js/application.js" />"></script>
</body>
</html>