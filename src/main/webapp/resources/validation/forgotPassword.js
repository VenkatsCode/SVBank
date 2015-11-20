$(document).ready(function(){ 
console.info("into ready state");

$.validator.addMethod("alphanumeric", function(value, element) {
    return this.optional(element) || /^(?!\s*$)[a-zA-Z0-9\s]+$/.test(value);
}); 

$.validator.addMethod("pwcheck", function (value) {    
    return /[\@\#\$\%\^\&\*\(\)\_\+\!]/.test(value) && /[a-z]/.test(value) && /[0-9]/.test(value) && /[A-Z]/.test(value)
}); 

    $("#setnewpassword").validate({ 
    	rules: {
            "securityAnswer1": {
                required: true,
                alphanumeric: true
            },
            "securityAnswer2": {
                required: true,
                alphanumeric: true
            },
            "password": {
                required: true,
                pwcheck: true
            },
            "rePassword": {
                required: true,
                equalTo : "#password"
            },
        },
        messages: {
            "securityAnswer1": {
                required: "securityAnswer1 is required!",
                alphanumeric: "Only alphanumerics and spaces are allowed"
            },
            "securityAnswer2": {
                required: "securityAnswer2 is required!",
                alphanumeric: "Only alphanumerics and spaces are allowed"
            },
            "password": {
                required: "Please enter a 4 digit number",
                pwcheck: "Password Must be contain at least 8 characters, least 1 number and both lower and uppercase letters and special characters",
                minlength: "Password must be a min of 6 characters",
            },
            "repassword": {
                required: "Please re-enter the pin",
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
    
    
    $("#userId").validate({ 
    	rules: {
            "userId": {
                required: true,
                minlength: 12,
                maxlength: 12,
                number: true,
            }
        },
        messages: {
            "userId": {
                required: "securityAnswer1 is required!",
                minlength: "Should be 12-digit number",
                maxlength: "Should be 12-digit number"
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