package com.example.android.contatosbootcamp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView:TextView = itemView.findViewById<TextView>(R.id.contact_name)
    val phoneNumberTextView:TextView = itemView.findViewById(R.id.contact_phone_number)
}
