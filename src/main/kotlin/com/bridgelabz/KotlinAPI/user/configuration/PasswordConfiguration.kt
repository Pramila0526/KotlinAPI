package com.bridgelabz.KotlinAPI.user.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class PasswordConfiguration {
    /**
     * @return
     * Password encoder - Spring Security provides BCryptPasswordEncoder , and implementation of Spring's
     * PasswordEncoder interface that uses the BCrypt strong hashing function to encode
     * the password.
     */
    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}