package com.everis.listadecontatos.feature.contato

import android.os.Bundle
import android.view.View
import com.everis.listadecontatos.R
import com.everis.listadecontatos.application.ContatoApplication
import com.everis.listadecontatos.bases.BaseActivity
import com.everis.listadecontatos.feature.listacontatos.model.ContatosVO
import com.everis.listadecontatos.helper.HelperDB
import kotlinx.android.synthetic.main.activity_contato.*
import kotlinx.android.synthetic.main.activity_contato.toolBar
import java.lang.Exception

class ContatoActivity : BaseActivity() {

    private var idContact: Int = -1
    private lateinit var database: HelperDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

        ContatoApplication.instance.helperDB.also { database = it }

        setupToolBar(toolBar, "Contato", true)
        setupContato()
        btnSalvarConato.setOnClickListener { onClickSalvarContato() }

    }

    private fun setupContato() {
        idContact = intent.getIntExtra("index", -1)
        if (idContact == -1) {
            btnExcluirContato.visibility = View.GONE
            return
        }
        progress.visibility = View.VISIBLE
        Thread {
            //Thread.sleep(1500)
            try {
                val lista: List<ContatosVO> = database.searchContacts("$idContact", true)
                val contact: ContatosVO = lista.getOrNull(0) ?: return@Thread

                runOnUiThread {
                    etNome.setText(contact.nome)
                    etTelefone.setText(contact.telefone)
                    progress.visibility = View.GONE
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }.start()
    }

    private fun onClickSalvarContato() {
        val nome = etNome.text.toString()
        val telefone = etTelefone.text.toString()
        val contato = ContatosVO(
            idContact,
            nome,
            telefone
        )
        progress.visibility = View.VISIBLE
        Thread {
            //Thread.sleep(1500)
            try {

                if (idContact == -1) {
                    //ContatoSingleton.lista.add(contato)
                    database.saveContact(contato)
                } else {
                    // ContatoSingleton.lista.set(index,contato) // update
                    database.updateContact(contato)
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            runOnUiThread {
                finish()
            }
        }.start()
    }

    fun onClickExcluirContato(view: View) {
        if (idContact > -1) {
            //ContatoSingleton.lista.removeAt(index)
            progress.visibility = View.VISIBLE
            //Thread.sleep(1500)
            Thread {
                try {

                    database.deleteContact(idContact)

                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                runOnUiThread { finish() }
            }.start()

        }
    }
}
