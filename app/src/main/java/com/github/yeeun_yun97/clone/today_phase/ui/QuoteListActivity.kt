package com.github.yeeun_yun97.clone.today_phase.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.yeeun_yun97.clone.today_phase.QuoteAdapter
import com.github.yeeun_yun97.clone.today_phase.R
import com.github.yeeun_yun97.clone.today_phase.custom_context.QuoteBindingActivity
import com.github.yeeun_yun97.clone.today_phase.databinding.ActivityQuoteListBinding

class QuoteListActivity : QuoteBindingActivity<ActivityQuoteListBinding>() {

    //adapter
    private lateinit var quoteAdapter : QuoteAdapter

    override fun setBindingVariables() {

    }

    override fun getLayoutId(): Int = R.layout.activity_quote_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quoteAdapter= QuoteAdapter(::startEditActivity)

        val recyclerView = binding.QuoteListActivityQuoteRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = quoteAdapter;

        val createQuoteButton = binding.QuoteListActivityCreateQuoteButton
        createQuoteButton.setOnClickListener { startEditActivity() }
    }

    override fun onStart() {
        super.onStart()
        quoteAdapter.refreshDataSet()
    }

    private fun startEditActivity(){                    //create
        startActivity(Intent(this, QuoteEditActivity::class.java))
    }

    private fun startEditActivity(index: Int) {         //update
        startActivity(Intent(this, QuoteEditActivity::class.java).apply { putExtra("idx", index) })
    }


}