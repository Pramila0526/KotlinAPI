package com.bridgelabz.KotlinAPI.user.service

import com.bridgelabz.KotlinAPI.user.dto.RegistrationDto
import com.bridgelabz.KotlinAPI.user.response.Response


interface IUserService {
    fun register(regdto: RegistrationDto?): Response?
}