package junroot.study.tacos

data class Order(
	val deliveryName: String?,
	val deliveryStreet: String?,
	val deliveryCity: String?,
	val deliveryState: String?,
	val deliveryZip: String?,
	val ccNumber: String?,
	val ccExpiration: String?,
	val ccCVV: String?
) {
	constructor() : this(null, null, null, null, null, null, null, null)
}
