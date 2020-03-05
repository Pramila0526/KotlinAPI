package com.bridgelabz.KotlinAPI.user.dto

import javax.validation.constraints.NotBlank


/**********************************************************************************************************
 * @author    :Pramila Tawari
 * Purpose	:ForgetPasswordDto uses Email for password recovery
 */
class ForgotPasswordDto {
    @NotBlank
    var email: String? = null

    constructor() {}
    constructor(email: String?) : super() {
        this.email = email
    }

    override fun toString(): String {
        return "ForgetPasswordDto [email=$email]"
    }
}