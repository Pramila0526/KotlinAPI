package com.bridgelabz.KotlinAPI.user.controller

import com.bridgelabz.KotlinAPI.user.dto.ForgotPasswordDto
import com.bridgelabz.KotlinAPI.user.dto.LoginDto
import com.bridgelabz.KotlinAPI.user.dto.RegistrationDto
import com.bridgelabz.KotlinAPI.user.dto.ResetPasswordDto
import com.bridgelabz.KotlinAPI.user.response.Response
import com.bridgelabz.KotlinAPI.user.service.UserServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    var userServiceImp: UserServiceImp? = null

    @GetMapping("/demo")
    fun demo() : String {
            return "Hello User!!";
    }

    @PostMapping("/register")
    fun Register(@RequestBody regDto: @Valid RegistrationDto?): ResponseEntity<Response?>? {
        return ResponseEntity<Response?>(userServiceImp!!.register(regDto), HttpStatus.OK) // give response for user 200
    }

    // API for validating the user into the database.
    @PostMapping("/validate")
    fun validate(@RequestParam token: @Valid String?): ResponseEntity<Response?>? {
        return ResponseEntity(userServiceImp!!.validateUser(token), HttpStatus.OK)
    }

    @PutMapping("/login")
    fun loginUser(@RequestBody logindto: LoginDto?): ResponseEntity<Response?>? {
        return ResponseEntity(userServiceImp!!.loginUser(logindto!!), HttpStatus.OK)
    }

    @PostMapping("/forgotpassword")
    fun forgotPassword(@RequestBody forgetPasswordDto: ForgotPasswordDto?): ResponseEntity<Response?>? {
        return ResponseEntity(userServiceImp!!.forgotPassword(forgetPasswordDto!!), HttpStatus.OK)
    }

    // API for setting new Password
    @PutMapping("/setpassword")
    fun setNewPassword(@RequestHeader token: String?,
                       @RequestBody setpassworddto: ResetPasswordDto?): ResponseEntity<Response?>? {
        return ResponseEntity(userServiceImp!!.setPassword(setpassworddto!!, token), HttpStatus.OK)
    }

}