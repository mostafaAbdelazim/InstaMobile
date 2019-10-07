package com.task.instamobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.instamobile.R
import com.task.instamobile.databinding.RecipeCellBindingImpl
import com.task.instamobile.model.Recipe

class RecipesListAdapter(
    private val data: List<Recipe>,
    private val myClickListener: HomeAdapter.MyClickListener
) :
    RecyclerView.Adapter<RecipesListAdapter.RecipesListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder {
        val binding: RecipeCellBindingImpl = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recipe_cell, parent, false
        )
        return RecipesListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        holder.bind(data[position], myClickListener)
    }

    class RecipesListViewHolder(val binding: RecipeCellBindingImpl) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe, myClickListener: HomeAdapter.MyClickListener) {
            binding.root.setOnClickListener { myClickListener.myOnClick(recipe) }
            binding.recipe = recipe
            binding.executePendingBindings()
        }
    }
}