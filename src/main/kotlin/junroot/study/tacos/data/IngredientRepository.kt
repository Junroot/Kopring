package junroot.study.tacos.data

import junroot.study.tacos.Ingredient

interface IngredientRepository {

	fun findAll(): Iterable<Ingredient>
	fun findById(id: String): Ingredient?
	fun save(ingredient: Ingredient): Ingredient
}
