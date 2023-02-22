package me.benhunter.pipes.ui.groups

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.benhunter.pipes.*
import java.net.UnknownHostException

class GitlabGroupsViewModel : ViewModel() {

    private lateinit var preferences: Preferences
    private val account = MutableLiveData<Account>()
    private val gitlabGroups = MutableLiveData<List<GitlabGroup>>()

    fun loadAccount(sharedPreferences: SharedPreferences) {
        preferences = Preferences(sharedPreferences)
//        account.postValue(preferences.loadAccount())
        account.value = preferences.loadAccount()
    }

    fun observeGitlabGroups(): LiveData<List<GitlabGroup>> {
        return gitlabGroups
    }

    fun fetchGroups() = viewModelScope.launch(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        if (account.value == null) throw RuntimeException("Can't fetch Gitlab groups until account is set")

        val gitlabApi = GitlabApi.create(account.value!!)
        val gitlabRepository = GitlabRepository(gitlabApi)

        try {
            gitlabGroups.postValue(gitlabRepository.fetchGroups())
        } catch (e: Exception) {
            Log.d(javaClass.simpleName, "fetchGroups Exception: $e")
            if (e is UnknownHostException) throw e
        }
    }
}
