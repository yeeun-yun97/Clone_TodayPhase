package com.github.yeeun_yun97.clone.today_phase

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class QuoteEditActivity : AppCompatActivity() {
    //sharedPreference
    private val pref: SharedPreferences by lazy {
        getSharedPreferences(
            "quotes",
            Context.MODE_PRIVATE
        )
    }

    private val quoteEditText: EditText by lazy { findViewById(R.id.QuoteEditActivity_QuoteEdtiText) }
    private val quoteAuthorEditText: EditText by lazy { findViewById(R.id.QuoteEditActivity_QuoteAuthorEditText) }
    private val saveButton: Button by lazy { findViewById(R.id.QuoteEditActivity_saveButton) }
    private val cancelButton: Button by lazy { findViewById(R.id.QuoteEditActivity_cancelButton) }

    private var quote: Quote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_edit)
        val intent = intent
        val index = intent.getIntExtra("idx", -1)
        if (index != -1) {
            quote = Quote.getQuoteFromPreference(pref, index)
            quoteEditText.setText(quote?.text)
            quoteAuthorEditText.setText(quote?.from)
        }

        saveButton.setOnClickListener {
            Quote.saveToPreference(pref,quote?.idx,quoteEditText.text.toString(),quoteAuthorEditText.text.toString())
            onBackPressed()
        }
        cancelButton.setOnClickListener {
            onBackPressed()
        }

    }


}