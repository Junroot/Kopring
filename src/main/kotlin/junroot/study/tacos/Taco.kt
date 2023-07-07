package junroot.study.tacos

import org.springframework.data.rest.core.annotation.RestResource
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestResource(rel="tacos", path="tacos")
@Entity
class Taco(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
	var createdAt: Date,

	@Size(min = 5, message = "Name must be at least 5 characters long")
	@NotNull
	val name: String,

	@ManyToMany(targetEntity = Ingredient::class)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	@NotNull
	val ingredients: List<Ingredient>
) {
	@PrePersist
	fun createdAt() {
		createdAt = Date()
	}
}
