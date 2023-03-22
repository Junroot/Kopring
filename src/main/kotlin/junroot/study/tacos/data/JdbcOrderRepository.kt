package junroot.study.tacos.data

import junroot.study.tacos.Order
import junroot.study.tacos.Taco
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

fun <T : Any> toMap(obj: T): MutableMap<String, Any?> {
	return (obj::class as KClass<T>).memberProperties.associate { prop ->
		prop.name to prop.get(obj)?.let { value ->
			if (value::class.isData) {
				toMap(value)
			} else {
				value
			}
		}
	}.toMutableMap()
}


@Repository
class JdbcOrderRepository(
	private val jdbc: JdbcTemplate
): OrderRepository {
	private final val orderInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
		.withTableName("Taco_Order")
		.usingGeneratedKeyColumns("id")
	private final val orderTacoInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
		.withTableName("Taco_Order_Tacos")

	override fun save(order: Order): Order {
		order.placedAt = Date()
		val orderId = saveOrderDetails(order)
		order.id = orderId
		val tacos = order.tacos

		for (taco in tacos) {
			saveTacoToOrder(taco, orderId)
		}
		return order
	}

	private fun saveOrderDetails(order: Order): Long {
		val values = toMap(order)
		values["placedAt"] = order.placedAt
		return orderInserter.executeAndReturnKey(values)
			.toLong()
	}

	private fun saveTacoToOrder(taco: Taco, orderId: Long) {
		val values = HashMap<String, Any?>()
		values["tacoOrder"] = orderId
		values["taco"] = taco.id
		orderTacoInserter.execute(values)
	}
}
