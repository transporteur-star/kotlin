package com.jack.recyclerviewapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jack.recyclerviewapp2.databinding.BookBinding

class BookAdapter (
    var bookList: List<Book>
        ) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

            inner class BookViewHolder(val binding: BookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.binding.txtBookTitle.text = bookList[position].bookTitle
        holder.binding.cbDone.isChecked = bookList[position].isChecked!!
        holder.binding.txtComment.text = bookList[position].comment
        holder.binding.txtDate.text = bookList[position].date
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}