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
<div class="jumbotron palette-turquoise" style="height: 500px;">

<div class="form-group">
                <h3 class="tile-title">List of Pending Requests</h3>
                <div class="table-responsive">
                <table  class="table table-bordered table-hover">
                <thead>
                <tr   class="success">
                <th>S.No</th>
                <th>Request ID</th>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>Address</th>
                <th>Phone Number</th>
                <th>Email ID</th>
                <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="request" items="${accountRequests}" varStatus="count">
                <tr   class="info">
                <td>${count.index+1}</td>
               <td>${request.reqId}</td>
                <td>${request.firstName} ${request.lastName}</td>
                <td>${request.dob}</td>
                <td>${request.address}</td>
                <td>${request.phoneNumber}</td>
                <td>${request.email}</td>
                <td>
                <c:if test="${request.status== 1}">
                <a href="accReqDet/${request.reqId}">View</a>
                </c:if>
                </td>
                </tr>
				</c:forEach>
				</tbody>
				</table>
				</div>
               <!--  <a class="btn btn-primary btn-large btn-block" href="http://designmodo.com/flat">View More</a> -->
              </div>
            </div>
      </div>

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
</body>
</html>