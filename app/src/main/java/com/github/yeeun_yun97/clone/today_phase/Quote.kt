package com.github.yeeun_yun97.clone.today_phase

import android.content.SharedPreferences
import android.util.ArraySet
import android.util.Log

data class Quote(var idx: Int, var text: String, var from: String = "") {
    companion object {

        fun editQuoteToPreference(
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
                Log.d("alert", "quoteIsEmpty")
            }
        }

        fun addQuoteToPreference(
            pref: SharedPreferences,
            text: String,
            from: String = ""
        ) {
            if (text.isNotEmpty()) {
                val length = pref.getInt("next", 0)
                val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
                val editor = pref.edit()

                editor.putString("$length.text", text)
                editor.putString("$length.from", from)
                idxSet.add(length.toString())
                editor.putStringSet("indices", idxSet)
                editor.putInt("next", length + 1)
                editor.apply()
            } else {
                Log.d("alert", "quoteIsEmpty")
            }
        }


        fun getQuotesFromPreference(pref: SharedPreferences): MutableList<Quote> {
            val idxSet = pref.getStringSet("indices", HashSet<String>())
            val quotes = mutableListOf<Quote>()
            if (idxSet != null) {
                for (idx in idxSet) {
                    if (pref.contains("$idx.text")) {
                        val quoteText = pref.getString("$idx.text", "")!!
                        val quoteFrom = pref.getString("$idx.from", "")!!
                        if (quoteText.isNotBlank()) quotes.add(0,
                            Quote(
                                idx.toInt(),
                                quoteText,
                                quoteFrom
                            )
                        )
                    }
                }
            }
            return quotes
        }

        fun getQuoteFromPreference(pref: SharedPreferences, i: Int): Quote? {
            val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
            if (idxSet.contains("$i")) {
                val quoteText = pref.getString("$i.text", "")!!
                val quoteFrom = pref.getString("$i.from", "")!!
                if (quoteText.isNotBlank()) return Quote(i, quoteText, quoteFrom)
            }
            return null
        }


        fun removeQuoteFromPreference(pref: SharedPreferences, idx: Int) {
            val editor = pref.edit()
            val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
            editor.remove("$idx.text")
            editor.remove("$idx.from")
            idxSet.remove("$idx")
            editor.putStringSet("indices", idxSet)
            editor.apply()
        }
    }
}