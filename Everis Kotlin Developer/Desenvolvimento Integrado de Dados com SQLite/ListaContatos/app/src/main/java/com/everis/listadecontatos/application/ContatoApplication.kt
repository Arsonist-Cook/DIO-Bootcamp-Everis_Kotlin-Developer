package com.everis.listadecontatos.application

import android.app.Application
import com.everis.listadecontatos.helper.HelperDB

class ContatoApplication: Application() {
    companion object{
        lateinit var instance:ContatoApplication
    }
    lateinit var helperDB:HelperDB
        private set

    override fun onCreate() {
        super.onCreate()
        helperDB = HelperDB(this)
        instance = this
    }
}