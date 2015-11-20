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

<style>
    #slideShowImages { /* The following CSS rules are optional. */
      border: 1px gray solid;
      background-color: lightgray;
    }   
  
    #slideShowImages img { /* The following CSS rules are optional. */
      border: 0.8em black solid;
      padding: 3px;
    }   
  </style>
  </head>
  <body class="palette-silver">
<%@include file="/WEB-INF/views/fragments/userHeader.jsp" %>

    <div class="container">
      <h4>Account Summary</h4>
      <div class="row">
        <div class="col-lg-10">
          <div class="row">
            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
              <div class="tile">
                <h3 class="tile-title">Chequing Account</h3>
                <p class="text-primary"><b>Account Number: ${userAccounts.chqAcc}</b></p>
               	<a class="btn btn-info btn-large btn-block">Balance: <b>${userAccountBalance.chqAccBalance}$</b></a>
                <a class="btn btn-primary btn-large btn-block" href="http://designmodo.com/flat">View More</a>
              </div>
            </div>

            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
              <div class="tile">
                <h3 class="tile-title">Savings Account</h3>
                <p class="text-primary"><b>Account Number: ${userAccounts.savAcc}</b></p>
                <a class="btn btn-info btn-large btn-block">Balance: <b>${userAccountBalance.savAccBalance}$</b></a>
                <a class="btn btn-primary btn-large btn-block" href="http://designmodo.com/flat">View More</a>
              </div>
            </div>

            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
              <div class="tile">
                <h3 class="tile-title">Credit Account</h3>
                <p class="text-primary"><b>Account Number: ${userAccounts.creditAcc}</b></p>
               <a class="btn btn-info btn-large btn-block">Balance: <b>${userAccountBalance.creditAccBalance}$</b></a>
                <a class="btn btn-primary btn-large btn-block" href="http://designmodo.com/flat">View More</a>
              </div>
            </div>
            
            <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3">
              <div class="tile palette-concrete">
                <h3 class="tile-title">Total Balance
                <br/><br/><br/>
                </h3>
               <a class="btn btn-success btn-large btn-block">Banking Balance: <b>${totalAccountBalance}$</b></a>
               <a class="btn btn-success btn-large btn-block">Borrowing Balance: <b>${userAccountBalance.creditAccBalance}$</b></a>
              </div>
            </div>

            </div>
          </div> <!-- /row -->
      </div> <!-- /row -->
      
    </div><!-- /.container -->

<!-- jQuery (necessary for Flat UI's JavaScript plugins) -->
<script src="<c:url value="/resources/dist/js/vendor/jquery.min.js" />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="/resources/dist/js/flat-ui.min.js" />"></script>
<script src="<c:url value="/resources/dist/assets/js/application.js" />"></script>

</body>
</html>