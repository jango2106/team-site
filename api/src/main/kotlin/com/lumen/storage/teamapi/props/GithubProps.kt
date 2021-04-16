package com.lumen.storage.teamapi.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "github")
@ConstructorBinding
@Component
data class GithubProps(
    val teams: List<String> = listOf(),
    val ignoredRepos: List<String> = listOf(),
    val apiKey: String = "",
    val baseApiUrl: String = ""
)