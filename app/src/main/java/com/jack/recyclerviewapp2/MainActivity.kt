package com.jack.recyclerviewapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
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

        supportActionBar?.hide()

        bookList = mutableListOf()



        adapter = BookAdapter(bookList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        addBook()
        getUserdata()


    }

    // Load
    private fun getUserdata() {
        dbref = FirebaseDatabase.getInstance().getReference("Users")

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



    private fun addBook() {
        binding.btnAdd.setOnClickListener {
            val bookTitle = binding.txtEditBookTitle.text.toString()
            if (bookTitle.isEmpty()) {
                Toast.makeText(this, "Enter your book title", Toast.LENGTH_SHORT).show()
            } else {
                dbref = FirebaseDatabase.getInstance().reference
                val bookItem = Book(bookTitle, false)
                dbref.child("Users").push().setValue(bookItem)
//
            }
        }
    }
}