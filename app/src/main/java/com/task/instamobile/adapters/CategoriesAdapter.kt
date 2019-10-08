package com.task.instamobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.instamobile.databinding.CategoriesCellBinding
import com.task.instamobile.model.Category

class CategoriesAdapter(
    private val dataSet: List<Category>,
    private val myClickListener: MyClickListener
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            CategoriesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(dataSet[position], myClickListener)
    }

    class CategoriesViewHolder(private val binding: CategoriesCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category, myClickListener: MyClickListener) {
            binding.root.setOnClickListener { myClickListener.myOnClick(category) }
            binding.category = category
            binding.executePendingBindings()
        }
    }

    interface MyClickListener {
        fun myOnClick(category: Category)
    }
}