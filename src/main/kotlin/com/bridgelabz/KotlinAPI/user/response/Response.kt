package com.bridgelabz.KotlinAPI.user.response

import java.io.Serializable

class Response : Serializable {
    var status = 0
    var message: String? = null
    var data: Any? = null
    var userData: Any? = null

    constructor() {}
    constructor(status: Int, message: String?, data: Any?) : super() {
        this.status = status
        this.message = message
        this.data = data
    }

    constructor(status: Int, message: String?) : super() {
        this.status = status
        this.message = message
    }

    constructor(status: Int, data1: Any?, data: Any?) : super() {
        this.status = status
        this.data = data1
        this.data = data
    }

    constructor(status: Int, message: String?, data: Any?, userData: Any?) : super() {
        this.status = status
        this.message = message
        this.data = data
        this.userData = userData
    }

    override fun toString(): String {
        return "Response [status=$status, message=$message, data=$data]"
    }

    companion object {
        const val serialversionuid = 1L
    }
}