package com.example.github_search_app.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github_search_app.R
import com.example.github_search_app.databinding.RvFragmentBinding
import com.example.github_search_app.model.Item
import com.squareup.picasso.Picasso

class Main_RecyclerView_Adapter: RecyclerView.Adapter<Main_RecyclerView_Adapter.mainHolder>() {

    var items = ArrayList<Item>()

    fun setUpdatedData(items : ArrayList<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    class mainHolder(view: View): RecyclerView.ViewHolder(view) {

        var binding = RvFragmentBinding.bind(view)
        fun bind (data: Item) = with(binding) {
            RepoName.text = data.name
            OwnerName.text = data.owner.login

            val url  = data.owner.avatar_url

            Picasso.get()
                .load(url)
                .into(ImageV)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_fragment, parent, false)

        return mainHolder(view)
    }

    override fun onBindViewHolder(holder: mainHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}