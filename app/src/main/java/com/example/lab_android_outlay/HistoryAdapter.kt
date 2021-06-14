package com.example.lab_android_outlay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_android_outlay.databinding.ItemCategoryBinding
import com.example.lab_android_outlay.databinding.ItemHistoryBinding

class HistoryAdapter(val data: List<HistoryUserData>) : RecyclerView.Adapter<HistoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryVH(view)
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

}

class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {

    val ui: ItemHistoryBinding = ItemHistoryBinding.bind(view)

    fun bind(historyData: HistoryUserData) {

        ui.amount.text = historyData.amount.toString()
        ui.icon.setImageResource(historyData.icon)
        ui.textView.setText(historyData.text)
    }
}

open class CategoryAdapter(val data: List<Category>) : RecyclerView.Adapter<CategoryVH>() {

    companion object {
        var index = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryVH(view)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.bind(data[position])
        if(index != position){
            holder.itemView.setBackgroundResource(R.color.blackTheme)
        } else {
            holder.itemView.setBackgroundResource(R.color.clickOnBlackTheme)
        }
        holder.itemView.setOnClickListener {
            index = position
            notifyDataSetChanged()
        }

    }

    override fun getItemCount() = data.size

}

class CategoryVH(view: View) : RecyclerView.ViewHolder(view) {

    val ui: ItemCategoryBinding = ItemCategoryBinding.bind(view)

    fun bind(category: Category) {
        ui.icon.setImageResource(category.icon)
        ui.textView.setText(category.text)
    }
}