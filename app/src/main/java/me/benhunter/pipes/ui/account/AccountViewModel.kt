package me.benhunter.pipes.ui.account

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.benhunter.pipes.Account
import me.benhunter.pipes.Preferences

class AccountViewModel : ViewModel() {

    private lateinit var preferences: Preferences
    private val account = MutableLiveData<Account>()

    fun account(): LiveData<Account> {
        return account
    }

    fun setAccount(account: Account) {
        Log.d(javaClass.name, "setAccount")
        testAccount(account)
        preferences.setAccount(account)
        this.account.postValue(account)
    }

    private fun testAccount(account: Account) {
        return
    }

    fun loadAccount(sharedPreferences: SharedPreferences) {
        preferences = Preferences(sharedPreferences)
        account.postValue(preferences.loadAccount())
    }
}

