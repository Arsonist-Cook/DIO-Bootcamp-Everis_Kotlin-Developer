package com.everis.bootcamp.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val textViewData: TextView by lazy {
        findViewById<TextView>(R.id.textview_data)
    }
    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.progress_indicator)
    }
    private val buttonLoadData: Button by lazy {
        findViewById<Button>(R.id.button_load_data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLoadData.setOnClickListener {
            launchAstrosTask()
        }
    }


    fun showData(list: List<AstrosPeople>?) {
        val stringBuilder: StringBuilder = StringBuilder()
        if (list != null) {
            for (astroPeople in list) {
                stringBuilder.appendLine("${astroPeople.name} - ${astroPeople.craft}")
            }
        }
        textViewData.text = stringBuilder.toString()
    }

    fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoadingIndicator() {
        progressBar.visibility = View.GONE
    }


    fun launchAstrosTask() {
        val task: TaskAstros = TaskAstros()
        task.execute()
    }


    inner class TaskAstros : AsyncTask<Void, Int, List<AstrosPeople>>() {
        private val repository: AstrosRepository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        override fun doInBackground(vararg params: Void?): List<AstrosPeople>? {
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstrosPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }

    }
}
