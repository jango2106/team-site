package com.lumen.storage.teamapi.service

import com.lumen.storage.teamapi.model.github.GithubRepo
import com.lumen.storage.teamapi.model.github.GithubRepoList
import com.lumen.storage.teamapi.props.GithubProps
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.client.RestTemplate

class AsyncGithubService(
    private val githubProps: GithubProps,
    private val restTemplate: RestTemplate
) {
    @Scheduled(cron = "0 0/10 0 ? * * *")
    fun fetchGithubPullInfo() {
        githubProps.teams.forEach {
            getTeamRepos(it)
        }

    }

    fun getTeamRepos(teamName: String): GithubRepoList {
        return restTemplate.getForObject(
            "${githubProps.baseApiUrl}/orgs/CenturyLinkCloud/teams/advanced-storage/repos",
            GithubRepoList::class.java
        )!!
    }
}