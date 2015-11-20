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
    <%@include file="/WEB-INF/views/fragments/adminHeader.jsp" %>
    
    <div class="container">
    <form:form class="form-horizontal" action="changeRequestStatus" method="POST"
			modelAttribute="status">
			<div class="jumbotron palette-concrete">
				<h4>Details</h4>

				<div class="form-group">
					<label for="firstName" class="col-lg-3 control-label">First
						Name</label>
					<div class="col-lg-9">
						<form:input type="text" path="firstName"
							id="firstName" readonly="true" placeholder="required" value="${accountRequestDetails.firstName}"/>
					</div>
				</div>

				<div class="form-group">
					<label for="lastName" class="col-lg-3 control-label">Last
						Name</label>
					<div class="col-lg-9">
						<form:input type="text" readonly="true" path="lastName"
							id="lastName" placeholder="required" value="${accountRequestDetails.lastName}"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="gender" class="col-lg-3 control-label">Gender</label>
					<div class="col-lg-9">
						<c:if test="${accountRequestDetails.gender==1}">
						<form:input type="text" readonly="true" path="gender"
							id="gender"  value="${accountRequestDetails.gender}"/>
						</c:if>
						<c:if test="${accountRequestDetails.gender==2}">
						<form:input type="text" readonly="true" path="gender"
							id="gender"  value="${accountRequestDetails.gender}"/>
						</c:if>
						<c:if test="${accountRequestDetails.gender==3}">
						<form:input type="text" readonly="true" path="gender"
							id="gender" value="${accountRequestDetails.gender}"/>
						</c:if>
					</div>
				</div>
			
				<div class="form-group">
					<label for="dob" class="col-lg-3 control-label">Date of
						Birth</label>
					<div class="col-lg-9">
						<form:input type="text" path="dob" id="dob"
							readonly="true" placeholder="required" value="${accountRequestDetails.dob}"/>
					</div>
				</div>
			
				<div class="form-group">
					<label for="dob" class="col-lg-3 control-label">Address</label>
					<div class="col-lg-9">
						<form:textarea readonly="true" path="address" rows="3" value="${accountRequestDetails.address}"/>
					</div>
				</div>
			

				<div class="form-group">
					<label for="phone_number" class="col-lg-3 control-label">Phone
						Number</label>
					<div class="col-lg-9">
						<form:input type="text" readonly="true" path="phoneNumber"
							id="phone_number" value="${accountRequestDetails.phoneNumber}" />
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-lg-3 control-label">Email ID</label>
					<div class="col-lg-9 input-group input-group-sm">
						<span class="input-group-addon">@</span>
						<form:input type="email" readonly="true" path="email"
							id="email" value="${accountRequestDetails.email}" />
					</div>
				</div>

				<div class="form-group">
					<label for="status" class="col-lg-3 control-label">Change Status</label>
					<div class="col-lg-9">
						<form:select data-toggle="select" path="status" name="status"
							class="form-control select select-default" onchange="statusRejectDiv(this.value);">
							<option value="1">Status</option>
							<option value="2">Approve</option>
							<option value="3">Reject</option>
						</form:select>
						<form:input type="text" path="reqId" style="display: none;" />
					</div>
				</div>
				<div id="rejectreason" style="display: none;" class="form-group">
					<label for="rejectreason" class="col-lg-3 control-label">Reason for Rejection</label>
					<div class="col-lg-9">
						<form:textarea class="form-control" path="rejectReason" rows="3" />
					</div>
				</div>
				<div class="form-group" align="center">
				<button type="submit" class="btn btn-primary btn-lg">Submit</button>
				<button type="button" class="btn btn-info btn-sm" onclick="history.back();">Back</button>
			</div>
			</div>
			</form:form>
    </div><!-- /.container -->

<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
<script src="<c:url value="/resources/dist/assets/js/application.js" />"></script>

<script>
      $(document).ready(function(){
        $('select[name="inverse-dropdown"], select[name="inverse-dropdown-optgroup"], select[name="inverse-dropdown-disabled"]').select2({dropdownCssClass: 'select-inverse-dropdown'});

        $('select[name="searchfield"]').select2({dropdownCssClass: 'show-select-search'});
        $('select[name="inverse-dropdown-searchfield"]').select2({dropdownCssClass: 'select-inverse-dropdown show-select-search'});
      });

$("#slideshow > div:gt(0)").hide();

setInterval(function() { 
  $('#slideshow > div:first')
    .fadeOut(0)
    .next()
    .fadeIn(1000)
    .end()
    .appendTo('#slideshow');
},  5000);
</script>

<script>
function statusRejectDiv(value) 
{
if(value==3)
	{$("#rejectreason").show();}
else
	{$("#rejectreason").hide();}
}
</script>
</body>
</html>