package com.example.userprofile.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofile.R

class RepositoriesRecyclerAdapter(val viewModel: MainViewModel, val arrayList: ArrayList<Repository>,
                                  val context: Context): RecyclerView.Adapter<RepositoriesRecyclerAdapter.RepositoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesRecyclerAdapter.RepositoryViewHolder {

        val root = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

        return RepositoryViewHolder(root)
    }

    override fun onBindViewHolder(holder: RepositoriesRecyclerAdapter.RepositoryViewHolder, position: Int) {
        val txt = arrayList[position]
        holder.titletxt.text = txt.title
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
            Toast.makeText(context,"List is empty! Please check your internet connection.",Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    inner  class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titletxt: TextView = itemView.findViewById(R.id.titletxtt)
        }
}