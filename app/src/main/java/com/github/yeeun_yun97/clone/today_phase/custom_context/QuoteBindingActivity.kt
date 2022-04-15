package com.github.yeeun_yun97.clone.today_phase.custom_context

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class QuoteBindingActivity<T : ViewBinding> : AppCompatActivity() {
    protected val binding: T by lazy { DataBindingUtil.setContentView(this, getLayoutId()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingVariables()
    }

    /**
     * onCreate에서 딱 한번 Binding variable 설정하는 메소드.
     */
    protected abstract fun setBindingVariables()

    /**
     * 액티비티 자신의 레이아웃 id를 반환한다.
     */
    protected abstract fun getLayoutId(): Int


}