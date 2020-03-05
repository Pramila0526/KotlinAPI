package com.bridgelabz.KotlinAPI.user.service

import com.bridgelabz.KotlinAPI.user.configuration.ModelMapperConfiguration
import com.bridgelabz.KotlinAPI.user.configuration.PasswordConfiguration
import com.bridgelabz.KotlinAPI.user.dto.ForgotPasswordDto
import com.bridgelabz.KotlinAPI.user.dto.LoginDto
import com.bridgelabz.KotlinAPI.user.dto.RegistrationDto
import com.bridgelabz.KotlinAPI.user.dto.ResetPasswordDto
import com.bridgelabz.KotlinAPI.user.exception.custom.ForgotPasswordException
import com.bridgelabz.KotlinAPI.user.exception.custom.RegistrationExcepton
import com.bridgelabz.KotlinAPI.user.exception.custom.ValidateUserException
import com.bridgelabz.KotlinAPI.user.model.User
import com.bridgelabz.KotlinAPI.user.repository.UserRepository
import com.bridgelabz.KotlinAPI.user.response.Response
import com.bridgelabz.KotlinAPI.user.utility.MessageUtility
import com.bridgelabz.KotlinAPI.user.utility.ResponseMessage
import com.bridgelabz.KotlinAPI.user.utility.TokenUtility
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
@PropertySource("message.properties")
 class UserServiceImp : IUserService {

   var logger: Logger = LoggerFactory.getLogger(UserServiceImp::class.java)

   @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    @Autowired
    private lateinit var mapper: ModelMapper

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var passwordConfiguration : PasswordConfiguration

   @Autowired
    private lateinit var messageUtility : MessageUtility

    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var tokenutility: TokenUtility

   @Autowired
   private lateinit var modelMapper: ModelMapperConfiguration

   var responseMessage = ResponseMessage()

   override fun register(regdto: RegistrationDto?): Response? {


        var user: User = mapper.map(regdto, User::class.java)

       if(userRepository.findAll().stream().anyMatch { i -> i?.email == regdto?.email })
       {
          logger.info("Registration Completed");
          throw RegistrationExcepton(responseMessage.EMAIL_ALREADY_REGISTERED)
       }

      var password : String = passwordConfiguration.encoder().encode(regdto?.password)

      logger.info("$user")

      user = userRepository.save(user)

       if(user == null){
          logger.info("Null Content")
          throw RegistrationExcepton(responseMessage.ENTER_EMAIL)
       }

       val token = tokenutility.createToken(user.email)

      logger.info("$token");

      javaMailSender
               .send(messageUtility.verifyUserMail(regdto?.email.toString(), token, responseMessage.REGISTRATION_MAIL_TEXT));


      logger.info("Token Sent");

      return Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.registration"),environment.getProperty("success.status"));
   }

   fun validateUser(token: String?): Response? {
      val email = tokenutility.getUserToken(token!!) // get user id from user token.

      val user: User? = userRepository.findByEmail(email)

      return if (user != null) { // if userid is found validate should be true
         user.isValidate = true
         userRepository.save(user)
         Response(environment.getProperty("status.ok.code")!!.toInt(),
                 environment.getProperty("status.success.emailverified"), environment.getProperty("success.status"))
      } else {
         Response(environment.getProperty("status.badrequest.code")!!.toInt(),
                 environment.getProperty("status.success.emailnotverified"), environment.getProperty("failure.status"))
      }
   }

   /**
    * @return User Login Method :- Login the Authenticated User
    */
   fun loginUser(logindto: LoginDto): Response? {

      val user: User? = userRepository.findByEmail(logindto.email) // find email present or not
      logger.info("$user")

      if (user == null) {
         logger.info("Null Content")
         return Response(environment.getProperty("status.badrequest.code")!!.toInt(), environment.getProperty("status.success.usernotfound"), environment.getProperty("failure.status"))
      }

      val token = tokenutility.createToken(user.email)

      if (user.isValidate == false) {
         throw ValidateUserException(responseMessage.LINK_NOT_ACTIVE)
      } else {
         if (user.email == logindto.email
                 && user.password == logindto.password) { // encode the user
            logger.info("username password Matched")
            return Response(environment.getProperty("status.ok.code")!!.toInt(), environment.getProperty("status.success.login"), token, user)
         }
      }
      return Response(environment.getProperty("status.badrequest.code")!!.toInt(), environment.getProperty("status.success.loginunsuccess"), environment.getProperty("failure.status"))
   }

   /**
    * @return Forgot Passwrod Method :- In Case if Password is not remebering then
    * we can recover it by sending token to the email id.
    */
   fun forgotPassword(forgetPasswordDto: ForgotPasswordDto): Response? {
      val user: User? = userRepository.findByEmail(forgetPasswordDto.email) // find by user email id
      logger.info("$user");
      if (user == null) {
         logger.info("Null Content")
         throw ForgotPasswordException(responseMessage.USER_NOT_EXISTING)
      } else {
         val token = tokenutility.createToken(user.email)
         println(token)
         logger.info("Token Generated")

         javaMailSender
                 .send(messageUtility.verifyUserMail(forgetPasswordDto?.email.toString(), token, responseMessage.VERIFY_MAIL));

         logger.info("Token Sent $token") // email
      }
      return Response(environment.getProperty("status.ok.code")!!.toInt(), environment.getProperty("status.success.tokensent"), environment.getProperty("success.status"))
   }

   /**
    * @return Set Password Method :- Changing the Password
    */
   fun setPassword(resetPasswordDto : ResetPasswordDto, token: String?): Response? {

       val useremail = tokenutility.getUserToken(token!!)

       var user: User? = userRepository.findByEmail(useremail)

       println("2")
//       println(userUpdate)

      return if (resetPasswordDto.password == resetPasswordDto.confirmpassword) { // check password or cfmpassword
         logger.info("Username Password Matched")


           user?.password(passwordEncoder.encode(resetPasswordDto.password))
          // new password encode it
          println("$user")//          println("$user")
          if (user != null) {
              userRepository.save(user)
          }
              println("$user")

      Response(environment.getProperty("status.ok.code")!!.toInt(), environment.getProperty("status.success.passwordchanged"), environment.getProperty("success.status"))
      } else {
         Response(environment.getProperty("status.badrequest.code")!!.toInt(), environment.getProperty("status.success.passwordnotmatched"), environment.getProperty("failure.status"))
      }
   }
}

private operator fun Any?.invoke(encode: String?) {
}
