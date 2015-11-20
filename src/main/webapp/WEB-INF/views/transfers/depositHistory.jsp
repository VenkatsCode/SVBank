<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="utf-8">
<title>SV Bank</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/resources/dist/css/vendor/bootstrap.min.css" />" rel="stylesheet" type="text/css">
<!-- Loading Flat UI -->
<link href="<c:url value="/resources/dist/css/flat-ui.min.css" />" rel="stylesheet">
<link rel="shortcut icon" href="<c:url value="/resources/dist/img/favicon.ico" />">

</head>
<body class="palette-silver">
	<%@include file="/WEB-INF/views/fragments/userHeader.jsp"%>

	 <div class="container">
      <h4></h4>
        
         <div class="row">
          <div class="row">
            <div class="col-xs-12 col-sm-4 col-md-9 col-lg-9">
              <div class="tile">
                <h3 class="tile-title">List of Pending Requests</h3>
                <br/>
				<form:form class="form-horizontal" name="form" action="deposit" modelAttribute="depositHistory">
                <table border="1" style="width: 50em;">
					<tr align="left">
						<th colspan="2">Transaction ID</th>
						<th colspan="2">To Account</th>
						<th colspan="2">Amount</th>
						<th colspan="2">Date</th>
						<th colspan="2">Cheque No.</th>
						<th colspan="2">Status</th>
					</tr>
					<c:forEach var="depositHistory" items="${depositHistory}">
					<tr align="left">
						<td colspan="2">${depositHistory.transactionId}</td>
						<td colspan="2">${depositHistory.toAccount}</td>
						<td colspan="2">${depositHistory.amount}</td>
						<td colspan="2">${depositHistory.dateTransferred}</td>
						<td colspan="2">${depositHistory.chequeNo}</td>
						<td colspan="2">${depositHistory.status}</td>
					</tr>
					</c:forEach>
				</table>
				<br/>
					<button type="submit" class="btn btn-primary btn-lg btn-block">New Deposit</button>
				</form:form>
              </div>
            </div>
      </div><!-- /.row -->
      </div>
    </div><!-- /.container -->
	<script>
      $(function () {
        $('[data-toggle=tooltip]').tooltip();
      });
    </script>

	<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
	<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
	<script
		src="<c:url value="/resources/dist/assets/js/application.js" />"></script>
</body>
</html>