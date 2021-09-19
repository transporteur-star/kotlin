package com.jack.recyclerviewapp2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.jack.recyclerviewapp2.OnClick.ClickListener
import com.jack.recyclerviewapp2.OnClick.addOnItemClickListener
import com.jack.recyclerviewapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference


    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: BookAdapter
    private lateinit var bookList: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_RecyclerViewApp2)
        setContentView(binding.root)



        bookList = mutableListOf()



        adapter = BookAdapter(bookList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)



        binding.toolbar.btnBack.visibility = View.GONE

        getUserdata()
        addBook()

    }


    private fun addBook() {
        binding.toolbar.btnAddNewBook.setOnClickListener {

            val intent = Intent(this, RegisterBook::class.java)
            startActivity(intent)
        }
    }


    // リサイクルビューにbookアイテムをロード
    private fun getUserdata() {
        dbref = FirebaseDatabase.getInstance().getReference("Books")

        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                bookList = mutableListOf()

                for (userSnapshot in snapshot.children) {
                    val book = userSnapshot.getValue(Book::class.java)
                    bookList.add(book!!)
                }

                binding.recyclerView.adapter = BookAdapter(bookList)



            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }



}