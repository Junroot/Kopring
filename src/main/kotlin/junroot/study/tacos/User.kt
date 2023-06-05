package junroot.study.tacos

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Table(name = "Users")
@Entity
data class User(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val id: Long? = null,
	private val username: String,
	private val password: String,
	val fullname: String,
	val street: String,
	val city: String,
	val state: String,
	val zip: String,
	val phoneNumber: String
) : UserDetails {
	override fun getAuthorities(): Collection<GrantedAuthority> {
		return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
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
