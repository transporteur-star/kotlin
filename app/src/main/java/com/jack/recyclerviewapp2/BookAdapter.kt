package com.jack.recyclerviewapp2

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jack.recyclerviewapp2.databinding.BookBinding

class BookAdapter (var bookList: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {



    inner class BookViewHolder(val binding: BookBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cbDone.setOnClickListener {
                Log.d("demo", "Test")
                //チェックしたアイテムの削除??
//                val dbRef = FirebaseDatabase.getInstance().reference
//                dbRef.child("Books/${bookId}").removeValue().addOnSuccessListener {
//
//                }.addOnFailureListener {
//
//                }
            }
        }
    }


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