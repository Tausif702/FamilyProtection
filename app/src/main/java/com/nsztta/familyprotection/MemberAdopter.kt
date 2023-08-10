package com.nsztta.familyprotection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdopter(private val listMember: List<MemberModel>) : RecyclerView.Adapter<MemberAdopter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdopter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.items_member,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: MemberAdopter.ViewHolder, position: Int) {

        val item = listMember[position]
        holder.user_name.text = item.name


    }

    override fun getItemCount(): Int {
        return listMember.size
    }
    class ViewHolder(private val item:View): RecyclerView.ViewHolder(item) {
        val image:ImageView=item.findViewById(R.id.user_img)
        val user_name:TextView=item.findViewById(R.id.user_name_one)
    }
}