package junroot.study.tacos

import junroot.study.tacos.web.api.TacoModel
import org.junit.jupiter.api.Test
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.client.Traverson
import java.net.URI

class TraversonTest {

	private val traverson = Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON)

	@Test
	fun traversonTest() {

		val ingredientType = object : ParameterizedTypeReference<CollectionModel<Ingredient>>() {}
		val ingredientRes = traverson.follow("ingredients")
			.toObject(ingredientType)

		val ingredients = ingredientRes?.content

		println(ingredients)
	}

	@Test
	fun tacoTest() {
		val tacoType = object : ParameterizedTypeReference<CollectionModel<Taco>>() {}
		val tacoRes = traverson.follow("tacos").follow("recents")
			.toObject(tacoType)

		println(tacoRes.content)
	}
}
