package com.jack.recyclerviewapp2

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jack.recyclerviewapp2.databinding.BookBinding





class BookAdapter (var bookList: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {


    inner class BookViewHolder(val binding: BookBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }




    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.binding.txtBookTitle.text = bookList[position].bookTitle
        holder.binding.txtComment.text = bookList[position].comment
        holder.binding.txtDate.text = bookList[position].date

        //各bookアイテムのボタンに機能を追加
        holder.binding.btnDelete.setOnClickListener {


            //ダイアログ表示
            val edit = EditText(it.context)
            val dialog = AlertDialog.Builder(it.context)
            dialog.setTitle("Delete")
            dialog.setMessage("Do you really want to delete this record?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->

                    //タップしたbookアイテムのkeyを取得
                    val id: String = bookList[position].key.toString()

                    //データベースを参照し取得したkeyとマッチするノードを削除
                    val dbRef = FirebaseDatabase.getInstance().reference
                    dbRef.child("Books").child(id).removeValue().addOnSuccessListener {
                        Toast.makeText(dialog.context, "Deleted", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(dialog.context, "Something went wrong.", Toast.LENGTH_SHORT).show()
                    }

                })
                .setNegativeButton("No", null)
            dialog.show()



//
        }



    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}