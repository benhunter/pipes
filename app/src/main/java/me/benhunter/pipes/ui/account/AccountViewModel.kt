package me.benhunter.pipes.ui.account

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.benhunter.pipes.Account

class AccountViewModel : ViewModel() {

    private var preferences: SharedPreferences? = null
    private val account = MutableLiveData<Account>()

    fun account(): LiveData<Account> {
        return account
    }

    fun setAccount(account: Account) {
        Log.d(javaClass.name, "setAccount")
        testAccount(account)
        val preferencesEditor = preferences?.edit()
        preferencesEditor?.let {
            it.putString("server", account.server)
            it.putString("token", account.token)
            it.apply()

            this.account.postValue(account)

            Log.d(javaClass.name, "setAccount $account ${account.server}")
        }
    }

    fun loadAccountFromSharedPreferences() {
        Log.d(javaClass.name, "loadAccountFromSharedPreferences")
        preferences?.let {
            val account = Account(
                it.getString("server", "") ?: "",
                it.getString("token", "") ?: ""
            )
            this.account.postValue(account)
            Log.d(javaClass.name, "loadAccountFromSharedPreferences $account ${account.server}")
        }
    }

    private fun testAccount(account: Account) {
        return
    }

    fun setSharedPreferences(preferences: SharedPreferences?) {
        this.preferences = preferences
    }
}
