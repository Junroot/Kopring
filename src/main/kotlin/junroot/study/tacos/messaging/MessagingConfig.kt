package junroot.study.tacos.messaging

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class MessagingConfig {

	@Bean
	fun orderQueue(): Queue {
		return Queue("tacocloud.order.queue", false)
	}

	@Bean
	fun producerFactory(): ProducerFactory<String, Any> {
		val props = mapOf<String, Any>(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java)
		return DefaultKafkaProducerFactory(props)
	}

	@Bean
	fun kafkaTemplate(): KafkaTemplate<String, Any> {
		return KafkaTemplate(producerFactory())
	}

	@Bean
	fun messageConverter(): MessageConverter {
		return Jackson2JsonMessageConverter()
	}
}
