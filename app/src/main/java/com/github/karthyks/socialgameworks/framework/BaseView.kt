package com.github.karthyks.socialgameworks.framework

import android.content.Context

/**
 * Created by karthy07 on 12/1/2017.
 */
interface BaseView {
    fun getContext() : Context

    fun showDialog()

    fun showMessage(message: String)

    fun showError(message: String)
}