package com.iwa.ktlanding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class KotlinlandingserviceApplication

fun main(args: Array<String>) {
	runApplication<KotlinlandingserviceApplication>(*args)
}
