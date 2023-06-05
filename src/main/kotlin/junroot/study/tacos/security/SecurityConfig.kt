package junroot.study.tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {
	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http.authorizeRequests()
			.antMatchers("/design", "/orders")
			.hasRole("USER")
			.antMatchers("/", "/**")
			.permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticate")
			.usernameParameter("user")
			.passwordParameter("pwd")
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.csrf()
				.ignoringAntMatchers("/h2-console/**")
			.and()
			.headers().frameOptions().sameOrigin()
		return http.build()
	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}
}
