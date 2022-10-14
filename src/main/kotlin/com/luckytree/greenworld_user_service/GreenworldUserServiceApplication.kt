package com.luckytree.greenworld_user_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class GreenworldUserServiceApplication

fun main(args: Array<String>) {
	runApplication<GreenworldUserServiceApplication>(*args)
}
