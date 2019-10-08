package com.task.instamobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.instamobile.databinding.RecipeCellBinding
import com.task.instamobile.model.Recipe

class RecipesListAdapter(
    private val data: List<Recipe>,
    private val myClickListener: HomeAdapter.MyClickListener
) :
    RecyclerView.Adapter<RecipesListAdapter.RecipesListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder {
        val binding = RecipeCellBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecipesListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        holder.bind(data[position], myClickListener)
    }

    class RecipesListViewHolder(val binding: RecipeCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe, myClickListener: HomeAdapter.MyClickListener) {
            binding.root.setOnClickListener { myClickListener.myOnClick(recipe) }
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }
}