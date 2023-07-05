package junroot.study.tacos.web.api

import junroot.study.tacos.Ingredient
import org.springframework.hateoas.RepresentationModel

class IngredientModel private constructor(
	val name: String,
	val type: Ingredient.Type
) : RepresentationModel<IngredientModel>() {
	constructor(ingredient: Ingredient) : this(ingredient.name, ingredient.type)
}
