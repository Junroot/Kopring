package junroot.study.tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {

	@Bean
	fun userDetailsService(passwordEncoder: PasswordEncoder): InMemoryUserDetailsManager {
		val user: UserDetails = User.withUsername("user1")
			.password("{noop}password1")
			.authorities("ROLE_USER")
			.build()

		return InMemoryUserDetailsManager(user)
	}

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http.authorizeRequests()
			.antMatchers("/design", "/orders")
			.access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**")
			.access("permitAll")
			.and()
			.httpBasic()
		return http.build()
	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}
}
