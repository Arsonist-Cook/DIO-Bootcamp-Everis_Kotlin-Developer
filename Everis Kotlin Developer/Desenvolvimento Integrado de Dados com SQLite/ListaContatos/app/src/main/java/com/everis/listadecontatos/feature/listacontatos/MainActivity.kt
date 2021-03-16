package com.everis.listadecontatos.feature.listacontatos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.listadecontatos.R
import com.everis.listadecontatos.application.ContatoApplication
import com.everis.listadecontatos.bases.BaseActivity
import com.everis.listadecontatos.feature.contato.ContatoActivity
import com.everis.listadecontatos.feature.listacontatos.adapter.ContatoAdapter
import com.everis.listadecontatos.feature.listacontatos.model.ContatosVO
import com.everis.listadecontatos.helper.HelperDB
import com.everis.listadecontatos.singleton.ContatoSingleton
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : BaseActivity() {

    private var adapter: ContatoAdapter? = null
    private lateinit var database:HelperDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ContatoApplication.instance.helperDB.also { database = it }
        //geraListaDeContatos() //Mock Lista de contatos
        setupToolBar(toolBar, "Lista de contatos", false)
        setupRecyclerView()
        setupOnClicks()
    }

    private fun setupOnClicks() {
        fab.setOnClickListener { onClickAdd() }
        ivBuscar.setOnClickListener { onClickBuscar(true) }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContatoAdapter(this, mutableListOf<ContatosVO>()) { onClickItemRecyclerView(it) }
        recyclerView.adapter = adapter
    }

    /*
    private fun geraListaDeContatos() {
        ContatoSingleton.lista.add(ContatosVO(1, "Fulano", "(00) 9900-0001"))
        ContatoSingleton.lista.add(ContatosVO(2, "Ciclano", "(00) 9900-0002"))
        ContatoSingleton.lista.add(ContatosVO(3, "Vinicius", "(00) 9900-0001"))
    }
     */

    override fun onResume() {
        super.onResume()
        //adapter?.notifyDataSetChanged()
        onClickBuscar()
    }

    private fun onClickAdd() {
        val intent = Intent(this, ContatoActivity::class.java)
        startActivity(intent)
    }

    private fun onClickItemRecyclerView(index: Int) {
        val intent = Intent(this, ContatoActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }

    private fun onClickBuscar(showToast:Boolean = false) {
        val busca = etBuscar.text.toString()
        var listaFiltrada: List<ContatosVO> = mutableListOf<ContatosVO>()
        progress.visibility = View.VISIBLE
        Thread {
            //Thread.sleep(1500)
            try {

                listaFiltrada = database.searchContacts(busca)

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            runOnUiThread {
                adapter = ContatoAdapter(this, listaFiltrada) { onClickItemRecyclerView(it) }
                progress.visibility = View.GONE
                recyclerView.adapter = adapter
                if(showToast) {
                    Toast.makeText(this, "Buscando por $busca", Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }

}
