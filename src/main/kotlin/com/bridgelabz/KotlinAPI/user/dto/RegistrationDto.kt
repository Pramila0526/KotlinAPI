package com.bridgelabz.KotlinAPI.user.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

class RegistrationDto {
    @NotBlank(message = "First Name is mandatory")
    var firstname: String? = null

    @NotBlank(message = "Last Name is mandatory")
    var lastname: String? = null

    @NotBlank
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "phone format wrong")
    var email: String? = null

    @NotBlank(message = "password is mandatory")
    var password: String? = null

    @NotBlank
    @Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "Phone format wrong")
    var phonenumber: String? = null

    constructor() {}
    constructor(
            @NotBlank(message = "First Name is mandatory") @Pattern(regexp = "^[a-zA-Z]*$") firstname: String?,
            @NotBlank(message = "Last Name is mandatory") @Pattern(regexp = "^[a-zA-Z]*$") lastname: String?,
            @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "phone format wrong") email: String?,
            @NotBlank(message = "password is mandatory") password: String?,
            @Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "phone format wrong") @NotBlank(message = "phone is mandatory") phonenumber: String?) : super() {
        this.firstname = firstname
        this.lastname = lastname
        this.email = email
        this.password = password
        this.phonenumber = phonenumber
    }

    override fun toString(): String {
        return ("RegistrationDto [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
                + password + ", phonenumber=" + phonenumber + "]")
    }
}