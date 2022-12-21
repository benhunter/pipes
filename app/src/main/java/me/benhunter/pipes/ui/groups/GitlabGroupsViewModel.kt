package me.benhunter.pipes.ui.groups

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.benhunter.pipes.Account
import me.benhunter.pipes.GitlabApi
import me.benhunter.pipes.GitlabGroup
import me.benhunter.pipes.Preferences

class GitlabGroupsViewModel : ViewModel() {

    private lateinit var preferences: Preferences
    private val account = MutableLiveData<Account>()
    private val gitlabGroups = MutableLiveData<List<GitlabGroup>>()

    fun loadAccount(sharedPreferences: SharedPreferences) {
        preferences = Preferences(sharedPreferences)
        account.postValue(preferences.loadAccount())
    }

    fun observeGitlabGroups(): LiveData<List<GitlabGroup>> {
        return gitlabGroups
    }

    fun fetchGroups() {
        if (account.value == null) throw RuntimeException("Can't fetch Gitlab groups until account is set")

        val gitlabApi = GitlabApi.create(account.value!!.server)

        TODO("Not yet implemented")
    }
}
