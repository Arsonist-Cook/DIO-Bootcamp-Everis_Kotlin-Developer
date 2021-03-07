package com.example.android.anothercontactlist

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContactHelper(private val sharedPreferences: SharedPreferences) {
    fun getContactList(): List<Contact> {
        val stringList:String? = sharedPreferences.getString(R.string.contact_list_key.toString(),"[]")
        val turnsType = object: TypeToken<List<Contact>>(){}.type
        return Gson().fromJson(stringList, turnsType)
    }

    fun setContactList(list: List<Contact>) {
        val json:String = Gson().toJson(list)
        sharedPreferences.edit()
            .putString(R.string.contact_list_key.toString(), json)
            .commit()

    }
}