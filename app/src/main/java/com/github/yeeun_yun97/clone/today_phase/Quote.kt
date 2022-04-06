package com.github.yeeun_yun97.clone.today_phase

import android.content.SharedPreferences
import android.util.Log
import java.util.*
import kotlin.collections.HashSet

data class Quote(var idx: Int, var text: String, var from: String = "") {
    companion object {
        fun createQuote(
            pref: SharedPreferences,
            text: String,
            from: String = ""
        ) {
            if (text.isNotEmpty()) {
                val index = pref.getInt("next", 0)

                val editor = pref.edit()
                editor.putString("$index.text", text)
                editor.putString("$index.from", from)

                val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
                idxSet.add(index.toString())
                editor.putStringSet("indices", idxSet)

                editor.putInt("next", index + 1)

                editor.apply()
            } else {
                //비어있는 격언을 저장하려고 하는 상황. 나중에 UI 추가하면 좋겠다.
                Log.d("alert", "quoteIsEmpty")
            }
        }

        fun readAllQuotes(pref: SharedPreferences): MutableList<Quote> {
            val idxSet = pref.getStringSet("indices", HashSet<String>())
            val quotes = mutableListOf<Quote>()
            if (idxSet != null) {
                for (idx in idxSet) {
                    val quoteText = pref.getString("$idx.text", "")!!
                    val quoteFrom = pref.getString("$idx.from", "")!!
                    if (quoteText.isNotBlank()) quotes.add(
                        0,
                        Quote(
                            idx.toInt(),
                            quoteText,
                            quoteFrom
                        )
                    )

                }
            }
            return quotes
        }


        fun readQuoteByIndex(pref: SharedPreferences, index: Int): Quote {
            val quoteText = pref.getString("$index.text", "")!!
            val quoteFrom = pref.getString("$index.from", "")!!
            return Quote(index, quoteText, quoteFrom)
        }

        fun readRandomQuote(pref: SharedPreferences): Quote {
            val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
            if(idxSet.size>0) {
                return readQuoteByIndex(pref, idxSet.random().toInt())
            }else{
                return Quote(-1, "아직 명언 데이터가 없습니다.", "")
            }
        }

        fun updateQuote(
            pref: SharedPreferences,
            idx: Int,
            text: String,
            from: String = ""
        ) {
            if (text.isNotEmpty()) {
                val editor = pref.edit()
                editor.putString("$idx.text", text)
                editor.putString("$idx.from", from.trim())
                editor.apply()
            } else {
                //비어있는 격언을 저장하려고 하는 상황. 나중에 UI 추가하면 좋겠다.
                Log.d("alert", "quoteIsEmpty")
            }
        }

        fun deleteQuoteByIndex(pref: SharedPreferences, idx: Int) {
            val editor = pref.edit()
            editor.remove("$idx.text")
            editor.remove("$idx.from")
            val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
            idxSet.remove("$idx")
            editor.putStringSet("indices", idxSet)
            editor.apply()
        }

    }
}