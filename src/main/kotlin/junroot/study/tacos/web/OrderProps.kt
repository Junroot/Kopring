package junroot.study.tacos.web

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "taco.orders")
@ConstructorBinding
data class OrderProps(
	val pageSize: Int = 20
)

