package junroot.study.tacos.security

import junroot.study.tacos.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm(
	val username: String,
	val password: String,
	val fullname: String,
	val street: String,
	val city: String,
	val state: String,
	val zip: String,
	val phone: String
) {

	fun toUser(passwordEncoder: PasswordEncoder): User {
		return User(
			username = username,
			password = passwordEncoder.encode(password),
			fullname = fullname,
			street = street,
			city = city,
			state = state,
			zip = zip,
			phoneNumber = phone
		)
	}
}
