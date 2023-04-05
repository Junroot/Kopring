package junroot.study.tacos

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long?,
	private val username: String,
	private val password: String,
	val fullname: String,
	val street: String,
	val city: String,
	val state: String,
	val zip: String,
	val phoneNumber: String
) : UserDetails {

	companion object {
		val AUTHORITIES = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
	}

	override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
		return AUTHORITIES
	}

	override fun getPassword(): String {
		return password
	}

	override fun getUsername(): String {
		return username
	}

	override fun isAccountNonExpired(): Boolean {
		return true
	}

	override fun isAccountNonLocked(): Boolean {
		return true
	}

	override fun isCredentialsNonExpired(): Boolean {
		return true
	}

	override fun isEnabled(): Boolean {
		return true
	}
}
