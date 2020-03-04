package com.bridgelabz.KotlinAPI.user.utility

import com.bridgelabz.KotlinAPI.user.exception.custom.RegistrationExcepton

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*


@Component
class TokenUtility {
    private val SECRET_KEY = "secret"

    fun createToken(email: String?): String {
        return Jwts.builder().setSubject(email).setIssuedAt(Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact()
    }

    // Method to get User Token
    fun getUserToken(token: String): String {
        var claim: Claims? = null
        try {
            println("token :-$token")
            claim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody()
            System.out.println(claim.getSubject())
        } catch (e: Exception) {
            throw RegistrationExcepton("Invalid")
        }
        return claim.getSubject()
    }
}
