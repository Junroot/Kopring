package junroot.study.tacos

import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class Taco(
	var id: Long? = null,
	var createdAt: Date? = null,
	@Size(min = 5, message = "Name must be at least 5 characters long")
	@NotNull
	val name: String? = null,
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	@NotNull
	val ingredients: List<Ingredient>? = null
)
