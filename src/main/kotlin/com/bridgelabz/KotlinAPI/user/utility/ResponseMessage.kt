package com.bridgelabz.KotlinAPI.user.utility

class ResponseMessage {
     val EMAIL_ALREADY_REGISTERED = "Email Id is Already Registered, Please try with other Email"
     val REGISTRATION_MAIL_TEXT = ("\t Validate your email \n"
            + "http://localhost:3000/login?token=")
     val INVALID_EMAIL = "Invalid Username Or Empty Field*"
     val ENTER_EMAIL = "Please Enter Email ID First*"
     val LINK_NOT_ACTIVE = "Activate your account link that sent to your email id"
     val USER_ADDED_SUCCESSFULLY = "User Registration Successful!!"
     val NOT_VERFIY_EMAIL = "Please Verify Your Email"
     val EMAIL_VERIFIED = "Email Verification Successful!!"
     val LOGIN_SUCCESSFUL = "Succesfully Logged In"
     val LOGIN_UNSUCCESSFUL = "Login Failed*"
     val USER_UPDATE_SUCCESSFULLY = "User Successfully Updated!!"
     val USER_NOT_EXISTING = "User Doesn't Exists or Invalid Email"
     val PASSWORD_CHANGED_SUCCESSFULLY = "Password Successfully Changed!!"
     val PASSWORD_NOT_CHANGED_SUCCESSFULLY = "Password Not Changed**"
    //public static final String MAIL_SENT = "Token has been Sent to your Mail";
     val PASSWORD_NOT_MATCHING = "Both password should be matched, Please Try Again"
     val INVALID_TOKEN = "Invalid Token**"
     val VERIFY_MAIL = ("\t Validate your email \n"
            + "http://localhost:3000/resetpassword?token=")
    // Status Code
     val OK = 200
     val BAD_REQUEST = 500
     val NOT_FOUND = 404
    // For Notes
     val NOTE_CREATED = "Notes created Successfully"
     val NOTE_UPDATED = "Note updated Successfully"
    val NOTE_DELETED: Any = "Note Deleted"
}