package junroot.study

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

	@Bean
	fun inventoryService(): InventoryService {
		return InventoryService()
	}

	@Bean
	fun productService(): ProductService {
		return ProductService(inventoryService())
	}
}
