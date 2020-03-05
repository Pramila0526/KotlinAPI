package com.bridgelabz.KotlinAPI.user.dto

import javax.validation.constraints.NotBlank

/**********************************************************************************************************
 * @author    :Pramila Tawari
 * Purpose	:Login Credentials
 */
class LoginDto {
    //@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "mail id format wrong")
    @NotBlank
    var email: String? = null
    @NotBlank
    var password: String? = null

    constructor() {}
    constructor(email: String?, password: String?) : super() {
        this.email = email
        this.password = password
    }

    override fun toString(): String {
        return "LoginDto [email=$email, password=$password]"
    }
}
