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
      <h4>Welcome ${username}</h4>
        
         <div class="row">
        <div class="col-lg-10">
          <div class="row">
            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
              <%-- <div class="tile">
                <h3 class="tile-title">Notifications</h3>
                <c:forEach var="notification" items="${notifications}">
                <li style="list-style-type: none;"><div class="palette-turquoise img-rounded">
				${notification}
				</div></li>
				</c:forEach>
               <!--  <a class="btn btn-primary btn-large btn-block" href="http://designmodo.com/flat">View More</a> -->
              </div> --%>
            </div>
        
         <div class="col-md-8">
      <div class="content">
     
       <div id="slideshow">
   <div>
     <img src="<c:url value="/resources/dist/img/home-images/big-gold.jpg" />" class="img-rounded img-responsive" align="middle">
   </div>
   <div>
     <img src="<c:url value="/resources/dist/img/home-images/graph.jpg" />" class="img-rounded img-responsive" align="middle">
   </div>
</div>
</div>
</div>
     
      </div><!-- /.row -->
</div>      
      </div>
      
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
</body>
</html>