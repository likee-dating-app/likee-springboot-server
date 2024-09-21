package com.rdktechnologies.BackendTemplate

import com.rdktechnologies.BackendTemplate.repository.role.Role
import com.rdktechnologies.BackendTemplate.repository.user.UserRepository
import com.rdktechnologies.BackendTemplate.repository.user.Users
import com.rdktechnologies.BackendTemplate.util.autowired
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@EnableJpaRepositories
@SpringBootApplication
@EnableAutoConfiguration
@CrossOrigin
@EnableScheduling
@EnableAsync
@OpenAPIDefinition(
	info = Info(
		title = "Likee Dating App",
		description = "A Ai based dating app.",
		termsOfService = "http://swagger.io/terms/",
		license = License(
			name = "Apache 2.0",
			url = "http://springdoc.org"),
		version = "1.0"
	)
)
@SecurityScheme(
	name = "bearerAuth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
class Application {

	@EventListener(ApplicationReadyEvent::class)
	fun runAfterStartup() {
		val userRepository: UserRepository by autowired()
		val bCryptPasswordEncoder: BCryptPasswordEncoder by autowired()
		val usersData = userRepository.findByEmail("admin@rsquare.com")
		if (!usersData.isPresent && usersData.isEmpty){
			userRepository.save(
				Users(
					firstName = "Admin",
					lastName = "User",
					email = "admin@rsquare.com",
					keypassword = bCryptPasswordEncoder.encode("Admin@123"),
					role = Role.ADMIN,
				)
			)
		}

	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}


