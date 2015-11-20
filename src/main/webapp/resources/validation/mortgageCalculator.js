$(document).ready(function(){ 
console.info("into ready state mortgage calculator");

$('#term').on('change', function() {
  //alert( this.value ); // or $(this).val()
  if(this.value == 12)
  {
	  $('#loanInterest').val('2.75% per annum');
  }
  else if(this.value == 24)
  {
	  $('#loanInterest').val('2.85% per annum');
  }
  else if(this.value == 60)
  {
	  $('#loanInterest').val('4.75% per annum');
  }
  else if(this.value == 120)
  {
	  $('#loanInterest').val('6.00% per annum');
  }
  else {
	  $('#loanInterest').val(''); 
  }
});

$.validator.addMethod("depends", function(value, element) {
	 if($('#term').val() == 0){
	        return false;
	    }
	    return true;
}); 

$("#mortgageCalculator").validate({ 
	
	rules: {
        "loanAmount": {
            required: true,
            number: true
        },
        "term": {
        	depends: true
        }
	},
    messages: {
        "loanAmount": {
            required: "loanAmount is required!"
        },
        "term": {
            depends: "payable term is required!"
        }
    },
    highlight: function(element) {
        $(element).closest('.form-group').addClass('has-error');
        $(element).closest('.form-group').removeClass('has-success');
    },
    unhighlight: function(element) {
        $(element).closest('.form-group').removeClass('has-error');
        $(element).closest('.form-group').addClass('has-success');
    }
	
});

$( "#calculate" ).click(function() {
	
	  console.info( "in calculate" );
	  var monthlyPayment;
	  
	  var principal = $('#loanAmount').val();
	  var interest;
	  var term = $('#term').val();
	  if(term == 12)
	  {
		  interest = 2.75 / 1200;
	  }
	  else if(term == 24)
	  {
		  interest = 2.85 / 1200;
	  }
	  else if(term == 60)
	  {
		  interest = 4.75 / 1200;
	  }
	  else if(term == 120)
	  {
		  interest = 6.00 / 1200;
	  }
	  
	  var numerator = principal * interest * Math.pow(1+interest, term);
	  console.info(numerator);
	  
	  var denominator = Math.pow(1+interest, term) - 1;
	  console.info(denominator);
	  
	  monthlyPayment = numerator / denominator;
	  console.info(monthlyPayment);
	  
	  if($.isNumeric(monthlyPayment))
		  {
	  $('#montlyPayable').val(monthlyPayment+" CAD per month"); 
		  } else 
		  {
	  $('#montlyPayable').val("");	  
		  }
	  
});

});