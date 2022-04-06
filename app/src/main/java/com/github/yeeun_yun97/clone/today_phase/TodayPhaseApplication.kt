package com.github.yeeun_yun97.clone.today_phase

import android.app.Application
import android.content.Context

class TodayPhaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Quote에 pref 값을 넣어준다.
        //아무래도 Activity에 넣는 것보다 Application에서 넣는 것이 더 나을 것 같았다!
        Quote.pref = getSharedPreferences("quotes", Context.MODE_PRIVATE)
    }
}