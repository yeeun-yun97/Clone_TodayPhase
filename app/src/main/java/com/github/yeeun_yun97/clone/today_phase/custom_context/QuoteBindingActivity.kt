package com.github.yeeun_yun97.clone.today_phase.custom_context

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class QuoteBindingActivity<Binding : ViewDataBinding> : AppCompatActivity() {
    protected val binding: Binding by lazy { DataBindingUtil.setContentView(this, getLayoutId()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingVariables()
    }

    /**
     * onCreate에서 한번만 설정하면 되는 Binding variable들을 여기서 설정한다.
     */
    protected abstract fun setBindingVariables()

    /**
     * 액티비티 자신의 레이아웃 id를 반환한다.
     */
    protected abstract fun getLayoutId(): Int


}