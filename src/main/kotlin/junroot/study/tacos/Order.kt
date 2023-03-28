package junroot.study.tacos

import org.hibernate.validator.constraints.CreditCardNumber
import java.io.Serializable
import java.util.Date
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
@Table(name="Taco_Order")
class Order(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long?,
	var placedAt: Date?,
	@field:NotBlank(message = "Name is required")
	val deliveryName: String?,
	@field:NotBlank(message = "Street is required")
	val deliveryStreet: String?,
	@field:NotBlank(message = "City is required")
	val deliveryCity: String?,
	@field:NotBlank(message = "State is required")
	val deliveryState: String?,
	@field:NotBlank(message = "Zip is required")
	val deliveryZip: String?,
	@field:CreditCardNumber(message = "Not a valid credit card number")
	val ccNumber: String?,
	@field:Pattern(
		regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
		message = "Must be formatted MM/YY"
	)
	val ccExpiration: String?,
	@field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	val ccCVV: String?,
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

	constructor() : this(null, null, null, null, null, null, null, null, null, null)
}
