package com.example.tp01driver.common

import android.util.Log

open class BasePresenter() : BaseContract.Presenter {
    override fun onAttached() {
        Log.e("BasePresenter onAttached()", "")
        TODO("Not yet implemented")
    }

    override fun onDetached() {
        Log.e("BasePresenter onDetached()", "")
        TODO("Not yet implemented")
    }
}