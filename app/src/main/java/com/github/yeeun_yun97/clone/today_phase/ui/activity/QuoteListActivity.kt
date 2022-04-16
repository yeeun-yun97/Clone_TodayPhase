package com.github.yeeun_yun97.clone.today_phase.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.yeeun_yun97.clone.today_phase.ui.adapter.QuoteAdapter
import com.github.yeeun_yun97.clone.today_phase.R
import com.github.yeeun_yun97.clone.today_phase.databinding.ActivityQuoteListBinding

class QuoteListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityQuoteListBinding.inflate(layoutInflater) }

    //adapter
    private lateinit var quoteAdapter : QuoteAdapter

    private fun getLayoutId(): Int = R.layout.activity_quote_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quoteAdapter= QuoteAdapter(::startEditActivity)

        val recyclerView = binding.QuoteListActivityQuoteRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = quoteAdapter;

        val createQuoteButton = binding.QuoteListActivityCreateQuoteButton
        createQuoteButton.setOnClickListener { startEditActivity() }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        quoteAdapter.refreshDataSet()
    }

    private fun startEditActivity(index: Int = -1) {         //update
        startActivity(Intent(this, QuoteEditActivity::class.java).apply { putExtra("idx", index) })
    }
}