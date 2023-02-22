package me.benhunter.pipes

import com.google.gson.GsonBuilder

class GitlabRepository(private val gitlabApi: GitlabApi) {

    private val gson = GsonBuilder().create()

    suspend fun fetchGroups(): List<GitlabGroup> {
        val groups = gitlabApi.getGroups().data.children
        return groups
    }
}