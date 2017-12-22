package com.github.karthyks.socialgameworks.framework

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity<in V : BaseView, T : IBasePresenter<V>>
    : AppCompatActivity(), BaseView {

    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun showDialog() {

    }

    override fun showError(message: String) {

    }

    override fun showMessage(message: String) {

    }

    override fun getContext(): Context = this

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}