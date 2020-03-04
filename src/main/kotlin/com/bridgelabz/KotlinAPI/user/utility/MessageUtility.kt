package com.bridgelabz.KotlinAPI.user.utility

import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Component


@Component
class MessageUtility {
    fun verifyUserMail(email: String, token: String, link: String): SimpleMailMessage {
        val message = SimpleMailMessage()
        message.setFrom("forgotbridge70@gmail.com")
        message.setTo(email) // Sending Email
        message.setSubject("Test")
        message.setText("Hello " + (link + token)) // For Sending the token to email
        println("In Mail: $email")
        return message
    }
}

