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
	<%@include file="/WEB-INF/views/fragments/userHeader.jsp"%>

	<div class="container">
		<h4>Welcome ${username}</h4>

		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-4">
						<div class="tile palette-turquoise" style="height: 350px">
							<table class="table table-bordered table-hover">
								<thead>
									<tr class="success">
										<th><div align="center">Notifications</div></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="notification" items="${notifications}">
										<tr class="info">
											<td>${notification}</td>
										</tr>
									</c:forEach>
									<tr class="info">
											<td>Now a revamped user interface. Enjoy !!!</td>
										</tr>
										<tr class="info">
											<td>All new features COMING SOON !!!</td>
										</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="col-md-7">

						<div id="myCarousel" class="carousel slide" data-ride="carousel"
							data-interval="4000">
							<!-- Indicators -->
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="1"></li>
								<li data-target="#myCarousel" data-slide-to="2"></li>
								<li data-target="#myCarousel" data-slide-to="3"></li>
							</ol>

							<!-- Wrapper for slides -->
							<div class="carousel-inner" role="listbox">
								<div class="item active">
									<img
										src="<c:url value="/resources/dist/img/home-images/sv-bank-first.png" />"
										class="img-responsive" width="650" height="600">
								</div>

								<div class="item">
									<img
										src="<c:url value="/resources/dist/img/home-images/sv-bank-fifth.png" />"
										class="img-responsive" width="650" height="600">
								</div>

								<div class="item">
									<img
										src="<c:url value="/resources/dist/img/home-images/sv-bank-third.png" />"
										class="img-responsive" width="650" height="600">
								</div>

								<div class="item">
									<img
										src="<c:url value="/resources/dist/img/home-images/sv-bank-fourth.png" />"
										class="img-responsive" width="650" height="600">
								</div>
							</div>

							<!-- Left and right controls -->
							<a class="left carousel-control" href="#myCarousel" role="button"
								data-slide="prev"> <!--  <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> -->
								<span class="sr-only">Previous</span>
							</a> <a class="right carousel-control" href="#myCarousel"
								role="button" data-slide="next"> <!-- <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> -->
								<span class="sr-only">Next</span>
							</a>
						</div>
					</div>

				</div>
				<!-- /.row -->
			</div>
		</div>

	</div>
	<!-- /.container -->

	<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
	<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
	<script
		src="<c:url value="/resources/dist/assets/js/application.js" />"></script>

	<script>
		$(document)
				.ready(
						function() {
							$(
									'select[name="inverse-dropdown"], select[name="inverse-dropdown-optgroup"], select[name="inverse-dropdown-disabled"]')
									.select2(
											{
												dropdownCssClass : 'select-inverse-dropdown'
											});

							$('select[name="searchfield"]').select2({
								dropdownCssClass : 'show-select-search'
							});
							$('select[name="inverse-dropdown-searchfield"]')
									.select2(
											{
												dropdownCssClass : 'select-inverse-dropdown show-select-search'
											});
						});
	</script>
</body>
</html>