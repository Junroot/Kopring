package junroot.study.tacos.data

import junroot.study.tacos.Ingredient
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class JdbcIngredientRepository(
	private val jdbc: JdbcTemplate
) : IngredientRepository  {

	override fun findAll(): Iterable<Ingredient> {
		return jdbc.query(
			"select id, name, type from Ingredient",
			this::mapRowToIngredient
		)
	}

	override fun findById(id: String): Ingredient? {
		return jdbc.queryForObject(
			"select id, name, type from Ingredient where id=?",
			this::mapRowToIngredient,
			id
		)
	}

	override fun save(ingredient: Ingredient): Ingredient {
		jdbc.update(
			"insert into Ingredient (id, name, type) values (?, ?, ?)",
			ingredient.id,
			ingredient.name,
			ingredient.type
		)
		return ingredient
	}

	private fun mapRowToIngredient(resultSet: ResultSet, rowNum: Int): Ingredient {
		return Ingredient(
			resultSet.getString("id"),
			resultSet.getString("name"),
			Ingredient.Type.valueOf(resultSet.getString("type"))
		)
	}
}
