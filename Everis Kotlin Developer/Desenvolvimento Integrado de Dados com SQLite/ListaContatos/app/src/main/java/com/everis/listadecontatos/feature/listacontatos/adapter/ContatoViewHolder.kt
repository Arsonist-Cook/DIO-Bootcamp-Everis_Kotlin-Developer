package com.everis.listadecontatos.feature.listacontatos.adapter

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.everis.listadecontatos.R
import kotlinx.android.synthetic.main.item_contato.view.*

class ContatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var name:TextView = itemView.findViewById(R.id.tvNome)
    var telephone:TextView = itemView.findViewById(R.id.tvTelefone)
}