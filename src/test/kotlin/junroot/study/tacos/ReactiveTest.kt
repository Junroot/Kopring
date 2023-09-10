package junroot.study.tacos

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration
import java.util.stream.Stream

class ReactiveTest {

	@Test
	fun createFlux_just() {
		val fruitFlux: Flux<String> = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry")

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Orange")
			.expectNext("Grape")
			.expectNext("Banana")
			.expectNext("Strawberry")
			.verifyComplete()
	}

	@Test
	fun createFlux_fromArray() {
		val fruits: Array<String> = arrayOf("Apple", "Orange", "Grape", "Banana", "Strawberry")
		val fruitFlux = Flux.fromArray(fruits)

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Orange")
			.expectNext("Grape")
			.expectNext("Banana")
			.expectNext("Strawberry")
			.verifyComplete()
	}

	@Test
	fun createFlux_fromIterable() {
		val fruits: List<String> = listOf("Apple", "Orange", "Grape", "Banana", "Strawberry")
		val fruitFlux = Flux.fromIterable(fruits)

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Orange")
			.expectNext("Grape")
			.expectNext("Banana")
			.expectNext("Strawberry")
			.verifyComplete()
	}

	@Test
	fun createFlux_fromStream() {
		val fruits: Stream<String> = Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry")
		val fruitFlux = Flux.fromStream(fruits)

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Orange")
			.expectNext("Grape")
			.expectNext("Banana")
			.expectNext("Strawberry")
			.verifyComplete()
	}

	@Test
	fun creatFlux_range() {
		val rangeFlux = Flux.range(1, 5)

		StepVerifier.create(rangeFlux)
			.expectNext(1)
			.expectNext(2)
			.expectNext(3)
			.expectNext(4)
			.expectNext(5)
			.verifyComplete()
	}

	@Test
	fun createFlux_interval() {
		val intervalFlux = Flux.interval(Duration.ofSeconds(1))
			.take(5)

		StepVerifier.create(intervalFlux)
			.expectNext(0L)
			.expectNext(1L)
			.expectNext(2L)
			.expectNext(3L)
			.expectNext(4L)
			.verifyComplete()
	}

	@Test
	fun mergeFluxes() {
		val characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
			.delayElements(Duration.ofMillis(500))
		val foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")
			.delaySubscription(Duration.ofMillis(250))
			.delayElements(Duration.ofMillis(500))

		val mergedFlux = characterFlux.mergeWith(foodFlux)

		StepVerifier.create(mergedFlux)
			.expectNext("Garfield")
			.expectNext("Lasagna")
			.expectNext("Kojak")
			.expectNext("Lollipops")
			.expectNext("Barbossa")
			.expectNext("Apples")
			.verifyComplete()
	}

	@Test
	fun zipFluxes() {
		val characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
		val foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")

		val zippedFLux = Flux.zip(characterFlux, foodFlux)

		StepVerifier.create(zippedFLux)
			.expectNextMatches{ it.t1 == "Garfield" && it.t2 == "Lasagna" }
			.expectNextMatches{ it.t1 == "Kojak" && it.t2 == "Lollipops" }
			.expectNextMatches{ it.t1 == "Barbossa" && it.t2 == "Apples" }
			.verifyComplete()
	}

	@Test
	fun zipFluxesToObject() {
		val characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
		val foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")

		val zippedFLux = Flux.zip(characterFlux, foodFlux) { c, f -> "$c eats $f" }

		StepVerifier.create(zippedFLux)
			.expectNext("Garfield eats Lasagna")
			.expectNext("Kojak eats Lollipops")
			.expectNext("Barbossa eats Apples")
			.verifyComplete()
	}

	@Test
	fun firstFlux() {
		val slowFlux = Flux.just(4, 5, 6)
			.delayElements(Duration.ofMillis(500))
			.delaySubscription(Duration.ofMillis(250))
		val fastFlux = Flux.just(1, 2, 3)
			.delayElements(Duration.ofMillis(500))

		val firstFlux = Flux.firstWithSignal(slowFlux, fastFlux)

		StepVerifier.create(firstFlux)
			.expectNext(1)
			.expectNext(2)
			.expectNext(3)
			.verifyComplete()
	}
}
