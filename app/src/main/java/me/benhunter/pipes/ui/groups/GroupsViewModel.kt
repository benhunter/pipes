package me.benhunter.pipes.ui.groups

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.benhunter.pipes.Account

class GroupsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is groups Fragment"
    }
    val text: LiveData<String> = _text

    private val account = MutableLiveData<Account>()

    fun loadAccountFromSharedPreferences(preferences: SharedPreferences) {
        Log.d(javaClass.name, "loadAccountFromSharedPreferences")
        val account = Account(
            preferences.getString("server", "") ?: "",
            preferences.getString("token", "") ?: ""
        )
        this.account.postValue(account)
        Log.d(javaClass.name, "loadAccountFromSharedPreferences $account ${account.server}")
    }
}