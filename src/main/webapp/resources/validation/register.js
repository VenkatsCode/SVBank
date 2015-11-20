$(document).ready(function(){ 
console.info("into ready state");

$.validator.addMethod("alphanumeric", function(value, element) {
    return this.optional(element) || /^(?!\s*$)[a-zA-Z0-9\s]+$/.test(value);
}); 

    $("#registerUser").validate({ 
    	rules: {
            "firstName": {
                required: true,
                alphanumeric: true
            },
            "lastName": {
                required: true,
                alphanumeric: true
            },
            "dob": {
                required: true,
            },
            "phoneNumber": {
                required: true,
                minlength: 10,
                maxlength: 10,
                number: true
            },
            "email": {
                required: true,
            },
        },
        messages: {
            "firstName": {
                required: "first name is required!",
                alphanumeric: "Only alphanumerics and spaces are allowed"
            },
            "lastName": {
                required: "last name is required!",
                alphanumeric: "Only alphanumerics and spaces are allowed"
            },
            "dob": {
                required: "date of birth is required!",
            },
            "phoneNumber": {
                required: "phone number is required!",
                minlength: "Should be a 10 digit number",
                maxlength: "Should be a 10 digit number"
            },
            "email": {
                required: "email id is required!"
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