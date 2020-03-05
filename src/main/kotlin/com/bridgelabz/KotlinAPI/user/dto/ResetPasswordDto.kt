package com.bridgelabz.KotlinAPI.user.dto

import java.util.*
import javax.validation.constraints.NotBlank

/**********************************************************************************************************
 * @author :Pramila Tawari
 * Purpose :Confriming the Password
 */
class ResetPasswordDto {
    @NotBlank
    var password: String? = null
    @NotBlank
    var confirmpassword: String? = null

    constructor() {}
    constructor(password: String?, confirmpassword: String?) : super() {
        this.password = password
        this.confirmpassword = confirmpassword
    }

    override fun toString(): String {
        return "ResetPasswordDto [password=$password, confirmpassword=$confirmpassword]"
    }
}