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
<div class="jumbotron palette-turquoise" style="height: 500px;">

<div class="form-group">

<table class="table table-bordered table-hover">
	<thead>
		<tr  class="success">
			<th>Transaction ID</th>
			<th>Transfer From</th>
			<th>Amount</th>
			<th>Date</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="request" items="${requestList}" varStatus="count">
                <tr  class="info">
	                <td>${request.transactionId}</td>
	                <td>${request.fromAccount}</td>
	                <td>${request.amount} $</td>
	                <td>${request.dateCreated}</td>
                	<td>
		                <c:if test="${request.status== 'APPROVED'}">
		                <div style="color: green;">APPROVED</div>
		                </c:if>
		                 <c:if test="${request.status== 'PENDING'}">
		                 <a href="/SVBank/transac/transferAction?flag=true&id=${request.transactionId}&amt=${request.amount}">
		                 <button type="button" class="btn btn-success">APPROVE</button> </a>/ 
		                 <a href="/SVBank/transac/transferAction?flag=false&id=${request.transactionId}&amt=${request.amount}">
		                 <button type="button" class="btn btn-danger">REJECT</button> </a>
		                </c:if>
		                 <c:if test="${request.status== 'REJECTED'}">
		                <div style="color: red;">REJECTED</div>
		                </c:if>
                	</td>
                </tr>
        </c:forEach>
	</tbody>
</table>
</div>


<%-- 			<div class="form-group">
              <label for="fromAccount" class="col-lg-3 control-label">From Account</label>
              <div class="col-lg-9">
               <form:select data-toggle="select" path="fromAccount" class="form-control select select-default mrs mbm">
                <c:forEach var="userAccount" items="${userAccountsMap}">
        		<option value="${userAccount.key}"><c:out value="${userAccount.value}"/></option>
      			</c:forEach>
			      </form:select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="toAccount" class="col-lg-3 control-label">To Account</label>
              <div class="col-lg-9">
               <form:select data-toggle="select" path="toAccount" class="form-control select select-default mrs mbm">
                <c:forEach var="userAccount" items="${userAccountsMap}">
        		<option value="${userAccount.key}"><c:out value="${userAccount.value}"/></option>
      			</c:forEach>
			      </form:select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="amount" class="col-lg-3 control-label">Amount</label>
              <div class="col-lg-9">
                <form:input type="text" class="form-control" path="amount" placeholder="CAD" />
              </div>
            </div>
            
            <div class="form-group" align="center">
            <div class="col-lg-12" align="center">
				<button type="submit" class="btn btn-info btn-lg btn-block">Transfer</button>
				</div>
	  		</div>
	  		</div> --%>
	  		
</div>
</div>
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