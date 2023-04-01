package junroot.study.tacos.security

import mu.KLogging
import org.springframework.security.crypto.password.PasswordEncoder

class CustomPasswordEncoder : PasswordEncoder {

	companion object: KLogging()

	override fun encode(rawPassword: CharSequence?): String {
		logger.info { rawPassword }
		return rawPassword.toString()
	}

	override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
		return encode(rawPassword) == encodedPassword
	}
}
