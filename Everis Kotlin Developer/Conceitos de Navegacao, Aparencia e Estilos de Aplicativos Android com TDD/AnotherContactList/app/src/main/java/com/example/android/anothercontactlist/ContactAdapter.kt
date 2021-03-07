package com.example.android.anothercontactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val listener: ClickItemContactListener) :
    RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.concatct_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listToUpdate: List<Contact>) {
        list.clear()
        list.addAll(listToUpdate)
        notifyDataSetChanged()
    }

    inner class ContactAdapterViewHolder(itemView: View, val list:List<Contact>, val listener:ClickItemContactListener) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        val tvPhone: TextView = itemView.findViewById<TextView>(R.id.tv_phone)
        val ivPhotograph: ImageView = itemView.findViewById<ImageView>(R.id.iv_photograph)
        init{
            itemView.setOnClickListener{
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contact) {
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}