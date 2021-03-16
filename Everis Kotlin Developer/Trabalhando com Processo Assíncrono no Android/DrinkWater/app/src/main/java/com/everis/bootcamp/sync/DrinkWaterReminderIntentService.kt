package com.everis.bootcamp.sync

import android.app.IntentService
import android.content.Intent
import android.content.IntentSender



class DrinkWaterReminderIntentService : IntentService("DrinkWaterReminderIntentService"){

    override fun onHandleIntent(intent: Intent?) {
        val action:String? = intent?.action
        DrinkWaterReminderTask.executeTask(this, action)
    }
}