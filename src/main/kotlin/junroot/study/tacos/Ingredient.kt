package junroot.study.tacos

import javax.persistence.*

@Entity
class Ingredient(
	@Id
	val id: String,
	val name: String,
	@Enumerated(EnumType.STRING)
	val type: Type
) {

	enum class Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
