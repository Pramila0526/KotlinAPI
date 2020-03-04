package com.bridgelabz.KotlinAPI.user.service

import com.bridgelabz.KotlinAPI.user.configuration.PasswordConfiguration
import com.bridgelabz.KotlinAPI.user.dto.RegistrationDto
import com.bridgelabz.KotlinAPI.user.exception.custom.RegistrationExcepton
import com.bridgelabz.KotlinAPI.user.model.User
import com.bridgelabz.KotlinAPI.user.repository.UserRepository
import com.bridgelabz.KotlinAPI.user.response.Response
import com.bridgelabz.KotlinAPI.user.utility.MessageUtility
import com.bridgelabz.KotlinAPI.user.utility.ResponseMessage
import com.bridgelabz.KotlinAPI.user.utility.TokenUtility
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@PropertySource("message.properties")
class UserServiceImplementation : IUserService {

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val javaMailSender: JavaMailSender? = null

    @Autowired
    private val mapper: ModelMapper? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @Autowired
    private val passwordConfiguration : PasswordConfiguration? = null

    private val messageUtility : MessageUtility? = null

    @Autowired
    private val environment: Environment? = null

    @Autowired
    private val tokenutility: TokenUtility? = null

    override  fun register(regdto: RegistrationDto?): Response? {

        var user: User = mapper?.map(regdto, User::class.java)!!
        var responseMessage = ResponseMessage()
        println(user?.firstName)

            if(userRepository?.findAll()?.stream()?.anyMatch { i -> i?.email.equals(regdto?.email) }!!){

                throw RegistrationExcepton(responseMessage.EMAIL_ALREADY_REGISTERED)
        }
//        user.setPassword(passwordEncoder.encode(regdto.getPassword()));

        user?.password(passwordEncoder?.encode(regdto?.password))

         println(user)

          user = user?.let { userRepository.save(it) }

        if(user == null){
            throw RegistrationExcepton(responseMessage.ENTER_EMAIL)
        }

        val token = tokenutility!!.createToken(user.email)

        println(token)

        javaMailSender?.send(regdto?.email?.let { messageUtility?.verifyUserMail(it,token,responseMessage.REGISTRATION_MAIL_TEXT) })

        return Response(environment!!.getProperty("status.ok.code")!!.toInt(), environment.getProperty("status.success.registration"), environment.getProperty("success.status"))

    }
}

private operator fun String?.invoke(encode: String?) {

}
