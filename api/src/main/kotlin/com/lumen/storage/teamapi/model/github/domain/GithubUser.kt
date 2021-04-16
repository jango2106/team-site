package com.lumen.storage.teamapi.model.github.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class GithubUser(
    @Id
    var id: Int,

    @Column(unique = true, nullable = false)
    val name: String,

    @Column(unique = true, nullable = false)
    val avatarUrl: String
)