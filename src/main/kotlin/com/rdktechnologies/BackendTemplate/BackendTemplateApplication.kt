package com.rdktechnologies.BackendTemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class BackendTemplateApplication{




	@GetMapping("/hello")
	fun  hello(@RequestParam(value = "name", defaultValue = "World") name:String):String {
		return String.format("Hello brother %s!", name);
	}
}

fun main(args: Array<String>) {
	runApplication<BackendTemplateApplication>(*args)
}


