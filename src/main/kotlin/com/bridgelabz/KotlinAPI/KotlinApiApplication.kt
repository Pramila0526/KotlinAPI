package com.bridgelabz.KotlinAPI
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinApiApplication

fun main(args: Array<String>) {
//	runApplication<KotlinApiApplication>(*args)
	SpringApplication.run(KotlinApiApplication::class.java, *args)
}
