package com.example.android.anothercontactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import org.w3c.dom.Text

class ContactDetail : AppCompatActivity() {
    private var contact:Contact? = null
    companion object{
        const val EXTRA_CONTACT:String = "EXTRA_CONTACT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        initToolbar()
        getExtra()
        bindViews()
    }

    private fun bindViews() {
        findViewById<TextView>(R.id.tv_name).text = contact?.name
        findViewById<TextView>(R.id.tv_phone).text = contact?.phone
        //findViewById<ImageView>().
    }

    private fun initToolbar(){
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getExtra(){
        contact = intent.getParcelableExtra<Contact>(EXTRA_CONTACT)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}