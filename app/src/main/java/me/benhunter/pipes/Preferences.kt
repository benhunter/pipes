package me.benhunter.pipes

import android.content.SharedPreferences
import android.util.Log

class Preferences (private val preferences: SharedPreferences){

    fun loadAccount(): Account {
        Log.d(javaClass.name, "loadAccountFromSharedPreferences")
        val account = Account(
            preferences.getString("server", "") ?: "",
            preferences.getString("token", "") ?: ""
        )
        Log.d(javaClass.name, "loadAccountFromSharedPreferences $account ${account.server}")
        return account
    }

    fun setAccount(account: Account) {
        val preferencesEditor = preferences.edit()
        preferencesEditor?.let {
            it.putString("server", account.server)
            it.putString("token", account.token)
            it.apply()

            Log.d(javaClass.name, "setAccount $account ${account.server}")
        }
    }
}
