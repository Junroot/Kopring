package junroot.study.tacos

import org.springframework.hateoas.RepresentationModel
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.PrePersist
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

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
