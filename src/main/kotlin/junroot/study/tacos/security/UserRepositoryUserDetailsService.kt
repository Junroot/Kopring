package junroot.study.tacos.security

import junroot.study.tacos.data.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserRepositoryUserDetailsService(
	val userRepository: UserRepository
): UserDetailsService {

	override fun loadUserByUsername(username: String): UserDetails {
		return userRepository.findByUsername(username)
			?: throw UsernameNotFoundException("User '${username}' not found")
	}
}
