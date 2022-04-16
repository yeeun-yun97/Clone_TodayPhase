package com.github.yeeun_yun97.clone.today_phase.application

import android.app.Application
import android.content.Context
import com.github.yeeun_yun97.clone.today_phase.data.model.Quote

class TodayPhaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Quote에 pref 값을 넣어준다.
        //아무래도 Activity에서 넣는 것보다 Application에서 넣는 것이 더 나을 것 같았다!
        Quote.pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)

        //Quote에 딱 한번 초기값을 넣도록 한다.
        Quote.initQuotes()
    }
}