package com.example.android.anothercontactlist

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
class ContactHelperTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val sharedPreferences:SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    private val contactHelper:ContactHelper = ContactHelper(sharedPreferences)

    @Test
    fun `Quando chamar o metodo getListContacts() com 2 contatos, deve retornar uma lista de 2 contatos`() {
        //Prepare
        mockListTwoContacts()
        //validate
        val list:List<Contact> = contactHelper.getContactList()
        assertEquals(2, list.size)
    }
    @Test
    fun `Quando chamar o método getContactList() sem contatos, deve retornar lista vazia`(){
        //prepare
        mockEmptyList()
        //validate
        val list:List<Contact> = contactHelper.getContactList()
        assertEquals(0, list.size)
    }

    private fun mockListTwoContacts(){
        contactHelper.setContactList(
            arrayListOf(
                Contact(
                    "Fulano de Tal",
                    "(00) 00000-0000",
                    "img.png"
                ),
                Contact(
                    "Fulano de Qual",
                    "(99) 99999-9999",
                    "img.png"
                )
            )
        )
    }
    private fun mockEmptyList(){
       contactHelper.setContactList(arrayListOf<Contact>())
    }

}