package com.example.service.prag.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class EhCacheConfig {
    @Bean
    public CacheManager getCacheManager() {
        /*Todo CachingProvider()메서드를 통해서 CachingProviderRegistry 에서 CachingProvider를 가져온다.*/
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();

        CacheConfiguration<String, Object> configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(

                        // Todo 코드상으로 보면 Cache의 keyType과 Value Type을 String class와 ObjectClass로 지정하고있다.
                        String.class, Object.class, ResourcePoolsBuilder
                                .heap(100)
                                .offheap(10, MemoryUnit.MB))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(10)))
                .build();

        /* Todo Eh107Configuration을 사용하면, JCache의 표준 API와 Ehcache의 성능을 모두 활용할 수 있습니다.  */
        javax.cache.configuration.Configuration<String, Object> cacheConfiguration
                = Eh107Configuration.fromEhcacheCacheConfiguration(configuration);

        cacheManager.createCache("schedules", cacheConfiguration);

        return cacheManager;
    }
}