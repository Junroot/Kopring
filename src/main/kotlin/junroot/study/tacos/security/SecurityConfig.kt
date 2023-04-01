package junroot.study.tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.*
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@EnableWebSecurity
class SecurityConfig(private val dataSource: DataSource) {

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http.authorizeRequests()
			.antMatchers("/design", "orders")
			.access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**").access("permitAll")
			.and()
			.httpBasic()
		return http.build()
	}

	@Bean
	fun userDetailService(): UserDetailsService {
		val jdbcUserDetailsManager = JdbcUserDetailsManager(dataSource)
		jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username=?")
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, authority from authorities where username=?")

		return jdbcUserDetailsManager
	}

	@Bean
	fun delegatingPasswordEncoder(): PasswordEncoder {
		val encodingId = "bcrypt"
		val encoders: MutableMap<String, PasswordEncoder> = mutableMapOf()
		encoders[encodingId] = BCryptPasswordEncoder()

		encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
		encoders["scrypt"] = SCryptPasswordEncoder()
		encoders["argon2"] = Argon2PasswordEncoder()

		val delegatingPasswordEncoder = DelegatingPasswordEncoder(encodingId, encoders)
		delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(CustomPasswordEncoder())
		return delegatingPasswordEncoder
	}
}
