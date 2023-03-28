package junroot.study.tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig {

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
		val user1 = User.builder()
			.username("user1")
			.password("{noop}password1")
			.authorities("ROLE_USER")
			.build()
		val user2 = User.builder()
			.username("user2")
			.password("{noop}password2")
			.authorities("ROLE_USER")
			.build()
		return InMemoryUserDetailsManager(user1, user2)
	}
}
