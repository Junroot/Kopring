package junroot.study.tacos

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.CreditCardNumber

data class Order(
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
	val ccCVV: String?
) {
	constructor() : this(null, null, null, null, null, null, null, null)
}
