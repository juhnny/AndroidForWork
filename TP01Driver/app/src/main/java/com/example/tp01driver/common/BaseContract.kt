package com.example.tp01driver.common

interface BaseContract {
    interface View {
        fun startIndicator()
        fun stopIndicator()

    }

    interface Presenter{
        fun onAttached()
        fun onDetached()
    }
}