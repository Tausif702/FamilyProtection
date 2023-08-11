package com.nsztta.familyprotection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InviteAdopter(private val listContacts: List<ContactModel>):RecyclerView.Adapter<InviteAdopter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteAdopter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.item_invite,parent,false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listContacts[position]
        holder.name.text = item.name
    }

    class ViewHolder(private val item:View):RecyclerView.ViewHolder(item) {
        val name = item.findViewById<TextView>(R.id.name)
    }
}