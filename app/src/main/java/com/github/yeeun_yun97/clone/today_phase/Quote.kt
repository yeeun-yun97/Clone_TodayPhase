package com.github.yeeun_yun97.clone.today_phase

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.*
import kotlin.collections.HashSet

data class Quote(var idx: Int, var text: String, var from: String = "") {
    companion object {
        lateinit var pref: SharedPreferences

        fun createQuote(
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

        fun readAllQuotes(): MutableList<Quote> {
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

        fun readQuoteByIndex(index: Int): Quote {
            val quoteText = pref.getString("$index.text", "")!!
            val quoteFrom = pref.getString("$index.from", "")!!
            return Quote(index, quoteText, quoteFrom)
        }


        fun readRandomQuote(): Quote {
            val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
            if (idxSet.size > 0) {
                return readQuoteByIndex(idxSet.random().toInt())
            } else {
                return Quote(-1, "아직 명언 데이터가 없습니다.", "")
            }
        }

        fun updateQuote(
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

        fun deleteQuoteByIndex(idx: Int) {
            val editor = pref.edit()
            editor.remove("$idx.text")
            editor.remove("$idx.from")
            val idxSet = HashSet<String>(pref.getStringSet("indices", HashSet<String>()))
            idxSet.remove("$idx")
            editor.putStringSet("indices", idxSet)
            editor.apply()
        }

        //설치 후 최초에 딱 한번 초기값 넣기
        fun initQuotes(){
            if (!pref.getBoolean("init", false)) {
                Quote.createQuote(
                    "창조적인 삶을 살려면 내가 틀릴지도 모른다는 공포를 버려야 한다."
                )
                Quote.createQuote(
                    "성공한 사람이 되려고 노력하기보다 가치있는 사람이 되려고 노력하라.",
                    "알버트 아인슈타인"
                )
                Quote.createQuote(
                    "괴로운 시련처럼 보이는 것이 뜻밖의 좋은 일일 때가 많다.",
                    "오스카 와일드"
                )
                Quote.createQuote(
                    "추구할 수 있는 용기가 있다면 우리의 모든 꿈은 이뤄질 수 있다.",
                    "월트 디즈니"
                )
                Quote.createQuote(
                    "실패에서부터 성공을 만들어 내라. 좌절과 실패는 성공으로 가는 가장 확실한 디딤돌이다.",
                    "데일 카네기"
                )
                val editor = pref.edit()
                editor.putBoolean("init", true)
                editor.apply()
            }
        }
    }
}
