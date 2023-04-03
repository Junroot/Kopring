package junroot.study.tacos.data

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.integration.redis.util.RedisLockRegistry
import org.springframework.integration.support.locks.ExpirableLockRegistry
import java.time.Duration

@Configuration
class RedisDistributedLockConfiguration {

	companion object {
		private const val REDIS_KEY = "key"
	}

	@Bean
	fun lockRegistry(redisConnectionFactory: RedisConnectionFactory): ExpirableLockRegistry {
		val redisLockRegistry = RedisLockRegistry(
			redisConnectionFactory,
			REDIS_KEY,
			Duration.ofSeconds(30).toMillis()
		)
		redisLockRegistry.setRedisLockType(RedisLockRegistry.RedisLockType.PUB_SUB_LOCK)
		return redisLockRegistry
	}
}
