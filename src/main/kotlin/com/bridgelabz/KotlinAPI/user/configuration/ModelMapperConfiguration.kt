package com.bridgelabz.KotlinAPI.user.configuration

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ModelMapperConfiguration {
    /**
     * @return
     * Password encoder - Spring Security provides BCryptPasswordEncoder , and implementation of Spring's
     * PasswordEncoder interface that uses the BCrypt strong hashing function to encode
     * the password.
     */
    @Bean
    fun getMapper(): ModelMapper {
        return ModelMapper()
    }
}