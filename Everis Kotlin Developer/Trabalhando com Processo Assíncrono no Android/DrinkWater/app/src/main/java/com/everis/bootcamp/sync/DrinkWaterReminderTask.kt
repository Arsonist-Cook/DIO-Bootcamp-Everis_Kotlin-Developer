package com.everis.bootcamp.sync

import android.content.Context
import com.everis.bootcamp.utils.PreferencesUtils


class DrinkWaterReminderTask {

    companion object {
        const val ACTION_INCREMENT_WATER_COUNT: String = "action-increment-water-count"
        private fun incrementWaterCount(context: Context) {
            PreferencesUtils.incrementWaterCount(context)
        }

        fun executeTask(context: Context, action: String?) {
            if (action == ACTION_INCREMENT_WATER_COUNT) {
                incrementWaterCount(context)
            }
        }

    }
}


