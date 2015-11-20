<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>SV Bank</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Loading Bootstrap -->
    <link href="<c:url value="/resources/dist/css/vendor/bootstrap.min.css" />" rel="stylesheet" type="text/css">
    <!-- Loading Flat UI -->
    <link href="<c:url value="/resources/dist/css/flat-ui.min.css" />" rel="stylesheet">
    <link rel="shortcut icon" href="<c:url value="/resources/dist/img/favicon.ico" />">
    
    <!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
    <script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/resources/dist/js/vendor/video.js" />"></script>
    <script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
    
  </head>
  <body class="palette-silver">
      <style>
      body {
        min-height: 500px;
        padding-top: 70px;
        
      }
    </style>
  
      <!-- Static navbar -->
    <div class="navbar navbar-default navbar-fixed-top palette-green-sea" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a class="navbar-brand" href="#">SV Bank</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a>With You .... Forever !!!</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
    <div class="container">
   <div class="col-md-3">
     <img src="<c:url value="/resources/dist/img/logos/logo_green.png" />" class="img-rounded img-responsive" align="bottom">
   </div>
    
        <div class="col-md-9">
        <c:if test="${not empty message}">
		    <div class="alert alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${message}</strong>
		    </div>
		</c:if>
		
		<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST" data-toggle="validator">
          <div class="login-form">
            <div class="form-group">
              <input type="text" name="j_username" data-minlength="12" data-maxlength="12" class="form-control login-field" value="" placeholder="Enter User ID" id="login-name" />
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
              <input type="password" name="j_password" data-minlength="4" class="form-control login-field" value="" placeholder="Enter Password" id="login-pass" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

			<button type="submit" class="btn btn-primary btn-lg btn-block">Log in</button>
			<button type="button" onclick="window.location.href='register'" class="btn btn-primary btn-lg btn-block">Register</button>
            <a class="login-link" href="forgotpassword">Lost your password?</a>
            <!-- <a class="login-link" href="regtest">Register</a> -->
          </div>
          </form>
        </div>
    </div>
    <!-- /.container -->


  </body>
</html>
