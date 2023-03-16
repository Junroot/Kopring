package junroot.study.tacos

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class Taco(
	@Size(min = 5, message = "Name must be at least 5 characters long")
	@NotNull
	val name: String,
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	@NotNull
	val ingredients: List<String>
)
