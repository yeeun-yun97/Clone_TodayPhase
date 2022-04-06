package com.github.yeeun_yun97.clone.today_phase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.github.yeeun_yun97.clone.today_phase.databinding.ActivityQuoteMainBinding
import java.util.*

class QuoteMainActivity : AppCompatActivity() {
    //data binding
    private val binding: ActivityQuoteMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_quote_main)
    }

    //model
    private lateinit var quote: Quote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //이거 activity 말고 application에서 하게 나중에 수정하는 게 좋을 것 같음.
        Quote.pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)

        initQuotes()
        quote = Quote.readRandomQuote()
        binding.quote = quote
    }

    override fun onRestart() {
        super.onRestart()
        quote = Quote.readRandomQuote()
        binding.quote = quote
    }

    private fun initQuotes() =Quote.initQuotes()


    fun startListActivity(view: View) {
        startActivity(
            Intent(
                this,
                QuoteListActivity::class.java
            )
        )
    }

    fun shareQuote(view: View) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TITLE, "힘이 되는 명언")
            putExtra(Intent.EXTRA_SUBJECT, "힘이 되는 명언")
            putExtra(Intent.EXTRA_TEXT, "${quote.text}\n출처 : ${quote.from}")
            type = "text/plain"
        }
        val chooser = Intent.createChooser(intent, "명언 공유")
        view.context.startActivity(chooser)
    }

}