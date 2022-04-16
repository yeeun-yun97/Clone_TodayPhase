package com.github.yeeun_yun97.clone.today_phase.ui.activity.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class QuoteDataBindingActivity<T : ViewDataBinding> : AppCompatActivity(){
    protected val binding: T by lazy { DataBindingUtil.setContentView(this, getLayoutId()) }

    abstract fun getLayoutId(): Int

}