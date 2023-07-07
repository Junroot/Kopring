package junroot.study

import junroot.study.tacos.Ingredient
import junroot.study.tacos.Order
import junroot.study.tacos.Taco
import junroot.study.tacos.User
import junroot.study.tacos.data.IngredientRepository
import junroot.study.tacos.data.OrderRepository
import junroot.study.tacos.data.TacoRepository
import junroot.study.tacos.data.UserRepository
import junroot.study.tacos.web.OrderProps
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.text.SimpleDateFormat
import java.util.*

@ConfigurationPropertiesScan("junroot.study.tacos")
@SpringBootApplication
class StudyApplication {
	@Bean
	fun dataLoader(
		ingredientRepository: IngredientRepository,
		userRepository: UserRepository,
		tacoRepository: TacoRepository,
		orderRepository: OrderRepository
	): CommandLineRunner {
		return CommandLineRunner {
			val ingredient1 = ingredientRepository.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
			val ingredient2 = ingredientRepository.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
			ingredientRepository.save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
			ingredientRepository.save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
			ingredientRepository.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
			ingredientRepository.save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
			ingredientRepository.save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
			ingredientRepository.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
			ingredientRepository.save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
			ingredientRepository.save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))

			val user = userRepository.save(
				User(
					id = null,
					username = "user1",
					password = "password1",
					fullname = "user1",
					street = "street1",
					city = "city1",
					state = "state1",
					zip = "123",
					phoneNumber = "01012341234"
				)
			)

			repeat(30) {
				tacoRepository.save(Taco(
					null,
					Date(),
					"tacoName",
					ingredients = listOf(ingredient1, ingredient2)
				))
			}
			val taco = tacoRepository.save(Taco(
				null,
				Date(),
				"tacoName",
				ingredients = listOf(ingredient1, ingredient2)
			))

			orderRepository.save(Order(
				null,
				Date(),
				"dateName",
				"street",
				"city",
				"state",
				"zip",
				"ccNumber",
				"expriation",
				"cvv",
				user,
				mutableListOf(taco)
			))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<StudyApplication>(*args)
}
