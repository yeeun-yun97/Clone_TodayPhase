package com.github.yeeun_yun97.clone.today_phase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class QuoteListActivity : AppCompatActivity() {

    //view comps
    private val quoteRecyclerView: RecyclerView by lazy { findViewById(R.id.QuoteListActivity_quoteRecyclerView) }
    private val createQuoteButton: FloatingActionButton by lazy { findViewById(R.id.QuoteListActivity_createQuoteButton) }

    //adapter
    private val quoteAdapter by lazy {QuoteAdapter(::startEditActivity)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        quoteRecyclerView.setHasFixedSize(false)
        quoteRecyclerView.layoutManager = LinearLayoutManager(this)
        quoteRecyclerView.adapter = quoteAdapter;

        createQuoteButton.setOnClickListener {
            var intent= Intent(this,QuoteEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        quoteAdapter.update()
    }

    fun startEditActivity(index: Int){
        startActivity(Intent(this,QuoteEditActivity::class.java).apply { putExtra("idx",index) })
    }
}