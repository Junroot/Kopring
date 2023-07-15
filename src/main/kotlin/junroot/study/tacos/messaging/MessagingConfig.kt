package junroot.study.tacos.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import junroot.study.tacos.Order
import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import javax.jms.Destination

@Configuration
class MessagingConfig {
	@Bean
	fun orderQueue(): Destination {
		return ActiveMQQueue("tacocloud.order.queue")
	}

	@Bean
	fun messageConverter(objectMapper: ObjectMapper): MappingJackson2MessageConverter {
		return MappingJackson2MessageConverter().apply {
			setObjectMapper(objectMapper)
			setTypeIdPropertyName("_typeId")
			setTypeIdMappings(
				mapOf<String, Class<*>>(
					"order" to Order::class.java
				)
			)
		}
	}
}
