package com.lumen.storage.teamapi.model.github.domain

import java.util.*
import javax.persistence.*

@Entity
data class GithubPull(
    @EmbeddedId
    val githubPullId: GithubPullId,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val url: String,
    @Column(nullable = false)
    val createdDate: Date,
    @Column(nullable = false)
    val lastModifiedDate: Date,
    @Column(nullable = false)
    val isDraft: Boolean,
    @OneToOne
    val author: GithubUser,
    @ManyToMany
    val reviewers: List<GithubUser> = listOf(),
    @ManyToMany
    val assignees: List<GithubUser> = listOf()
)