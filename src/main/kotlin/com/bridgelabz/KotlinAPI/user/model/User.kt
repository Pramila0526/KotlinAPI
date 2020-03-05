package com.bridgelabz.KotlinAPI.user.model
import org.springframework.stereotype.Component
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

@Component
@Entity
@Table(name = "userDetails")
  class User: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Int = 0

    var firstName: String? = null

    var lastName: String? = null

    @Email(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
    var email: String? = null

    var password: String? = null

    var phoneNumber: Long = 0

    var isValidate = false


    constructor() {}

    constructor(id: Int, @NotNull firstName: String?, @NotNull lastName: String?, @NotNull email: String?,
                @NotNull password: String?, @NotNull phoneNumber: Long, @NotNull validate: Boolean, profilePic: String?) : super() {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.phoneNumber = phoneNumber
        isValidate = validate
    }



    override fun toString(): String {
        return ("User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", phoneNumber=" + phoneNumber + ", validate=" + isValidate
                )
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
