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




            if (title.isEmpty()) {
                Toast.makeText(this, "Please Enter Book title.", Toast.LENGTH_SHORT).show()
            } else {
                dbref = FirebaseDatabase.getInstance().reference
                val bookItem = Book(title, date, comment, false)
                dbref.child("Books").push().setValue(bookItem)
                finish()
            }
        }




    }


}