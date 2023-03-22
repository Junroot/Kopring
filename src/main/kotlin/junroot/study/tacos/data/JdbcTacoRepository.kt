package junroot.study.tacos.data

import junroot.study.tacos.Ingredient
import junroot.study.tacos.Taco
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.sql.Types
import java.util.*

@Repository
class JdbcTacoRepository(
	private val jdbc: JdbcTemplate
): TacoRepository {

	override fun save(design: Taco): Taco {
		val tacoId = saveTacoInfo(design)
		design.id = tacoId
		for (ingredient in design.ingredients!!) {
			saveIngredientToTaco(ingredient, tacoId)
		}
		return design
	}

	private fun saveTacoInfo(taco: Taco): Long {
		taco.createdAt = Date()
		val psc = PreparedStatementCreatorFactory(
			"insert into Taco (name, created_at) values (?, ?)",
			Types.VARCHAR,
			Types.TIMESTAMP
		).newPreparedStatementCreator(
			listOf(
				taco.name,
				Timestamp(taco.createdAt!!.time)
			)
		)

		val keyHolder = GeneratedKeyHolder()
		jdbc.update(psc, keyHolder)
		return keyHolder.key?.toLong() ?: -1
	}

	private fun saveIngredientToTaco(ingredient: Ingredient, tacoId: Long) {
		jdbc.update(
			"insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
			tacoId,
			ingredient.id
		)
	}
}
