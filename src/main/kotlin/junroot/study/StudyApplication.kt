package junroot.study

import junroot.study.tacos.Ingredient
import junroot.study.tacos.User
import junroot.study.tacos.data.IngredientRepository
import junroot.study.tacos.data.UserRepository
import junroot.study.tacos.web.OrderProps
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@ConfigurationPropertiesScan("junroot.study.tacos")
@SpringBootApplication
class StudyApplication {
	@Bean
	fun dataLoader(
		ingredientRepository: IngredientRepository,
		userRepository: UserRepository
	): CommandLineRunner {
		return CommandLineRunner {
			ingredientRepository.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
			ingredientRepository.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
			ingredientRepository.save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
			ingredientRepository.save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
			ingredientRepository.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
			ingredientRepository.save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
			ingredientRepository.save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
			ingredientRepository.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
			ingredientRepository.save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
			ingredientRepository.save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))

			userRepository.save(
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
		}
	}
}

fun main(args: Array<String>) {
	runApplication<StudyApplication>(*args)
}
