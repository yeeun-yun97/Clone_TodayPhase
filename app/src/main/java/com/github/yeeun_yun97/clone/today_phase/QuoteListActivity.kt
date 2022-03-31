package com.github.yeeun_yun97.clone.today_phase

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteListActivity : AppCompatActivity() {

    //view comps
    private val quoteRecyclerView : RecyclerView by lazy { findViewById(R.id.QuoteListActivity_quoteRecyclerView) }

    //sharedPreference
    private val pref: SharedPreferences by lazy{getSharedPreferences("quotes", Context.MODE_PRIVATE)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        quoteRecyclerView.setHasFixedSize(false)
        quoteRecyclerView.layoutManager=LinearLayoutManager(this)
        quoteRecyclerView.adapter=QuoteAdapter(pref);
    }
}