package com.bridgelabz.KotlinAPI.user.controller

import com.bridgelabz.KotlinAPI.user.dto.RegistrationDto
import com.bridgelabz.KotlinAPI.user.response.Response
import com.bridgelabz.KotlinAPI.user.service.UserServiceImplementation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    var userServiceImp: UserServiceImplementation? = null

    @GetMapping("/demo")
    fun demo() : String {
            return "Hello User!!";
    }

    @PostMapping("/register")
    fun Register(@RequestBody regDto: @Valid RegistrationDto?): ResponseEntity<Response?>? {
        return ResponseEntity<Response?>(userServiceImp!!.register(regDto), HttpStatus.OK) // give response for user 200
    }

}