package com.example.testtaskvolodymyrkulyniak.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskvolodymyrkulyniak.R
import com.example.testtaskvolodymyrkulyniak.data.models.Result
import com.example.testtaskvolodymyrkulyniak.databinding.BookItemBinding

class BookAdapter(val onItemClicked: (String) -> Unit) :
    PagingDataAdapter<Result, BookAdapter.BookViewHolder>(DiffCallback) {

    inner class BookViewHolder(
        private var binding: BookItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind (data: Result) {

            binding.rank.text = binding.root.context.getString(R.string.rank, data.rank.toString())
            val book = data.book_details.first()
            binding.authorName.text = binding.root.context.getString(R.string.author, book.author)
            binding.bookName.text = book.title
            binding.publisherName.text = binding.root.context.getString(R.string.publisher, book.publisher)
            binding.bookDescription.text = book.description
            val button = binding.button

            button.apply {
                text = binding.root.context.getString(R.string.buy_button)
            setOnClickListener { onItemClicked(data.amazon_product_url)}}

            Glide.with(binding.imageView)
                .asDrawable()
                .placeholder(R.drawable.loadgif)
                .load(R.drawable.book_placeholder_2)
                .into(binding.imageView)
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.amazon_product_url == oldItem.amazon_product_url
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.amazon_product_url == newItem.amazon_product_url
            }
        }}


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val dataItem = getItem(position)!!
        holder.apply {
            bind(dataItem)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val  viewHolder = BookViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.layoutPosition
        }
        return viewHolder}
    }



