package com.bridgelabz.KotlinAPI.user.exception.custom

class ForgotPasswordException(message: String?) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}