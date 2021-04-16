package com.lumen.storage.teamapi.model.github.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class GithubPullId(
    val repoName: String,
    val pullNumber: Int
): Serializable