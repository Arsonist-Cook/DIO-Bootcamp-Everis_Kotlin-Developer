package com.example.android.anothercontactlist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.anothercontactlist.ContactDetail.Companion.EXTRA_CONTACT
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), ClickItemContactListener {
    private val rvList: RecyclerView by lazy { findViewById(R.id.rv_list) }
    private val adapter: ContactAdapter = ContactAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)
        initDrawer()
        fetchContactList()
        bindViews()
        //updateList()
    }

    private fun initDrawer() {
        val drawerLayout: DrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun fetchContactList() {
        val list: List<Contact> = arrayListOf<Contact>(
            Contact(
                "Igor Ferrani",
                "(00) 00000-0000",
                "mock.png"
            ),
            Contact(
                "José Almeida",
                "(11) 11111-1111",
                "mock.png"
            ),
            Contact(
                "Igor Ferrani",
                "(00) 00000-0000",
                "mock.png"
            )
        )
        getInstanceSharedPreferences()?.edit {
            val json:String = Gson().toJson(list)
            putString(R.string.contact_list_key.toString(), json)
            commit()
        }
    }
    private fun getInstanceSharedPreferences(): SharedPreferences? {
        return getSharedPreferences("${packageName}.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun bindViews() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getContactList():List<Contact>{
        val stringList:String? = getInstanceSharedPreferences()?.getString(R.string.contact_list_key.toString(),"[]")
        val turnsType = object:TypeToken<List<Contact>>(){}.type
        return Gson().fromJson(stringList, turnsType)
    }

    private fun updateList() {
        val list:List<Contact> = getContactList()
        adapter.updateList(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                showToast("Exibindo Item de Menu 1")
                true
            }
            R.id.item_menu_2 -> {
                showToast("Exibindo Item de Menu 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}