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

      <div class="row">
        <div class="col-lg-12">
          <div class="row">
          <c:out value='${accRegMessage}' />
     	
     	<form:form class="form-horizontal" id="addRecep" name="form" action="addRecep" modelAttribute="receps">
          <div id="addshow" style="display: none;">
            <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
              <div class="tile">
                <h3 class="tile-title">Add Recipient</h3><br/>
                <form:input type="email" class="form-control" path="email" placeholder="email id" /><br/>
                <form:input type="text" class="form-control" path="nickname" placeholder="nick name" /><br/>
                <button type="submit" class="btn btn-info btn-lg btn-block">Add</button>
              </div>
            </div>
            </div>
            </form:form>
        
        <form:form class="form-horizontal" id="otransfer" name="form" action="otransfer" modelAttribute="oAccounts">    
		<div class="col-xs-12 col-sm-4 col-md-8 col-lg-8">
		<div class="jumbotron palette-concrete" style="background-color: #eff0f2;">
			<div class="form-group">
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
                <c:forEach var="recipient" items="${recipientsMap}">
        		<option value="${recipient.key}"><c:out value="${recipient.value}"/></option>
      			</c:forEach>
			      </form:select>
			      <div align="left"><a href="javascript:addNew();">Add Recipient</a></div>
              </div>
            </div>
            
            <div class="form-group">
              <label for="amount" class="col-lg-3 control-label">Amount</label>
              <div class="col-lg-9">
                <form:input type="text" class="form-control" path="amount" placeholder="CAD" />
              </div>
            </div>
            
            <div class="form-group">
              <label for="securityPin" class="col-lg-3 control-label">Security Pin</label>
              <div class="col-lg-9">
                <input type="text" class="form-control" name="securityPin" placeholder="4-digit pin" />
              </div>
            </div>
            
            <input type="hidden" id="chqAccBalance" name="modelAttr1" value="${userAccountBalance.chqAccBalance}" />
            <input type="hidden" id="savAccBalance" name="modelAttr2" value="${userAccountBalance.savAccBalance}" />
            <input type="hidden" id="creditAccBalance" name="modelAttr3" value="${userAccountBalance.creditAccBalance}" />
            <div class="form-group" align="center">
            <div class="col-lg-12" align="center">
				<button type="submit" class="btn btn-info btn-lg btn-block">Transfer</button>
				</div>
	  		</div>
		</div>
		</div>
		</form:form>

</div>
</div>
</div>
	  		
</div>
</div>
<script>
function addNew() 
{
$("#addshow").show();
$("#updateshow").hide();
}
</script>

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