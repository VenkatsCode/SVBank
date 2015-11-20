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

<div class="jumbotron palette-turquoise">
			
<div class="form-group">

<table class="table table-bordered table-hover">
	<thead>
		<tr  class="danger">
			<th colspan="2" style="text-align: center;">MORTGAGE RATES</th>
		</tr>
	</thead>
	<thead>
		<tr  class="success">
			<th>TERM</th>
			<th>RATES</th>
		</tr>
	</thead>
	<tbody>
                <tr  class="info">
	                <td>1 Year</td>
	                <td>2.75 %</td>
                </tr>
                <tr  class="info">
	                <td>2 Years</td>
	                <td>2.85 %</td>
                </tr>
                <tr  class="info">
	                <td>5 Years</td>
	                <td>4.75 %</td>
                </tr>
                <tr  class="info">
	                <td>10 Years</td>
	                <td>6.00 %</td>
                </tr>
	</tbody>
</table>

<div class="alert alert-warning">
  <strong>Note: Mortgage rates may vary !!!</strong>
</div>

</div>

</div>	
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