package com.jack.recyclerviewapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.jack.recyclerviewapp2.databinding.ActivityRegisterBookBinding
import java.text.SimpleDateFormat
import java.util.*

class RegisterBook : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBookBinding

    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBookBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_RecyclerViewApp2)
        setContentView(binding.root)




        binding.toolbar.btnAddNewBook.visibility = View.GONE

        setDate()
        addBook()
        back()
    }

    private fun setDate() {
        val today = SimpleDateFormat("yyyy/MM/dd")
        val currentDate: String = today.format(Date())

        binding.inputDate.setText(currentDate)
    }

    private fun back() {
        binding.toolbar.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun addBook() {
        binding.btnSave.setOnClickListener {
            val date = binding.inputDate.text.toString()
            val title = binding.inputTitle.text.toString()
            val comment = binding.inputComment.text.toString()


            //タイトル名は必須
            if (title.isEmpty()) {
                Toast.makeText(this, "Please Enter the title of the Book.", Toast.LENGTH_SHORT).show()
            } else {
                dbref = FirebaseDatabase.getInstance().reference

                // Books/　のパスにノードを新しく作成、名前の指定がないため、IDが自動的に作成される？
                val bookRef = dbref.child("Books").push()

                //　Book オブジェクトを作成、タイトル、日付、コメント、自動的割り当てられたIDをKEYのフィールドに追加
                val bookItem = Book(title, date, comment, bookRef.key)

                //　Book オブジェクトを作成したノードにアサイン
                bookRef.setValue(bookItem)
                finish()
            }
        }




    }


}