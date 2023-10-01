package com.example.testtaskvolodymyrkulyniak.ui.adapters

import android.graphics.text.LineBreaker
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskvolodymyrkulyniak.R
import com.example.testtaskvolodymyrkulyniak.data.models.BookOptions
import com.example.testtaskvolodymyrkulyniak.databinding.BookOptionItemBinding

class BookOptionsAdapter(val onItemClicked: (String) -> Unit): RecyclerView.Adapter<BookOptionsAdapter.BookOptionViewHolder>() {

     val optionsList = BookOptions.listOfOptions
    class BookOptionViewHolder(val binding: BookOptionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookOptions: BookOptions){
            binding.textView.breakStrategy = LineBreaker.BREAK_STRATEGY_SIMPLE
            binding.textView.text = binding.root.context.getString(bookOptions.option)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookOptionViewHolder {
        val  viewHolder = BookOptionsAdapter.BookOptionViewHolder(
            BookOptionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.layoutPosition
            var query = optionsList[position].query
            onItemClicked(query)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return optionsList.size
    }

    override fun onBindViewHolder(holder: BookOptionViewHolder, position: Int) {
        val item = optionsList.get(position)
        holder.bind(item)
    }

}