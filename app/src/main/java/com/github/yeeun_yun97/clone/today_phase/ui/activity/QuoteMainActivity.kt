package com.github.yeeun_yun97.clone.today_phase.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.github.yeeun_yun97.clone.today_phase.R
import com.github.yeeun_yun97.clone.today_phase.databinding.ActivityQuoteMainBinding
import com.github.yeeun_yun97.clone.today_phase.data.model.Quote
import com.github.yeeun_yun97.clone.today_phase.ui.activity.basic.QuoteDataBindingActivity

class QuoteMainActivity : QuoteDataBindingActivity<ActivityQuoteMainBinding>() {

    //model
    private lateinit var quote: Quote

    override fun getLayoutId(): Int = R.layout.activity_quote_main

    private fun setBindingVariables() {
        binding.listener = View.OnClickListener {
            when (it.id) {
                R.id.QuoteMainActivity_shareButton -> shareQuote(it)
                R.id.QuoteMainActivity_viewQuoteButton -> startListActivity()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingVariables()
        refreshBindingVariables()
    }

    override fun onRestart() {
        super.onRestart()
        refreshBindingVariables()
    }

    private fun refreshBindingVariables() {
        quote = Quote.readRandomQuote()
        binding.quote = quote
    }

    fun startListActivity() {
        startActivity(Intent(this, QuoteListActivity::class.java))
    }

    fun shareQuote(view: View) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TITLE, "힘이 되는 명언")
            putExtra(Intent.EXTRA_SUBJECT, "힘이 되는 명언")
            putExtra(Intent.EXTRA_TEXT, "${quote.text}\n출처 : ${quote.from.ifEmpty { "미상" }}")
            type = "text/plain"
        }
        val chooser = Intent.createChooser(intent, "명언 공유")
        view.context.startActivity(chooser)
    }
}