package junroot.study

import junroot.study.tacos.Ingredient
import junroot.study.tacos.data.IngredientRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class StudyApplication {
	@Bean
	fun dataLoader(repo: IngredientRepository): CommandLineRunner {
		return CommandLineRunner {
			repo.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP))
			repo.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP))
			repo.save(Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN))
			repo.save(Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN))
			repo.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES))
			repo.save(Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES))
			repo.save(Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE))
			repo.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE))
			repo.save(Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE))
			repo.save(Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<StudyApplication>(*args)
}
