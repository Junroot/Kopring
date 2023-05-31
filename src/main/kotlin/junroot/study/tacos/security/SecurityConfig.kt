package junroot.study.tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@EnableWebSecurity
@Configuration
class SecurityConfig(
	val dataSource: DataSource
) {

	@Bean
	fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsManager {
		val user: UserDetails = User.withUsername("user1")
			.password("{noop}password1")
			.authorities("ROLE_USER")
			.build()

		val jdbcUserDetailsManager = JdbcUserDetailsManager(dataSource).apply {
			createUser(user)
			setUsersByUsernameQuery("select username,password,enabled from users where username = ?")
			setAuthoritiesByUsernameQuery("select username,authority from authorities where username = ?")
		}

		return jdbcUserDetailsManager
	}

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http.authorizeRequests()
			.antMatchers("/design", "/orders")
			.access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**")
			.access("permitAll")
			.and()
			.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
			.headers().frameOptions().sameOrigin()
		return http.build()
	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}
}
