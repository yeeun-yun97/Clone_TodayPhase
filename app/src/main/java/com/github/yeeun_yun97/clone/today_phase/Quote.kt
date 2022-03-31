package com.github.yeeun_yun97.clone.today_phase

import android.content.SharedPreferences

data class Quote(var idx: Int, var text: String, var from: String = "") {
    companion object {
        fun saveToPreference(
            pref: SharedPreferences,
            idx: Int,
            text: String,
            from: String = ""
        ): Quote {
            val editor = pref.edit()
            editor.putString("$idx.text", text)
            editor.putString("$idx.from", from.trim())

            editor.apply()
            return Quote(idx, text, from)
        }

        fun getQuotesFromPreference(pref: SharedPreferences):MutableList<Quote>{
            val quotes = mutableListOf<Quote>()
            for(i in 0 until 20){
                if(pref.contains("$i.text")){
                    val quoteText= pref.getString("$i.text","")!!
                    val quoteFrom=pref.getString("$i.from","")!!
                    if(quoteText.isNotBlank()) quotes.add(Quote(i,quoteText,quoteFrom))
                }
            }
            return quotes
        }

        fun getQuoteFromPreference(pref: SharedPreferences, i:Int):Quote?{
                if(pref.contains("$i.text")){
                    val quoteText= pref.getString("$i.text","")!!
                    val quoteFrom=pref.getString("$i.from","")!!
                    if(quoteText.isNotBlank()) return Quote(i,quoteText,quoteFrom)
                }
            return null
        }


        fun removeQuoteFromPreference(pref: SharedPreferences, idx: Int) {
            val editor = pref.edit()

            editor.remove("$idx.text")
            editor.remove("$idx.from")
            editor.apply()
        }
    }
}