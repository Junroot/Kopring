package junroot.study.tacos

import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Taco_Order")
class Order(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long?,
	var placedAt: Date,
	@field:NotBlank(message = "Name is required")
	var deliveryName: String,
	@field:NotBlank(message = "Street is required")
	var deliveryStreet: String,
	@field:NotBlank(message = "City is required")
	var deliveryCity: String,
	@field:NotBlank(message = "State is required")
	var deliveryState: String,
	@field:NotBlank(message = "Zip is required")
	var deliveryZip: String,
	//	@field:CreditCardNumber(message = "Not a valid credit card number")
	var ccNumber: String,
	//	@field:Pattern(
	//		regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
	//		message = "Must be formatted MM/YY"
	//	)
	var ccExpiration: String,
	//	@field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	var ccCVV: String,
	@ManyToOne
	@JoinColumn(name = "user_id")
	var user: User,
	@ManyToMany(targetEntity = Taco::class)
	val tacos: MutableList<Taco> = ArrayList()
) : Serializable {

	companion object {
		@JvmStatic
		private val serialVersionUID = 1L
	}

	fun addDesign(taco: Taco) {
		tacos.add(taco)
	}

	@PrePersist
	fun placedAt() {
		placedAt = Date()
	}
}
