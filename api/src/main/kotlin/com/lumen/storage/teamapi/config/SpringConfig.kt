package com.lumen.storage.teamapi.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.client.RestTemplate
import java.util.concurrent.Executor

@Configuration
@EnableScheduling
class SpringConfig {
    @Bean("githubTaskExecutor")
    fun githubTaskExecutor(): Executor {
        return ThreadPoolTaskExecutor().apply {
            corePoolSize = 2
            maxPoolSize = 2
            setQueueCapacity(500)
            setThreadNamePrefix("GithubLookup")
            initialize()
        }
    }

    @Bean
    fun getRestTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }
}
