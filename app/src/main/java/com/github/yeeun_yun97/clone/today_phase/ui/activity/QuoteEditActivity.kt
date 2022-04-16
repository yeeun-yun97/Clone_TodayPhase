package com.github.yeeun_yun97.clone.today_phase.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.github.yeeun_yun97.clone.today_phase.R
import com.github.yeeun_yun97.clone.today_phase.databinding.ActivityQuoteEditBinding
import com.github.yeeun_yun97.clone.today_phase.data.model.Quote
import com.github.yeeun_yun97.clone.today_phase.ui.activity.basic.QuoteDataBindingActivity

class QuoteEditActivity : QuoteDataBindingActivity<ActivityQuoteEditBinding>() {
    private lateinit var quote: Quote

    override fun getLayoutId(): Int = R.layout.activity_quote_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingVariables()
    }

    fun setBindingVariables() {
        val index = intent.getIntExtra("idx", -1)
        quote =
            if (index == -1) Quote(index, "", "")       //mode create new
            else Quote.readQuoteByIndex(index)                   //mode update

        binding.quote=quote
        binding.listener = View.OnClickListener {
            when (it.id) {
                R.id.QuoteEditActivity_saveButton -> save()
                R.id.QuoteEditActivity_cancelButton -> cancel()
            }
        }
    }

    private fun save() {
        if (quote.idx == -1) {              //create new
            Quote.createQuote(
                quote.text,
                quote.from
            )
        } else {                            //update
            Quote.updateQuote(
                quote.idx,
                quote.text,
                quote.from
            )
        }
        onBackPressed()
    }

    private fun cancel() {
        onBackPressed()
    }

}