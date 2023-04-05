package junroot.study.tacos.security

import junroot.study.tacos.data.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserRepositoryUserDetailsService(
	private val userRepository: UserRepository
) : UserDetailsService {

	override fun loadUserByUsername(username: String): UserDetails {
		val user = userRepository.findByUsername(username)
		if (user != null) {
			return user
		}
		throw UsernameNotFoundException("User '$username' not found")
	}
}
