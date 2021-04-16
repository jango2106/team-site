package com.lumen.storage.teamapi.repository

import com.lumen.storage.teamapi.model.github.domain.GithubPullId
import org.springframework.data.repository.CrudRepository

interface GithubPull: CrudRepository<GithubPull, GithubPullId>