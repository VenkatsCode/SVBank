$(document).ready(function(){ 
console.info("into ready state transfer");

$.validator.addMethod("validateAmount", function(value, element) {
	var id = $("#fromAccount").val()%100;
	var result;
	if(id==1){
		result = (value <= parseInt(chqAccBalance,10));
	}
	if(id==2){
		result = (value <= parseInt(savAccBalance,10));
	}
	if(id==3){
		result = (value <= parseInt(creditAccBalance,10));
	}
	//console.info(result);
    return result;
});

$.validator.addMethod("alphanumeric", function(value, element) {
    return this.optional(element) || /^(?!\s*$)[a-zA-Z0-9\s]+$/.test(value);
}); 

var chqAccBalance = $("#chqAccBalance").val();
var savAccBalance = $("#savAccBalance").val();
var creditAccBalance = $("#creditAccBalance").val();

    $("#btransfer").validate({ 
    	
    	rules: {
            "amount": {
                required: true,
                number: true,
                validateAmount: true
            }
        },
        messages: {
            "amount": {
                required: "amount is required!",
                validateAmount: "No sufficient funds... try another account"
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
    
 $("#otransfer").validate({ 
    	
    	rules: {
            "amount": {
                required: true,
                number: true,
                validateAmount: true
            },
            "securityPin": {
                required: true,
                minlength: 4,
                maxlength: 4,
                number: true,
            }
        },
        messages: {
            "amount": {
                required: "amount is required!",
                validateAmount: "No sufficient funds... try another account"
            },
            "securityPin": {
            	required: "security pin is required!",
            	minlength: "should be a 4 digit number",
            	maxlength: "should be a 4 digit number"
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
 
 $("#addRecep").validate({ 
 	
 	rules: {
         "email": {
             required: true,
         },
         "nickname": {
             required: true,
             alphanumeric: true
         }
     },
     messages: {
         "email": {
             required: "email id is required!",
         },
         "nickname": {
         	required: "nickname is required!",
         	alphanumeric: "Only alphanumerics and spaces are allowed"
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
    
});