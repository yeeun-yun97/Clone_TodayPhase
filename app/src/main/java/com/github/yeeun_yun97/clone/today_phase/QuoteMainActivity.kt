package com.github.yeeun_yun97.clone.today_phase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class QuoteMainActivity : AppCompatActivity() {

    //view comps
    private val quoteTextView: TextView by lazy {findViewById(R.id.QuoteMainActivity_quoteTextView)}
    private val quoteAuthorTextView: TextView by lazy {findViewById(R.id.QuoteMainActivity_quoteAuthorTextView)}
    private val viewQuoteButton : Button by lazy {findViewById(R.id.QuoteMainActivity_viewQuoteButton)}
    private val editQuoteTextView : TextView by lazy {findViewById(R.id.QuoteMainActivity_editQuoteTextView)}

    //sharedPreference
    private val pref: SharedPreferences by lazy{getSharedPreferences("quotes", Context.MODE_PRIVATE)}
    //model
    private lateinit var quotes: MutableList<Quote>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_main)
        initQuotes()

        quotes = Quote.getQuotesFromPreference(pref)
        this.randomQuote()

        viewQuoteButton.setOnClickListener { randomQuote() }
        editQuoteTextView.setOnClickListener { startActivity(Intent(this,QuoteListActivity::class.java)) }
    }

    fun initQuotes(){
        val initialized = pref.getBoolean("init",false)
        if(!initialized){
            Quote.saveToPreference(pref,0,"창조적인 삶을 살려면 내가 틀릴지도 모른다는 공포를 버려야 한다.",)
            Quote.saveToPreference(pref,1, "성공한 사람이 되려고 노력하기보다 가치있는 사람이 되려고 노력하라.", "알버트 아인슈타인")
            Quote.saveToPreference(pref,2, "괴로운 시련처럼 보이는 것이 뜻밖의 좋은 일일 때가 많다.", "오스카 와일드")
            Quote.saveToPreference(pref,3, "추구할 수 있는 용기가 있다면 우리의 모든 꿈은 이뤄질 수 있다.", "월트 디즈니")
            Quote.saveToPreference(pref,4,"실패에서부터 성공을 만들어 내라. 좌절과 실패는 성공으로 가는 가장 확실한 디딤돌이다.","데일 카네기")

            val editor = pref.edit()
            editor.putBoolean("init",true)
            editor.apply()
        }
    }

    fun randomQuote(){
        var randomIndex= Random().nextInt(quotes.size)
        var randomQuote= quotes[randomIndex]

        if(quotes.isEmpty()){
            quoteTextView.setText("아직 명언 데이터가 없습니다.")
            quoteAuthorTextView.setText("-")
        }else {
            quoteTextView.setText(randomQuote.text)
            quoteAuthorTextView.setText(randomQuote.from)
        }
    }

}