package junroot.study.tacos.security

import junroot.study.tacos.data.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/register")
@Controller
class RegistrationController(
	private val userRepository: UserRepository,
	private val passwordEncoder: PasswordEncoder
) {

	@GetMapping
	fun registerForm(): String {
		return "registration"
	}

	@PostMapping
	fun processRegistration(form: RegistrationForm): String {
		userRepository.save(form.toUser(passwordEncoder))
		return "redirect:/login"
	}
}
