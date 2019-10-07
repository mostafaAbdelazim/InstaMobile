package com.task.instamobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.instamobile.databinding.HomeCellBinding
import com.task.instamobile.model.Recipe

class HomeAdapter(private val data: List<Recipe>, private val myClickListener: MyClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position], myClickListener)
    }

    class HomeViewHolder(private val binding: HomeCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe, myClickListener: MyClickListener) {
            binding.root.setOnClickListener { v -> myClickListener.myOnClick(recipe) }
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }

    interface MyClickListener {
        fun myOnClick(recipe: Recipe)
    }
}