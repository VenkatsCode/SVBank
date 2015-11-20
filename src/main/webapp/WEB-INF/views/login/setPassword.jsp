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
<body>

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
    
    <form:form class="form-horizontal" name="form" action="setPwd" modelAttribute="setPassword">
	<div class="jumbotron palette-concrete">
        <h4>Password Details</h4>
            <div class="form-group">
              <label for="password" class="col-lg-3 control-label">Password</label>
              <div class="col-lg-9">
                <form:input type="password" class="form-control" path="password" placeholder="required" />
              </div>
            </div>
            <div class="form-group">
              <label for="repassword" class="col-lg-3 control-label">Re-enter Password</label>
              <div class="col-lg-9">
                <input type="password" class="form-control" id="repassword" placeholder="required" >
              </div>
            </div>
      </div>

      <div class="jumbotron palette-concrete">
        <h4>Security Questions</h4>
  		<div class="form-group">
              <label for="securityQuestion1" class="col-lg-3 control-label">Security Question 1</label>
              <div class="col-lg-9">
               <form:select data-toggle="select" path="securityQuestion1" class="form-control select select-default mrs mbm">
                <c:forEach var="securityQuestion" items="${securityQuestions}">
        		<option value="${securityQuestion.key}"><c:out value="${securityQuestion.value}"/></option>
      			</c:forEach>
			      </form:select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="securityAnswer1" class="col-lg-3 control-label">Answer</label>
              <div class="col-lg-9">
                <form:input type="text" class="form-control" path="securityAnswer1" placeholder="required" />
              </div>
            </div>
            
            <div class="form-group">
              <label for="securityQuestion2" class="col-lg-3 control-label">Security Question 2</label>
              <div class="col-lg-9">
               <form:select data-toggle="select" path="securityQuestion2" class="form-control select select-default mrs mbm">
        		<c:forEach var="securityQuestion" items="${securityQuestions}">
        		<option value="${securityQuestion.key}"><c:out value="${securityQuestion.value}"/></option>
      			</c:forEach>
			    </form:select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="securityAnswer2" class="col-lg-3 control-label">Answer</label>
              <div class="col-lg-9">
                <form:input type="text" class="form-control" path="securityAnswer2" placeholder="required" />
              </div>
            </div>
      </div>    
      
      <div class="form-group" align="center">
		<button type="submit" class="btn btn-primary btn-lg btn-block">Change Password</button>
	  </div>
    
    </form:form>
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